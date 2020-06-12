package demo.com.campussecondbookrecycle.service;

import java.util.List;

import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Models.OrderVo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderService {

//    Call<>
    @GET ("order/get_list_by_type.do")
    Call<HttpResult<List<OrderVo>>> list(@Query("orderType") int orderType);

    @GET ("order/create.do")
    Call<HttpResult<List<OrderVo>>> create(@Query("shippingId")int shippingId,@Query("orderType") int orderType);

    @GET ("order/cancle.do")
    Call<HttpResult<String>> cancle(@Query("orderNo") Long orderNo);

    @GET ("order/pay.do")
    Call<HttpResult<String>> pay(@Query("orderNo") Long orderNo);
}
