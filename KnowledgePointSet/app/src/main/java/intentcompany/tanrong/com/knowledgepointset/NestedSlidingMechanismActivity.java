package intentcompany.tanrong.com.knowledgepointset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.Adapter.QuickAdapter;
import intentcompany.tanrong.com.knowledgepointset.Utils.ToastUtils;
/*嵌套滑动机制
        Android 5.0推出了嵌套滑动机制，在之前，一旦子View处理了触摸事件，
        父View就没有机会再处理这次的触摸事件，而嵌套滑动机制解决了这个问题，能够实现如下效果：*/
/**
 * RecyclerView实现瀑布流
 *
 * 由于该例子比较简单，因此顺便介绍万能Adapter方案
 */
public class NestedSlidingMechanismActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private QuickAdapter<Integer> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_sliding_mechanism);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(NestedSlidingMechanismActivity.this,"点击了toolbar");
            }
        });

        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new QuickAdapter<Integer>(initData()) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_nestedslidingme;
            }

            @Override
            public void convert(VH holder, Integer data, int position) {
                ImageView imageView = holder.getView(R.id.image);
                Picasso.with(NestedSlidingMechanismActivity.this).load(data).into(imageView);
                //holder.itemView.setOnClickListener();  此处添加点击事件
            }

            @Override
            public int getItemViewType(int position) {
                return super.getItemViewType(position);
            }
        };
        mAdapter.setHasStableIds(true);
        ((SimpleItemAnimator)mRv.getItemAnimator()).setSupportsChangeAnimations(false);
        mRv.setAdapter(mAdapter);

    }

    public List<Integer> initData(){
        Integer[] images = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
                R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10
        };
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<2;i++){
            for(Integer image:images){
                list.add(image);
            }
        }
        return list;
    }
}

        /*为了支持嵌套滑动，子View必须实现NestedScrollingChild接口，父View必须实现NestedScrollingParent接口。
        而RecyclerView实现了NestedScrollingChild接口，而CoordinatorLayout实现了NestedScrollingParent接口，
        上图是实现CoordinatorLayout嵌套RecyclerView的效果。

        为了实现上图的效果，需要用到的组件有：

        CoordinatorLayout: 布局根元素。
        AppBarLayout: 包裹的内容作为应用的Bar。
        CollapsingToolbarLayout: 实现可折叠的ToolBar。
        ToolBar: 代替ActionBar。
        实现中需要注意的点有：

        我们为ToolBar的app:layout_collapseMode设置为pin，表示折叠之后固定在顶端，而为ImageView的app:layout_collapseMode设置为parallax，
                表示视差模式，即渐变的效果。
        为了让RecyclerView支持嵌套滑动，还需要为它设置app:layout_behavior="@string/appbar_scrolling_view_behavior"。
        为CollapsingToolbarLayout设置app:layout_scrollFlags="scroll|exitUntilCollapsed"，其中scroll表示滚动出屏幕，
                exitUntilCollapsed表示退出后折叠。*/

