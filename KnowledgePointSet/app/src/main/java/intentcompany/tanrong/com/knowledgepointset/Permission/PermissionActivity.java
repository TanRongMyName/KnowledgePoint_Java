package intentcompany.tanrong.com.knowledgepointset.Permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import intentcompany.tanrong.com.knowledgepointset.Permission.core.IPermission;
import intentcompany.tanrong.com.knowledgepointset.R;


//透明无感知的activity   切入后真是实现权限申请的类
public class PermissionActivity extends Activity {
    private static IPermission permissionListener;
    private String[] mPermissions;
    private int mRequestCode;
    private static final String PARAM_PERMISSION="PARAM_PERMISSION";
    private static final String PARAM_REQUEST_CODE="PARAM_REQUEST_CODE";

    public static void  requestPermission(Context context, String[] permissions, int requestCode, IPermission iPermission){
        permissionListener=iPermission;
        Intent intent=new Intent(context,PermissionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle=new Bundle();
        bundle.putStringArray(PARAM_PERMISSION,permissions);
        bundle.putInt(PARAM_REQUEST_CODE,requestCode);
        intent.putExtras(bundle);

        context.startActivity(intent);
        if(context instanceof  Activity){
            ((Activity) context).overridePendingTransition(0,0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
       this.mPermissions=getIntent().getStringArrayExtra(PARAM_PERMISSION);
       this.mRequestCode=getIntent().getIntExtra(PARAM_REQUEST_CODE,0);
       if(mPermissions==null||permissionListener==null){
           this.finish();
           return;
       }
       //权限检查
        if(PermissionUtils.hasPermission(this,mPermissions)){
           permissionListener.ganted();
           finish();
           return;
        }
        // 权限 申请
        ActivityCompat.requestPermissions(this,this.mPermissions,this.mRequestCode);
        Log.d(PARAM_PERMISSION,"PermissionActivity");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //权限请求成功
        if(PermissionUtils.verifyPermission(this,grantResults)){
            permissionListener.ganted();
            finish();
            return;
        }
        //用户点击了 不再提示 提示UI  跳转到设置
        if(!PermissionUtils.shouIdShowRequestPermissionRationale(this,permissions)){
            permissionListener.denied();
            finish();
            return ;
        }
        //取消
        permissionListener.cancled();
        finish();
        //权限请求失败

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}
