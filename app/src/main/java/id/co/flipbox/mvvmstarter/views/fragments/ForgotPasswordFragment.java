package id.co.flipbox.mvvmstarter.views.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import id.co.flipbox.mvvmstarter.R;
import id.co.flipbox.mvvmstarter.data.DataManager;
import id.co.flipbox.mvvmstarter.data.events.ErrorEvent;
import id.co.flipbox.mvvmstarter.data.events.ForgotPasswordSuccessEvent;
import id.co.flipbox.mvvmstarter.databinding.FragmentForgotPasswordBinding;
import id.co.flipbox.mvvmstarter.utils.constants.S;

public class ForgotPasswordFragment extends BaseFragment
{

    private OnForgotFragmentInteractionListener mListener;
    private FragmentForgotPasswordBinding mBinding;

    public ForgotPasswordFragment ()
    {
        // Required empty public constructor
        setArguments(new Bundle());
    }

    @Override
    public void onAttach (Context context)
    {
        super.onAttach(context);
        if (context instanceof OnForgotFragmentInteractionListener)
        {
            mListener = (OnForgotFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                                       + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false);

        initUI();
        initEvent();

        return mBinding.getRoot();
    }

    @Override
    void initUI ()
    {
        getActivity().setTitle(S.title_lupa_password);
    }

    @Override
    void initEvent ()
    {
        mBinding.forgotSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                hideKeyboard();
                submitForgotPassword();
            }
        });

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

    @Override
    public void onDetach ()
    {
        super.onDetach();
        mListener = null;
    }

    private void submitForgotPassword ()
    {
        String id = mBinding.emailWrapper.getEditText().getText().toString();
        if (!validate(id))
        {
            return;
        }

        //precaution for double click
        mBinding.forgotSubmit.setEnabled(false);

        showLoading(S.loading_text);
        DataManager.forgotPassword(id);
    }

    public boolean validate (String email)
    {
        boolean valid = true;
        if (email.length() == 0)
        {
            mBinding.emailWrapper.setError(S.error_id_kosong);
            valid = false;
        }

        return valid;
    }

    @Subscribe
    public void onSuccess (ForgotPasswordSuccessEvent event)
    {
        hideLoading();
        mListener.showLoginForm();
        Toast.makeText(getContext(), S.success_lupa_password, Toast.LENGTH_SHORT).show();
        mBinding.forgotSubmit.setEnabled(true);
    }

    @Subscribe
    public void onFailed (ErrorEvent event)
    {
        hideLoading();
        Toast.makeText(getContext(), event.getMessage(), Toast.LENGTH_SHORT).show();
        mBinding.forgotSubmit.setEnabled(true);
    }

    public interface OnForgotFragmentInteractionListener
    {
        void showLoginForm ();
    }
}
