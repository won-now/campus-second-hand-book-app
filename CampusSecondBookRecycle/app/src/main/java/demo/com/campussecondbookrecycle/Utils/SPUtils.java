package demo.com.campussecondbookrecycle.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import demo.com.campussecondbookrecycle.common.SPConstants;
import demo.com.campussecondbookrecycle.helpers.UserHelper;

public class SPUtils {
    /**
     * 当用户登录时，利用SharedPreference保存登录用户的用户标记（手机号）
     */
    public static boolean saveUser(Context context,String userNum){
        SharedPreferences sp = context.getSharedPreferences
                (SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SPConstants.SP_KEY_NUM,userNum);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 验证是否存在已登录用户
     */
    public static boolean isLoginUser(Context context){
        boolean result = false;

        SharedPreferences sp = context.getSharedPreferences
                (SPConstants.SP_NAME_USER,Context.MODE_PRIVATE);
        String userNum = sp.getString(SPConstants.SP_KEY_NUM,"");

        if(!TextUtils.isEmpty(userNum)){
            result = true;
            UserHelper.getInstance().setUserNum(userNum);
        }
        return result;
    }

    /**
     * 删除用户标记
     */
    public static boolean removeUser(Context context){
        SharedPreferences sp = context.getSharedPreferences
                (SPConstants.SP_NAME_USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(SPConstants.SP_KEY_NUM);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 保存用户cookie
     */
    public static boolean saveLoginCookie(Context context,String cookie){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPConstants.LOGIN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SPConstants.SESSION,cookie);
        return editor.commit();
    }

    public static String getLoginCookie(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SPConstants.LOGIN,Context.MODE_PRIVATE);
        return sharedPreferences.getString(SPConstants.SESSION,"");
    }
}
