package intentcompany.tanrong.com.knowledgepointset;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import intentcompany.tanrong.com.knowledgepointset.Permission.annotation.Permission;
import intentcompany.tanrong.com.knowledgepointset.Permission.annotation.PermissionCancled;
import intentcompany.tanrong.com.knowledgepointset.Permission.annotation.PermissionDenied;
import intentcompany.tanrong.com.knowledgepointset.Utils.ToastUtils;

public class PermissionRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_request);
        requestPermission();
    }


    public void onBackup(View view){
        finish();
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
