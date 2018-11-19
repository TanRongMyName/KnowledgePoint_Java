package intentcompany.tanrong.com.knowledgepointset.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class UIUtils {
    //获取系统的 dimen 文件 系统dimen 文件中 定义了很多的 参数
    private static final String DIMEN_CLASS = "com.android.internal.R$dimen";
    //参照设备的宽高
    public final float STANDARD_WIDTH=720;
    public final float STANDARD_HEIGHT=1232;
    //实际设备的宽高
    public  float displayMetricsWidth=0;
    public  float displayMetricsHeight=0;
    private static UIUtils utils ;
    private Context context;
    public static UIUtils getInstance(Context context){
        if(utils == null){
            utils = new UIUtils(context);
        }
        return utils;
    }


    //获取 实际设备的宽高
    public UIUtils(Context context){
        this.context=context;
        WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        //加载当前界面信息
        DisplayMetrics displayMetrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        //已经减去了虚拟按键的 高度
        if(displayMetricsHeight==0.0f||displayMetricsWidth==0.0f){
            int systemBarHeight=getValue(context,"system_bar_height",48);
            //宽比高大
            if(displayMetrics.widthPixels>displayMetrics.heightPixels) {

                this.displayMetricsWidth = displayMetrics.heightPixels;
                this.displayMetricsHeight = displayMetrics.widthPixels-systemBarHeight;
            }else{
                this.displayMetricsWidth = displayMetrics.widthPixels;
                this.displayMetricsHeight = displayMetrics.heightPixels-systemBarHeight;
            }
        }
    }


    //通过实际设备的宽高 对参照设备宽高进行比例换算
    public float getHorizontalScaleValue(){
        return displayMetricsWidth/STANDARD_WIDTH;
    }
    public float getVerticalScaleValue(){
        return displayMetricsHeight/STANDARD_HEIGHT;
    }

    // 给出系数


    private int getValue(Context context, String system_bar_height, int i) {


        try {
            Class<?> clazz=Class.forName(DIMEN_CLASS);
            Object r = clazz.newInstance();
            Field field=clazz.getField(system_bar_height);
            int x= (int) field.get(r);
            return context.getResources().getDimensionPixelOffset(x);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
