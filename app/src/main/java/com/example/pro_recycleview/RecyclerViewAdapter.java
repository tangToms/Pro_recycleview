package com.example.pro_recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//创建RecyclerView Adapter
public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<String> mArrayList;
    private OnItemClickListener onItemClickListener;
    public RecyclerViewAdapter(ArrayList<String> arrayList){
        mArrayList=arrayList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //获取Item页面
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.l_item,null);
        //创建ViewHolder
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        //设置控件
        TextView textView=((MyViewHolder)holder).textView;
        textView.setText(mArrayList.get(position));
        //为TextView设置点击事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    //绑定调用点击事件
                    onItemClickListener.onItemClick(holder.itemView,position);
                }
            }
        });
    }
    //获取RecyclerView中Item数量
    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    //ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        //构造方法
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv1);
        }
    }
    //设置ItemClickListener接口
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //添加Item
    public void addItem(String str,int position){
        mArrayList.add(position,str);
        //使用这种方式更新View,没有动画效果
        //notifyDataSetChanged();
        //这种会使用ItemAnimater设置动画效果
        notifyItemInserted(position);
    }
    //移除Item
    public void removeItem(int position){
        mArrayList.remove(position);
        //没有动画效果
        //notifyDataSetChanged();
        //动画效果
        notifyItemRemoved(position);
    }
}
