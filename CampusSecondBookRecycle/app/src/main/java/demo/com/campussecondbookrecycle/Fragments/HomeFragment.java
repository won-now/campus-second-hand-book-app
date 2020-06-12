package demo.com.campussecondbookrecycle.Fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import demo.com.campussecondbookrecycle.Adapters.BookListAdapter;
import demo.com.campussecondbookrecycle.Models.BookBrief;
import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.activities.BookDetailActivity;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.BookService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView mRvBooksList;
    private BookService bookService;
    private List<BookBrief> bookBriefs;
    private BookListAdapter mBookListAdapter;
    private SparseIntArray categorybooks = new SparseIntArray();

    private static final String KEY_TITLE = "KEY_TITLE";

    public static HomeFragment newInstance(int title){
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TITLE, title+1);
        Log.d("Title","title:"+title);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d("lifecycle","onCreate");
        categorybooks.put(1, Const.ParentCategory.WENXUE.getId());
        categorybooks.put(2, Const.ParentCategory.WENHUA.getId());
        categorybooks.put(3, Const.ParentCategory.JINGGUAN.getId());
        categorybooks.put(4, Const.ParentCategory.KEJI.getId());
//        mHandler = new Handler();
        mBookListAdapter = new BookListAdapter(getActivity());
        bookBriefs = new ArrayList<>();

        bookService = RetrofitHelper.getRetrofit(getActivity()).create(BookService.class);
        Bundle arguments = getArguments();
//        bookController = new BookController(RetrofitHelper.getRetrofit(),getActivity(),mHandler);
        Call<HttpResult<List<BookBrief>>> call = bookService.getBookList(null,categorybooks.get(arguments.getInt(KEY_TITLE)));
        call.enqueue(new Callback<HttpResult<List<BookBrief>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<BookBrief>>> call, Response<HttpResult<List<BookBrief>>> response) {
                HttpResult<List<BookBrief>> httpResult = response.body();
                List<BookBrief> bookBriefs = httpResult.getData();
                mBookListAdapter.setData(bookBriefs);
                mBookListAdapter.notifyDataSetChanged();
                Log.d("author","22222222222222222222");
                mBookListAdapter.setOnItemClickListener(new BookListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view) {
                        int position = mRvBooksList.getChildAdapterPosition(view);
                        int bookId = bookBriefs.get(position).getId();
                        int categoryId = bookBriefs.get(position).getCategoryId();
                        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
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

            @Override
            public void onFailure(Call<HttpResult<List<BookBrief>>> call, Throwable t) {
//                Toast.makeText(getActivity(),"获取分类书籍失败",Toast.LENGTH_LONG).show();
                Log.d("fail","获取分类书籍失败");
            }
        });


        int i = 0;
        if(arguments != null){
            i = arguments.getInt(KEY_TITLE);
        }
//        String jsonString = null;
        if(getActivity() == null){
            return;
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("lifecycle","onViewCreated");
        mRvBooksList = view.findViewById(R.id.rv_bookslist);
        mRvBooksList.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRvBooksList.setAdapter(new BookListAdapter(getActivity(), bookBriefs));//mBookList.getList()
      //mBookList.getList()
        mRvBooksList.setAdapter(mBookListAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        Drawable dividerDrawable = ContextCompat.getDrawable(getActivity(),R.drawable.item_divider);
        divider.setDrawable(dividerDrawable);
        mRvBooksList.addItemDecoration(divider);
    }
}
