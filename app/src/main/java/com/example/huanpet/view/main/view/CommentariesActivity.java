package com.example.huanpet.view.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

public class CommentariesActivity extends BaseActivity {

    private CustomTool commentaries_custom;
    private ListView commentaries_list;

    @Override
    public int initLayoutID() {
        return R.layout.activity_commentaries;
    }

    public void initView() {
        commentaries_custom = (CustomTool) findViewById(R.id.commentaries_custom);
        commentaries_list = (ListView) findViewById(R.id.commentaries_list);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setMyAppTitle() {
        commentaries_custom.setRightIcon(R.drawable.map);
        commentaries_custom.setReturnBtn("");
        commentaries_custom.setAppTitle("寄养评价");
        commentaries_custom.initViewsVisible(true, true, false, false);
        commentaries_custom.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                finish();
            }
        });
        commentaries_custom.setOnRightImgClickLisrener(new CustomTool.OnRightImgClickListener() {
            @Override
            public void onRightImgClick(View v) {

            }
        });
    }
}
