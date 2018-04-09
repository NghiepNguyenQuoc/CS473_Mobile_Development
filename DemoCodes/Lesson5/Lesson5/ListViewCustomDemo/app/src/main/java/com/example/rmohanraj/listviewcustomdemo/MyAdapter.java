package com.example.rmohanraj.listviewcustomdemo;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by rMohanraj on 4/6/2017.
 */

public class MyAdapter extends BaseAdapter {
    /* 1. Specify the path to retrieve the files - Here my specific folder is sample_images
    *     Create a specific folder sample_images on your device internal memory and copy some images in that folder
    * 2.  Create a File object by passing the path
    * 3.  Using list() method from the file object to retrieve all images files.
    *     This method returns an array of String */
    String path="/storage/sdcard0/sample_images/"; // We discussed two ways to retrieve from phone internal memory.
    File f=new File(path);
    String[] files=f.list(); // Hold the list of files
    @Override
    // How many values are going to present in the  ListView - which ListView Count
    public int getCount() {
        return files.length;   // length provide the number of files stored in the array
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    /*Here the return type of this method is View.
    View represents in which format you want to present the UI.
    To do this create an XML file which customize the user requirements and convert into View*/
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtains the LayoutInflater from the given context, in which activity class you want to present
        LayoutInflater inflater = LayoutInflater.from(MainActivity.mainActivity);

        // Convert the XML file into View object
        View view = inflater.inflate(R.layout.activity_myview,null);

        // Configure the Id for individual UI from the activity_myview.xml
        ImageView imageView = (ImageView)view.findViewById(R.id.lview);
        TextView fname = (TextView)view.findViewById(R.id.tv1);
        TextView fsize = (TextView)view.findViewById(R.id.tv2);
        Button delete = (Button)view.findViewById(R.id.bt1);

       /* To display the actual image in each row,
          already we have the actual path and retrieve each image using position
          and then make the String into File.
          Set the image to the ImageView UI using Uri.fromFile() method by passing the File object*/
        String new_path = path + files[position];
        final File new_file = new File(new_path);
        imageView.setImageURI(Uri.fromFile(new_file));
       // Set the file name and file size to the TextView UI
        fname.setText(files[position]); // Retrieve the file name
        fsize.setText(new_file.length() + " bytes"); // Retrieve the file size

        // Perform the action once the user click the Del button by implementing OnClickListener()
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new_file.delete(); // Remove the selected file
                MainActivity.mainActivity.refresh(); // refresh the ListView UI after deletion
            }
        });
        return view;
    }
}
