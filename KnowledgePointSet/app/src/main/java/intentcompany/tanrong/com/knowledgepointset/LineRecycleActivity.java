package intentcompany.tanrong.com.knowledgepointset;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.Adapter.QuickAdapter;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.AlphaAnimatorAdapter;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.ScaleInAnimatorAdapter;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.SlideInBottomAnimatorAdapter;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.SlideInLeftAnimatorAdapter;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.SlideInRightAnimatorAdapter;
import intentcompany.tanrong.com.knowledgepointset.Animator.otherAnimator.SwingBottomInAnimationAdapter;
import intentcompany.tanrong.com.knowledgepointset.GlobalData.StaticString;
import intentcompany.tanrong.com.knowledgepointset.R;
import intentcompany.tanrong.com.knowledgepointset.Utils.StringArrayToListArray;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class LineRecycleActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    protected QuickAdapter<String> mAdapter;
    public List<String> list;
    public LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_recycle);
        setupSpinner();
        setupSpinner2();

        //Setup RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        //mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
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
        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecyclerView.setAdapter(animatorAdapter);
        //添加分割线 简单的
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //第二种添加分割线
        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.custom_divider));
        mRecyclerView.addItemDecoration(divider);
    }

    public void addResource(View view){
        mAdapter.addItemLast("新元素");
        mLayoutManager.scrollToPosition(list.size());
    }
    public void deleResource(View view){
        mAdapter.removeItemLast();
        mLayoutManager.scrollToPosition(list.size());
    }
    //第一种动画 第二种动画
    private void setupSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.adapters, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(mAdapter,mRecyclerView);
                        mRecyclerView.setAdapter(animatorAdapter);
                        break;
                    case 1:
                        SlideInLeftAnimatorAdapter slideInLeftAnimationRecyclerViewAdapter = new SlideInLeftAnimatorAdapter(mAdapter, mRecyclerView);
                        mRecyclerView.setAdapter(slideInLeftAnimationRecyclerViewAdapter);
                        break;
                    case 2:
                        SlideInRightAnimatorAdapter slideInRightAnimatorAdapter = new SlideInRightAnimatorAdapter(mAdapter, mRecyclerView);
                        mRecyclerView.setAdapter(slideInRightAnimatorAdapter);
                        break;
                    case 3:
                        SlideInBottomAnimatorAdapter slideInBottomAnimatorAdapter= new SlideInBottomAnimatorAdapter(mAdapter, mRecyclerView);
                        mRecyclerView.setAdapter(slideInBottomAnimatorAdapter);
                        break;
                    case 4:
                        ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mAdapter, mRecyclerView);
                        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);
                        break;
                    case 5:
//                        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(mAdapter, mRecyclerView);
//                        mRecyclerView.setAdapter(swingBottomInAnimationAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    //采用对第三方的recycle-animation lib 使用
    //https://www.jianshu.com/p/0292bf221966?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
    //简书的地址
    //同时可以在 adapter 中实现动画效果
    private void setupSpinner2() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.adapters, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        AlphaInAnimationAdapter  animatorAdapter = new AlphaInAnimationAdapter(mAdapter);
                        animatorAdapter.setDuration(1000);
                        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
                        mRecyclerView.setAdapter(animatorAdapter);
                        break;
                    case 1:
                        SlideInLeftAnimationAdapter slideinleft = new SlideInLeftAnimationAdapter(mAdapter);
                        slideinleft.setDuration(1000);
                        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
                        mRecyclerView.setAdapter(slideinleft);
                        break;
                    case 2:
                        SlideInRightAnimationAdapter slideinright = new SlideInRightAnimationAdapter(mAdapter);
                        slideinright.setDuration(1000);
                        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
                        mRecyclerView.setAdapter(slideinright);
                        break;
                    case 3:
                        SlideInBottomAnimationAdapter slideinbuttom = new SlideInBottomAnimationAdapter(mAdapter);
                        slideinbuttom.setDuration(1000);
                        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
                        mRecyclerView.setAdapter(slideinbuttom);
                        break;
                    case 4:
                        ScaleInAnimationAdapter scal = new ScaleInAnimationAdapter(mAdapter);
                        scal.setDuration(1000);
                        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
                        mRecyclerView.setAdapter(scal);

                        break;
                    case 5:
//                        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(mAdapter, mRecyclerView);
//                        mRecyclerView.setAdapter(swingBottomInAnimationAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //第一种动画 第二种动画
    //这种事应用 recycleview-animators的使用
//    static class MyViewHolder extends RecyclerView.ViewHolder implements AnimateViewHolder {
//        public MyViewHolder(View itemView) {
//            super(itemView);
//        }
//
//        @Override
//        public void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {
//
//        }
//
//        @Override
//        public void animateRemoveImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
//            ViewCompat.animate(itemView)
//                    .translationY(-itemView.getHeight() * 0.3f)
//                    .alpha(0)
//                    .setDuration(300)
//                    .setListener(listener)
//                    .start();
//        }
//
//        @Override
//        public void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
//            ViewCompat.setTranslationY(itemView, -itemView.getHeight() * 0.3f);
//            ViewCompat.setAlpha(itemView, 0);
//        }
//
//        @Override
//        public void animateAddImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
//            ViewCompat.animate(itemView)
//                    .translationY(0)
//                    .alpha(1)
//                    .setDuration(300)
//                    .setListener(listener)
//                    .start();
//        }
//    }
}
