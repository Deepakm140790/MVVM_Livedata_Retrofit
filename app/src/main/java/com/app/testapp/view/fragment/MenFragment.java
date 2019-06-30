package com.app.testapp.view.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.testapp.R;
import com.app.testapp.model.CategoryDataModel;
import com.app.testapp.util.Helper;
import com.app.testapp.util.PrintLog;
import com.app.testapp.view.adapter.MenGridViewAdapter;
import com.app.testapp.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MenFragment extends Fragment implements MenGridViewAdapter.ClickListener {
    private static final String TAG = "MenFragment";
    private Context mContext;

    private Unbinder mUnbinder;
    private List<CategoryDataModel> mDataArray = new ArrayList<>();
    private MenGridViewAdapter mGridViewAdapter;

    private String mDataUrl;
    private CategoryViewModel mCategoryViewModel;

    @BindView(R.id.pb_men)
    ProgressBar mProgressBar;
    @BindView(R.id.rv_men)
    RecyclerView mRecyclerView;

    public MenFragment(String data) {
        mDataUrl = data;
    }

    @Override
    public void onAttach(Context context) {
        PrintLog.d(TAG, "onAttach: ");
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrintLog.d(TAG, "onCreate: ");
        //View model
        mCategoryViewModel = ViewModelProviders.of(getActivity()).get(CategoryViewModel.class);
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PrintLog.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_men, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        PrintLog.d(TAG, "onStart()");
    }

    private void init() {
        mGridViewAdapter = new MenGridViewAdapter(mContext, mDataArray);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnItemTouchListener(new MenGridViewAdapter.RecyclerTouchListener(mContext, mRecyclerView, this));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(mContext, R.dimen.grid_item_offset);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(mGridViewAdapter);
    }

    private void loadData() {
        showProgressBar();
        mCategoryViewModel.getData(mDataUrl).observe(this, categoryDataModels -> {
            PrintLog.d(TAG, "onChange: ");
            hideProgressBar();
            if (categoryDataModels == null) {
                return;
            }
            mDataArray.clear();
            mDataArray.addAll(categoryDataModels);
            mGridViewAdapter.notifyDataSetChanged();
        });
        //RxJava
//        mCategoryViewModel.getData(mDataUrl)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(dataObserver());
//        mCompositeDisposable.add(dataObserver());
    }

//    private DisposableObserver<List<CategoryDataModel>> dataObserver() {
//        return new DisposableObserver<List<CategoryDataModel>>() {
//            @Override
//            public void onNext(List<CategoryDataModel> categoryDataModels) {
//                PrintLog.d(TAG, "onNext: ");
//                mDataArray.clear();
//                mDataArray = categoryDataModels;
//                for (CategoryDataModel categoryDataModel : categoryDataModels) {
//                    PrintLog.d(TAG, "id -> " + categoryDataModel.getId());
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                PrintLog.e(TAG, "onError: ");
//                Helper.showToast(mContext, e.toString());
//            }
//
//            @Override
//            public void onComplete() {
//                PrintLog.d(TAG, "onComplete: ");
//            }
//        };
//    }

    private void showProgressBar() {
        if (mProgressBar == null)
            return;
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        if (mProgressBar == null)
            return;
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        PrintLog.d(TAG, "onConfigurationChanged()");
        super.onConfigurationChanged(newConfig);
        if (mRecyclerView == null)
            return;
        Helper.recyclerViewConfiguration(newConfig, mContext, mRecyclerView);
    }

    @Override
    public void onStop() {
        PrintLog.d(TAG, "onStop()");
        hideProgressBar();
        super.onStop();
    }

    @Override
    public void onClick(View view, int position) {
        Helper.showToast(mContext, "GridView item clicked and index position : " + position);
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    /**
     * This class is used for view Decoration.
     */
    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
        private int mItemOffset;

        public ItemOffsetDecoration(int itemOffset) {
            mItemOffset = itemOffset;
        }

        public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
            this(context.getResources().getDimensionPixelSize(itemOffsetId));
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
        }
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        PrintLog.d(TAG, "onDestroyView: ");
        super.onDestroyView();
    }
}