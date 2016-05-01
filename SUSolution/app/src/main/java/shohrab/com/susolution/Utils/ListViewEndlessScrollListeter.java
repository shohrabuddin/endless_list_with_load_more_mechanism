package shohrab.com.susolution.Utils;

import android.widget.AbsListView;

/**
 * Created by Shohrab on 4/8/2016.
 */
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
