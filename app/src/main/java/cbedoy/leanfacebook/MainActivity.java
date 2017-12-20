package cbedoy.leanfacebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.login.LoginResult;

import retrofacebook.Facebook;
import rx.functions.Action0;
import rx.functions.Action1;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private Facebook mFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFacebook = Facebook.create(this);
        mFacebook.logIn().doOnNext(new Action1<LoginResult>() {
            @Override
            public void call(LoginResult loginResult) {

                Timber.d("Access Token %s", loginResult.getAccessToken());
                Timber.d("Denied Permissions %s", loginResult.getRecentlyDeniedPermissions());
                Timber.d("Grated Permissions %s", loginResult.getRecentlyGrantedPermissions());
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {

            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Timber.d(throwable);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mFacebook.onActivityResult(requestCode, resultCode, data);
    }
}
