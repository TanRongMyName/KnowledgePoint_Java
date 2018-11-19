package intentcompany.tanrong.com.knowledgepointset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.Adapter.MDGridRvDividerDecoration;
import intentcompany.tanrong.com.knowledgepointset.Adapter.MDStaggeredRvAdapter;
import intentcompany.tanrong.com.knowledgepointset.Adapter.QuickAdapter;
import intentcompany.tanrong.com.knowledgepointset.Utils.StringArrayToListArray;

public class StaggeredGridlayoutActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MDStaggeredRvAdapter myadpter;
    private ArrayList<String> list;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_gridlayout);
        initView();
        initData();
    }

    private void initData() {
        String[] testData=getResources().getStringArray(R.array.testData);
        list=new ArrayList<>(StringArrayToListArray.stringArrayToListArray(testData));
        myadpter=new MDStaggeredRvAdapter(list);
        // 初始化布局管理器
        mLayoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(myadpter);
        // 设置间隔样式
        mRecyclerView.addItemDecoration(new MDGridRvDividerDecoration(this));

    }
    private void initView(){
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
    }
}
