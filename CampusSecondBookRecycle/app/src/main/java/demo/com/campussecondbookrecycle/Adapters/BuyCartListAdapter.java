package demo.com.campussecondbookrecycle.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import demo.com.campussecondbookrecycle.Models.BookBrief;
import demo.com.campussecondbookrecycle.Models.BuyCartBook;
import demo.com.campussecondbookrecycle.Models.BuyCartBookVo;
import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.activities.LoginActivity;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.BuycartService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyCartListAdapter  extends RecyclerView.Adapter<BuyCartListAdapter.BuyCartViewHolder> {

    private Context mContext;
    private List<BuyCartBookVo> buyCartBookVoList;

    public BuyCartListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<BuyCartBookVo> data){
        buyCartBookVoList = data;
    }

    @NonNull
    @Override
    public BuyCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_list_buy_cart,parent,false);
        BuyCartViewHolder viewHolder = new BuyCartViewHolder(viewItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BuyCartViewHolder holder, int position) {
        BuyCartBookVo buyCartBookVo = buyCartBookVoList.get(position);
        Glide.with(mContext).load(buyCartBookVo.getBookMainImage()).into(holder.ivCover);
        holder.tvName.setText(buyCartBookVo.getBookName());
        holder.tvPrice.setText("￥" + buyCartBookVo.getBookPrice());
        int code = buyCartBookVo.getBookChecked();
        holder.ckSelect.setChecked(code == 1 ? Const.CartCheck.CHECKED.isChecked() : Const.CartCheck.UNCHECKED.isChecked());
        holder.ckSelect.setOnClickListener(new View.OnClickListener() {
            BuycartService buycartService = RetrofitHelper.getRetrofit(mContext).create(BuycartService.class);
            @Override
            public void onClick(View v) {
                if(!holder.ckSelect.isChecked()){
                    Call<HttpResult<BuyCartBook>> call= buycartService.unSelect(buyCartBookVo.getBookId());
                    call.enqueue(new Callback<HttpResult<BuyCartBook>>() {
                        @Override
                        public void onResponse(Call<HttpResult<BuyCartBook>> call, Response<HttpResult<BuyCartBook>> response) {
                            Log.d("unselect","res");
                            if (response.body() != null && response.body().getStatus() != Const.SUCCESS_STATUS){
                                Toast.makeText(mContext,"已取消",Toast.LENGTH_SHORT).show();
                                holder.ckSelect.setChecked(false);
                                notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<HttpResult<BuyCartBook>> call, Throwable t) {
                            Log.d("unselect","fail");
                    }
                    });
                }else {
                    Call<HttpResult<BuyCartBook>> call= buycartService.select(buyCartBookVo.getBookId());
                    call.enqueue(new Callback<HttpResult<BuyCartBook>>() {
                        @Override
                        public void onResponse(Call<HttpResult<BuyCartBook>> call, Response<HttpResult<BuyCartBook>> response) {
                            Log.d("select","res");
                            if (response.body() != null && response.body().getStatus() != Const.SUCCESS_STATUS){
                                holder.ckSelect.setChecked(true);
                                Toast.makeText(mContext,"已选中",Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<HttpResult<BuyCartBook>> call, Throwable t) {
                            Log.d("selete","fail");
                        }
                    });
                }
            }
        });
        holder.ivCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuycartService buycartService = RetrofitHelper.getRetrofit(mContext).create(BuycartService.class);
                String bookId = buyCartBookVo.getBookId() + "";
                Call<HttpResult<BuyCartBook>> call= buycartService.deleteBook(bookId);
                call.enqueue(new Callback<HttpResult<BuyCartBook>>() {
                    @Override
                    public void onResponse(Call<HttpResult<BuyCartBook>> call, Response<HttpResult<BuyCartBook>> response) {
                        HttpResult<BuyCartBook> httpResult = response.body();
                        if (httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                            buyCartBookVoList.remove(position);
                            notifyItemRemoved(position);
                            notifyDataSetChanged();
                        }else if(httpResult.getStatus() == 10){
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            mContext.startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<HttpResult<BuyCartBook>> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return buyCartBookVoList == null ? 0 : buyCartBookVoList.size();
    }

    class BuyCartViewHolder extends RecyclerView.ViewHolder{

        CheckBox ckSelect;
        TextView tvName,tvPrice,tvQuality;
        ImageView ivCover,ivCancle;

        public BuyCartViewHolder(@NonNull View itemView) {
            super(itemView);
            ckSelect = itemView.findViewById(R.id.ck_select);
            tvName = itemView.findViewById(R.id.tv_bookName);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvQuality = itemView.findViewById(R.id.tv_quality);
            ivCover = itemView.findViewById(R.id.iv_book_cover);
            ivCancle = itemView.findViewById(R.id.iv_cancle);
        }
    }
}


