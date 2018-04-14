package com.nghiepnguyen.lession8;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraGalleryFragment extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    //  String path = "/storage/sdcard0/test.jpg";
    Button cam;
    File f;
    Uri file;
    String path;

    ImageView iView;

    public CameraGalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera_gallery, container, false);
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mypicture.jpg";
        cam = (Button) view.findViewById(R.id.camera);
        iView = (ImageView) view.findViewById(R.id.iv);
        // If we don’t have the appropriate permissions, we disable the button until we do.
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            cam.setEnabled(false);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        return view;
    }

    // Check permission of the camera Intent & External Storage
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                cam.setEnabled(true);
            }
        }
    }

    // Store the file in the by creating Specific Folder
    public void camera1(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 1);
    }

    public void camera2(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    // Store the image in the specific path
    public void camera(View v) {
        // Create a Camera Intent
        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
        // Create a file with the specified path
        f = new File(path);
        // Store the capture into SDCard storage

        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        // Start the Camera Intent activity with the request_code 1
        startActivityForResult(i, 1);
    }

    public void gallery(View v) {
        Intent i = new Intent();
        // Activity Action for the intent : Pick an item from the data, returning what was selected.
        i.setAction(Intent.ACTION_PICK);
        i.setType("image/*");
        // Start the Gallery Intent activity with the request_code 2
        startActivityForResult(i, 2);
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }

    // To perform post Activities write your logic in the onActivityResult(), the user actions are determined based on the requestCode
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Intent object data automatically store the selected file path from the Image Gallery from your device storage
        super.onActivityResult(requestCode, resultCode, data);

        // Logic to from specified file path
      /*  if(requestCode==1 && resultCode==RESULT_OK){ // For Clicking Camera button
           // Set the captured image to the ImageView Component
              // iView.setImageURI(Uri.fromFile(f));
               iView.setImageURI(file);
        }*/
        // Logic to get from Bundle
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            iView.setImageBitmap(imageBitmap);
        } else if (requestCode == 2) { // For Clicking Gallery button
            // Set the selected image from the device image gallery to the ImageView component
            iView.setImageURI(data.getData());
        }

    }
}
