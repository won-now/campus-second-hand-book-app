package demo.com.campussecondbookrecycle.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import demo.com.campussecondbookrecycle.Adapters.BookListAdapter;
import demo.com.campussecondbookrecycle.Models.BookBrief;
import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.Views.SearchView;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.BookService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBookListActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    private SearchView mSearchView;
    private RecyclerView mResultList;
    private BookListAdapter mBookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        mSearchView = findViewById(R.id.sv_book);
        mResultList = findViewById(R.id.rv_result_list);

        mBookListAdapter = new BookListAdapter(SearchBookListActivity.this);
        Intent intent = getIntent();
        String keyword = intent.getStringExtra(Const.SEARCH_KEYWORD);
        mSearchView.setInput(keyword);

        getBookList(keyword);

        mResultList.setLayoutManager(new LinearLayoutManager(SearchBookListActivity.this));
//        mRvBooksList.setAdapter(new BookListAdapter(getActivity(), bookBriefs));//mBookList.getList()
        //mBookList.getList()
        mResultList.setAdapter(mBookListAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(SearchBookListActivity.this,DividerItemDecoration.VERTICAL);
        Drawable dividerDrawable = ContextCompat.getDrawable(SearchBookListActivity.this,R.drawable.item_divider);
        divider.setDrawable(dividerDrawable);
        mResultList.addItemDecoration(divider);
    }


    public void getBookList(String keyword){
        BookService bookService = RetrofitHelper.getRetrofit(SearchBookListActivity.this).create(BookService.class);
        Call<HttpResult<List<BookBrief>>> call = bookService.getBookList(keyword,null);
        call.enqueue(new Callback<HttpResult<List<BookBrief>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<BookBrief>>> call, Response<HttpResult<List<BookBrief>>> response) {
                HttpResult<List<BookBrief>> httpResult = response.body();
                if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    List<BookBrief> bookBriefs = httpResult.getData();
                    mBookListAdapter.setData(bookBriefs);
                    mBookListAdapter.notifyDataSetChanged();
                    mBookListAdapter.setOnItemClickListener(new BookListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view) {
                            int position = mResultList.getChildAdapterPosition(view);
                            int bookId = bookBriefs.get(position).getId();
                            int categoryId = bookBriefs.get(position).getCategoryId();
                            Intent intent = new Intent(SearchBookListActivity.this, BookDetailActivity.class);
                            intent.putExtra(Const.BOOKID,bookId);
//                Bundle bundle = new Bundle();
//                bundle.putInt();
                            intent.putExtra(Const.CATEGORYID,categoryId);
                            startActivity(intent);
                        }

                        @Override
                        public void onItemLongClick(View view) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<HttpResult<List<BookBrief>>> call, Throwable t) {
                Log.d("fail","获取书籍失败");
                Toast.makeText(SearchBookListActivity.this,"获取书籍失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            String keyword = mSearchView.getInputSrc();
            Intent intent = new Intent(this, BookDetailActivity.class);
            intent.putExtra(Const.SEARCH_KEYWORD,keyword);
            startActivity(intent);
        }
        return false;
    }
}
