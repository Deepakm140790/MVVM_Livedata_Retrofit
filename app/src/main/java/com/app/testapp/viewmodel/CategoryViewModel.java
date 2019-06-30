package com.app.testapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.app.testapp.model.CategoryDataModel;
import com.app.testapp.model.CategoryModel;
import com.app.testapp.repository.CategoryRepository;
import com.app.testapp.util.PrintLog;

import java.util.List;

/**
 * Class is responsible to give the Category data to client.
 */
public class CategoryViewModel extends ViewModel {
    private static final String TAG = "CategoryViewModel";
    private CategoryRepository mCategoryRepository;

    public CategoryViewModel() {
        mCategoryRepository = CategoryRepository.getInstance();
    }

    public LiveData<List<CategoryModel>> getCategory(String url) {
        PrintLog.d(TAG, "getCategory: ");
        return mCategoryRepository.getCategory(url);
    }

    public LiveData<List<CategoryDataModel>> getData(String url) {
        PrintLog.d(TAG, "getData: ");
        return mCategoryRepository.getData(url);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        PrintLog.d(TAG, "onCleared: ");
    }
}
