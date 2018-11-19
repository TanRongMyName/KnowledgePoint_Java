package intentcompany.tanrong.com.knowledgepointset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.Adapter.NormalAdapterWrapper;
import intentcompany.tanrong.com.knowledgepointset.Adapter.QuickAdapter;

import intentcompany.tanrong.com.knowledgepointset.GlobalData.StaticString;
import intentcompany.tanrong.com.knowledgepointset.Utils.StringArrayToListArray;
import intentcompany.tanrong.com.knowledgepointset.Utils.ToastUtils;
//讲解了 不同的Recycle 布局  同时Recycle 的上拉加载 下拉刷新 动画效果  分割线 同时还有 添加标题 和底部

public class RecycleActivity extends AppCompatActivity implements View.OnClickListener {
    public  RecyclerView recycleview;
    public List<String> list;
    //适配器
    public QuickAdapter<String>itemAdapter;
    //布局管理器
    public LinearLayoutManager mLayoutManager;
    //动画实现
    public RecyclerView.ItemAnimator myitemAnimator;
    //分割线添加
    public RecyclerView.ItemDecoration myItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recycle);
        initView();
        initData();
        initEvent();

    }
    private void initView() {
        recycleview= (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void initData() {
        List<String> currentList= StringArrayToListArray.stringArrayToListArray(StaticString.RecyViewData);
        list=new ArrayList<>(currentList);
        mLayoutManager=new LinearLayoutManager(this);
        recycleview.setLayoutManager(mLayoutManager);
        itemAdapter= new QuickAdapter<String>(list) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.main_recycle_item_layout;
            }

            @Override
            public void convert(QuickAdapter.VH holder, String data, int position) {
                  TextView tv=holder.setText(R.id.textView,data);
                  tv.setOnClickListener(RecycleActivity.this);
            }
        };
        recycleview.setAdapter(itemAdapter);
        //myitemAnimator=new DefaultItemAnimator();//默认动画  如果没有特殊效果使用

//        RecyclerView的四大组成是：
//        Layout Manager：Item的布局。
//        Adapter：为Item提供数据。
//        Item Decoration：Item之间的Divider。
//        Item Animator：添加、删除Item动画。

    }
    private void initEvent(){

    }
    //点击事件  触发
    @Override
    public void onClick(View v) {
        Intent intent;
        switch(((TextView)v).getText().toString()){
            case "线性布局":
                //跳转到下一个activity
                intent=new Intent(RecycleActivity.this,LineRecycleActivity.class);
                startActivity(intent);
                break;
            case "网格布局":
                //跳转到下一个activity
                intent=new Intent(RecycleActivity.this,GridRecycleActivity.class);
                startActivity(intent);
                break;
            case "流式布局":
                intent=new Intent(RecycleActivity.this,StaggeredGridlayoutActivity.class);
                startActivity(intent);
                //跳转到下一个activity
                break;
            case "标题添加":
                list.add("a");list.add("a");list.add("a");list.add("a");list.add("a");list.add("a");list.add("a");list.add("a");
                list.add("a");list.add("a");list.add("a");list.add("a");list.add("a");list.add("a");list.add("a");list.add("a");
                NormalAdapterWrapper newAdapter = new NormalAdapterWrapper(itemAdapter);
                newAdapter.setOnloadMoreListener(new NormalAdapterWrapper.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        ToastUtils.showToast(RecycleActivity.this,"加载更多");//异步实现动态加载数据
                    }
                },recycleview);
                View headerView = LayoutInflater.from(this).inflate(R.layout.header_view, recycleview, false);
                View footerView = LayoutInflater.from(this).inflate(R.layout.footer_view, recycleview, false);
                newAdapter.addFooterView(footerView);
                newAdapter.addHeaderView(headerView);
                recycleview.setAdapter(newAdapter);
                break;
            case "空的RecycleView":
                intent =new Intent(RecycleActivity.this,EmptyRecycleActivity.class);
                startActivity(intent);
                break;
            case "item滑动事件":
                intent=new Intent(RecycleActivity.this,ItemEventActivity.class);
                startActivity(intent);
                break;
            case "嵌套滑动机制":
                intent=new Intent(RecycleActivity.this,NestedSlidingMechanismActivity.class);
                startActivity(intent);
                break;
            case "弹幕的使用":
                intent=new Intent(RecycleActivity.this,DanMuRecycle.class);
                startActivity(intent);
                break;
            case "多个布局类型显示":
                intent=new Intent(RecycleActivity.this,MultiItemRvActivity.class);
                startActivity(intent);
                break;
            case "多个RecycleView滑动事件冲突":
                intent=new Intent(RecycleActivity.this,RecycleEventCrashActivity.class);
                startActivity(intent);
                break;
            case "下拉刷新":
                break;
            case "back":
                RecycleActivity.this.finish();
                break;

            default:
                ToastUtils.showToast(this,"点击了未知控件");
                break;
        }

    }
    //需要自己去解决的问题
    //StaggeredGridLayoutManager

    //知识点 RecyclerView的四大组成是：

