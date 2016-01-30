package com.cobaltsteel.savingsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class PreferencesActivity extends Activity {
	private Spinner spNumber;
	private Spinner spRange;
	private DatePicker dptargetDate;
	private Button btnSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preferences);
		
		Integer [] arrayNumber = new Integer[] {
				1,2,3,4,5,6,7,8,9,10,11,12};
		
		String [] arrayRange = new String[] {
				"Day/s","Week/s","Month/s","Year/s"};
		
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		spNumber = (Spinner) findViewById(R.id.spTargetNumber);
		spRange = (Spinner) findViewById(R.id.spTargetRange);
		ArrayAdapter<Integer> adaptNumber = new ArrayAdapter<Integer>(
				this,android.R.layout.simple_spinner_item,arrayNumber );
		ArrayAdapter<String> adaptRange = new ArrayAdapter<String>(
				this,android.R.layout.simple_spinner_item,arrayRange );
		spNumber.setAdapter(adaptNumber);
		spRange.setAdapter(adaptRange);
		spNumber.setSelection(0);
		spRange.setSelection(0);
		//Remove comment for build Api 11 and up
		//dptargetDate.setMinDate(System.currentTimeMillis() - 2000);
	        
	}
	
	public void onRadioButtonClicked(View v){
		RadioButton rbRange = (RadioButton) findViewById(R.id.rbRange);
		RadioButton rbDate = (RadioButton) findViewById(R.id.rbDate);
		TextView lblTargetRange = (TextView) findViewById(R.id.lblTargetRange);
		dptargetDate = (DatePicker) findViewById(R.id.dptargetDate);
		
		if(!btnSubmit.isEnabled()){
			btnSubmit.setEnabled(true);
		}
		if (rbRange.isChecked()){
			spNumber.setVisibility(View.VISIBLE);
			spRange.setVisibility(View.VISIBLE);
			lblTargetRange.setVisibility(View.VISIBLE);
			dptargetDate.setVisibility(View.GONE);
		}
		else if(rbDate.isChecked()){
			spNumber.setVisibility(View.GONE);
			spRange.setVisibility(View.GONE);
			lblTargetRange.setVisibility(View.GONE);
			dptargetDate.setVisibility(View.VISIBLE);
		}
	}
}
