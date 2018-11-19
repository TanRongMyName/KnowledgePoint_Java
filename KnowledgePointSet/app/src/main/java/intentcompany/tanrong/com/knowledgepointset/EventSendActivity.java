package intentcompany.tanrong.com.knowledgepointset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.DispatchTouchEvent.DividerItemDecoration;
import intentcompany.tanrong.com.knowledgepointset.DispatchTouchEvent.ItemTouchHelpCallback;
import intentcompany.tanrong.com.knowledgepointset.DispatchTouchEvent.MainRecyclerAdapter;
import intentcompany.tanrong.com.knowledgepointset.DispatchTouchEvent.TestModel;
import intentcompany.tanrong.com.knowledgepointset.DispatchTouchEvent.itemTouchHelper.ItemTouchHelper;
import intentcompany.tanrong.com.knowledgepointset.Utils.LogUtils;

public class EventSendActivity extends AppCompatActivity {
    private boolean isSend=true;
    private Button mybutton;
    private RecyclerView mRecyclerView;
    private MainRecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_send);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mAdapter.updateData(createTestDatas());

        ItemTouchHelpCallback callback = new ItemTouchHelpCallback();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }
    private List<TestModel> createTestDatas() {
        List<TestModel> result = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestModel testModel= new TestModel(i,":Item Swipe Action Button Container Width");
            if (i == 1) {
                testModel = new TestModel(i, "Item Swipe with Action container width and no spring");
            }
            if (i == 2) {
                testModel = new TestModel(i, "Item Swipe with RecyclerView Width");
            }
            result.add(testModel);
        }
        return result;
    }
    public void onBackup(View view){
        finish();
    }
    public void initEvent(){
        //mybutton=findViewById(R.id.event_dispathch);
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSend=!isSend;
                if(isSend) {
                    mybutton.setText("拦截事件");
                }else{
                    mybutton.setText("不拦截事件");
                }
                LogUtils.d("isSend=="+isSend);
            }
        });
        mybutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // return false;
                view.performClick();//实现点击事件
                return true;
            }
        });
    }




}
