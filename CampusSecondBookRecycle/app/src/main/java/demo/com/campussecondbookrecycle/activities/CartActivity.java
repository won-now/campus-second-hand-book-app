package demo.com.campussecondbookrecycle.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import demo.com.campussecondbookrecycle.Adapters.BuyCartListAdapter;
import demo.com.campussecondbookrecycle.Fragments.OrderFragment;
import demo.com.campussecondbookrecycle.Models.BuyCartBook;
import demo.com.campussecondbookrecycle.Models.BuyCartBookVo;
import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Models.OrderVo;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.BuycartService;
import demo.com.campussecondbookrecycle.service.OrderService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private RecyclerView mRvCart;
//    private CheckBox mCkSelectAll;
//    private TextView mTvSelectAll;
    private ImageView mIvSell;
    private BuycartService mBuycartService;
    private OrderService mOrderService;
    private BuyCartListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mRvCart = findViewById(R.id.rv_buyCart);
        mIvSell = findViewById(R.id.iv_sell);
//        mCkSelectAll = findViewById(R.id.ck_select_all);
//        mTvSelectAll = findViewById(R.id.tv_select_all);
        mAdapter = new BuyCartListAdapter(CartActivity.this);
        mRvCart.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        mRvCart.setAdapter(mAdapter);

        mBuycartService = RetrofitHelper.getRetrofit(CartActivity.this).create(BuycartService.class);
        Call<HttpResult<BuyCartBook>> call = mBuycartService.getCartBookList();
        call.enqueue(new Callback<HttpResult<BuyCartBook>>() {
            @Override
            public void onResponse(Call<HttpResult<BuyCartBook>> call, Response<HttpResult<BuyCartBook>> response) {
                Log.d("buycart","response");
                HttpResult<BuyCartBook> httpResult = response.body();
                if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    List<BuyCartBookVo> buyCartBookVoList = httpResult.getData().getBuyCartBookVoList();
                    mAdapter.setData(buyCartBookVoList);
                    mAdapter.notifyDataSetChanged();
//                    if(httpResult.getData().isAllChecked()){
//                        mCkSelectAll.setChecked(true);
//                        mTvSelectAll.setText("全不选");
//                    }
                }else if(httpResult.getStatus() == 10){
                    Intent intent = new Intent(CartActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<HttpResult<BuyCartBook>> call, Throwable t) {
                Log.d("buycart","fail");
            }
        });

//        mCkSelectAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mCkSelectAll.isChecked()){
//                    mBuycartService = RetrofitHelper.getRetrofit(CartActivity.this).create(BuycartService.class);
//                    Call<HttpResult<BuyCartBook>> call2 = mBuycartService.unSelectAll();
//                    call2.enqueue(new Callback<HttpResult<BuyCartBook>>() {
//                        @Override
//                        public void onResponse(Call<HttpResult<BuyCartBook>> call, Response<HttpResult<BuyCartBook>> response) {
//                            HttpResult httpResult = response.body();
//                            if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
//
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<HttpResult<BuyCartBook>> call, Throwable t) {
//
//                        }
//                    });
//                }
//            }
//        });
    }

    public void createOrder(View view){
        mOrderService = RetrofitHelper.getRetrofit(CartActivity.this).create(OrderService.class);
        Call<HttpResult<List<OrderVo>>> call = mOrderService.create(32,Const.TRADE_BUY);
        call.enqueue(new Callback<HttpResult<List<OrderVo>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<OrderVo>>> call, Response<HttpResult<List<OrderVo>>> response) {
                HttpResult httpResult = response.body();
                if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    Intent intent = new Intent(CartActivity.this, MainActivity.class);
                    intent.putExtra("order",0x123);
                    startActivity(intent);
                }else {
                    Toast.makeText(CartActivity.this,httpResult.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HttpResult<List<OrderVo>>> call, Throwable t) {
                Log.d("createOrder","fail");
            }
        });
    }

    public void toSell(View view){
        Intent intent = new Intent(CartActivity.this,SellCartActivity.class);
        startActivity(intent);
    }
}
