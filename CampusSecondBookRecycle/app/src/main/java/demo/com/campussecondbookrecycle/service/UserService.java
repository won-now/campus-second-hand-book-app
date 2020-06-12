package demo.com.campussecondbookrecycle.service;



import java.util.List;

import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Models.User;
import demo.com.campussecondbookrecycle.Models.UserBook;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @FormUrlEncoded
    @POST("user/register.do")
    Call<HttpResult> register(@Field("userNum")String userNum,@Field("password")String password,@Field("username")String username);

    @FormUrlEncoded
    @POST("user/login.do")
    Call<HttpResult> login(@Field("user_num")String userNum,@Field("password")String password);

    @GET("user/get_user_book.do")
    Call<HttpResult<List<UserBook>>> getBookrack();

    @GET("user/get_user_info.do")
    Call<HttpResult<User>> getUserInfo();
}
