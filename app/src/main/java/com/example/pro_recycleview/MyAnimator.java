package com.example.pro_recycleview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

//直接继承RecyclerView.ItemAnimator,所有动画控制逻辑自己编写
//这里我们继承默认DeaultAnimator,重写对应动画方法即可
public class MyAnimator extends RecyclerView.ItemAnimator {
    //当一个ViewHolder移除时，被RecycleView调用
    @Override
    public boolean animateDisappearance(@NonNull final RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
        Log.i("MyAnimator","disappearance");
        final View view= viewHolder.itemView;
        ViewPropertyAnimator viewAnimator=view.animate();
        viewAnimator.alpha(0);
        viewAnimator.setDuration(getRemoveDuration());
        viewAnimator.scaleX(100);
        viewAnimator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                dispatchAnimationFinished(viewHolder);
                Log.i("MyAnimator","animation end");
            }

            @Override
            public void onAnimationStart(Animator animation) {
                dispatchAnimationStarted(viewHolder);
                Log.i("MyAnimator","animation start");
            }
        });
        return false;
    }
    //当一个ViewHolder加入layout时被RecycleView调用
    @Override
    public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        Log.i("MyAnimator","appearance");
        viewHolder.itemView.setAlpha(1);
        return true;
    }
    //当ViewHolder加入layout前，或加入layout后，
    // 并且RecycleView没有收到RecycleView.Adapter.notifyItemChanged,RecycleView.Adapter.notifyDatasetChanged方法调用
    @Override
    public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        Log.i("MyAnimator","animatePersistance");
        return false;
    }
    //当ViewHolder加入layout前，或加入layout后，
    //并且RecycleView收到RecycleView.Adapter.notifyItemChanged调用
    @Override
    public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
        Log.i("MyAnimator","animateChange");
        return false;
    }

    //当上述方法有返回true,调用runPendingAnimations
    @Override
    public void runPendingAnimations() {
        Log.i("MyAnimator","runPendingAnimation");
    }

    //需要立即结束当前Item动画，执行方法
    @Override
    public void endAnimation(@NonNull RecyclerView.ViewHolder item) {
        Log.i("MyAnimator","endAnimation");
    }
    //需要立即结束所有Item动画，执行方法
    @Override
    public void endAnimations() {
        Log.i("MyAnimator","endAnimations");
    }

    //页面RecyclerView滑动，增加、删除、拖动Item效果，都会执行代码
    @Override
    public boolean isRunning() {
        Log.i("MyAnimator","running");
        return false;
    }
}
