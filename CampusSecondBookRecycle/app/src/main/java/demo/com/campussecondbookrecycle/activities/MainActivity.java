package demo.com.campussecondbookrecycle.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.com.campussecondbookrecycle.Fragments.HomeFragment;
import demo.com.campussecondbookrecycle.Fragments.MeFragment;
import demo.com.campussecondbookrecycle.Fragments.OrderFragment;
import demo.com.campussecondbookrecycle.Fragments.PagerFragment;

import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.Views.SearchView;
import demo.com.campussecondbookrecycle.Views.TabView;
import demo.com.campussecondbookrecycle.common.Const;


public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    private TabView mTabHome, mTabCart, mTabOrder, mTabMe;
    private SearchView mSearch;
    private List<TabView> mTabs = new ArrayList<>();
    private PagerFragment mPagerFragment;
    private TabView currentTab;
    private HomeFragment mHomeFragment;
    private OrderFragment mOrderFragment;
    private MeFragment mMeFragment;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

//        mTabOrder.setOnClickListener(this);
    }

    private void initView() {
//        mVpMain = findViewById(R.id.vp_main);
//        mTlNav = findViewById(R.id.tl_home);
        mSearch = findViewById(R.id.search_book);

        mTabHome = findViewById(R.id.tab_home);
        mTabOrder = findViewById(R.id.tab_order);
        mTabCart = findViewById(R.id.tab_cart);
        mTabMe = findViewById(R.id.tab_me);

        mTabHome.setContent(R.drawable.home, R.drawable.home_selected, "首页");
        mTabOrder.setContent(R.drawable.order, R.drawable.order_selected, "订单");
        mTabCart.setContent(R.drawable.cart, R.drawable.cart_selected, "买书卖书");
        mTabMe.setContent(R.drawable.me, R.drawable.me_selected, "我");

        mTabs.add(mTabHome);
        mTabs.add(mTabOrder);
        mTabs.add(mTabCart);
        mTabs.add(mTabMe);

        currentTab = mTabHome;

        mHomeFragment = new HomeFragment();
        mOrderFragment = new OrderFragment();
        mMeFragment = new MeFragment();

        for (TabView tab : mTabs){
            if(tab == mTabHome)
                tab.setSelected(true);
            else
                tab.setSelected(false);
        }

        if(mPagerFragment == null){
            mPagerFragment = new PagerFragment();
        }

        int id = getIntent().getIntExtra("order",0);
        if(id == 0x123){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_replaced,mOrderFragment)
                    .addToBackStack(null)
                    .commit();
        }else {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_replaced,mPagerFragment).commit();
        }
//        mCurrentFragment = mPagerFragment;
    }

    public void selectHome(View view){
        ft =getSupportFragmentManager().beginTransaction();
        Log.d("click","home:我被点了");
        ft.replace(R.id.fl_replaced,mPagerFragment).commit();
//        ft.hide(mCurrentFragment).show(mHomeFragment).commit();
        currentTab.setSelected(false);
        mTabHome.setSelected(true);
        currentTab = mTabHome;
//        mCurrentFragment = mHomeFragment;
    }

    public void selectCart(View view){
        Intent intent = new Intent(MainActivity.this,CartActivity.class);
        startActivity(intent);
    }

    public void selectOrder(View view){
        ft =getSupportFragmentManager().beginTransaction();
        Log.d("click","order:我被点了");
        ft.replace(R.id.fl_replaced,mOrderFragment).commit();
//        ft.hide(mCurrentFragment).show(mOrderFragment).commit();
        currentTab.setSelected(false);
        mTabOrder.setSelected(true);
        currentTab = mTabOrder;
//        mCurrentFragment = mOrderFragment;
    }

    public void selectMe(View view){
        ft =getSupportFragmentManager().beginTransaction();
        Log.d("click","order:我被点了");
        ft.replace(R.id.fl_replaced,mMeFragment).commit();
        currentTab.setSelected(false);
        mTabMe.setSelected(true);
        currentTab = mTabMe;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            String keyword = mSearch.getInputSrc();
            Intent intent = new Intent(this, SearchBookListActivity.class);
            intent.putExtra(Const.SEARCH_KEYWORD,keyword);
            startActivity(intent);
        }
        return false;
    }
}