//    Layout Manager：Item的布局。
//    Adapter：为Item提供数据。
//    Item Decoration：Item之间的Divider。
//    Item Animator：添加、删除Item动画。

    //关于 LayoutManager 的使用有下面一些常见的 API（有些在 LayoutManager 实现的子类中）
//    canScrollHorizontally();//能否横向滚动
//    canScrollVertically();//能否纵向滚动
//    scrollToPosition(int position);//滚动到指定位置
//
//    setOrientation(int orientation);//设置滚动的方向
//    getOrientation();//获取滚动方向
//
//    findViewByPosition(int position);//获取指定位置的Item View
//    findFirstCompletelyVisibleItemPosition();//获取第一个完全可见的Item位置
//    findFirstVisibleItemPosition();//获取第一个可见Item的位置
//    findLastCompletelyVisibleItemPosition();//获取最后一个完全可见的Item位置
//    findLastVisibleItemPosition();//获取最后一个可见Item的位置

    /*Item Decoration间隔样式

    RecyclerView通过addItemDecoration()方法添加item之间的分割线。Android并没有提供实现好的Divider，因此任何分割线样式都需要自己实现。

    自定义间隔样式需要继承RecyclerView.ItemDecoration类，该类是个抽象类，官方目前并没有提供默认的实现类，主要有三个方法。

    onDraw(Canvas c, RecyclerView parent, State state)，在Item绘制之前被调用，该方法主要用于绘制间隔样式。
    onDrawOver(Canvas c, RecyclerView parent, State state)，在Item绘制之前被调用，该方法主要用于绘制间隔样式。
    getItemOffsets(Rect outRect, View view, RecyclerView parent, State state)，设置item的偏移量，偏移的部分用于填充间隔样式，
    即设置分割线的宽、高；在RecyclerView的onMesure()中会调用该方法。
    onDraw()和onDrawOver()这两个方法都是用于绘制间隔样式，我们只需要复写其中一个方法即可。*/

    /*Item Animator动画

    RecyclerView能够通过mRecyclerView.setItemAnimator(ItemAnimator animator)设置添加、删除、移动、改变的动画效果。

    RecyclerView提供了默认的ItemAnimator实现类：DefaultItemAnimator。如果没有特殊的需求，默认使用这个动画即可。

            // 设置Item添加和移除的动画
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    下面就添加一下删除和添加Item的动作。在Adapter里面添加方法。*/
    /* DefaultItemAnimator源码分析

    这里我们通过分析DefaultItemAnimator的源码来介绍如何自定义Item Animator。

    DefaultItemAnimator继承自SimpleItemAnimator，SimpleItemAnimator继承自ItemAnimator。

    首先我们介绍ItemAnimator类的几个重要方法：

    animateAppearance(): 当ViewHolder出现在屏幕上时被调用（可能是add或move）。
    animateDisappearance(): 当ViewHolder消失在屏幕上时被调用（可能是remove或move）。
    animatePersistence(): 在没调用notifyItemChanged()和notifyDataSetChanged()的情况下布局发生改变时被调用。
    animateChange(): 在显式调用notifyItemChanged()或notifyDataSetChanged()时被调用。
    runPendingAnimations(): RecyclerView动画的执行方式并不是立即执行，而是每帧执行一次，比如两帧之间添加了多个Item，则会将这些将要执行的动画Pending住，保存在成员变量中，等到下一帧一起执行。该方法执行的前提是前面animateXxx()返回true。
    isRunning(): 是否有动画要执行或正在执行。
    dispatchAnimationsFinished(): 当全部动画执行完毕时被调用。
    上面的方法比较难懂，不过没关系，因为Android提供了SimpleItemAnimator类（继承自ItemAnimator），该类提供了一系列更易懂的API，在自定义Item Animator时只需要继承SimpleItemAnimator即可：

    animateAdd(ViewHolder holder): 当Item添加时被调用。
    animateMove(ViewHolder holder, int fromX, int fromY, int toX, int toY): 当Item移动时被调用。
    animateRemove(ViewHolder holder): 当Item删除时被调用。
    animateChange(ViewHolder oldHolder, ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop):
    当显式调用notifyItemChanged()或notifyDataSetChanged()时被调用。
    对于以上四个方法，注意两点：

    当Xxx动画开始执行前（在runPendingAnimations()中）需要调用dispatchXxxStarting(holder)，执行完后需要调用dispatchXxxFinished(holder)。
    这些方法的内部实际上并不是书写执行动画的代码，而是将需要执行动画的Item全部存入成员变量中，并且返回值为true，
    然后在runPendingAnimations()中一并执行。*/







}
