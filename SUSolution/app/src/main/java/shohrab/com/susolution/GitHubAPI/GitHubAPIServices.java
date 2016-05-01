package shohrab.com.susolution.GitHubAPI;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;
import shohrab.com.susolution.GitHubAPI.Response.GitHubAPIRepoResponse;

/**
 * Created by Shohrab on 4/8/2016.
 * This interface makes Github API call for a particular user and return data into an observable list
 */
public interface GitHubAPIServices {
    @GET("/users/{username}/repos")
    Observable<List<GitHubAPIRepoResponse>> getRepositoryList(
            @Path("username") String username,
            @Query("page") int pageNumber,
            @Query("per_page") int repoPerPage
    );
}
