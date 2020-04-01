package com.example.pro_recycleview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;

//平移动画，从左到右
public class TransXAnimator extends BaseAnimator {
    //重写动画添加方法
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        resetAnimation(holder);
        //设置自定义动画
        View view = holder.itemView;
        //设置动画开始前，view位置
        view.setTranslationX(-view.getWidth());
        //将Viewholder加入Add队列
        mPendingAdditions.add(holder);
        return true;
    }

    //重写添加动画实现方法
    @Override
    void animateAddImpl(final RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;
        final ViewPropertyAnimator animation = view.animate();
        mAddAnimations.add(holder);
        //平移动画
        animation.translationX(0);
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

    //移除动画
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        resetAnimation(holder);
        //设置自定义动画
        View view = holder.itemView;
        //设置动画开始前，view位置
        view.setTranslationX(0);
        mPendingRemovals.add(holder);
        return true;
    }

    //重写移除动画实现方法
    @Override
    public void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        //获取View
        final View view = holder.itemView;
        //获取animate对象
        final ViewPropertyAnimator animation = view.animate();
        mRemoveAnimations.add(holder);
        //执行动画逻辑
        animation.translationX(view.getWidth());
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
