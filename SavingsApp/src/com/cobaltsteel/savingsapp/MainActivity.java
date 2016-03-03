package com.cobaltsteel.savingsapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private SharedPreferences sharedPref;
	private TextView txtSavings;
	Button btnEnter;
    Button btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sharedPref = getSharedPreferences(StaticData.prefName, MODE_PRIVATE);
        boolean isthereAlarm = sharedPref.getBoolean("isthereAlarm", false);
        int totalSavings = sharedPref.getInt("Savings", 0);
        btnEnter = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        txtSavings = (TextView) findViewById(R.id.txtSavings);
        if(isthereAlarm){        	
        	btnEnter.setEnabled(false);
        	btnStop.setEnabled(true);
        }
        else{
        	btnEnter.setEnabled(true);
        	btnStop.setEnabled(false);
        }
        txtSavings.setText(txtSavings.getText().toString() + totalSavings);
    }
    
    public void startApp(View v){
    	startActivity(new Intent(getApplication(), PreferencesActivity.class));
    }
    
    public void cancelAlarm(View v){
    	Intent intentstop = new Intent(this, AlarmReceiver.class);
    	PendingIntent senderstop = PendingIntent.getBroadcast(this,
    	            StaticData.code, intentstop, 0);
    	AlarmManager alarmManagerstop = (AlarmManager) getSystemService(ALARM_SERVICE);
<<<<<<< HEAD
    	txtSavings.setText(txtSavings.getText().toString() + "0");
=======

>>>>>>> origin/master
    	alarmManagerstop.cancel(senderstop);
    	SharedPreferences.Editor edit = sharedPref.edit();
    	edit.putBoolean("isthereAlarm", false);
    	edit.putInt("Savings", 0);
    	edit.commit();
    	btnEnter.setEnabled(true);
    	btnStop.setEnabled(false);
    }
}
