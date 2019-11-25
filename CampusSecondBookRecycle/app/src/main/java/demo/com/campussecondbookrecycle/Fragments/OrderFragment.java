package demo.com.campussecondbookrecycle.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import Models.OrderModel;
import demo.com.campussecondbookrecycle.R;

/**
 * 进入页面时，先检查用户是否登录，没有登录则跳转登录界面。
 * 若已经登录则从服务端提交用户名，获取用户订单。
 */
public class OrderFragment extends Fragment {


    private OrderModel mOrders;
    private Button mBtnBuy,mBtnSell;
    private ViewPager mVpOrder;

//    public static OrderFragment newInstance(int type){
//        Bundle bundle = new Bundle();
//        bundle.putInt(KEY_TRADE, type);
//        OrderFragment orderFragment = new OrderFragment();
//        orderFragment.setArguments(bundle);
//        return orderFragment;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mType = getArguments().getInt(KEY_TRADE);

//        if(type == 0){
//            //获取用户购买订单
//        }else if(type == 1){
////获取用户出售订单
//        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVpOrder = view.findViewById(R.id.vp_orders);
        mVpOrder.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return NestedOrderFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}
