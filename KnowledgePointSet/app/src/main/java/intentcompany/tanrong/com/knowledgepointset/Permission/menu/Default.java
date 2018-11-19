package intentcompany.tanrong.com.knowledgepointset.Permission.menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import intentcompany.tanrong.com.knowledgepointset.Permission.menu.base.IMenu;


/**
 * @date 创建时间：2018/4/18
 * @description
 */

public class Default implements IMenu {

    @Override
    public Intent getMenuIntent(Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        return intent;
    }

}
