package intentcompany.tanrong.com.knowledgepointset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.Adapter.NormalAdapter;
import intentcompany.tanrong.com.knowledgepointset.Adapter.SimpleItemTouchCallback;
import intentcompany.tanrong.com.knowledgepointset.Model.ObjectModel;

/**
 * item 滑动事件 左右滑动消失  上下滑动 切换位置
 */
public class ItemEventActivity extends AppCompatActivity implements NormalAdapter.OnStartDragListener{
    private RecyclerView mRv;
    private NormalAdapter mAdapter;
    private ItemTouchHelper mHelper;
    private List<ObjectModel> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_event);
        initView();
        initData();
    }

    private void initData() {

        mAdapter = new NormalAdapter(mData = initData2(), this);
        mRv.setAdapter(mAdapter);
        mHelper = new ItemTouchHelper(new SimpleItemTouchCallback(mAdapter, mData));
        mHelper.attachToRecyclerView(mRv);


    }
    public ArrayList<ObjectModel> initData2(){
        ArrayList<ObjectModel> models = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.titles);
        for(int i=0;i<titles.length;i++){
            ObjectModel model = new ObjectModel();
            model.number = i + 1;
            model.title = titles[i];
            models.add(model);
        }
        return models;
    }


    private void initView(){
        mRv = (RecyclerView) findViewById(R.id.recyclerView);
        mRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void startDrag(RecyclerView.ViewHolder holder) {
        mHelper.startDrag(holder);
    }
}
