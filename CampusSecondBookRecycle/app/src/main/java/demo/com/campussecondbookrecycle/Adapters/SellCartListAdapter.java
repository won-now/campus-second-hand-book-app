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

import demo.com.campussecondbookrecycle.Models.BuyCartBook;
import demo.com.campussecondbookrecycle.Models.BuyCartBookVo;
import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Models.SellCartBookVo;
import demo.com.campussecondbookrecycle.Models.SellCartVo;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.activities.LoginActivity;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.BuycartService;
import demo.com.campussecondbookrecycle.service.SellcartService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellCartListAdapter extends RecyclerView.Adapter<SellCartListAdapter.BuyCartViewHolder> {

    private Context mContext;
    private List<SellCartBookVo> sellCartBookVoList;

    public SellCartListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<SellCartBookVo> data){
        sellCartBookVoList = data;
    }

    @NonNull
    @Override
    public BuyCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_list_sell_cart,parent,false);
        BuyCartViewHolder viewHolder = new BuyCartViewHolder(viewItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BuyCartViewHolder holder, int position) {
        SellCartBookVo sellCartBookVo = sellCartBookVoList.get(position);
        Glide.with(mContext).load(sellCartBookVo.getBookMainImage()).into(holder.ivCover);
        holder.tvName.setText(sellCartBookVo.getBookName());
        holder.tvPrice.setText("￥" + sellCartBookVo.getValuation());
        int code = sellCartBookVo.getBookChecked();
        holder.ckSelect.setChecked(code == 1 ? Const.CartCheck.CHECKED.isChecked() : Const.CartCheck.UNCHECKED.isChecked());
        holder.ckSelect.setOnClickListener(new View.OnClickListener() {
            SellcartService sellcartService = RetrofitHelper.getRetrofit(mContext).create(SellcartService.class);
            @Override
            public void onClick(View v) {
                if(holder.ckSelect.isChecked()){
                    Call<HttpResult<SellCartVo>> call= sellcartService.unSelect(sellCartBookVo.getBookISBN());
                    call.enqueue(new Callback<HttpResult<SellCartVo>>() {
                        @Override
                        public void onResponse(Call<HttpResult<SellCartVo>> call, Response<HttpResult<SellCartVo>> response) {
                            Log.d("unselect","res");
                            if (response.body() != null && response.body().getStatus() != Const.SUCCESS_STATUS){
                                Toast.makeText(mContext,"已取消",Toast.LENGTH_SHORT).show();
                                holder.ckSelect.setChecked(false);
                            }
                        }

                        @Override
                        public void onFailure(Call<HttpResult<SellCartVo>> call, Throwable t) {
                            Log.d("unselect","fail");
                    }
                    });
                }else {
                    Call<HttpResult<SellCartVo>> call= sellcartService.select(sellCartBookVo.getBookISBN());
                    call.enqueue(new Callback<HttpResult<SellCartVo>>() {
                        @Override
                        public void onResponse(Call<HttpResult<SellCartVo>> call, Response<HttpResult<SellCartVo>> response) {
                            Log.d("select","res");
                            if (response.body() != null && response.body().getStatus() != Const.SUCCESS_STATUS){
                                holder.ckSelect.setChecked(true);
                                Toast.makeText(mContext,"已选中",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<HttpResult<SellCartVo>> call, Throwable t) {
                            Log.d("selete","fail");
                        }
                    });
                }
            }
        });
        holder.ivCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellcartService sellcartService = RetrofitHelper.getRetrofit(mContext).create(SellcartService.class);
                Call<HttpResult<SellCartVo>> call= sellcartService.deleteBook(sellCartBookVo.getBookISBN());
                call.enqueue(new Callback<HttpResult<SellCartVo>>() {
                    @Override
                    public void onResponse(Call<HttpResult<SellCartVo>> call, Response<HttpResult<SellCartVo>> response) {
                        HttpResult<SellCartVo> httpResult = response.body();
                        if (httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                            sellCartBookVoList.remove(position);
                            notifyItemRemoved(position);
                            notifyDataSetChanged();
                        }else if(httpResult.getStatus() == 10){
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            mContext.startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<HttpResult<SellCartVo>> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return sellCartBookVoList == null ? 0 : sellCartBookVoList.size();
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


