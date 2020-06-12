package demo.com.campussecondbookrecycle.service;

import java.util.List;

import demo.com.campussecondbookrecycle.Models.BookBrief;
import demo.com.campussecondbookrecycle.Models.BookDetail;
import demo.com.campussecondbookrecycle.Models.HttpResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {

    @GET("book/list.do")
    Call<HttpResult<List<BookBrief>>> getBookList(@Query("keyword")String keyword, @Query("categoryId")Integer categoryId);

    @GET("book/detail.do")
    Call<HttpResult<BookDetail>> getBookDetail(@Query("bookId")int bookId);

}
