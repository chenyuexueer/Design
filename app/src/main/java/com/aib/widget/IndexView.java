package com.aib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xxx.design.R;

public class IndexView extends View {

    /**
     * 每条的宽和高
     */
    private int itemWidth;
    private int itemHeight;

    private Paint paint;


    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    private int textSize;


    public IndexView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndexView);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.IndexView_text_size:
                    textSize = typedArray.getDimensionPixelSize(index, 12);
                    break;
            }
        }

        typedArray.recycle();

        paint = new Paint();
        paint.setColor(Color.WHITE);//设置颜色
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体字
        paint.setTextSize(textSize);
    }

    /**
     * 测量方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth = getMeasuredWidth();
        itemHeight = getMeasuredHeight() / words.length;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < words.length; i++) {

            if (touchIndex == i) {
                //设置灰色
                paint.setColor(Color.GRAY);
            } else {
                //设置白色
                paint.setColor(Color.WHITE);
            }


            String word = words[i];//A

            Rect rect = new Rect();
            //画笔
            //0,1的取一个字母
            paint.getTextBounds(word, 0, 1, rect);

            //字母的高和宽
            int wordWidth = rect.width();
            int wordHeight = rect.height();

            //计算每个字母在视图上的坐标位置
            float wordX = itemWidth / 2 - wordWidth / 2;
            float wordY = itemHeight / 2 + wordHeight / 2 + i * itemHeight;

            canvas.drawText(word, wordX, wordY, paint);

        }
    }

    /**
     * 字母的下标位置
     */
    private int touchIndex = -1;

    /**
     * 手指按下文字变色
     * 1.重写onTouchEvent(),返回true,在down/move的过程中计算
     * int touchIndex = Y / itemHeight; 强制绘制
     * <p/>
     * 2.在onDraw()方法对于的下标设置画笔变色
     * <p/>
     * 3.在up的时候
     * touchIndex  = -1；
     * 强制绘制
     *
     * @param event
     * @return
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float Y = event.getY();
                int index = (int) (Y / itemHeight);//字母索引
                if (index != touchIndex && Y >= 0) {
                    touchIndex = index;
                    invalidate();//强制绘制onDraw();
                    if (onIndexChangeListener != null && touchIndex < words.length) {
                        onIndexChangeListener.onIndexChange(words[touchIndex]);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                touchIndex = -1;
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 字母下标索引变化的监听器
     */
    public interface OnIndexChangeListener {

        /**
         * 当字母下标位置发生变化的时候回调
         *
         * @param word 字母（A~Z）
         */
        void onIndexChange(String word);
    }

    private OnIndexChangeListener onIndexChangeListener;

    /**
     * 设置字母下标索引变化的监听
     *
     * @param onIndexChangeListener
     */
    public void setOnIndexChangeListener(OnIndexChangeListener onIndexChangeListener) {
        this.onIndexChangeListener = onIndexChangeListener;
    }
}
