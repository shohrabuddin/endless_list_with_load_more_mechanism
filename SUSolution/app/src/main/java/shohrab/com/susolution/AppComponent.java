package shohrab.com.susolution;

/**
 * Created by Shohrab on 4/8/2016.
 * This interface makes a link between Injected fields/methods and Module which provides dependencies
 */

import javax.inject.Singleton;

import dagger.Component;
import shohrab.com.susolution.GitHubAPI.GitHubAPIModule;
import shohrab.com.susolution.GitHubRepoList.GitHubRepoListComponent;
import shohrab.com.susolution.GitHubRepoList.GitHubRepoListModule;

@Singleton
@Component(modules = {AppModule.class, GitHubAPIModule.class})
public interface AppComponent {
    //GitHubRepoListComponent will have access to instances produced by AppModule and GitHubAPIModule
    // as it is a subcomponent of AppComponent
    GitHubRepoListComponent inject(GitHubRepoListModule module);
}
