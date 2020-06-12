package demo.com.campussecondbookrecycle.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import demo.com.campussecondbookrecycle.Models.BookBrief;
import demo.com.campussecondbookrecycle.R;

public class BookListAdapter  extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {

    private Context mContext;
    private List<BookBrief> books;
    private OnItemClickListener mItemClickListener;

    public BookListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<BookBrief> books){
        this.books = books;
    }
//    public BookListAdapter(Context mContext, List<BookBrief> books) {
//        this.mContext = mContext;
//        this.books = books;
//    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_list_books,parent,false);
        BookListViewHolder viewHolder = new BookListViewHolder(viewItem);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(v);
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mItemClickListener.onItemLongClick(v);
                return true;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position) {

        BookBrief bookBrief = books.get(position);
        Glide.with(mContext).load(bookBrief.getMainImage()).into(holder.mIvBook);
        Log.d("url",bookBrief.getMainImage());
        holder.mTvAuthor.setText(bookBrief.getAuthor());
        holder.mTvName.setText(bookBrief.getName());
        holder.mTvPrice.setText("￥" + bookBrief.getPrice());
        String desc = "";
        int quality = bookBrief.getQuality();
        switch (quality){
            case 1:
                desc = "全新品";
                break;
            case 2:
                desc = "良品";
                break;
            case 3:
                desc = "中品";
                break;
        }
        holder.mTvQuality.setText(desc);
    }

    @Override
    public int getItemCount() {
        return books == null ? 0 : books.size();
    }



    class BookListViewHolder extends RecyclerView.ViewHolder{
        TextView mTvName, mTvAuthor, mTvPrice,mTvQuality;
        ImageView mIvBook;

        public BookListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvAuthor = itemView.findViewById(R.id.tv_author);
            mTvPrice = itemView.findViewById(R.id.tv_price);
            mIvBook = itemView.findViewById(R.id.iv_book);
            mTvQuality = itemView.findViewById(R.id.tv_quality);
        }
    }

    public static interface OnItemClickListener{
        void onItemClick(View view);
        void onItemLongClick(View view);
    }
}

