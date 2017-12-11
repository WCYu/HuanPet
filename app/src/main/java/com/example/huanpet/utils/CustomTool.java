package com.example.huanpet.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huanpet.R;

/**
 * Created by 阿禹 on 2017/12/6.
 */

public class CustomTool extends LinearLayout{
    private OnLeftButtonClickListener mLeftButtonClickListener;
    private OnRightImgClickListener onRightImgClickListener;
    private OnRightTitleClickListener onRightTitleClick;//
    private MyViewHolder mViewHolder;
    private View viewAppTitle;

    public CustomTool(Context context) {
        this(context, null);
    }

    public CustomTool(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTool(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        viewAppTitle = inflater.inflate(R.layout.custom_tool, null);
        this.addView(viewAppTitle, layoutParams);

        mViewHolder = new MyViewHolder(this);
        mViewHolder.returnBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLeftButtonClickListener != null) {
                    mLeftButtonClickListener.onLeftButtonClick(view);
                }
            }
        });
        mViewHolder.ivRightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onRightImgClickListener.onRightImgClick(view);
            }
        });

        mViewHolder.tvRightTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onRightImgClickListener.onRightImgClick(view);
            }
        });
        //
        mViewHolder.tvRightTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightTitleClick.onRightTitleClick(v);
            }
        });
    }

    public void initViewsVisible(boolean isLeftButtonVisile, boolean isCenterTitleVisile, boolean isRightIconVisile, boolean isRightTitleVisile) {
        // 左侧返回
        mViewHolder.lly_zong.setVisibility(isLeftButtonVisile ? View.VISIBLE : View.INVISIBLE);
        mViewHolder.tvCenterTitle.setVisibility(isCenterTitleVisile ? View.VISIBLE : View.INVISIBLE);
        mViewHolder.ivRightImg.setVisibility(isRightIconVisile ? View.VISIBLE : View.INVISIBLE);
        mViewHolder.tvRightTitle.setVisibility(isRightTitleVisile ? View.VISIBLE : View.INVISIBLE);
    }


    public void setAppTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mViewHolder.tvCenterTitle.setText(title);
        }
    }

    public void setReturnBtn(String title){
        if (!TextUtils.isEmpty(title)) {
            mViewHolder.returnBtn.setText(title);
        }
    }
    public void setRightTitle(String text) {
        if (!TextUtils.isEmpty(text)) {
            mViewHolder.tvRightTitle.setText(text);
        }
    }

    public void setRightIcon(int res) {
        if(res!=0){
            mViewHolder.ivRightImg.setImageResource(res);
        }
    }

    public void setLeftOnclick(OnLeftButtonClickListener mOnLeftButtonClickListener) {
        if (mOnLeftButtonClickListener != null) {

        }
    }

    public void setAppBackground(int color) {
        viewAppTitle.setBackgroundColor(color);
    }

    public void setOnLeftButtonClickListener(OnLeftButtonClickListener listen) {
        mLeftButtonClickListener = listen;
    }
    public void setOnRightImgClickLisrener(OnRightImgClickListener onRightImgClickListener){
        this.onRightImgClickListener=onRightImgClickListener;
    }

    //
    public void setOnRightTitleClickListener(OnRightTitleClickListener listen) {

        onRightTitleClick = listen;
    }

    public interface OnLeftButtonClickListener {
        void onLeftButtonClick(View v);
    }
    public interface OnRightImgClickListener{
        void onRightImgClick(View v);
    }
    //
    public interface OnRightTitleClickListener{
        void onRightTitleClick(View v);
    }
    static class MyViewHolder {
        LinearLayout lly_zong;
        TextView tvCenterTitle;
        ImageView ivRightImg;
        TextView tvRightTitle;
        TextView returnBtn;

        public MyViewHolder(View v) {
            lly_zong = v.findViewById(R.id.lly_zong);
            tvCenterTitle = v.findViewById(R.id.tvCenterTitle);
            ivRightImg = v.findViewById(R.id.ivRightImg);
            tvRightTitle =v.findViewById(R.id.tvRightTitle);
            returnBtn= v.findViewById(R.id.return_btn);
        }
    }
}
