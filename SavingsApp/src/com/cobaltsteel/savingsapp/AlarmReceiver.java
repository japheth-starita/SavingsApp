package com.cobaltsteel.savingsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
<<<<<<< HEAD
=======
		Toast.makeText(arg0, "Hello", Toast.LENGTH_LONG).show();
		Log.d("Hello", "Hiasdasdasdasda");
>>>>>>> origin/master
		Intent intent = new Intent(arg0, DialogActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		arg0.startActivity(intent);
		
	}

}
