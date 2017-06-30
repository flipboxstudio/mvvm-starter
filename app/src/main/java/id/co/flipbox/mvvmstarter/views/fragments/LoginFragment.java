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

import br.com.ilhasoft.support.validation.Validator;
import id.co.flipbox.mvvmstarter.R;
import id.co.flipbox.mvvmstarter.data.DataManager;
import id.co.flipbox.mvvmstarter.data.events.ErrorEvent;
import id.co.flipbox.mvvmstarter.data.events.LoginSuccessEvent;
import id.co.flipbox.mvvmstarter.databinding.FragmentLoginBinding;
import id.co.flipbox.mvvmstarter.utils.constants.S;
import id.co.flipbox.mvvmstarter.views.activities.ViewPagerActivity;
import id.co.flipbox.sosoito.LoadingUtils;

public class LoginFragment extends BaseFragment
{
    private static final String TAG = LoginFragment.class.getSimpleName();

    private FragmentLoginBinding               mBinding;
    private Validator                          mValidator;
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
        mValidator = new Validator(mBinding);
        mValidator.enableFormValidationMode();

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
        final String password = mBinding.etPassword.getText().toString();

        if (!mValidator.validate())
        {
            return;
        }

        //precaution for double click
        mBinding.btnLogin.setEnabled(false);

        mBinding.loginLoading.showCustomLoading(true, "Signing in...");
        DataManager.can().login(id, password);
    }

    @Subscribe
    public void onSuccess (LoginSuccessEvent event)
    {
        mBinding.loginLoading.showCustomLoading(false);
        mBinding.btnLogin.setEnabled(true);
        Intent intent = new Intent(getContext(), ViewPagerActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Subscribe
    public void onFailed (ErrorEvent event)
    {
        mBinding.loginLoading.showCustomLoading(false);
        Toast.makeText(getContext(), event.getMessage(), Toast.LENGTH_LONG).show();
        mBinding.btnLogin.setEnabled(true);
    }

    public interface OnLoginFragmentInteractionListener
    {
        void onForgotPasswordClick ();
    }
}
