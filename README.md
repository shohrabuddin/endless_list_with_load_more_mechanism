# Project Description

The main purpose of this project is to learn how to load data into a list view while users scroll down. I mean instead of fetching huge amount of data at once from a remote API small amount of data should be downloaded to fill out the list view and the subsequent data should be fetched from remote API while users scroll down gradually. This technique would be useful in the following scenarios:

- if API returns huge amount of data
- if you do not want your user to wait long time to laod this huge amount of data at once
- if you want to save user's data (internet) volume
- if you do not want to load data from cache

# Project Goals

1. Request the GitHub API to show [XING's public repositories] and parse the JSON
   response.
2. Display a list of repositories, each entry should show
    - repo name
    - description
    - login of the owner
    
3. Request only 10 repos at a time. Use an endless list with a load more mechanism. The
   load more should be triggered when the scrolling is close to reaching the end of the
list. __This is the main goal of this project.__

4. Show a light green background if the `fork` flag is false or missing, a white one
   otherwise.

5. On a long click on a list item show a dialog to ask if go to repository `html_url` or
   owner `html_url` which is opened then in the browser.

# Pagination

In order to achieve our goal 3, the API should have pagination feature. Let's understand what does pagination mean in terms of data query. Assume that you are requesting github API which will return list of repositories of a user. If a user has 300 repositories (just imagine) then it is not a good idea to fetch all 300 repos at once. So there should be some kinds of mechanism so that you can specifiy how many repositories you would like to fetch per API call. Let's say you want 20 repositories per request. In this case total number of pages will be 300/20=15. There could be an option to specifiy page number along with number of items per page. This technique is called pagenation. Github API provides pagination feature. The API URL to get repository list of a user is 'https://api.github.com/user/[userid]/repos' and for the pagination feature the URL looks like this 'https://api.github.com/user/[userid]/repos?page=2&per_page=20'. 

You can learn about github pagination API from https://developer.github.com/v3/#pagination.


# Libraries

I have used the following third party libraries:
RxAndroid, Retrofit, OkHttp, Dagger, ButterKnife, javax.annotations


# Java Class to handle Scroll Listenter of ListView

We have to create a seperate class which will handle the scroll listener of list view. This class implements the AbsListView.OnScrollListener interface. We will call the API each time when users scroll down to the list view and if the minimum number of items to be displayed in the current scoll position is 3. Let's make it more clear. For example you request the API by requesting 20 items per page. Asume that your phone can display 10 items at a time within it's screen size. So when you scroll down and 17th item is shown in the list view then we have to call the API again to fetch the next 20 items. And it will continue this process while user will continue scrolling down and down. Hope it is clear to you now.

I have created the following class to handle list view scroll listener

```java

    public abstract class ListViewEndlessScrollListeter implements AbsListView.OnScrollListener {

    // The minimum amount of items to have below the current scroll position
    // before loading more.
    private int mVisibleThreshold = 3;
    // The current offset index of data has loaded
    private int mCurrentPage = 0;
    // The total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;
    // True if we are still wait for the last set of data to load
    private boolean mLoading = true;
    // Sets the starting page index
    private int mStartingPageIndex = 0;

    public ListViewEndlessScrollListeter() {
    }

    public ListViewEndlessScrollListeter(int visibleThreshold) {
        this.mVisibleThreshold = visibleThreshold;
    }

    public ListViewEndlessScrollListeter(int visibleThreshold, int startingPageIndex) {
        this.mVisibleThreshold = visibleThreshold;
        this.mStartingPageIndex = startingPageIndex;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // If the total item count is less than the previous total item,  then assume that
        // the list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            this.mCurrentPage = this.mStartingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) { this.mLoading = true; }
        }
        // If it's still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (mLoading && (totalItemCount > previousTotalItemCount)) {
            mLoading = false;
            previousTotalItemCount = totalItemCount;
            mCurrentPage++;
        }

        // If data isn't currently loading, we check to see if we have reached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        if (!mLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + mVisibleThreshold)) {
            mLoading = onLoadMore(mCurrentPage + 1, totalItemCount);
        }
    }

    // Defines the process for actually loading more data based on page
    public abstract boolean onLoadMore(int page, int totalItemsCount);
}

```

You simply have to override onLoadMore(int page, int totalItemsCount) method of the above class in your activity's oncreate() method as follows:

```java
  
  ListView mLvRepo;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_repo_list);
        ButterKnife.bind(this);
        
        ........
        
            mLvRepo.setOnScrollListener(new ListViewEndlessScrollListeter() {
                @Override
                public boolean onLoadMore(int page, int totalItemsCount) {
                    mGitHubRepoPresenter.callRepoAPI(mApplication.getString(R.string.git_user_name),page,REPO_PER_PAGE);
                    return true;
                }
            });
        }

        ..........
    }


```

And that's it. Happy endless loading :) 

# Final comments
You can find the complete source code in this repository.
