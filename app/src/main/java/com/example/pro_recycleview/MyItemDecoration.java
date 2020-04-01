package com.example.pro_recycleview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private int mDividerWidth;

    public MyItemDecoration(){
        initPaint();
        initDivider();
    }

    private void initPaint(){
        mPaint=new Paint();
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xFF00FFCC);
    }
    private void initDivider(){
        mDividerWidth=10;
    }

    //绘制内容会被ItemView覆盖
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //获取子ItemView数
        int childCount = parent.getChildCount();
        //获取左右边距
        int left = parent.getLeft()+parent.getPaddingLeft();
        int right = parent.getRight()-parent.getPaddingRight();
        for (int i=0;i<childCount;i++){
            //获取子ItemView对象
            View view=parent.getChildAt(i);
            //分隔线绘制位置
            int top = view.getBottom();
            int bottom = view.getBottom()+mDividerWidth;
            //绘制分割线
            c.drawRect(left,top,right,bottom,mPaint);
        }

    }
    //绘制在ItemView之上
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
    //获取重设ItemView之间偏移量
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //重设ItemView边框偏移，不设置ItemView就原样
        outRect.bottom = mDividerWidth;
    }
}
