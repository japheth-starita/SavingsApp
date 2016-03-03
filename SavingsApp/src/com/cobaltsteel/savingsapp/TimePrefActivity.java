package com.cobaltsteel.savingsapp;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimePrefActivity extends Activity {
	//Dont change. 852 is the request code
	private int requestCode = StaticData.code;
	private TimePicker tpAlarmTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_pref);
		tpAlarmTime = (TimePicker) findViewById(R.id.tpAlarmTime);
		GregorianCalendar currentCal = (GregorianCalendar) GregorianCalendar.getInstance();
		
	}
	
	public void setAlarm(View v){
		int tpHour = tpAlarmTime.getCurrentHour();
		int tpMinute = tpAlarmTime.getCurrentMinute();
		GregorianCalendar currentCal = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar alarmCal = new GregorianCalendar();
		alarmCal.set(GregorianCalendar.YEAR, currentCal.get(GregorianCalendar.YEAR));
		alarmCal.set(GregorianCalendar.MONTH, currentCal.get(GregorianCalendar.MONTH));
		alarmCal.set(GregorianCalendar.DATE, currentCal.get(GregorianCalendar.DATE));
		alarmCal.set(GregorianCalendar.HOUR_OF_DAY, tpHour);
		alarmCal.set(GregorianCalendar.MINUTE, tpMinute);
		alarmCal.add(GregorianCalendar.DATE, 1);
		long alarmTime = alarmCal.getTimeInMillis();
		Toast.makeText(this, "Alarm Scheduled for " + alarmCal.getTime(), Toast.LENGTH_LONG).show();
		
		Intent intent = new Intent(this, AlarmReceiver.class);
		PendingIntent alarmIntent = PendingIntent.getBroadcast(this, requestCode, intent, 0);
		// create the object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        //alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, alarmIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime, AlarmManager.INTERVAL_DAY, alarmIntent);
        //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarmTime, AlarmManager.INTERVAL_DAY, alarmIntent);

        SharedPreferences.Editor edit = getSharedPreferences(StaticData.prefName, MODE_PRIVATE).edit();
    	edit.putBoolean("isthereAlarm", true);
    	edit.commit();
	}
}
