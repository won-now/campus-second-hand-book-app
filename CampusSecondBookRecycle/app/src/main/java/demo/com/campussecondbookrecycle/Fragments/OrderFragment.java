package demo.com.campussecondbookrecycle.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.com.campussecondbookrecycle.R;

public class OrderFragment extends Fragment {

    private TabLayout mTlOrder;
    private ViewPager mVpOrder;
    private List<String> titles = new ArrayList<>(Arrays.asList("购书订单","售书订单"));


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
        mTlOrder = view.findViewById(R.id.tl_order);
        mTlOrder.setupWithViewPager(mVpOrder);
        mVpOrder.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return NestedOrderFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return titles.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
    }
}
