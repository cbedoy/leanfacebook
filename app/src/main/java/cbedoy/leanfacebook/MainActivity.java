package cbedoy.leanfacebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.login.LoginResult;

import java.util.Arrays;
import java.util.Collection;

import retrofacebook.Facebook;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private Facebook mFacebook;

    private Collection<String> mPermissions = Arrays.asList("public_profile",
            "email",
            "user_birthday",
            "user_friends",
            "user_about_me",
            "user_location");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFacebook = Facebook.create(this);

        findViewById(R.id.main_activity_start).setOnClickListener(view ->  {
            mFacebook.logInWithReadPermissions(mPermissions)
                    .flatMap(login -> mFacebook.getProfile())
                    .subscribe(profile -> {
                                Timber.d("Profile: %s", profile);
                                Timber.d("Name: %s", profile);
                                Timber.d("Email: %s", profile.email());
                            },
                            throwable -> {
                                Timber.d(throwable);

                            },
                            () -> {
                                Timber.d("Completed");
                            });
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mFacebook.onActivityResult(requestCode, resultCode, data);
    }
}
