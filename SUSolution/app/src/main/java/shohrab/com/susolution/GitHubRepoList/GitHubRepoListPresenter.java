package shohrab.com.susolution.GitHubRepoList;

import android.app.Application;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import shohrab.com.susolution.GitHubAPI.GitHubAPIServices;
import shohrab.com.susolution.GitHubAPI.Response.GitHubAPIRepoResponse;
import shohrab.com.susolution.Utils.Utility;

/**
 * Created by XYZ on 4/8/2016.
 * This class implements all actions of View class and update data for views
 */
public class GitHubRepoListPresenter implements GitHubRepoListContract.Action{


    private GitHubRepoListContract.View mView;
    private GitHubAPIServices mApiServices;
    private Utility mUtiliy;
    private Application mApplication;

    public GitHubRepoListPresenter(GitHubRepoListContract.View view,
                                   GitHubAPIServices apiServices, Utility utility,
                                   Application application) {
        mView = view;
        mApiServices = apiServices;
        mUtiliy = utility;
        mApplication = application;
    }



    @Override
    public void listViewLongClick(GitHubAPIRepoResponse response) {
        mView.showAlertDialog(response);
    }

    @Override
    public void callRepoAPI(String userName, int page, int repo_perPage) {
        mView.showPB(true);

        mApiServices.getRepositoryList(userName, page, repo_perPage)
                .subscribeOn(Schedulers.newThread())//observable is running in a different thread
                .observeOn(AndroidSchedulers.mainThread())//observer observes this observable on UI thread. without this we can not call any view (activity) methods on onNext()
                .subscribe(new Observer<List<GitHubAPIRepoResponse>>() {// When an Observer subscribes to an Observable a Subscription is created. Observable doesn't emmit any data until an observer subscribes to it
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showPB(false);
                    }

                    @Override
                    public void onNext(List<GitHubAPIRepoResponse> gitHubAPIRepoResponse) {
                        mView.showPB(false); // we can call view methods because observer observes the observable on UI thread
                        mView.loadRepo(gitHubAPIRepoResponse);

                    }
                });
    }

}
