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

import Models.BookModel;
import demo.com.campussecondbookrecycle.R;

public class BookListAdapter  extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {

    private Context mContext;
    private List<BookModel> books;

    public BookListAdapter(Context mContext, List<BookModel> books) {
        this.mContext = mContext;
        this.books = books;
    }

    @NonNull
    @Override
    public BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_list_books,parent,false);
        return new BookListViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position) {
        BookModel bookModel = books.get(position);

        Glide.with(mContext).load(bookModel.getImgURL()).into(holder.mIvBook);
        holder.mTvAuthor.setText(bookModel.getAuthor());
        holder.mTvName.setText(bookModel.getName());
        holder.mTvPrice.setText("￥" + bookModel.getPrice());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookListViewHolder extends RecyclerView.ViewHolder{
        TextView mTvName, mTvAuthor, mTvPrice;
        ImageView mIvBook;

        public BookListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvAuthor = itemView.findViewById(R.id.tv_author);
            mTvPrice = itemView.findViewById(R.id.tv_price);
            mIvBook = itemView.findViewById(R.id.iv_book);
        }
    }
}
