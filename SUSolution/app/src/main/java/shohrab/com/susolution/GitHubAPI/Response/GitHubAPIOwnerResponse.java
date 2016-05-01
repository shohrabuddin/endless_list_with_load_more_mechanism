package shohrab.com.susolution.GitHubAPI.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shohrab on 4/8/2016.
 * POJO class for GithubOwner response
 */
public class GitHubAPIOwnerResponse {
    @SerializedName("login")
    String gitHubLogin;
    @SerializedName("html_url")
    String ownerHMLURL;

    public String getGitHubLogin() {
        return gitHubLogin;
    }

    public void setGitHubLogin(String gitHubLogin) {
        this.gitHubLogin = gitHubLogin;
    }

    public String getOwnerHMLURL() {
        return ownerHMLURL;
    }

    public void setOwnerHMLURL(String ownerHMLURL) {
        this.ownerHMLURL = ownerHMLURL;
    }
}
