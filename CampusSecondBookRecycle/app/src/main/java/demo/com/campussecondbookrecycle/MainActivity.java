package demo.com.campussecondbookrecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.FragmentTransitionSupport;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.com.campussecondbookrecycle.Fragments.HomeFragment;
import demo.com.campussecondbookrecycle.Fragments.OrderFragment;
import demo.com.campussecondbookrecycle.Views.TabView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mVpMain;
    private TabLayout mTlNav;
    private TabView mTabHome, mTabTextbook, mTabOrder, mTabMe;
    private List<String> titles =
            new ArrayList<>(Arrays.asList("小说","互联网","心理学","经济学"));
    private List<TabView> mTabs = new ArrayList<>();
    private HomeFragment mHomeFragment;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mTlNav.setupWithViewPager(mVpMain);
        mVpMain.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                mHomeFragment = HomeFragment.newInstance(position);
                fragments.add(mHomeFragment);
                Log.d("Test",position+"");
                return mHomeFragment;
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

        mTabOrder.setOnClickListener(this);
    }

    private void initView() {
        mVpMain = findViewById(R.id.vp_main);
        mTlNav = findViewById(R.id.tl_home);
        mTabHome = findViewById(R.id.tab_home);
        mTabOrder = findViewById(R.id.tab_order);
        mTabTextbook = findViewById(R.id.tab_textbook);
        mTabMe = findViewById(R.id.tab_me);

        mTabHome.setContent(R.drawable.home, R.drawable.home_selected, "首页");
        mTabOrder.setContent(R.drawable.order, R.drawable.order_selected, "订单");
        mTabTextbook.setContent(R.drawable.textbook, R.drawable.textbook_selected, "教辅");
        mTabMe.setContent(R.drawable.me, R.drawable.me_selected, "我");

        mTabs.add(mTabHome);
        mTabs.add(mTabOrder);
        mTabs.add(mTabTextbook);
        mTabs.add(mTabMe);

        for (TabView tab : mTabs){
            if(tab == mTabHome)
                tab.setSelected(true);
            else
                tab.setSelected(false);
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.tab_order:
                if(mHomeFragment.isAdded()){
                    for(Fragment fragment : fragments){
                        ft.hide(fragment);
                    }
                }
                ft.add(R.id.fl_replaced,new OrderFragment()).commit();
                mTlNav.setVisibility(View.GONE);
                mTabOrder.setSelected(true);
                mTabHome.setSelected(false);
        }
    }
}
