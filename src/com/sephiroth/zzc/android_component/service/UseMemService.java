package com.sephiroth.zzc.android_component.service;

import java.util.ArrayList;

import com.sephiroth.zzc.android_component.util.AndroidUtilFunc;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class UseMemService extends BaseService {
	public MyBinder mBinder = new MyBinder();

	public int currentNum = 0;

	public class MyBinder extends Binder implements IUseMemService {
		public UseMemService getService() {
			return UseMemService.this;
		}

		@Override
		public void startThread() {
			threadAble = true;
		}

		@Override
		public void stopThread() {
			threadAble = false;
		}

		// @Override
		// public int getCurrentNum() {
		// return currentNum;
		// }
	}

	private class forMem {
		public forMem() {
			super();
			int i=(int) (Math.random()*1000000000);
			this.longNum = 1000000000+i;
			this.longStr = "扫偶发介绍的覅解耦清分机EFI价位覅去哦付金额为欺负飞机我去哦飞机请我覅全文覅接钱未付将诶却无法"+String.valueOf(i);
		}

		long longNum;
		String longStr;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// AndroidUtil.Logger.write(onBind);
		AndroidUtilFunc.PL("onBind");
		// mainLoop();
		return mBinder;
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
		
		mainThread = new Thread(new Runnable() {
			@SuppressWarnings("unused")
			@Override
			public void run() {  
				while (true) {
					while (threadAble) {
						// currentNum++;
						// AndroidUtilFunc.PL(currentNum);
						// SystemClock.sleep(1000);

						int j = 0;
						ArrayList<ArrayList<forMem>> outData = new ArrayList<ArrayList<forMem>>();
						for (int i = 0; i < 100000000; i++) {
							j++;
							ArrayList<forMem> innerData = new ArrayList<forMem>();
							for (int t = 0; t < j; t++) {
								forMem fm = new forMem();
								innerData.add(fm);
							}
							outData.add(innerData);
							
							int index1 = (int) (Math.random() * outData.size());
							ArrayList<forMem> tem_inner_data = outData
									.get(index1);
							int index2 = (int) (Math.random() * tem_inner_data
									.size());
							forMem tem_fm = tem_inner_data.get(index2);

							if(outData.size()%100==0){
								AndroidUtilFunc.PL("index1 =" + index1);
								AndroidUtilFunc.PL("index2 =" + index2);
								AndroidUtilFunc.PL("tem_fm.longNum ="
										+ tem_fm.longNum);
								AndroidUtilFunc.PL("tem_fm.longStr ="
										+ tem_fm.longStr);

								AndroidUtilFunc.PL("outData.size() = "
										+ outData.size());
								AndroidUtilFunc.PL("outData.get(index2).size() = "
										+ outData.get(index2).size());
							}
							
							//轮询一次所有变量，以保证内存被占用
							for(ArrayList<forMem> afm:outData){
								for(forMem fmm:afm){
									long num=fmm.longNum;
									String s=fmm.longStr;
								}
							}
						}
					}
				}
			}
		});
		mainThread.start();

		super.onCreate();
	}

	// public void mainLoop() {}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		AndroidUtilFunc.PL("onStart");
		// mainLoop();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onDestroy() {
		// AndroidUtil.Logger.write("onDestroy");
		AndroidUtilFunc.PL("onDestroy");
		threadAble = false;
		currentNum = 0;
		mBinder = null;
		mainThread.stop();
		super.onDestroy();
	}
}
