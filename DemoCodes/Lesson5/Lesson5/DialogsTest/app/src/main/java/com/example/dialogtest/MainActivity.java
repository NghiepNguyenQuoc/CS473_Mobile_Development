package com.example.dialogtest;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.durgasoft.dialogstest.R;

public class MainActivity extends AppCompatActivity {

    Button yes_btn,no_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void custom(View v){
            final Dialog d=new Dialog(this);
            d.setContentView(R.layout.dialog_view);
            yes_btn=(Button)d.findViewById(R.id.button);
             no_btn=(Button)d.findViewById(R.id.button2);
            yes_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    d.dismiss();
                    finish();
                }
            });
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.setTitle("Message");
        d.show();
    }


    public void alert(View v){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Message");
        dialog.setMessage("Are u sure want to exit ?");
        dialog.setIcon(R.drawable.g_logo);

        DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==dialog.BUTTON_POSITIVE){
                    dialog.dismiss();
                    finish();
                }else if(which==dialog.BUTTON_NEGATIVE){
                   dialog.dismiss();
                }
            }
        };
        dialog.setPositiveButton("Yes", listener);
        dialog.setNegativeButton("No", listener);
        dialog.show();

    }


    public void datePicker(View v){

        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Toast.makeText(MainActivity.this, "Day :"+dayOfMonth+"\n Month :"+monthOfYear+"\n Year :"+year,
                        Toast.LENGTH_SHORT).show();
            }
        };


        DatePickerDialog dpDialog=new DatePickerDialog(this,listener,2016,03,22);
           dpDialog.show();

    }

    public void timePicker(View v){

        TimePickerDialog.OnTimeSetListener listener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                Toast.makeText(MainActivity.this, "Hour :"+hourOfDay+"\n Minute :"+minute,
                        Toast.LENGTH_SHORT).show();
            }
        };

        TimePickerDialog tpd=new TimePickerDialog(this,listener,9,18,false);
        tpd.show();

    }

    ProgressDialog d_pDialog;
    ProgressDialog i_pDialog;


    public void determinant(View v){
        d_pDialog=new ProgressDialog(this);
        d_pDialog.setTitle("Message");
        d_pDialog.setMessage("please wait page is loading...");
        d_pDialog.setIcon(R.drawable.g_logo);
        d_pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  // determinant
        d_pDialog.setMax(30);
        d_pDialog.show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(d_pDialog.getProgress()<30){
                    d_pDialog.setProgress(d_pDialog.getProgress()+1);
                    new Handler().postDelayed(this,1000);
                }else{
                    d_pDialog.dismiss();
                }
            }
        }, 1000);

    }


    public void indeterminant(View v){
        i_pDialog=new ProgressDialog(this);
        i_pDialog.setTitle("Message");
        i_pDialog.setMessage("please wait page is loading...");
        i_pDialog.setIcon(R.drawable.g_logo);
        i_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  // determinant
        i_pDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    i_pDialog.dismiss();
            }
        }, 30000);
    }
    public void dfragment(View v){
        new SampleDialogFragment().show(getFragmentManager(), "sample");
    }
}
