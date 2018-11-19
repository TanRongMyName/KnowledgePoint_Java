package intentcompany.tanrong.com.knowledgepointset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.Adapter.NormalAdapter;
import intentcompany.tanrong.com.knowledgepointset.Adapter.NormalAdapterEmptey;
import intentcompany.tanrong.com.knowledgepointset.Adapter.QuickAdapter;
import intentcompany.tanrong.com.knowledgepointset.Model.ObjectModel;
import intentcompany.tanrong.com.knowledgepointset.R;
import intentcompany.tanrong.com.knowledgepointset.SelfView.EmptyRecyclerView;
import intentcompany.tanrong.com.knowledgepointset.Utils.LogUtils;

public class EmptyRecycleActivity extends AppCompatActivity {

    private List<ObjectModel> mData;
    private EmptyRecyclerView mRv;
    NormalAdapterEmptey mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_recycle);
        mRv = (EmptyRecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mAdapter = new NormalAdapterEmptey(mData);
        //View view = LayoutInflater.from(this).inflate(R.layout.empty, null);
        View view = findViewById(R.id.text_empty);
        mRv.setEmptyView(view);
        mRv.setAdapter(mAdapter);

    }
}
