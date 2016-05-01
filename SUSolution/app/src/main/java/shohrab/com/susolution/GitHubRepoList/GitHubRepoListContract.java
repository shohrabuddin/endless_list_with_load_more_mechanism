package shohrab.com.susolution.GitHubRepoList;

import java.util.List;

import shohrab.com.susolution.GitHubAPI.Response.GitHubAPIRepoResponse;

/**
 * Created by XYZ on 4/8/2016.
 * This interface provides methods which are implemented in View(Activity) and Presenter
 */
public interface GitHubRepoListContract {

    public interface View{

        public void loadRepo(List<GitHubAPIRepoResponse> repoList);
        public void showPB(boolean isDisplay);
        public void showAlertDialog(GitHubAPIRepoResponse response);

    }

    public interface Action{

        public void listViewLongClick(GitHubAPIRepoResponse response);
        public void callRepoAPI(String userName, int page, int repo_perPage);
    }
}
