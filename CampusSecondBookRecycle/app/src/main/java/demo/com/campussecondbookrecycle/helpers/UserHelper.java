package demo.com.campussecondbookrecycle.helpers;

/**
 * 1.登录
 *      1.当用户登录时，利用SharedPreference保存登录用户的用户标记（学号）
 *      2.利用全局单例类UserHelper保存登录信息
 *          1.用户登录之后
 *          2.用户打开应用程序，检测SharedPreference中是否存在用户标记，
 *          如果存在则为UserHelper进行赋值，并且进入主页，如果不存在，进入登录页面
 * 2.退出
 *      1.删除掉SharedPreference保存的用户标记，退出到登录页面
 */
public class UserHelper {

    private static UserHelper instance;

    public static UserHelper getInstance(){
        if(instance == null){
            synchronized (UserHelper.class){
                if(instance == null)
                    instance = new UserHelper();
            }
        }
        return instance;
    }

    private String userNum;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
}
