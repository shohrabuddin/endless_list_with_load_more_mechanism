package shohrab.com.susolution.GitHubRepoList;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import shohrab.com.susolution.GitHubAPI.Response.GitHubAPIRepoResponse;
import shohrab.com.susolution.R;

/**
 * Created by Shohrab on 4/8/2016.
 */
public class GitHubRepoListAdapter extends ArrayAdapter<GitHubAPIRepoResponse>{

    private LayoutInflater mInflater;
    private Context mContext;

    public GitHubRepoListAdapter(Context context, List<GitHubAPIRepoResponse> repoList) {
        super(context, 0, repoList);
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RepositoryHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.git_hub_repo_listview_layout, parent, false);
            holder = new RepositoryHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RepositoryHolder) convertView.getTag();
        }

        GitHubAPIRepoResponse repoResponse = getItem(position);
        holder.tVRepoName.setText(repoResponse.getRepoName());
        holder.tVRepoOwner.setText(repoResponse.getOwnerResponse().getGitHubLogin());
        holder.tVRepoDesc.setText(repoResponse.getRepoDescription());

        if(repoResponse.isForked())
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.lightGreen));
        else
            convertView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));


        return convertView;
    }

    static class RepositoryHolder {
        @Bind(R.id.gitHubRepoListViewLayout_TVRepoName)
        TextView tVRepoName;
        @Bind(R.id.gitHubRepoListViewLayout_TVRepoOwner)
        TextView tVRepoOwner;
        @Bind(R.id.gitHubRepoListViewLayout_TVRepoDesc)
        TextView tVRepoDesc;


        public RepositoryHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
