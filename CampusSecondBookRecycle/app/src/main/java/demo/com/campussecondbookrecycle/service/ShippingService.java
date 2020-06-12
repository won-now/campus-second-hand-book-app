package demo.com.campussecondbookrecycle.service;

import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Models.Shipping;
import demo.com.campussecondbookrecycle.Models.ShippingId;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShippingService {

    @GET("shipping/add.do")
    Call<HttpResult<ShippingId>> add(@Query("shipping")Shipping shipping);

    @GET("shipping/update.do")
    Call<HttpResult> update(@Query("shipping")Shipping shipping);
}
