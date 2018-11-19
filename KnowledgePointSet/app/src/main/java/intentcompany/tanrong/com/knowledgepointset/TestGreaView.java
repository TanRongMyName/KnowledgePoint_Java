package intentcompany.tanrong.com.knowledgepointset;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import intentcompany.tanrong.com.knowledgepointset.Aspect.AspectJAnnotation;
import intentcompany.tanrong.com.knowledgepointset.Permission.annotation.Permission;
import intentcompany.tanrong.com.knowledgepointset.Permission.annotation.PermissionCancled;
import intentcompany.tanrong.com.knowledgepointset.Permission.annotation.PermissionDenied;
import intentcompany.tanrong.com.knowledgepointset.SelfView.GearView;
import intentcompany.tanrong.com.knowledgepointset.Utils.ToastUtils;


public class TestGreaView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_grea_view);
        GearView vi= (GearView) findViewById(R.id.grearView);
        //vi.startTranslation();
        vi.startRotato(5);
        test();
        //requestPermission();
    }
    @AspectJAnnotation(value = Manifest.permission.CAMERA)
    public void test(){
        Log.i("tag00","检查权限");
    }

    @Permission(Manifest.permission.CAMERA)
    public void requestPermission(){
        //申请成功 继续执行
        Log.d("TestGreaView", "requestPermission ");
        ToastUtils.showToast(this,"requestPermission_success");
    }
    @PermissionCancled(requestCode = 200)
    public void requestCancle(){
         //申请失败 执行
        ToastUtils.showToast(this,"requestCancle");

    }
    @PermissionDenied
    public void requestDained(){
          //选中 冲不执行  需要跳转到 设置页面
        ToastUtils.showToast(this,"requestDained");

    }


}
