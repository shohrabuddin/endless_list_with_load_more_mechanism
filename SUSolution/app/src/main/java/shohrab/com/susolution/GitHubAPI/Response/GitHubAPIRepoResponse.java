package shohrab.com.susolution.GitHubAPI.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shohrab on 4/8/2016.
 * POJO class for GithubRepository API response
 */
public class GitHubAPIRepoResponse {
    @SerializedName("name")
    String repoName;
    @SerializedName("description")
    String repoDescription;
    @SerializedName("html_url")
    String repoHTMLURL;
    @SerializedName("fork")
    boolean isForked;
    @SerializedName("owner")
    GitHubAPIOwnerResponse ownerResponse;


    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoDescription() {
        return repoDescription;
    }

    public void setRepoDescription(String repoDescription) {
        this.repoDescription = repoDescription;
    }

    public GitHubAPIOwnerResponse getOwnerResponse() {
        return ownerResponse;
    }

    public void setOwnerResponse(GitHubAPIOwnerResponse ownerResponse) {
        this.ownerResponse = ownerResponse;
    }

    public String getRepoHTMLURL() {
        return repoHTMLURL;
    }

    public void setRepoHTMLURL(String repoHTMLURL) {
        this.repoHTMLURL = repoHTMLURL;
    }

    public boolean isForked() {
        return isForked;
    }

    public void setForked(boolean forked) {
        isForked = forked;
    }
}
