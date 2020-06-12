package demo.com.campussecondbookrecycle.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;
import java.util.Map;

import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Models.Shipping;
import demo.com.campussecondbookrecycle.Models.ShippingId;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.common.SPConstants;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.ShippingService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingActivity extends AppCompatActivity {

    private EditText mEtName,mEtMobile,mEtDistrict,mEtBuilding,mEtAddress;
    private String name,mobile,district,address,building;
    private ShippingService shippingService;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        mEtName = findViewById(R.id.et_name);
        mEtMobile = findViewById(R.id.et_mobile);
        mEtDistrict = findViewById(R.id.et_district);
        mEtAddress = findViewById(R.id.et_address);
        mEtBuilding = findViewById(R.id.et_buliding);
        shippingService = RetrofitHelper.getRetrofit(this).create(ShippingService.class);
        SharedPreferences sp = getSharedPreferences
                ("shipping", Context.MODE_PRIVATE);
        name = sp.getString("name","");
        mobile = sp.getString("mobile","");
        address = sp.getString("address","");
        building = sp.getString("building","");
        district = sp.getString("district","");
        mEtName.setText(name);
        mEtBuilding.setText(building);
        mEtAddress.setText(address);
        mEtMobile.setText(mobile);
        mEtDistrict.setText(district);
    }

    public void add(View view){
        Call<HttpResult<ShippingId>> call = shippingService.add(getShipping());
        call.enqueue(new Callback<HttpResult<ShippingId>>() {
            @Override
            public void onResponse(Call<HttpResult<ShippingId>> call, Response<HttpResult<ShippingId>> response) {
                HttpResult httpResult = response.body();
                if(httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    ShippingId id  = (ShippingId) httpResult.getData();
//                    Float shippingId = (float) map.get("shippingId");
                    SharedPreferences sp = getSharedPreferences
                            ("shipping", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("shippingId",id.getShippingId());
                    editor.commit();
                }
                Toast.makeText(ShippingActivity.this,httpResult.getMsg(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<HttpResult<ShippingId>> call, Throwable t) {

            }
        });
    }

    public void update(View view){
        Call<HttpResult> call = shippingService.update(getShipping());
        call.enqueue(new Callback<HttpResult>() {
            @Override
            public void onResponse(Call<HttpResult> call, Response<HttpResult> response) {
                HttpResult httpResult = response.body();
                if (httpResult != null){
                    Toast.makeText(ShippingActivity.this,httpResult.getMsg(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<HttpResult> call, Throwable t) {

            }
        });
    }

    public Shipping getShipping(){
        name = mEtName.getText().toString();
        mobile = mEtMobile.getText().toString();
        address = mEtAddress.getText().toString();
        building = mEtBuilding.getText().toString();
        district = mEtDistrict.getText().toString();
        userId = getIntent().getIntExtra("userId",21);
        Shipping shipping = new Shipping();
        shipping.setReceiverName(name);
        shipping.setUserId(userId);
        shipping.setReceiverMobile(mobile);
        shipping.setReceiverBuilding(building);
        shipping.setReceiverDistrict(district);
        shipping.setReceiverAddress(address);
        SharedPreferences sp = getSharedPreferences
                ("shipping", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",name);
        editor.putString("mobile",mobile);
        editor.putString("address",address);
        editor.putString("building",building);
        editor.putString("district",district);
        editor.commit();
        return shipping;
    }
}
