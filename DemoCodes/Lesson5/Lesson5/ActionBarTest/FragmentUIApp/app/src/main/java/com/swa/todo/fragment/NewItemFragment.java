package com.swa.todo.fragment;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.swa.todo.R;
import com.swa.todo.TagEnum;
import com.swa.todo.adapter.TagSpinnerAdapter;
import com.swa.todo.model.Item;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class NewItemFragment extends Fragment {

	private static List<TagEnum> tagList = new ArrayList<TagEnum>();
	static {
		tagList.add(TagEnum.BLACK);
		tagList.add(TagEnum.BLUE);
		tagList.add(TagEnum.GREEN);
		tagList.add(TagEnum.RED);
		tagList.add(TagEnum.YELLOW);
	}
	
	private TagEnum currentTag;
	private static Date selDate = new Date();

	public NewItemFragment() {}

	static TextView tvDate;
	static TextView tvTime;
	
	static SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
	static SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v =  inflater.inflate(R.layout.add_item_layout, container, false);
		
		Button addBtn = (Button) v.findViewById(R.id.addBtn);
		final EditText edtName = (EditText) v.findViewById(R.id.edtName);
		final EditText edtDescr = (EditText) v.findViewById(R.id.edtDescr);
		final EditText edtNote = (EditText) v.findViewById(R.id.edtNote);
		
		Spinner sp = (Spinner) v.findViewById(R.id.tagSpinner);
		TagSpinnerAdapter tsa = new TagSpinnerAdapter(getActivity(), tagList);
		sp.setAdapter(tsa);
		
		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adptView, View view,
					int pos, long id) {
				
				currentTag = (TagEnum) adptView.getItemAtPosition(pos);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		addBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// We retrieve data inserted
				Item i = new Item();
				i.setName(edtName.getText().toString());
				i.setDescr(edtDescr.getText().toString());
				i.setNote(edtNote.getText().toString());
				i.setTag(currentTag);
				i.setDate(selDate);
				// Safe cast
				( (AddItemListener) getActivity()).onAddItem(i);
			}
		});
		
		// We set the current date and time
		tvDate = (TextView) v.findViewById(R.id.inDate);
		tvTime = (TextView) v.findViewById(R.id.inTime);
		
		tvDate.setText(sdfDate.format(selDate));
		tvTime.setText(sdfTime.format(selDate));
		
		tvDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DatePickerFragment dpf = new DatePickerFragment();
				dpf.show(getFragmentManager(), "datepicker");
				
			}
		});

		tvTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TimePickerFragment tpf = new TimePickerFragment();
				tpf.show(getFragmentManager(), "timepicker");
				
			}
		});
		
		return v;
	}
	
	
	
	public interface AddItemListener {
		public void onAddItem(Item item);
	}

	
	// We create a Dialog fragment holding Date picker
	public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

		@Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the current date as the default date in the picker
	        final Calendar c = Calendar.getInstance();
	        c.setTime(selDate);
	        int year = c.get(Calendar.YEAR);
	        int month = c.get(Calendar.MONTH);
	        int day = c.get(Calendar.DAY_OF_MONTH);

	        // Create a new instance of DatePickerDialog and return it
	        return new DatePickerDialog(getActivity(), this, year, month, day);
	    }
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			
			
			Calendar c = Calendar.getInstance();
			c.set(year, monthOfYear, dayOfMonth, 9, 0);
			selDate = c.getTime();
			
			
			tvDate.setText(sdfDate.format(selDate));
		}
		
	}
	
	// We create a Dialog fragment holding Date picker
	public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

		@Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the current date as the default date in the picker
	        final Calendar c = Calendar.getInstance();
	        c.setTime(selDate);
	        int hour = c.get(Calendar.HOUR_OF_DAY);
	        int minute = c.get(Calendar.MINUTE);

		    // Create a new instance of TimePickerDialog and return it
		     return new TimePickerDialog(getActivity(), this, hour, minute,
		                DateFormat.is24HourFormat(getActivity()));

	       
	    }
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			Calendar c = Calendar.getInstance();
			c.setTime(selDate);
			c.set(Calendar.HOUR_OF_DAY, hourOfDay);
			c.set(Calendar.MINUTE, minute);	
			
			selDate = c.getTime();
			
			// We set the hour
			tvTime.setText(sdfTime.format(selDate));
		}
		
	}
}
