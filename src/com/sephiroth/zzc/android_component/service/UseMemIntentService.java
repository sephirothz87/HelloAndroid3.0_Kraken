package com.sephiroth.zzc.android_component.service;

import com.sephiroth.zzc.android_component.util.AndroidUtilFunc;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class UseMemIntentService extends IntentService{
	
	public UseMemIntentService(String name) {
		super(name);
	}

	public Binder binder=new Binder();
	
	@Override
	public IBinder onBind(Intent intent) {
		// AndroidUtil.Logger.write(onBind);
		AndroidUtilFunc.PL("onBind");
		mainLoop();
		return binder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		AndroidUtilFunc.PL("onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onCreate() {
		// AndroidUtil.Logger.write("onCreate");
		AndroidUtilFunc.PL("onCreate");
		super.onCreate();
	}

	public void mainLoop() {
		for (int i = 0; i < 10; i++) {
			// AndroidUtil.Logger.write("i = " + i);
			AndroidUtilFunc.PL("i = " + i);
		}
	}

	@Override
	public void onStart(Intent intent, int startId) {
		AndroidUtilFunc.PL("onStart");
		mainLoop();
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		// AndroidUtil.Logger.write("onDestroy");
		AndroidUtilFunc.PL("onDestroy");
		super.onDestroy();
	}
	
	@Override  
    public int onStartCommand(Intent intent, int flags, int startId) {  
		AndroidUtilFunc.PL("onStartCommand");
        return super.onStartCommand(intent, flags, startId);   
    }  
	
	@Override  
    public void setIntentRedelivery(boolean enabled) {   
		AndroidUtilFunc.PL("setIntentRedelivery");
        super.setIntentRedelivery(enabled);   
    }   

	@Override
	protected void onHandleIntent(Intent intent) {
		AndroidUtilFunc.PL("onHandleIntent");
		for (int i = 0; i < 100; i++) {
			// AndroidUtil.Logger.write("i = " + i);
			AndroidUtilFunc.PL("i = " + i);
//			SystemClock.sleep(500);
		}
		
	}
}
