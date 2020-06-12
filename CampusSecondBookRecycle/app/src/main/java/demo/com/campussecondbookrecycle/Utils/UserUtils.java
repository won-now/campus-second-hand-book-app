package demo.com.campussecondbookrecycle.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Models.User;
import demo.com.campussecondbookrecycle.activities.LoginActivity;
import demo.com.campussecondbookrecycle.common.Const;
import demo.com.campussecondbookrecycle.helpers.RetrofitHelper;
import demo.com.campussecondbookrecycle.helpers.UserHelper;
import demo.com.campussecondbookrecycle.service.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserUtils {

    /**
     * 验证登录用户输入合法性
     */
    public static boolean validateLogin(Context context,
                                        String userNum,
                                        String password){

        UserService userService = RetrofitHelper.getRetrofit(context).create(UserService.class);
        Call<HttpResult> call = userService.login(userNum,password);
        call.enqueue(new Callback<HttpResult>() {
            @Override
            public void onResponse(Call<HttpResult> call, Response<HttpResult> response) {
//                Log.d("res","repsons");
                HttpResult httpResult = response.body();
//                Log.d("cookie",response.headers().get("set-cookie"));
                if (httpResult != null && httpResult.getStatus() == Const.SUCCESS_STATUS){
                    //        保存用户登录标记
                    boolean isSave = SPUtils.saveUser(context,userNum);
                    if(!isSave){
                        Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT).show();
                    }
                    Log.d("isSave","11111");
                    Log.d("isSave",userNum);
                    //        在全局单例中，保存用户标记
                    UserHelper.getInstance().setUserNum(userNum);
                }else {
                    Toast.makeText(context,httpResult.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HttpResult> call, Throwable t) {
//                Log.d("FAIL!!!","FFFFFFFFFFFFFFFFF");
                Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT).show();
            }
        });

//        /**
//         * 1.用户当前手机号是否已经被注册了
//         * 2.用户输入的手机号和密码是否匹配
//         */
//        if(!UserUtils.userExistFromPhone(phone)){
//            Toast.makeText(context,"当前手机号未注册",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        RealmHelp realmHelp = new RealmHelp();
//        boolean result = realmHelp.validateUser
//                (phone,EncryptUtils.encryptMD5ToString(password));
//
//        if(!result){
//            Toast.makeText(context,"手机号或者密码不正确",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (!isSave)
//            return false;

//
////        保存音乐源数据
//        realmHelp.setMusicSource(context);
//        realmHelp.close();
        return true;
    }

    /**
     * 退出登录
     */
    public static void logout(Context context){
//        删除SharedPreference保存的用户标记
        boolean isRemove = SPUtils.removeUser(context);
        if(!isRemove){
            Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT).show();
            return;
        }

////        删除数据源
//        RealmHelp realmHelp = new RealmHelp();
//        realmHelp.removeMusicSource();
//        realmHelp.close();
//        Intent intent = new Intent(context, imooc.com.musicdemo.activities.LoginActivity.class);
////        添加intent标识符，清理task栈，并且新生成一个task栈
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
//                Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
////        定义Activity跳转动画
//        ((Activity)context).overridePendingTransition
//                (R.anim.open_enter,R.anim.open_exit);
    }

    /**
     * 注册用户
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     */
//    public static boolean registerUser(
//            Context context,String phone,
//            String password,String passwordConfirm){
//        if (!RegexUtils.isMobileExact(phone)){
//            Toast.makeText(context,"无效手机号",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if(StringUtils.isEmpty(password) || !password.equals(passwordConfirm)){
//            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT).show();
//            return false;
//        }
////        用户当前输入的手机号是否已经被注册
//        /**
//         * 1.通过Realm获取到当前已注册的所有用户
//         * 2.根据用户输入的手机号匹配查询的所有用户，如果可以匹配则证明该手机号已被注册
//         */
//        if(UserUtils.userExistFromPhone(phone)){
//            Toast.makeText(context,"该手机号已存在",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        UserModel userModel = new UserModel();
//        userModel.setPhone(phone);
//        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));
//
//        saveUser(userModel);
//
//        return true;
//    }

//    public static void saveUser(UserModel userModel) {
//        RealmHelp realmHelp = new RealmHelp();
//        realmHelp.saveUser(userModel);
//        realmHelp.close();
//    }

    /**
     * 根据学号判断用户是否已经存在
     */
    public static boolean userExistFromNum(String userNum){
        boolean result = false;
        //todo

//        RealmHelp realmHelp = new RealmHelp();
//        List<UserModel> allUser = realmHelp.getAllUser();
//
//        for(UserModel userModel : allUser){
//            if(userModel.getPhone().equals(phone)){
//                result = true;
//                break;
//            }
//        }
//        realmHelp.close();
        return result;
    }

    /**
     * 验证是否存在已登录用户
     */
    public static boolean validateUserLogin(Context context){
        return SPUtils.isLoginUser(context);
    }

    /**
     * 修改密码
     * 1.数据验证
     *      1.原密码是否输入
     *      2.新密码是否输入并且新密码与确定密码是否相同
     *      3.原密码是否输入正确
     *          1.Realm 获取当前登录的用户模型
     *          2.根据用户模型中保存的密码匹配用户原密码是否正确
     * 2.利用Realm模型自动更新的特性完成密码的修改
     */
//    public static boolean changePassword(Context context,String oldPassword,
//                                         String password,String passwordConfirm){
//        if(TextUtils.isEmpty(oldPassword)){
//            Toast.makeText(context,"请输入原密码",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if(TextUtils.isEmpty(password) || !password.equals(passwordConfirm)){
//            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        RealmHelp realmHelp = new RealmHelp();
//        UserModel userModel = realmHelp.getUser();
//        if(!EncryptUtils.encryptMD5ToString(oldPassword).equals(userModel.getPassword())){
//            Toast.makeText(context,"原密码不正确",Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        realmHelp.changePassword(EncryptUtils.encryptMD5ToString(password));
//        realmHelp.close();
//        return true;
//    }
}
