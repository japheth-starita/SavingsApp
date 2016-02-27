package com.cobaltsteel.savingsapp;

import java.util.GregorianCalendar;

import android.util.Log;

public class StaticData {
	public static float dblTargetMoney = 0;
	public static int dblSavingsPerDay = 0;
	public static String strRange = "";
	public static int intNum = 0;
	//Don't Change. 852
	public static int code = 852;
	//Don't Change Pref Name
	public static String prefName = "AlarmPref";
	
	public static void computeMoney(){
		GregorianCalendar currentCal = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar TargetCal = (GregorianCalendar) GregorianCalendar.getInstance();
		TargetCal.add(returnField(), intNum);
		dblSavingsPerDay = (int) Math.round(dblTargetMoney/daysBetween(currentCal, TargetCal));
		Log.d("PerDay", dblSavingsPerDay+"");
	}
	
	public static int returnField(){
		if(strRange.contains("Day")){
			return GregorianCalendar.DAY_OF_YEAR;
		}else if(strRange.contains("Week")){
			return GregorianCalendar.WEEK_OF_YEAR;
		}
		else if(strRange.contains("Month")){
			return GregorianCalendar.MONTH;
		}
		else{
			return GregorianCalendar.DAY_OF_YEAR;
		}
	}
	
	public static int daysBetween(GregorianCalendar d1, GregorianCalendar d2){
        return (int)( (d2.getTimeInMillis() - d1.getTimeInMillis()) / (1000 * 60 * 60 * 24));
	}
}
