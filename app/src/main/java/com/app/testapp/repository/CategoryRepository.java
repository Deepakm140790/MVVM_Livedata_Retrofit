package com.app.testapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.testapp.model.CategoryDataModel;
import com.app.testapp.model.CategoryModel;
import com.app.testapp.network.CategoryApi;
import com.app.testapp.network.RetrofitClient;
import com.app.testapp.util.PrintLog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Class is responsible to get the Category data from server.
 */
public class CategoryRepository {
    private static final String TAG = "CategoryRepository";
    private static CategoryRepository mCategoryRepository = null;
    private Retrofit mRetrofit;

    private CategoryRepository() {
        mRetrofit = RetrofitClient.getClient();
    }

    /**
     * Get Instance of the class.
     * @return
     */
    public static CategoryRepository getInstance() {
        if (mCategoryRepository == null) {
            mCategoryRepository = new CategoryRepository();
        }
        return mCategoryRepository;
    }

    /**
     * Get category list from server
     * @param url
     * @return
     */
    public LiveData<List<CategoryModel>> getCategory(String url) {
        PrintLog.d(TAG, "getCategory: " + url);
        MutableLiveData<List<CategoryModel>> mutableLiveData = new MutableLiveData<>();
        CategoryApi categoryApi = mRetrofit.create(CategoryApi.class);
        Call<List<CategoryModel>> call = categoryApi.getCategory(url);
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                PrintLog.d(TAG, "onResponse: " + response.body());
                if (response.isSuccessful())
                    mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                PrintLog.e(TAG, "onFailure: ");
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    /**
     * Get data list from server
     * @param url
     * @return
     */
    public LiveData<List<CategoryDataModel>> getData(String url) {
        PrintLog.d(TAG, "getData: " + url);
        MutableLiveData<List<CategoryDataModel>> mutableLiveData = new MutableLiveData<>();
        CategoryApi categoryApi = mRetrofit.create(CategoryApi.class);
        Call<List<CategoryDataModel>> call = categoryApi.getData(url);
        call.enqueue(new Callback<List<CategoryDataModel>>() {
            @Override
            public void onResponse(Call<List<CategoryDataModel>> call, Response<List<CategoryDataModel>> response) {
                PrintLog.d(TAG, "onResponse: " + response);
                if (response.isSuccessful())
                    mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CategoryDataModel>> call, Throwable t) {
                PrintLog.e(TAG, "onFailure: ");
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}
