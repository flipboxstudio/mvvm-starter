package id.co.flipbox.mvvmstarter.views.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonObject;

import id.co.flipbox.mvvmstarter.R;
import id.co.flipbox.mvvmstarter.data.DataManager;
import id.co.flipbox.mvvmstarter.databinding.FragmentForgotPasswordBinding;
import id.co.flipbox.mvvmstarter.utils.constants.S;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class ForgotPasswordFragment extends BaseFragment
{

    private FragmentForgotPasswordBinding       mBinding;
    private OnForgotFragmentInteractionListener mListener;

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
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
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
        mBinding.btnForgotSubmit.setOnClickListener(new View.OnClickListener()
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
    }

    @Override
    public void onStop ()
    {
        super.onStop();
    }

    @Override
    public void onDetach ()
    {
        super.onDetach();
        mListener = null;
    }

    private void submitForgotPassword ()
    {
        String id = mBinding.tilEmailWrapper.getEditText().getText().toString();
        if (!validate(id))
        {
            return;
        }

        //precaution for double click
        mBinding.btnForgotSubmit.setEnabled(false);

        mBinding.loadingForgot.showLoading(true);

        DataManager.can().forgotPassword(id).observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Consumer<JsonObject>()
                   {
                       @Override
                       public void accept (JsonObject object) throws Exception
                       {
                           mBinding.loadingForgot.showLoading(false);
                           mListener.showLoginForm();
                           Toast.makeText(getContext(), S.success_lupa_password, Toast.LENGTH_SHORT).show();
                           mBinding.btnForgotSubmit.setEnabled(true);
                       }
                   }, new Consumer<Throwable>() {
                       @Override
                       public void accept (Throwable throwable) throws Exception
                       {
                           mBinding.loadingForgot.showLoading(false);
                           Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                           mBinding.btnForgotSubmit.setEnabled(true);
                       }
                   });
    }

    public boolean validate (String email)
    {
        boolean valid = true;
        if (email.length() == 0)
        {
            mBinding.tilEmailWrapper.setError(S.error_id_kosong);
            valid = false;
        }

        return valid;
    }

    public interface OnForgotFragmentInteractionListener
    {
        void showLoginForm ();
    }
}
