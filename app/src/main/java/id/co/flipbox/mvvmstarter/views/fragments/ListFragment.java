package id.co.flipbox.mvvmstarter.views.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import id.co.flipbox.mvvmstarter.R;
import id.co.flipbox.mvvmstarter.data.DataManager;
import id.co.flipbox.mvvmstarter.data.events.ErrorEvent;
import id.co.flipbox.mvvmstarter.data.events.GetUserListSuccessEvent;
import id.co.flipbox.mvvmstarter.databinding.FragmentListBinding;
import id.co.flipbox.mvvmstarter.models.User;
import id.co.flipbox.mvvmstarter.utils.DummyDataFactory;
import id.co.flipbox.mvvmstarter.viewmodels.UserListViewModel;
import id.co.flipbox.mvvmstarter.views.adapters.ListAdapter;

/**
 * Created by bukhoriaqid on 11/27/16.
 */

public class ListFragment extends BaseFragment
{
    FragmentListBinding mBinding;
    List<User>          mUsers;


    public ListFragment ()
    {
        setArguments(new Bundle());
        mUsers = DummyDataFactory.createDummyUsers();
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState)
    {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        mBinding.setUsers(new UserListViewModel());

        mBinding.rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvContent.setAdapter(new ListAdapter(mUsers));

        mBinding.llUserList.showCustomLoading(true, "Loading User List...");
        DataManager.can().getUserList();

        return mBinding.getRoot();
    }

    @Override
    void initUI ()
    {

    }

    @Override
    void initEvent ()
    {

    }

    @Override
    public void onStart ()
    {
        super.onStart();
        event.register(this);
    }

    @Override
    public void onStop ()
    {
        super.onStop();
        event.unregister(this);
    }

    @Subscribe
    public void onSuccess (GetUserListSuccessEvent event)
    {
        mUsers.clear();
        mUsers.addAll(event.getUsers());
        mBinding.rvContent.getAdapter().notifyDataSetChanged();
        mBinding.llUserList.showCustomLoading(false);
        if (mUsers.size() == 0)
        {
            mBinding.llUserList.showEmptyView(true);
        }
    }

    @Subscribe
    public void onFailed (ErrorEvent event)
    {
        mBinding.llUserList.showCustomLoading(false);
        Toast.makeText(getContext(), event.getMessage(), Toast.LENGTH_LONG).show();
    }
}
