package com.sephiroth.zzc.android_component;


import com.sephiroth.zzc.java_common.service.Logger;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ALogger extends Logger {
	public String ATag;
	public Context Context;

	public ALogger(String filePath, String tag, String aTag, Context context) {
		super(filePath, tag);
		ATag = aTag;
		Context = context;
	}

	public ALogger(String filePath, String tag, Context context) {
		super(filePath, tag);
		ATag = tag;
		Context = context;
	}

	public void logcat(String log) {
		Log.e(ATag, log);
	}

	public void toast(String log) {
		Log.e(ATag, log);
		Toast.makeText(Context, log, Toast.LENGTH_SHORT).show();
	}

	public void write(String log) {
		Log.e(ATag, log);
		super.write(log);
	}

	public void write(String tag, String log) {
		Log.e(tag, log);
		super.write(tag, log);
	}
}
