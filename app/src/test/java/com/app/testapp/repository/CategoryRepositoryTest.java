package com.app.testapp.repository;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.app.testapp.model.CategoryDataModel;
import com.app.testapp.model.CategoryModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CategoryRepositoryTest {
    @Mock
    private CategoryRepository categoryRepository;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() throws Exception {

    }

    @Test
    public void getInstance() {
        assertNotNull(CategoryRepository.getInstance());
    }

    @Test
    public void getCategory() {
        final String URL = "Test";
        MutableLiveData<List<CategoryModel>> listMutableLiveData = new MutableLiveData<>();
        CategoryModel categoryModel = new CategoryModel("name", "data");
        List<CategoryModel> list = new ArrayList<>();
        list.add(categoryModel);
        listMutableLiveData.setValue(list);

        when(categoryRepository.getCategory(URL)).thenReturn(listMutableLiveData);
        System.out.println(listMutableLiveData.getValue());
    }

    @Test
    public void getData() {
        final String URL = "Test";
        MutableLiveData<List<CategoryDataModel>> listMutableLiveData = new MutableLiveData<>();
        List<CategoryDataModel> list = new ArrayList<>();
        CategoryDataModel categoryDataModel = new CategoryDataModel("1", "name", "status", 10, 121, 120, "photo");
        list.add(categoryDataModel);
        listMutableLiveData.setValue(list);

        when(categoryRepository.getData(URL)).thenReturn(listMutableLiveData);
        System.out.println(listMutableLiveData.getValue());
    }
}