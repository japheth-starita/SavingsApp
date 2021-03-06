package com.cobaltsteel.savingsapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PreferencesActivity extends Activity {
	private Spinner spNumber;
	private Spinner spRange;
	private EditText editMoney;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preferences);
		
		Integer [] arrayNumber = new Integer[] {
				1,2,3,4,5,6,7,8,9,10,11,12};
		
		String [] arrayRange = new String[] {
				"Day/s","Week/s","Month/s"};
		
		editMoney = (EditText) findViewById(R.id.txtTargetMoney);
		spNumber = (Spinner) findViewById(R.id.spTargetNumber);
		spRange = (Spinner) findViewById(R.id.spTargetRange);
		ArrayAdapter<Integer> adaptNumber = new ArrayAdapter<Integer>(
				this,android.R.layout.simple_spinner_item,arrayNumber );
		ArrayAdapter<String> adaptRange = new ArrayAdapter<String>(
				this,android.R.layout.simple_spinner_item,arrayRange );
		spNumber.setAdapter(adaptNumber);
		spRange.setAdapter(adaptRange);
		spNumber.setSelection(1);
		spRange.setSelection(1);
		//Remove comment for build Api 11 and up
		//dptargetDate.setMinDate(System.currentTimeMillis() - 2000);
	}
	
	public void submitPref(View v){
		String strRange = spRange.getSelectedItem().toString();
		int intNum = Integer.parseInt(spNumber.getSelectedItem().toString());
		float dblMoney;
		
		try{
			dblMoney = (float) Double.parseDouble(editMoney.getText().toString());
		}catch(NumberFormatException e){
			dblMoney = 0f;
		}
		
		if (dblMoney <= 0.0){
			Toast.makeText(getApplicationContext(), "No Target Money Found or Target Money Invalid", Toast.LENGTH_LONG).show();
			editMoney.requestFocus();
		}
		else if(strRange.contains("Day")){
			if (intNum<7){
				gotoTimePref(dblMoney, intNum, strRange);
			}else{notifyInvalidData(intNum, strRange);}
		}else if(strRange.contains("Week")){
			if (intNum < 4){
				gotoTimePref(dblMoney, intNum, strRange);
			}else{notifyInvalidData(intNum, strRange);}
		}
		else if(strRange.contains("Month")){
			if (intNum <= 12){
				gotoTimePref(dblMoney, intNum, strRange);
			}else{notifyInvalidData(intNum, strRange);}
		}
	}
	
	public void gotoTimePref(float money, int num, String ran){
		StaticData.dblTargetMoney = money;
		StaticData.intNum = num;
		StaticData.strRange = ran;
		Intent intent = new Intent(getApplicationContext(), TimePrefActivity.class);
		StaticData.computeMoney();
		
		//StorePreferenceFirst
		
		SharedPreferences.Editor edit = getSharedPreferences(StaticData.prefName, MODE_PRIVATE).edit();
		edit.putFloat("dblTargetMoney",  money);
		edit.putInt("intNum", num);
		edit.putString("strRange", ran);
		edit.putInt("SavingsPerDay",StaticData.dblSavingsPerDay);
		edit.commit();
		startActivity(intent);
	}
	
	public void notifyInvalidData(int num, String ran){
		Toast.makeText(getApplicationContext(), num + " " + ran + " is invalid.", Toast.LENGTH_LONG).show();
	}
	
}
