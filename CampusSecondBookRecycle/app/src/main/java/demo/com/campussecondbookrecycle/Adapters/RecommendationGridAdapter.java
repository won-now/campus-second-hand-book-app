package demo.com.campussecondbookrecycle.Adapters;

import android.content.Context;
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

public class RecommendationGridAdapter extends RecyclerView.Adapter<RecommendationGridAdapter.RecommendationViewHolder> {

    private Context mContext;
    private List<BookBrief> books;
    private OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mItemClickListener = onItemClickListener;
    }

    public RecommendationGridAdapter(Context mContext, List<BookBrief> books) {
        this.mContext = mContext;
        this.books = books;
    }

    @NonNull
    @Override
    public RecommendationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_grid_book,parent,false);
        RecommendationViewHolder viewHolder = new RecommendationViewHolder(viewItem);
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
    public void onBindViewHolder(@NonNull RecommendationViewHolder holder, int position) {
        BookBrief bookBrief = books.get(position);
        Glide.with(mContext).load(bookBrief.getMainImage()).into(holder.mIvBookCover);
        holder.mTvBookName.setText(bookBrief.getName());
    }

    @Override
    public int getItemCount() {
        return books == null ? 0 : books.size();
    }

    class RecommendationViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvBookCover;
        TextView mTvBookName;

        public RecommendationViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvBookCover = itemView.findViewById(R.id.iv_book);
            mTvBookName = itemView.findViewById(R.id.tv_name);
        }
    }

    public static interface OnItemClickListener{
        void onItemClick(View view);
        void onItemLongClick(View view);
    }
}
