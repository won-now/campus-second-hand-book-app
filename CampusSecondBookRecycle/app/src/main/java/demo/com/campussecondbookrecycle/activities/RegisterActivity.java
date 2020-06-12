package demo.com.campussecondbookrecycle.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.Utils.UserUtils;
import demo.com.campussecondbookrecycle.Views.InputView;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    private InputView mInputNo,mInputPassword,mInputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        initView();
    }

    private void initView() {
//        initNavBar(true,"注册",false);
//
        mInputNo = findViewById(R.id.input_No);
        mInputPassword = findViewById(R.id.input_password);
        mInputName = findViewById(R.id.input_name);
    }

    /**
     * 注册按钮点击事件
     * 1.用户输入合法性验证
     * 2.保存用户输入的手机号和密码（MD5加密密码）
     * @param v
     */
//        String phone = mInputPhone.getInputSrc();
    public void onRegisterClick(View v){
        String password = mInputPassword.getInputSrc();
        String No = mInputNo.getInputSrc();
        String name = mInputName.getInputSrc();

//        boolean result = UserUtils
//                .registerUser(this,phone,password,passwordConfirm);

        UserService userService = RetrofitHelper.getRetrofit(RegisterActivity.this).create(UserService.class);
        Call<HttpResult> call = userService.register(No,password,name);

        call.enqueue(new Callback<HttpResult>() {
            @Override
            public void onResponse(Call<HttpResult> call, Response<HttpResult> response) {
                HttpResult httpResult = response.body();
                String msg = "";
                if (httpResult == null)
                    return;
                if(httpResult.getStatus() != Const.SUCCESS_STATUS){
                    msg = httpResult.getMsg();
                    Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_SHORT).show();
                }
                if (httpResult.getStatus() == Const.SUCCESS_STATUS)
                    onBackPressed();
            }

            @Override
            public void onFailure(Call<HttpResult> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"注册失败!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
