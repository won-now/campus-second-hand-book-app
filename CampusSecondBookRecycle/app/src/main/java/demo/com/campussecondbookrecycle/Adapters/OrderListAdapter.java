package demo.com.campussecondbookrecycle.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Models.OrderModel;
import demo.com.campussecondbookrecycle.R;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder> {
    private Context mContext;
    private List<OrderModel> mLists;

    public OrderListAdapter(Context mContext, List<OrderModel> mLists) {
        this.mContext = mContext;
        this.mLists = mLists;
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_order,parent,false);
        return new OrderListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListViewHolder holder, int position) {
        OrderModel order = mLists.get(position);
        holder.mTvOrderId.setText(order.getOrderID());
        holder.mTvOrderDate.setText(order.getData());
        holder.mTvOrderNumberOfBook.setText(order.getBookNum() + "");
        holder.mRvOrderBookList.setAdapter(new NestedHorizontalOrderedBooksListAdapter(mContext,order.getBookList()));
        holder.mRvOrderBookList.setLayoutManager(new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    class OrderListViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvOrderId, mTvOrderNumberOfBook, mTvOrderDate;
        //订单中嵌套的书籍横向滚动列表
        private RecyclerView mRvOrderBookList;

        public OrderListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvOrderId = itemView.findViewById(R.id.tv_order_id);
            mTvOrderNumberOfBook = itemView.findViewById(R.id.tv_order_numberOfbook);
            mTvOrderDate = itemView.findViewById(R.id.tv_order_date);
            mRvOrderBookList = itemView.findViewById(R.id.rv_orderOfBookList);
        }
    }
}
