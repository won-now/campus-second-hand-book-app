package demo.com.campussecondbookrecycle.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.Utils.SPUtils;
import demo.com.campussecondbookrecycle.Utils.UserUtils;
import demo.com.campussecondbookrecycle.Views.InputView;


public class LoginActivity extends AppCompatActivity {

    private InputView mInputNo,mInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){
//        initNavBar(false,"登录",false);
        mInputNo = this.findViewById(R.id.input_usernum);
        mInputPassword = this.findViewById(R.id.input_password);
    }

    public void onRegisterClick(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void onCommitClick(View view){
        String No = mInputNo.getInputSrc();
        String password = mInputPassword.getInputSrc();

        if (TextUtils.isEmpty(No)){
            Toast.makeText(LoginActivity.this,"请输入学号",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!UserUtils.validateLogin(this,No,password)){
            return;
        }

        if(!SPUtils.isLoginUser(this)){
            return;
        }

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}
