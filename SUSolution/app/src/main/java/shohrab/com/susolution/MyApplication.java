package shohrab.com.susolution;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Shohrab on 4/8/2016.
 */
public class MyApplication extends Application {
    private AppComponent mAppComponent;

    public static MyApplication get(Context context){
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getmAppComponent (){return mAppComponent;}

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
