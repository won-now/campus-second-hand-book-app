package demo.com.campussecondbookrecycle.Fragments;

import android.content.Intent;
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

import java.util.List;

import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Adapters.OrderListAdapter;
import demo.com.campussecondbookrecycle.Models.OrderVo;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.activities.LoginActivity;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.OrderService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NestedOrderFragment extends Fragment {
    private static final String KEY_TRADE = "KEY_TRADE";
    public static final int TYPE_BUY = 0;
    public static final int TYPE_SOLD = 1;
    private int mType = -1;

    private RecyclerView mRvOrderList;
    private OrderListAdapter mOrderListAdapter;

    public static NestedOrderFragment newInstance(int type){
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TRADE, type);
        Log.d("position",type+"");
        NestedOrderFragment nestedOrderFragment = new NestedOrderFragment();
        nestedOrderFragment.setArguments(bundle);
        return nestedOrderFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOrderListAdapter = new OrderListAdapter(getActivity());
        OrderService orderService = RetrofitHelper.getRetrofit(getActivity()).create(OrderService.class);
        Log.d(KEY_TRADE,getArguments().getInt(KEY_TRADE)+"");
        Call<HttpResult<List<OrderVo>>> call = orderService.list(getArguments().getInt(KEY_TRADE,0));
        call.enqueue(new Callback<HttpResult<List<OrderVo>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<OrderVo>>> call, Response<HttpResult<List<OrderVo>>> response) {
                Log.d("order","response");
                HttpResult<List<OrderVo>> httpResult = response.body();
                if(httpResult != null) {
                    Log.d("order",httpResult.getStatus()+"");
//                    Log.d("order",httpResult.getMsg());
                }
                if (httpResult != null && httpResult.getStatus() == 0){
                    List<OrderVo> orderVoList = httpResult.getData();
                    mOrderListAdapter.setData(orderVoList);
//                    Log.d("order",orderVoList.get(0).getOrderNo()+"");
                    mOrderListAdapter.notifyDataSetChanged();
                }else if (httpResult.getStatus() == 10){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<HttpResult<List<OrderVo>>> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nested_order,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRvOrderList = view.findViewById(R.id.rv_order_list);
        mRvOrderList.setAdapter(mOrderListAdapter);
        mRvOrderList.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        Drawable dividerDrawable = ContextCompat.getDrawable(getActivity(),R.drawable.item_divider);
        divider.setDrawable(dividerDrawable);
        mRvOrderList.addItemDecoration(divider);
    }
}
