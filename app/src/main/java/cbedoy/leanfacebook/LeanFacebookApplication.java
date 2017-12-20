package cbedoy.leanfacebook;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * LeanFacebook
 * <p>
 * Created by bedoy on 12/20/17.
 */

public class LeanFacebookApplication extends MultiDexApplication {
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
    }
}
