package intentcompany.tanrong.com.knowledgepointset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.Adapter.MDGridRvDividerDecoration;
import intentcompany.tanrong.com.knowledgepointset.Adapter.QuickAdapter;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.AlphaAnimatorAdapter;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.ScaleInOutItemAnimator;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.SlideInOutBottomItemAnimator;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.SlideInOutLeftItemAnimator;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.SlideInOutRightItemAnimator;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.SlideInOutTopItemAnimator;
import intentcompany.tanrong.com.knowledgepointset.Utils.StringArrayToListArray;

public class GridRecycleActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    QuickAdapter<String> mAdapter;
    public List<String> list;
    public GridLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_recycle);

        //Setup Spinner
        setupSpinner();

        //Setup RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        //mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new GridLayoutManager(this, 5);
        mRecyclerView.setLayoutManager(mLayoutManager);
        String[] testData=getResources().getStringArray(R.array.testData);
        list=new ArrayList<>(StringArrayToListArray.stringArrayToListArray(testData));
        mAdapter = new QuickAdapter<String>(list) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.main_recycle_item_layout;
            }

            @Override
            public void convert(VH holder, String data, int position) {
                holder.setText(R.id.textView,data);
            }
        };

        AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(mAdapter, mRecyclerView);
        animatorAdapter.getViewAnimator().setInitialDelayMillis(500);
        mRecyclerView.setAdapter(animatorAdapter);
        mRecyclerView.setAdapter(mAdapter);
        //添加分割线
        mRecyclerView.addItemDecoration(new MDGridRvDividerDecoration(this));
    }
    public void addResource(View view){
        mAdapter.addItemLast("新元素");
        mLayoutManager.scrollToPosition(list.size());
    }
    public void deleResource(View view){
        mAdapter.removeItemLast();
        mLayoutManager.scrollToPosition(list.size());
    }

    private void setupSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.adapters, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        mRecyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(mRecyclerView));
                        break;
                    case 1:
                        mRecyclerView.setItemAnimator(new SlideInOutRightItemAnimator(mRecyclerView));
                        break;
                    case 2:
                        mRecyclerView.setItemAnimator(new SlideInOutTopItemAnimator(mRecyclerView));
                        break;
                    case 3:
                        mRecyclerView.setItemAnimator(new SlideInOutBottomItemAnimator(mRecyclerView));
                        break;
                    case 4:
                        mRecyclerView.setItemAnimator(new ScaleInOutItemAnimator(mRecyclerView));
                        break;
                    case 5:
//                        mRecyclerView.setItemAnimator(new SlideScaleInOutRightItemAnimator(mRecyclerView));
//                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



}
