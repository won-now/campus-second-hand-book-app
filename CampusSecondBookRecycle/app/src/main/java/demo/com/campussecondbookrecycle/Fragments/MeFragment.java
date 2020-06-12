package demo.com.campussecondbookrecycle.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import demo.com.campussecondbookrecycle.Adapters.BookrackAdapter;
import demo.com.campussecondbookrecycle.Models.BuyCartBook;
import demo.com.campussecondbookrecycle.Models.BuyCartBookVo;
import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Models.User;
import demo.com.campussecondbookrecycle.Models.UserBook;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.Utils.SPUtils;
import demo.com.campussecondbookrecycle.Views.GridSpaceItemDecoration;
import demo.com.campussecondbookrecycle.activities.LoginActivity;
import demo.com.campussecondbookrecycle.activities.ShippingActivity;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.common.SPConstants;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.BuycartService;
import demo.com.campussecondbookrecycle.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeFragment extends Fragment implements View.OnClickListener {

    private Button mBtLogout,mBtAddress;
    private RecyclerView mRvBookrack;

    private TextView mTvUserName,mTvUserNo;

    private BookrackAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RetrofitHelper.getRetrofit(getActivity()).create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtLogout = view.findViewById(R.id.bt_logout);
        mBtAddress = view.findViewById(R.id.bt_address);
        mRvBookrack = view.findViewById(R.id.rv_bookrack);
        mTvUserName = view.findViewById(R.id.tv_user_name);
        mTvUserNo = view.findViewById(R.id.tv_user_no);
        mRvBookrack.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mRvBookrack.addItemDecoration(new GridSpaceItemDecoration(4,mRvBookrack));
        mBtLogout.setOnClickListener(this);
        mBtAddress.setOnClickListener(this);

        getUser();

        UserService userService = RetrofitHelper.getRetrofit(getActivity()).create(UserService.class);
        Call<HttpResult<List<UserBook>>> call = userService.getBookrack();
        call.enqueue(new Callback<HttpResult<List<UserBook>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<UserBook>>> call, Response<HttpResult<List<UserBook>>> response) {
                HttpResult<List<UserBook>> httpResult = response.body();
                if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    List<UserBook> userBooks = httpResult.getData();
                    mAdapter = new BookrackAdapter(getActivity(),userBooks);
                    mAdapter.notifyDataSetChanged();
                    mRvBookrack.setAdapter(mAdapter);
                }else if(httpResult.getStatus() == 10){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<HttpResult<List<UserBook>>> call, Throwable t) {
                Log.d("bookrack","fail");
            }
        });
////        getCartList();

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.bt_logout:
                SPUtils.removeUser(getActivity());
                intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_address:
                intent = new Intent(getActivity(), ShippingActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void getUser(){
        UserService userService = RetrofitHelper.getRetrofit(getActivity()).create(UserService.class);
        Call<HttpResult<User>> call = userService.getUserInfo();
        call.enqueue(new Callback<HttpResult<User>>() {
            @Override
            public void onResponse(Call<HttpResult<User>> call, Response<HttpResult<User>> response) {
                HttpResult<User> httpResult = response.body();
                if (httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    User user = httpResult.getData();
                    mTvUserNo.setText(user.getUserNum());
                    mTvUserName.setText(user.getUsername());
                    Intent intent = new Intent(getActivity(),ShippingActivity.class);
                    int userId = user.getId();
                    intent.putExtra("userId",userId);
                }
            }

            @Override
            public void onFailure(Call<HttpResult<User>> call, Throwable t) {

            }
        });

    }
}
