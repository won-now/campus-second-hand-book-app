package demo.com.campussecondbookrecycle.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.math.BigDecimal;
import java.util.List;

import demo.com.campussecondbookrecycle.Adapters.RecommendationGridAdapter;
import demo.com.campussecondbookrecycle.Models.BookBrief;
import demo.com.campussecondbookrecycle.Models.BookDetail;
import demo.com.campussecondbookrecycle.Models.BuyCartBook;
import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.Utils.BigDecimalUtil;
import demo.com.campussecondbookrecycle.Views.CollapsibleTextView;
import demo.com.campussecondbookrecycle.Views.GridSpaceItemDecoration;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.BookService;
import demo.com.campussecondbookrecycle.service.BuycartService;
import jp.wasabeef.glide.transformations.BlurTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvBgBook,mIvBook,mIvHome,mIvCart;
    private Button mBtAdd;
    private TextView mTvName,mTvPrice,mTvAuthor,mTvPublisher,mTvOriginPrice,mTvQuality,mTvNew;
    private CollapsibleTextView mCvContent;
    private RecyclerView mRvRecommendation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        initView();
    }

    private void initView() {
        mIvBgBook = findViewById(R.id.iv_book_bg);
        mIvBook = findViewById(R.id.iv_book);
        mIvCart = findViewById(R.id.iv_cart);
        mIvHome = findViewById(R.id.iv_home);
        mBtAdd = findViewById(R.id.bt_add);
        mTvName = findViewById(R.id.tv_name);
        mTvPrice = findViewById(R.id.tv_price);
        mTvOriginPrice = findViewById(R.id.tv_origin_price);
        mTvAuthor = findViewById(R.id.tv_author);
        mTvPublisher = findViewById(R.id.tv_publisher);
        mTvNew = findViewById(R.id.tv_new);
        mTvQuality = findViewById(R.id.tv_quality);
        mCvContent = findViewById(R.id.cv_content);
        mRvRecommendation = findViewById(R.id.rv_recommendation);

        mBtAdd.setOnClickListener(this);
        mIvHome.setOnClickListener(this);
        mIvCart.setOnClickListener(this);

        Intent intent = getIntent();
        int bookId = intent.getIntExtra(Const.BOOKID,-1);
        int categoryId = intent.getIntExtra(Const.CATEGORYID,-1);
        Log.d("bookid",bookId+"");
        if (bookId < 0 || categoryId < 0) return;
        BookService bookService = RetrofitHelper.getRetrofit(BookDetailActivity.this).create(BookService.class);
        Call<HttpResult<BookDetail>> call = bookService.getBookDetail(bookId);
        call.enqueue(new Callback<HttpResult<BookDetail>>() {
            @Override
            public void onResponse(Call<HttpResult<BookDetail>> call, Response<HttpResult<BookDetail>> response) {
                Log.d("response",bookId+"");
                HttpResult<BookDetail> httpResult = response.body();
                if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    BookDetail bookDetail = httpResult.getData();
                    Glide.with(BookDetailActivity.this).load(bookDetail.getMainImage()).apply(RequestOptions.bitmapTransform(
                            new BlurTransformation(25,10))).into(mIvBgBook);
                    Glide.with(BookDetailActivity.this).load(bookDetail.getMainImage()).into(mIvBook);
                    mTvName.setText(bookDetail.getName());
                    mTvPrice.setText(bookDetail.getPrice().toString());
                    int quality = bookDetail.getQuality();
                    BigDecimal originPrice = BigDecimalUtil.mul(bookDetail.getPrice().doubleValue(),quality);
                    mTvOriginPrice.setText("￥"+originPrice);
                    mTvAuthor.setText(bookDetail.getAuthor());
                    mTvPublisher.setText(bookDetail.getPublisher());
                    mCvContent.setText(bookDetail.getDescription());
                    if (quality == 1){
                        mTvNew.setVisibility(View.VISIBLE);
                    }
                    String desc = "";
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
                    mTvQuality.setText(desc);
                }
            }

            @Override
            public void onFailure(Call<HttpResult<BookDetail>> call, Throwable t) {
                Log.d("failure","失败！！！");
            }
        });

        Call<HttpResult<List<BookBrief>>> call2 = bookService.getBookList(null,categoryId);
        call2.enqueue(new Callback<HttpResult<List<BookBrief>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<BookBrief>>> call, Response<HttpResult<List<BookBrief>>> response) {
                HttpResult<List<BookBrief>> httpResult = response.body();
                if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    List<BookBrief> books = httpResult.getData();
                    RecommendationGridAdapter adapter = new RecommendationGridAdapter(BookDetailActivity.this,books);
                    mRvRecommendation.setAdapter(adapter);
                    mRvRecommendation.setLayoutManager(new GridLayoutManager(BookDetailActivity.this,3));
                    mRvRecommendation.addItemDecoration(new GridSpaceItemDecoration(4,mRvRecommendation));
                    adapter.setOnItemClickListener(new RecommendationGridAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view) {
                            int position = mRvRecommendation.getChildAdapterPosition(view);
                            int bookId = books.get(position).getId();
                            int categoryId = books.get(position).getCategoryId();
                            Intent intent = new Intent(BookDetailActivity.this, BookDetailActivity.class);
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
                Log.d("failure","失败！！！");
            }
        });

        BuycartService buycartService = RetrofitHelper.getRetrofit(BookDetailActivity.this).create(BuycartService.class);
        Call<HttpResult<Boolean>> call3 = buycartService.isExist(bookId);
        call3.enqueue(new Callback<HttpResult<Boolean>>() {
            @Override
            public void onResponse(Call<HttpResult<Boolean>> call, Response<HttpResult<Boolean>> response) {
                HttpResult<Boolean> httpResult = response.body();
                if (httpResult.getData()){
                    mBtAdd.setEnabled(false);
                    mBtAdd.setBackgroundResource(R.drawable.bg_add_cart_false);
                }
            }

            @Override
            public void onFailure(Call<HttpResult<Boolean>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.iv_home:
                intent = new Intent(BookDetailActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_cart:
                intent = new Intent(BookDetailActivity.this,CartActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_add:
                BuycartService buycartService = RetrofitHelper.getRetrofit(BookDetailActivity.this).create(BuycartService.class);
                int bookId = getIntent().getIntExtra(Const.BOOKID,-1);
                Call<HttpResult<BuyCartBook>> call = buycartService.add(bookId);
                call.enqueue(new Callback<HttpResult<BuyCartBook>>() {
                    @Override
                    public void onResponse(Call<HttpResult<BuyCartBook>> call, Response<HttpResult<BuyCartBook>> response) {
                        HttpResult httpResult = response.body();
                        if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                            mBtAdd.setEnabled(false);
                            mBtAdd.setBackgroundResource(R.drawable.bg_add_cart_false);
                            Toast.makeText(BookDetailActivity.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<HttpResult<BuyCartBook>> call, Throwable t) {
                        Log.d("addCart","fail");
                    }
                });
        }
    }
}
