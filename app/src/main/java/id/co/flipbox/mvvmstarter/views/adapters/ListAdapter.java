package id.co.flipbox.mvvmstarter.views.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.co.flipbox.mvvmstarter.R;
import id.co.flipbox.mvvmstarter.databinding.FragmentListItemBinding;
import id.co.flipbox.mvvmstarter.models.User;
import id.co.flipbox.mvvmstarter.viewmodels.UserViewModel;

/**
 * Created by bukhoriaqid on 11/27/16.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>
{

    private FragmentListItemBinding mBinding;
    private List<User>              mUsers;

    public ListAdapter (List<User> users)
    {
        mUsers = users;
    }

    @Override
    public ListViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        mBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_list_item, parent, false);
        ListViewHolder vh = new ListViewHolder(mBinding.getRoot());
        vh.setBinding(mBinding);
        return vh;
    }

    @Override
    public void onBindViewHolder (ListViewHolder holder, int position)
    {
        holder.setUser(mUsers.get(position));
    }

    @Override
    public int getItemCount ()
    {
        return mUsers.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder
    {
        FragmentListItemBinding mBinding;

        ListViewHolder (View itemView)
        {
            super(itemView);
        }

        void setBinding (FragmentListItemBinding binding)
        {
            mBinding = binding;
        }

        public void setUser (User user)
        {
            if (mBinding.getUser() == null)
            {
                mBinding.setUser(new UserViewModel(user));
            }
            else
            {
                mBinding.getUser().setUser(user);
            }

        }
    }
}

