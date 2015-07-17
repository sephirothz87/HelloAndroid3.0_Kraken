package com.sephiroth.zzc.android_component.util;

import java.util.Date;

import com.sephiroth.zzc.android_component.util.AndroidUtil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class AndroidUtilFunc {
	public static void PL(String s){
		Log.e(AndroidUtil.LOG_TAG,s);
	}
	
	public static void PL(int i){
		Log.e(AndroidUtil.LOG_TAG,""+i);
	}
	
//	public static String getCurrentSimpleTimeForFile(){
//		return AndroidUtil.DATE_FORMAT_FOR_FILE.format(new java.util.Date());   
//	}
//	
//	public static String getCurrentSimpleTime(){
//		return AndroidUtil.DATE_FORMAT.format(new java.util.Date());   
//	}
//	
//	public static String getTimeForFile(Date d){
//		return AndroidUtil.DATE_FORMAT_FOR_FILE.format(d);
//	}
//	
//	public static String getTime(Date d){
//		return AndroidUtil.DATE_FORMAT.format(d);   
//	}
	
	public static long getUnixTimeStamp(){
		return new Date().getTime();
	}
	
	public static void PrintComponentInfo(){
		PL("component_version_code = "+AndroidUtil.COMMON_VERSION_CODE);
		PL("component_version_name = "+AndroidUtil.COMMON_VERSION_NAME);
		PL("component_sign = "+AndroidUtil.COMPONENT_SIGN);
	}
	
	public static void shortToast(Context context,String s){
		showToast(context, s, Toast.LENGTH_SHORT);
	}

	public static void longToast(Context context,String s) {
		showToast(context, s, Toast.LENGTH_LONG);
	}
	
	public static void showToast(Context context, String msg, int duration) {
		Toast.makeText(context, msg, duration).show();
	}
}
