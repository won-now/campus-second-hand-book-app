package demo.com.campussecondbookrecycle.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import demo.com.campussecondbookrecycle.R;
import demo.com.campussecondbookrecycle.Utils.UserUtils;
import demo.com.campussecondbookrecycle.Views.InputView;


public class ChangePasswordActivity extends AppCompatActivity {

    private InputView mOldPassword,mPassword,mPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initView();
    }

    private void initView() {
//        initNavBar(true,"修改密码",false);
//
//        mOldPassword = fd(R.id.input_old_password);
//        mPassword = fd(R.id.input_password);
//        mPasswordConfirm = fd(R.id.input_password_confirm);
    }

//    public void onChangePasswordClick(View view){
//        String oldPassword = mOldPassword.getInputSrc();
//        String password = mPassword.getInputSrc();
//        String passwordConfirm = mPasswordConfirm.getInputSrc();
//
//        boolean result = UserUtils.changePassword(
//                this,oldPassword,password,passwordConfirm);
//        if(!result)
//            return;
//        UserUtils.logout(this);
//    }
}
