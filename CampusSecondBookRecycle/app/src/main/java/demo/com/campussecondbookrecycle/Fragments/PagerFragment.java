package demo.com.campussecondbookrecycle.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.com.campussecondbookrecycle.Models.CategoryBody;
import demo.com.campussecondbookrecycle.Models.CategoryModel;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.service.CategoryService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PagerFragment extends Fragment {
    private ViewPager mVpMain;
    private TabLayout mTlNav;
//    private List<CategoryModel> categorys = getCategory(RetrofitHelper.getRetrofit());
    private List<String> titles = new ArrayList<>(Arrays.asList(Const.ParentCategory.WENXUE.getDesc(),Const.ParentCategory.WENHUA.getDesc(),
            Const.ParentCategory.JINGGUAN.getDesc(),Const.ParentCategory.KEJI.getDesc()));
    private HomeFragment mHomeFragment;
    private List<Fragment> fragments = new ArrayList<>();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVpMain = view.findViewById(R.id.vp_main);
        mTlNav = view.findViewById(R.id.tl_home);
        mTlNav.setupWithViewPager(mVpMain);

//        for (CategoryModel category : categorys){
//            if(category != null && category.isStatus()){
//                titles.add(category.getName());
//            }
//        }

        mVpMain.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(),
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
    }

    private List<CategoryModel> getCategory(Retrofit retrofit){
        List<CategoryModel> categoryList = new ArrayList<>();
        CategoryService categoryService = retrofit.create(CategoryService.class);
        Call<CategoryBody> call = categoryService.getParentCategory();
        call.enqueue(new Callback<CategoryBody>() {
            @Override
            public void onResponse(Call<CategoryBody> call, Response<CategoryBody> response) {
                CategoryBody categoryBody = response.body();
                if(categoryBody != null && categoryBody.getStatus() == 0){
                    for(CategoryModel category : categoryBody.getData())
                        categoryList.add(category);
                }
            }

            @Override
            public void onFailure(Call<CategoryBody> call, Throwable t) {
                Toast.makeText(getActivity(),"获取分类失败",Toast.LENGTH_LONG).show();
            }
        });
        return categoryList;
    }
}
