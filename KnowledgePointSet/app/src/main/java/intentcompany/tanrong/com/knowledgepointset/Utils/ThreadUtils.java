package intentcompany.tanrong.com.knowledgepointset.Utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by admin on 2018/7/30.
 */

public class ThreadUtils {
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    // 当前是单一线程池,如需多个线程池改变接口的实现类即可
    private static Executor sExecutor = Executors.newSingleThreadExecutor();

    /**
     * 描述：运行在子线程
     */
    public static void runOnSubThread(Runnable runnable) {
        sExecutor.execute(runnable);
    }

    /**
     * 描述：运行在主线程
     */
    public static void runOnMainThread(Runnable runnable) {
        sHandler.post(runnable);
    }
}
