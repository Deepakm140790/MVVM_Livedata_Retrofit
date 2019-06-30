package com.app.testapp.network;

import com.app.testapp.model.CategoryDataModel;
import com.app.testapp.model.CategoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface CategoryApi {
    @GET("{url}")
    Call<List<CategoryModel>> getCategory(@Path("url") String url);

    @GET
    Call<List<CategoryDataModel>> getData(@Url String url);
}
