package com.example.pro_recycleview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

import androidx.recyclerview.widget.RecyclerView;

//旋转动画
public class RotationXAnimator extends BaseAnimator {
    //添加
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        resetAnimation(holder);
        //自定义动画
        View view=holder.itemView;
        //设置View初始不可见
        view.setAlpha(0);
        //设置View初始旋转角度
        view.setRotationX(-180);
        mPendingAdditions.add(holder);
        return true;
    }
    //添加实现方法
    @Override
    void animateAddImpl(final RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;
        final ViewPropertyAnimator animation = view.animate();
        mAddAnimations.add(holder);
        //自定义动画
        animation.rotationX(0);
        animation.alpha(1).setDuration(getAddDuration())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        dispatchAddStarting(holder);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        view.setAlpha(1);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        animation.setListener(null);
                        dispatchAddFinished(holder);
                        mAddAnimations.remove(holder);
                        dispatchFinishedWhenDone();
                    }
                }).start();
    }

    //移除Item动画
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        resetAnimation(holder);
        mPendingRemovals.add(holder);
        //自定义动画
        View view=holder.itemView;
        //设置View初始不可见
        view.setAlpha(1);
        //设置View初始旋转角度
        view.setRotationX(0);
        return true;
    }
    //移除Item动画实现
    @Override
    public void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        //获取View
        final View view = holder.itemView;
        //获取animate对象
        final ViewPropertyAnimator animation = view.animate();
        mRemoveAnimations.add(holder);
        //执行动画逻辑
        animation.rotationX(180);
        animation.setDuration(getRemoveDuration()).alpha(0).setListener(
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        dispatchRemoveStarting(holder);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        animation.setListener(null);
                        view.setAlpha(1);
                        dispatchRemoveFinished(holder);
                        mRemoveAnimations.remove(holder);
                        dispatchFinishedWhenDone();
                    }
                }).start();
    }
}
