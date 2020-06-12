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

import demo.com.campussecondbookrecycle.Models.UserBook;
import demo.com.campussecondbookrecycle.R;

public class BookrackAdapter extends RecyclerView.Adapter<BookrackAdapter.BookrackViewHolder> {

    private Context mContext;
    private List<UserBook> userBookList;

    public BookrackAdapter(Context context, List<UserBook> userBookList){
        this.mContext = context;
        this.userBookList = userBookList;
    }


    @NonNull
    @Override
    public BookrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(mContext).inflate(R.layout.item_grid_bookrack,parent,false);
        return new BookrackViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull BookrackViewHolder holder, int position) {
        UserBook userBook = userBookList.get(position);
        Glide.with(mContext).load(userBook.getMainImage()).into(holder.ivCover);
        holder.tvName.setText(userBook.getBookName());
    }

    @Override
    public int getItemCount() {
        return userBookList == null ? 0 : userBookList.size();
    }

    class BookrackViewHolder extends RecyclerView.ViewHolder{

        ImageView ivCover;
        TextView tvName;

        public BookrackViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
