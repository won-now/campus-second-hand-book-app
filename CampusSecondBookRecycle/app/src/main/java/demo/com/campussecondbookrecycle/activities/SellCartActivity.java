package demo.com.campussecondbookrecycle.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import demo.com.campussecondbookrecycle.Adapters.BuyCartListAdapter;
import demo.com.campussecondbookrecycle.Adapters.SellCartListAdapter;
import demo.com.campussecondbookrecycle.Models.BuyCartBook;
import demo.com.campussecondbookrecycle.Models.BuyCartBookVo;
import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Models.OrderVo;
import demo.com.campussecondbookrecycle.Models.SellCartBookVo;
import demo.com.campussecondbookrecycle.Models.SellCartVo;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.BuycartService;
import demo.com.campussecondbookrecycle.service.OrderService;
import demo.com.campussecondbookrecycle.service.SellcartService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellCartActivity extends AppCompatActivity {

    private RecyclerView mRvCart;
    //    private CheckBox mCkSelectAll;
//    private TextView mTvSelectAll;
    private ImageView mIvBuy,mIvAdd;
    private EditText mEtISBN;
    private SellcartService mSellcartService;
    private OrderService mOrderService;
    private SellCartListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_cart);

        mRvCart = findViewById(R.id.rv_sell_cart);
        mIvBuy = findViewById(R.id.iv_buy);
        mIvAdd = findViewById(R.id.iv_add);
        mEtISBN = findViewById(R.id.et_isbn);

        mAdapter = new SellCartListAdapter(SellCartActivity.this);
        mRvCart.setLayoutManager(new LinearLayoutManager(SellCartActivity.this));
        mRvCart.setAdapter(mAdapter);

        refreshCart();
    }

    public void toBuy(View view){
        Intent intent = new Intent(SellCartActivity.this,CartActivity.class);
        startActivity(intent);
    }

    public void add(View view){
        String isbn = "";
        if (mEtISBN.getText() != null){
            isbn = mEtISBN.getText().toString();
        }
        mSellcartService = RetrofitHelper.getRetrofit(SellCartActivity.this).create(SellcartService.class);
        Call<HttpResult<SellCartVo>> call = mSellcartService.add(isbn);
        call.enqueue(new Callback<HttpResult<SellCartVo>>() {
            @Override
            public void onResponse(Call<HttpResult<SellCartVo>> call, Response<HttpResult<SellCartVo>> response) {
                Log.d("add","res");
                HttpResult<SellCartVo> httpResult = response.body();
                if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    refreshCart();
                }
            }

            @Override
            public void onFailure(Call<HttpResult<SellCartVo>> call, Throwable t) {
                Log.d("add","fail");
            }
        });
    }

    public void createOrder(View view){
        mOrderService = RetrofitHelper.getRetrofit(SellCartActivity.this).create(OrderService.class);
        Call<HttpResult<List<OrderVo>>> call = mOrderService.create(32,Const.TRADE_SELL);
        call.enqueue(new Callback<HttpResult<List<OrderVo>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<OrderVo>>> call, Response<HttpResult<List<OrderVo>>> response) {
                HttpResult httpResult = response.body();
                if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    Intent intent = new Intent(SellCartActivity.this,MainActivity.class);
                    startActivity(intent);
                }else if(httpResult.getStatus() == 10){
                    Intent intent = new Intent(SellCartActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(SellCartActivity.this,httpResult.getMsg(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<HttpResult<List<OrderVo>>> call, Throwable t) {

            }
        });

    }

    private void refreshCart(){
        mSellcartService = RetrofitHelper.getRetrofit(SellCartActivity.this).create(SellcartService.class);
        Call<HttpResult<SellCartVo>> call = mSellcartService.getCartBookList();
        call.enqueue(new Callback<HttpResult<SellCartVo>>() {
            @Override
            public void onResponse(Call<HttpResult<SellCartVo>> call, Response<HttpResult<SellCartVo>> response) {
                Log.d("buycart","response");
                HttpResult<SellCartVo> httpResult = response.body();
                if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    List<SellCartBookVo> sellCartBookVoList = httpResult.getData().getSellCartBookVoList();
                    mAdapter.setData(sellCartBookVoList);
                    mAdapter.notifyDataSetChanged();
//                    if(httpResult.getData().isAllChecked()){
//                        mCkSelectAll.setChecked(true);
//                        mTvSelectAll.setText("全不选");
//                    }
                }else if(httpResult.getStatus() == 10){
                    Intent intent = new Intent(SellCartActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<HttpResult<SellCartVo>> call, Throwable t) {
                Log.d("buycart","fail");
            }
        });
    }
}
