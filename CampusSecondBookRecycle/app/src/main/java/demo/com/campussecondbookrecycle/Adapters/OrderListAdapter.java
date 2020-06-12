package demo.com.campussecondbookrecycle.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Models.OrderItemVo;
import demo.com.campussecondbookrecycle.Models.OrderVo;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.activities.LoginActivity;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.OrderService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder> {
    private Context mContext;
    private List<OrderVo> mLists;
    private OrderService orderService;

    public OrderListAdapter(Context mContext) {
        this.mContext = mContext;
//        this.mLists = mLists;
    }

    public void setData(List<OrderVo> orderVoList){
        this.mLists = orderVoList;
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_order,parent,false);
        return new OrderListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {
        OrderVo orderVo = mLists.get(position);
        holder.mTvOrderId.setText(orderVo.getOrderNo().toString());
        holder.mTvOrderDate.setText(orderVo.getCreateTime());
        List<OrderItemVo> orderItemVoList = orderVo.getOrderItemVoList();
        holder.mTvOrderNumberOfBook.setText(orderItemVoList.size()+"");
        holder.mTvOrderStatus.setText(orderVo.getStatusDesc());
        if (orderVo.getStatus() == 10 && orderVo.getOrderType() == 0){
            holder.mBtPay.setVisibility(View.VISIBLE);
        }
        if (orderVo.getStatus() == 5 && orderVo.getStatus() == 10){
            holder.mBtCancle.setVisibility(View.VISIBLE);
        }
        holder.mRvOrderBookList.setAdapter(new NestedHorizontalOrderedBooksListAdapter(mContext,orderItemVoList));
        holder.mRvOrderBookList.setLayoutManager(new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false));
        holder.mBtPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderService = RetrofitHelper.getRetrofit(mContext).create(OrderService.class);
                Call<HttpResult<String>> call = orderService.pay(orderVo.getOrderNo());
                call.enqueue(new Callback<HttpResult<String>>() {
                    @Override
                    public void onResponse(Call<HttpResult<String>> call, Response<HttpResult<String>> response) {
                        HttpResult httpResult = response.body();
                        if (httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                            Toast.makeText(mContext,httpResult.getMsg(),Toast.LENGTH_SHORT).show();
                            holder.mBtPay.setVisibility(View.GONE);
                        }else if (httpResult.getStatus() == 10){
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            mContext.startActivity(intent);
                        }else {
                            Toast.makeText(mContext,httpResult.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<HttpResult<String>> call, Throwable t) {

                    }
                });
            }
        });
        holder.mBtCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderService = RetrofitHelper.getRetrofit(mContext).create(OrderService.class);
                Call<HttpResult<String>> call = orderService.cancle(orderVo.getOrderNo());
                call.enqueue(new Callback<HttpResult<String>>() {
                    @Override
                    public void onResponse(Call<HttpResult<String>> call, Response<HttpResult<String>> response) {
                        HttpResult httpResult = response.body();
                        if (httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                            Toast.makeText(mContext,httpResult.getMsg(),Toast.LENGTH_SHORT).show();
                            holder.mBtCancle.setVisibility(View.GONE);
                        }else if (httpResult.getStatus() == 10){
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            mContext.startActivity(intent);
                        }else {
                            Toast.makeText(mContext,httpResult.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<HttpResult<String>> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLists == null ? 0 : mLists.size();
    }

    class OrderListViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvOrderId, mTvOrderNumberOfBook, mTvOrderDate,mTvOrderStatus;
        private Button mBtPay,mBtCancle;
        //订单中嵌套的书籍横向滚动列表
        private RecyclerView mRvOrderBookList;

        public OrderListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvOrderId = itemView.findViewById(R.id.tv_order_id);
            mTvOrderNumberOfBook = itemView.findViewById(R.id.tv_order_numberOfbook);
            mTvOrderDate = itemView.findViewById(R.id.tv_order_date);
            mTvOrderStatus = itemView.findViewById(R.id.tv_order_status);
            mBtPay = itemView.findViewById(R.id.bt_pay);
            mBtCancle = itemView.findViewById(R.id.bt_cancle);
            mRvOrderBookList = itemView.findViewById(R.id.rv_orderOfBookList);
        }
    }

}
