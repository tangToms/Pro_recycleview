package com.example.pro_recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //上下文
    private Context mContext;
    private RecyclerView recyclerView;
    //按钮
    private Button button_add;
    private Button button_del;
    //显示list
    ArrayList<String> arrayList;
    //RecycleViewAdpater
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=MainActivity.this;
        recyclerView=findViewById(R.id.recyclerview1);
        button_add=findViewById(R.id.btn_add);
        button_del=findViewById(R.id.btn_del);
        //设置RecyclerView
        setRecyclerView();

        //设置按钮点击事件
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加Item
                recyclerViewAdapter.addItem("new Item",1);
            }
        });
        button_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除一个item
                recyclerViewAdapter.removeItem(1);
            }
        });
    }

    //设置recyclerview
    private void setRecyclerView(){
        //创建显示数据
        arrayList=new ArrayList<>();
        for(int i=0;i<10;i++){
            arrayList.add("Item"+i);
        }
        //创建适配adpter对象
        recyclerViewAdapter=new RecyclerViewAdapter(arrayList);
        //设置item点击事件
        recyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"点击了"+position,Toast.LENGTH_LONG).show();
            }
        });
        //设置适配器Adpter
        recyclerView.setAdapter(recyclerViewAdapter);
        //创建自定义布局layoutManager
        RecyclerView.LayoutManager manager=new MyLayoutManager();
        //使用默认线性布局
        manager=new LinearLayoutManager(mContext);
        //使用网格布局,参数1 context:上下文;参数2 spanCount：网格列数
        // manager=new GridLayoutManager(mContext,3);
        //使用网格布局，参数3：网格布局水平或竖直方向；参数4 reverseLayout：是否反向布局
        //manager=new GridLayoutManager(mContext,3,GridLayoutManager.HORIZONTAL,true);
        //瀑布流布局
        //manager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
        //设置布局管理器
        recyclerView.setLayoutManager(manager);
        //创建自定义动画对象
        //平移动画
        //RecyclerView.ItemAnimator animator=new TransXAnimator();
        //旋转动画
        RecyclerView.ItemAnimator animator=new RotationXAnimator();
        animator.setAddDuration(2000);
        animator.setRemoveDuration(2000);
        //使用默认动画
        //animator=new DefaultItemAnimator();
        //设置RecycleView Item动画
        recyclerView.setItemAnimator(animator);
        //创建自定义边线
        RecyclerView.ItemDecoration itemDecoration=new MyItemDecoration();
        //设置Recycle View 分界线
        recyclerView.addItemDecoration(itemDecoration);
    }
}
