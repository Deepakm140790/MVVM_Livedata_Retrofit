package com.app.testapp.view.adapter;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.testapp.R;
import com.app.testapp.model.CategoryDataModel;
import com.app.testapp.network.GlideLibHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class is a adapter for the Recycler grid view.
 */
public class WomenGridViewAdapter extends RecyclerView.Adapter<WomenGridViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<CategoryDataModel> mData;

    public WomenGridViewAdapter(Context context, List<CategoryDataModel> data) {
        mContext = context;
        mData = data;
    }

    /**
     * Holds the view objects.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.tv_comment)
        TextView comment;
        @BindView(R.id.tv_like)
        TextView like;
        @BindView(R.id.tv_price)
        TextView price;
        @BindView(R.id.iv_photo)
        ImageView photo;
        @BindView(R.id.iv_tag)
        ImageView tag;

        public MyViewHolder(@NonNull View view) {
            super(view);
            //Bind ButterKnife
            ButterKnife.bind(this, view);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gridview_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        CategoryDataModel dataModel = mData.get(position);
        myViewHolder.name.setText(dataModel.getName());
        myViewHolder.comment.setText(String.valueOf(dataModel.getComments()));
        myViewHolder.like.setText(String.valueOf(dataModel.getLikes()));
        myViewHolder.price.setText("$ " + dataModel.getPrice());
        if (dataModel.getStatus().equals("on_sale")) {
            myViewHolder.tag.setVisibility(View.GONE);
        } else {
            myViewHolder.tag.setVisibility(View.VISIBLE);
            myViewHolder.tag.setImageResource(R.mipmap.badge_soldout);
        }

        // Network call to download a image.
        if (mContext != null && dataModel.getPhotoUrl() != null && !dataModel.getPhotoUrl().isEmpty()) {
            GlideLibHelper.downloadImage(mContext, dataModel.getPhotoUrl(), myViewHolder.photo);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * Interface is used to send the onClick events to client.
     */
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    /**
     * This class implements RecyclerView OnItemTouchListener.
     */
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView,
                                     final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }
}
