package com.example.huanpet.view.pet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 阿三 on 2017/12/14.
 */
public class SideBar extends View {

    private Paint paint=new Paint();
    private TextView textView;
    public OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    // 26个字母
    public static String[] b = { "热门", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };
    private int choose=-1;

    public void setTextView(TextView mTextDialog) {
        this.textView = mTextDialog;
    }
    public SideBar(Context context) {
        super(context);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int heigth=getHeight();// 获取对应高度
        int width=getWidth();//获取对应的宽度
        int singleHeight=heigth/b.length; //获取每个字母的高度
        for(int i=0;i<b.length;i++ ){
            paint.setColor(Color.rgb(33,65,98));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(20);
            if(i==choose){
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }
            float xPos = width / 2 - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();// 重置画笔
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();// 点击y坐标
        final int oldChoose = choose;
        OnTouchingLetterChangedListener listener=onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * b.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
          switch (action){
              case MotionEvent.ACTION_UP:
                  choose=-1;

                invalidate();
                  break;
              default:
                  if (oldChoose != c) {
                      if (c >= 0 && c < b.length) {
                          if (listener != null) {
                              listener.onTouchingLetterChanged(b[c]);
                          }
//                          if (mTextDialog != null) {
//                              mTextDialog.setText(b[c]);
//                              mTextDialog.setVisibility(View.VISIBLE);
//                          }

                          choose = c;
                          invalidate();
                      }
                  }

          }
        return false;

    }

    /**
     * 向外公开的方法
     *
     * @param onTouchingLetterChangedListener
     */
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }
    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }
}
