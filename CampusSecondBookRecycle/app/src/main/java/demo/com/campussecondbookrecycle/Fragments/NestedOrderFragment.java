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

import java.util.List;

import Models.OrderListModel;
import demo.com.campussecondbookrecycle.Adapters.OrderListAdapter;
import Models.OrderModel;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.Utils.IOStreamHandler;

public class NestedOrderFragment extends Fragment {
    private static final String KEY_TRADE = "KEY_TRADE";
    public static final int TYPE_BUY = 0;
    public static final int TYPE_SOLD = 1;
    private int mType = -1;

    private RecyclerView mRvOrderList;
    private OrderListModel mOrderListModel;

    public static NestedOrderFragment newInstance(int type){
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TRADE, type);
        NestedOrderFragment nestedOrderFragment = new NestedOrderFragment();
        nestedOrderFragment.setArguments(bundle);
        return nestedOrderFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String jsonOrders = IOStreamHandler.JsontoString(getActivity(),"OrderData.json");
        mOrderListModel = JSON.parseObject(jsonOrders, OrderListModel.class);
        Log.d("BOOK",mOrderListModel.toString());
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
        mRvOrderList.setAdapter(new OrderListAdapter(getActivity(),mOrderListModel.getOrderList()));
        mRvOrderList.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        Drawable dividerDrawable = ContextCompat.getDrawable(getActivity(),R.drawable.item_divider);
        divider.setDrawable(dividerDrawable);
        mRvOrderList.addItemDecoration(divider);
    }
}
