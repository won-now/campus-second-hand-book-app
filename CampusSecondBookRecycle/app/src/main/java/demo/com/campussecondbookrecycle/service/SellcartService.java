package demo.com.campussecondbookrecycle.service;

import demo.com.campussecondbookrecycle.Models.BuyCartBook;
import demo.com.campussecondbookrecycle.Models.HttpResult;
import demo.com.campussecondbookrecycle.Models.SellCartVo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SellcartService {

    @GET("sellcart/list.do")
    Call<HttpResult<SellCartVo>> getCartBookList();

    @GET("sellcart/add.do")
    Call<HttpResult<SellCartVo>> add(@Query("bookISBN") String bookISBN);

    @GET("sellcart/delete_book.do")
    Call<HttpResult<SellCartVo>> deleteBook(@Query("bookISBNs") String bookISBNs);

    @GET("sellcart/select_all.do")
    Call<HttpResult<SellCartVo>> selectAll();

    @GET("sellcart/un_select_all.do")
    Call<HttpResult<SellCartVo>> unSelectAll();

    @GET("sellcart/select.do")
    Call<HttpResult<SellCartVo>> select(@Query("bookISBN") String bookISBN);

    @GET("buycart/un_select.do")
    Call<HttpResult<SellCartVo>> unSelect(@Query("bookISBN") String bookISBN);

//    @GET("sellcart/is_exsist.do")
//    Call<HttpResult<Boolean>> isExist(@Query("bookISBN") int bookISBN);
}
