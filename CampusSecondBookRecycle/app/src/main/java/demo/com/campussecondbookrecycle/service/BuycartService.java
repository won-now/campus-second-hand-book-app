package demo.com.campussecondbookrecycle.service;

import demo.com.campussecondbookrecycle.Models.BuyCartBook;
import demo.com.campussecondbookrecycle.Models.HttpResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BuycartService {

    @GET("buycart/list.do")
    Call<HttpResult<BuyCartBook>> getCartBookList();

    @GET("buycart/add.do")
    Call<HttpResult<BuyCartBook>> add(@Query("bookId")int bookId);

    @GET("buycart/delete_book.do")
    Call<HttpResult<BuyCartBook>> deleteBook(@Query("bookIds") String bookIds);

    @GET("buycart/select_all.do")
    Call<HttpResult<BuyCartBook>> selectAll();

    @GET("buycart/un_select_all.do")
    Call<HttpResult<BuyCartBook>> unSelectAll();

    @GET("buycart/select.do")
    Call<HttpResult<BuyCartBook>> select(@Query("bookId") int bookId);

    @GET("buycart/un_select.do")
    Call<HttpResult<BuyCartBook>> unSelect(@Query("bookId") int bookId);

    @GET("buycart/is_exsist.do")
    Call<HttpResult<Boolean>> isExist(@Query("bookId") int bookId);
}
