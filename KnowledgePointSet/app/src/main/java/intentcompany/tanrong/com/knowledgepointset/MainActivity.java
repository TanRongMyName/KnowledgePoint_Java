package intentcompany.tanrong.com.knowledgepointset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;


import java.util.ArrayList;
import java.util.List;

import intentcompany.tanrong.com.knowledgepointset.Adapter.Main_Recycle_ItemAdapter;
import intentcompany.tanrong.com.knowledgepointset.GlobalData.StaticString;
import intentcompany.tanrong.com.knowledgepointset.SelfView.CatLoadingView;
import intentcompany.tanrong.com.knowledgepointset.Utils.LogUtils;
import intentcompany.tanrong.com.knowledgepointset.Utils.StringArrayToListArray;
import intentcompany.tanrong.com.knowledgepointset.Utils.ToastUtils;

public class MainActivity extends AppCompatActivity {
    public  RecyclerView recycleview;
    public List<String> list;
    public Main_Recycle_ItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏  方法一
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        //方法2
        /*android:theme="@android:style/Theme.DeviceDefault.NoActionBar
        android:theme="@android:style/Theme.NoTitleBar.Fullscreen" // 不显示应用程序标题栏，并全屏
        android:theme="Theme.Light.NoTitleBar.Fullscreen" // 白色背景，无标题栏，全屏
        android:theme="Theme.Black.NoTitleBar.Fullscreen" // 黑色背景，无标题栏，全屏
        */
        //方法三 自定义 颜色
        /*<resources>
        <style name="Theme.NoTitle_FullScreen"> <!--自定义主题名称-->
        <item name="android:windowNoTitle">true</item>
        <item name="android:windowFullscreen">true</item>
        </style>
        </resources>
        */

        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        recycleview= (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void initData() {
        //返回的是 内部继承的Arraylist
        List<String> currentList= StringArrayToListArray.stringArrayToListArray(StaticString.RecycleData);
        list=new ArrayList<>(currentList);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter=new Main_Recycle_ItemAdapter(list, this, new Main_Recycle_ItemAdapter.EventSend() {
            Intent myIntent=null;
            @Override
            public void onClickEvent(String str) {
                LogUtils.v("str==="+str);
                switch(str){
                    case "RecycleView 学习":
                        myIntent=new Intent(MainActivity.this,RecycleActivity.class);
                        break;
                    case "屏幕适配":
                        myIntent=new Intent(MainActivity.this,ScreenAdapterActivity.class);
                        break;
                    case "底部菜单栏实现":
                        break;
                    case "标题栏二级菜单实现":
                        break;
                    case "一键加速实现":
                        break;
                    case "权限适配AOP":
                        myIntent=new Intent(MainActivity.this,PermissionRequestActivity.class);
                        break;
                    case "事件分发":
                        myIntent=new Intent(MainActivity.this,EventSendActivity.class);
                        break;
                    case "Meterial design":
                        //材料设计控件   一套UI设计标准
                        myIntent=new Intent(MainActivity.this,MeterialDesignActivity.class);
                        break;
                    case "MVP 框架":
                        //MVP 框架
                        myIntent=new Intent(MainActivity.this,MVPModelACtivity.class);
                        break;
                    case "短信认证":

                        break;
                    default:
                        break;
                }
                if(myIntent!=null) {
                    startActivity(myIntent);
                    myIntent=null;
                }else{
                    ToastUtils.showToast(MainActivity.this,"点击事件但是没有触动activity");
                }
            }
        });
        recycleview.setAdapter(itemAdapter);
    }
    private void initEvent(){
//        CatLoadingView mView = new CatLoadingView();
//        mView.show(getFragmentManager(), "");
    }
}
