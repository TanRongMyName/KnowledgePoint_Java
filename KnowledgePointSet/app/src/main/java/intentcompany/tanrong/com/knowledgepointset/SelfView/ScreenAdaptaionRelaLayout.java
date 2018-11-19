package intentcompany.tanrong.com.knowledgepointset.SelfView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import intentcompany.tanrong.com.knowledgepointset.Utils.UIUtils;

/**
 * 自定义 适配 RelativeLayout 布局
 * 自定义 适配 RelativeLayout 布局
 */
public class ScreenAdaptaionRelaLayout extends RelativeLayout {
    public ScreenAdaptaionRelaLayout(Context context) {
        super(context);
    }

    public ScreenAdaptaionRelaLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdaptaionRelaLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    static boolean isFlag=true;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(isFlag){
            int count = this.getChildCount();
            float scaleX =  UIUtils.getInstance(this.getContext()).getHorizontalScaleValue();
            float scaleY =  UIUtils.getInstance(this.getContext()).getVerticalScaleValue();
            for (int i = 0;i < count;i++){
                View child = this.getChildAt(i);

                //代表的是当前空间的所有属性列表
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                layoutParams.width = (int) (layoutParams.width * scaleX);
                layoutParams.height = (int) (layoutParams.height * scaleY);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
                layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
            }
            isFlag = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
