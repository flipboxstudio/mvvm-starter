package id.co.flipbox.mvvmstarter.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import id.co.flipbox.mvvmstarter.R;
import id.co.flipbox.mvvmstarter.data.DataManager;
import id.co.flipbox.mvvmstarter.data.events.ErrorEvent;
import id.co.flipbox.mvvmstarter.data.events.LoginSuccessEvent;
import id.co.flipbox.mvvmstarter.databinding.FragmentLoginBinding;
import id.co.flipbox.mvvmstarter.utils.constants.S;
import id.co.flipbox.mvvmstarter.views.activities.ViewPagerActivity;

public class LoginFragment extends BaseFragment
{
    private static final String TAG = LoginFragment.class.getSimpleName();
    private FragmentLoginBinding               mBinding;
    private OnLoginFragmentInteractionListener mListener;

    public LoginFragment ()
    {
        // Required empty public constructor
        setArguments(new Bundle());
    }

    @Override
    public void onAttach (Context context)
    {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentInteractionListener)
        {
            mListener = (OnLoginFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        initUI();
        initEvent();

        return mBinding.getRoot();
    }

    @Override
    void initUI ()
    {
        getActivity().setTitle(S.title_masuk);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    void initEvent ()
    {
        mBinding.btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                hideKeyboard();
                submitLogin();
            }
        });

        mBinding.tvForgotPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if (mListener != null)
                {
                    mListener.onForgotPasswordClick();
                }
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
    public void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
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

    public void submitLogin ()
    {
        final String id       = mBinding.tilUserIdWrapper.getEditText().getText().toString();
        final String password = mBinding.tilPasswordWrapper.getEditText().getText().toString();

        if (!validate(id, password))
        {
            return;
        }

        //precaution for double click
        mBinding.btnLogin.setEnabled(false);

        showLoading(S.loading_auth);
        DataManager.login(id, password);
    }

    public boolean validate (final String id, final String password)
    {
        boolean valid = true;

        if (id.length() == 0)
        {
            mBinding.tilUserIdWrapper.setError(S.error_id_kosong);
            valid = false;
        }
        if (password.length() == 0)
        {
            mBinding.tilPasswordWrapper.setError(S.error_password_kosong);
            valid = false;
        }

        return valid;
    }

    @Subscribe
    public void onSuccess (LoginSuccessEvent event)
    {
        hideLoading();
        mBinding.btnLogin.setEnabled(true);
        Intent intent = new Intent(getContext(), ViewPagerActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Subscribe
    public void onFailed (ErrorEvent event)
    {
        hideLoading();
        Toast.makeText(getContext(), event.getMessage(), Toast.LENGTH_LONG).show();
        mBinding.btnLogin.setEnabled(true);
    }

    public interface OnLoginFragmentInteractionListener
    {
        void onForgotPasswordClick ();
    }
}
