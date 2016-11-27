package id.co.flipbox.mvvmstarter.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bukhoriaqid on 11/27/16.
 */

public class CameraUtils
{
    private static Uri lastCameraImageSaved = new Uri.Builder().build();

    public static Uri getImageUri (Intent data) throws Exception
    {
        Uri imageUri;
        if (data == null)
        {
            imageUri = lastCameraImageSaved;
        }
        else
        {
            imageUri = data.getData();
        }
        return imageUri;
    }

    public static Intent getImageFromGalleryCamera (Context context)
    {
        // Determine Uri of camera image to save.
        File root = new File(Environment
                                     .getExternalStorageDirectory() + File.separator + "dianta/camera" + File.separator);
        root.mkdirs();
        String fname = "dianta-" + System.currentTimeMillis() + ".jpg";
        File sdImageMainDirectory = new File(root, fname);
        Uri outputFileUri = Uri.fromFile(sdImageMainDirectory);

        // camera
        List<Intent> cameraIntents = new ArrayList<>();
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PackageManager lPackageManager = context.getPackageManager();
        List<ResolveInfo> listCam = lPackageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo rInfo : listCam)
        {
            String packageName = rInfo.activityInfo.packageName;
            Intent lIntent = new Intent(captureIntent);
            lIntent.setComponent(new ComponentName(rInfo.activityInfo.packageName, rInfo.activityInfo.name));
            lIntent.setPackage(packageName);
            //save camera result to external storage
            lIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            cameraIntents.add(lIntent);
        }

        //ugly hacks for camera helper
        lastCameraImageSaved = outputFileUri;

        // gallery
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_PICK);

        Intent chooserIntent = Intent.createChooser(galleryIntent, "Pilih Sumber");
        chooserIntent
                .putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

        return chooserIntent;
    }
}
