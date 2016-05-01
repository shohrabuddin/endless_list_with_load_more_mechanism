package shohrab.com.susolution;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import shohrab.com.susolution.Utils.Utility;

/**
 * Created by Shohrab on 4/8/2016.
 * This class provides all application level dependencies
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Utility provideUtility(){
        return new Utility();
    }
}
