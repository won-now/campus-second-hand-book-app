package demo.com.campussecondbookrecycle.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import Models.BookModel;
import demo.com.campussecondbookrecycle.R;

public class NestedHorizontalOrderedBooksListAdapter extends RecyclerView.Adapter<NestedHorizontalOrderedBooksListAdapter.OrderBookViewHolder> {
    private Context mContext;
    private List<BookModel> mBooks;

    public NestedHorizontalOrderedBooksListAdapter(Context mContext, List<BookModel> mBooks) {
        this.mContext = mContext;
        this.mBooks = mBooks;
    }

    @NonNull
    @Override
    public OrderBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_orderbooks,parent,false);
        return new OrderBookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderBookViewHolder holder, int position) {
        Glide.with(mContext).load(mBooks.get(position).getImgURL()).into(holder.ivBook);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    class OrderBookViewHolder extends RecyclerView.ViewHolder{
        ImageView ivBook;

        public OrderBookViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBook = itemView.findViewById(R.id.iv_book);
        }
    }
}
