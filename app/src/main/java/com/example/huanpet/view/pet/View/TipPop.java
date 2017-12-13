package com.example.huanpet.view.pet.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.huanpet.R;

/**
 * Created by Administrator on 2017/12/10.
 */

public class TipPop extends PopupWindow {

    private Context context;

    public static TipPop getInstance(Activity activity){
        return new TipPop(activity);
    }

    public TipPop(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    public TipPop(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TipPop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        View view = View.inflate(context, R.layout.pop_tip, null);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);
        setContentView(view);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
//                WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
//                lp.alpha = 1f; //0.0-1.0
//                ((Activity)context).getWindow().setAttributes(lp);
            }
        });
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
//        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
//        lp.alpha = 0.5f; //0.0-1.0
//        ((Activity)context).getWindow().setAttributes(lp);
    }
}
