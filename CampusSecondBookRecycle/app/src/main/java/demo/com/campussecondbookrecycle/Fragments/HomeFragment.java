package demo.com.campussecondbookrecycle.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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

import com.alibaba.fastjson.JSON;

import demo.com.campussecondbookrecycle.Adapters.BookListAdapter;
import demo.com.campussecondbookrecycle.DailyBooksModel;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.Utils.IOStreamHandler;

public class HomeFragment extends Fragment {
    private RecyclerView mRvBooksList;
    private DailyBooksModel mBookList;

    private static final String KEY_TITLE = "KEY_TITLE";

    public static final int NOVELS = 1;
    public static final int INTERNET = 2;
    public static final int PSYCHOLOGY = 3;
    public static final int ECONOMICS = 4;

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

        Bundle arguments = getArguments();
        int i = 0;
        if(arguments != null){
            i = arguments.getInt(KEY_TITLE);
        }
        String jsonString = null;
        if(getActivity() == null){
            return;
        }
        switch (i){
            case 0:

                return;
            case NOVELS:

                jsonString = IOStreamHandler.JsontoString(getActivity(),"DataSource.json");
                Log.d("TEST",jsonString);
                break;
            case INTERNET:
                jsonString = IOStreamHandler.JsontoString(getActivity(),"DataSource.json");
                Log.d("TEST",jsonString);
                break;
            case PSYCHOLOGY:
                jsonString = IOStreamHandler.JsontoString(getActivity(),"DataSource.json");
                Log.d("TEST",jsonString);
                break;
            case ECONOMICS:
                jsonString = IOStreamHandler.JsontoString(getActivity(),"DataSource.json");
                Log.d("TEST",jsonString);
                break;
        }
        mBookList = JSON.parseObject(jsonString, DailyBooksModel.class);
//        JSON.parseObject()
        Log.d("BOOK",mBookList.toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRvBooksList = view.findViewById(R.id.rv_bookslist);
        mRvBooksList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvBooksList.setAdapter(new BookListAdapter(getActivity(), mBookList.getList()));
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        Drawable dividerDrawable = ContextCompat.getDrawable(getActivity(),R.drawable.item_divider);
        divider.setDrawable(dividerDrawable);
        mRvBooksList.addItemDecoration(divider);
    }
}
