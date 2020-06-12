package demo.com.campussecondbookrecycle.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.com.campussecondbookrecycle.R;

public class CartFragment extends Fragment {

    private TabLayout mTlCart;
    private ViewPager mVpCart;
    private List<String> titles = new ArrayList<>(Arrays.asList("购书车","售书车"));

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTlCart = view.findViewById(R.id.tl_cart);
        mVpCart = view.findViewById(R.id.vp_cart);
        mTlCart.setupWithViewPager(mVpCart);
    }
}
