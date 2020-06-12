package demo.com.campussecondbookrecycle.service;

import demo.com.campussecondbookrecycle.Models.CategoryBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("manage/category/get_category.do")
    Call<CategoryBody> getParentCategory();
}
