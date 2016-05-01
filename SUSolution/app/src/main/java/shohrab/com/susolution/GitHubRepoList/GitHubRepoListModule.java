package shohrab.com.susolution.GitHubRepoList;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import shohrab.com.susolution.ActivityScope;
import shohrab.com.susolution.GitHubAPI.GitHubAPIServices;
import shohrab.com.susolution.Utils.Utility;

/**
 * Created by Shohrab on 4/8/2016.
 * This class provides activity level dependencies
 */
@Module
public class GitHubRepoListModule {

    private GitHubRepoListActivity mGitHubRepoListActivity;

    public GitHubRepoListModule(GitHubRepoListActivity gitHubRepoListActivity) {
        mGitHubRepoListActivity = gitHubRepoListActivity;
    }

    @Provides
    @ActivityScope
    GitHubRepoListActivity provideGitHubRepoListActivity(){
        return mGitHubRepoListActivity;
    }

    @Provides
    @ActivityScope
    GitHubRepoListPresenter provideGitHubRepoListPresenter(Application application, Utility utility,
                                                           GitHubAPIServices apiServices,
                                                           GitHubRepoListActivity gitHubRepoListActivity){

        return new GitHubRepoListPresenter(gitHubRepoListActivity,apiServices, utility,application);

    }

}
