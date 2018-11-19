package intentcompany.tanrong.com.knowledgepointset.Permission.core;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import intentcompany.tanrong.com.knowledgepointset.Permission.PermissionActivity;
import intentcompany.tanrong.com.knowledgepointset.Permission.PermissionUtils;
import intentcompany.tanrong.com.knowledgepointset.Permission.annotation.Permission;
import intentcompany.tanrong.com.knowledgepointset.Permission.annotation.PermissionCancled;
import intentcompany.tanrong.com.knowledgepointset.Permission.annotation.PermissionDenied;


@Aspect
public class PermissionAspect {
    private static final String TAG="PermissionAspect";

    @Pointcut("execution(@intentcompany.tanrong.com.knowledgepointset.Permission.annotation.Permission * *(..)) && @annotation(permission)")
    public void requestPermission(Permission permission){
        Log.d(TAG, "requestPermission ");
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Around("requestPermission(permission)")
    public void aroundJointPoint(final ProceedingJoinPoint joinPoint, Permission permission) throws Throwable{

        //初始化context
        Context context = null;

        final Object object = joinPoint.getThis();
        Log.d(TAG, "object.toString() "+object.toString());
        Log.d(TAG, "object instanceof Context"+(object instanceof Context));
        Log.d(TAG, "object instanceof AppCompatActivity"+(object instanceof AppCompatActivity));
        Log.d(TAG, "object instanceof Activity"+(object instanceof Activity));
        if (joinPoint.getThis() instanceof Context) {
            context = (Context) object;
        } else if (joinPoint.getThis() instanceof android.support.v4.app.Fragment) {
            context = ((android.support.v4.app.Fragment) object).getActivity();
        } else if (joinPoint.getThis() instanceof Fragment) {
            context = ((Fragment) object).getActivity();
        } else {
        }
        if (context == null || permission == null) {
            Log.d(TAG, "aroundJonitPoint error ");
            return;
        }
        final Context finalContext = context;
        PermissionActivity.requestPermission(context, permission.value(), permission.requestCode(), new IPermission() {
            @Override
            public void ganted() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void cancled() {
                PermissionUtils.invokAnnotation(object, PermissionCancled.class);
            }

            @Override
            public void denied() {
                PermissionUtils.invokAnnotation(object, PermissionDenied.class);
                PermissionUtils.goToMenu(finalContext);
            }
        });

    }

}
