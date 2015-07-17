package com.sephiroth.zzc.android_component.activity;

import com.sephiroth.zzc.android_component.ALogger;

import android.app.Activity;
import android.widget.Toast;

public class BaseActivity extends Activity{
	public ALogger Logger;
	
	public void shortToast(String s){
		Toast.makeText(getApplicationContext(), s,
			     Toast.LENGTH_SHORT).show();
	}
}
