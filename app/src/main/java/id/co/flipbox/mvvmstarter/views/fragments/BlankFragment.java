package id.co.flipbox.mvvmstarter.views.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import id.co.flipbox.mvvmstarter.R;
import id.co.flipbox.mvvmstarter.utils.CameraUtils;

/**
 * Created by bukhoriaqid on 11/27/16.
 */

public class BlankFragment extends BaseFragment
{
    View mRootView;

    public BlankFragment ()
    {
        setArguments(new Bundle());
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState)
    {
        mRootView = inflater.inflate(R.layout.fragment_blank, container, false);
        initUI();
        initEvent();
        return mRootView;
    }

    @Override
    void initUI ()
    {

    }

    @Override
    void initEvent ()
    {
        mRootView.findViewById(R.id.btn_get_image).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent chooserIntent = CameraUtils.getImageFromGalleryCamera(v.getContext());
                // set unique number for image calling
                startActivityForResult(chooserIntent, 999);
            }
        });
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == 999)
            {
                try
                {
                    //use imageUri as you see fit. load into imageView or upload to server using InputStream
                    Toast.makeText(getContext(), CameraUtils.getImageUri(data).toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

