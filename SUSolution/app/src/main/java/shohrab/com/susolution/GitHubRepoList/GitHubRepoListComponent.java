package shohrab.com.susolution.GitHubRepoList;

import dagger.Subcomponent;
import shohrab.com.susolution.ActivityScope;

/**
 * Created by Shohrab on 4/8/2016.
 * This interface makes a link between Injected fields/methods and Module which provides dependencies
 */
@ActivityScope
@Subcomponent (modules = GitHubRepoListModule.class)
public interface GitHubRepoListComponent {
    GitHubRepoListActivity inject(GitHubRepoListActivity gitHubRepoListActivity);
}
