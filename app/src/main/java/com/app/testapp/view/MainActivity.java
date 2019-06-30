package com.app.testapp.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.app.testapp.R;
import com.app.testapp.model.CategoryModel;
import com.app.testapp.util.Helper;
import com.app.testapp.util.PrintLog;
import com.app.testapp.view.adapter.ViewPagerAdapter;
import com.app.testapp.view.fragment.AllFragment;
import com.app.testapp.view.fragment.MenFragment;
import com.app.testapp.view.fragment.WomenFragment;
import com.app.testapp.viewmodel.CategoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private CategoryViewModel mCategoryViewModel;
    private Unbinder mUnbinder;
    private ViewPagerAdapter adapter;
    private static final String URL = "m-et/Android/json/master.json";

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrintLog.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        // Bind the view using ButterKnife
        mUnbinder = ButterKnife.bind(this);

        //View model
        mCategoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        initView();
        loadCategory();
    }

    private void initView() {
        fab.setOnClickListener(this);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void loadCategory() {
        PrintLog.d(TAG, "loadCategory: ");
        mCategoryViewModel.getCategory(URL).observe(this,
                categoryModels -> {
                    PrintLog.d(TAG, "onChange: ");
                    if (categoryModels == null) {
                        Helper.showToast(getApplicationContext(), "Oops! something went wrong. Please check the network.");
                        return;
                    }
                    setupViewPager(categoryModels);
                });
    }

    private void setupViewPager(List<CategoryModel> categoryArray) {
        //TODO Change the logic
        PrintLog.d(TAG, "setupViewPager: ");
        if (categoryArray == null) {
            PrintLog.e(TAG, "setupViewPager: Category is null");
            return;
        }

        adapter.clearAdapter();
        for (CategoryModel categoryModel : categoryArray) {
            String name = categoryModel.getName();
            String data = categoryModel.getData();
            PrintLog.d(TAG, "categoryModel: " + name);
            PrintLog.d(TAG, "categoryModel: " + data);

            if (name.equalsIgnoreCase("Men")) {
                MenFragment menFragment = new MenFragment(data);
                adapter.addFragment(menFragment, name);
            }

            if (name.equalsIgnoreCase("All")) {
                AllFragment allFragment = new AllFragment(data);
                adapter.addFragment(allFragment, name);
            }

            if (name.equalsIgnoreCase("Women")) {
                WomenFragment womenFragment = new WomenFragment(data);
                adapter.addFragment(womenFragment, name);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                openCamera(getApplicationContext());
                break;

            default:
                break;
        }
    }

    private void openCamera(Context context) {
        if (Helper.checkCameraHardware(context)) {
            if (Helper.checkSelfPermisson(context, Manifest.permission.CAMERA)) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 0);
            } else {
                Helper.showToast(context, "Camera Permission not Granted");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        PrintLog.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        PrintLog.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        PrintLog.d(TAG, "onDestroy: ");
        mUnbinder.unbind();
        super.onDestroy();
    }
}
