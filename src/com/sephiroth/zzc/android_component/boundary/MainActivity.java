package com.sephiroth.zzc.android_component.boundary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.sephiroth.zzc.android_component.AssetsManager;
import com.sephiroth.zzc.android_component.R;
import com.sephiroth.zzc.android_component.service.IUseMemService;
import com.sephiroth.zzc.android_component.util.AndroidUtil;
import com.sephiroth.zzc.android_component.util.AndroidUtilFunc;
import com.sephiroth.zzc.android_component.util.PackageUtil;
import com.sephiroth.zzc.java_common.control.TimeManager;

public class MainActivity extends Activity {
	Button mButton_01_function;
	Button mButton_02_function;
	Button mButton_03_function;
	Button mButton_04_function;

	TextView mText_01;

	ActivityManager mActivityManager;
	Context mContext;

	Intent intent;

//	private final static int CWJ_HEAP_SIZE = 6* 1024* 1024 ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		AndroidUtilFunc.PrintComponentInfo();

		mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		mContext=this.getApplicationContext();

		mText_01 = (TextView) findViewById(R.id.text_01);

		mButton_01_function = (Button) findViewById(R.id.button_01_function);

		mButton_02_function = (Button) findViewById(R.id.button_02_function);

		mButton_03_function = (Button) findViewById(R.id.button_03_function);

		mButton_04_function = (Button) findViewById(R.id.button_04_function);

		AndroidUtilFunc.PL("test function start");
		testFunction();
		AndroidUtilFunc.PL("test function end");
	}

	// 2014-03-12 20:29:56 test intent
	void testFunction() {
		mButton_01_function
				.setOnClickListener(mButton_function_listener_20141226205935);
		mButton_02_function
				.setOnClickListener(mButton_function_listener_20140627173254);
		mButton_03_function
				.setOnClickListener(mButton_function_listener_20140628150643);
		mButton_04_function
				.setOnClickListener(mButton_function_listener_20140628150700);
	}
	
	//2014-12-26 20:57:35  ����Assets����ģ��
	OnClickListener mButton_function_listener_20141226205935 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			AssetsManager.copyFileTo("path/log.sh", "/sdcard/000test/temp2014-12-26-213515/sh2014-12-26-213447.txt", mContext,false);
		}
	};

	//2014-12-26 20:57:35  ����Assets����ģ��

	//2014-7-26 16:39:21	������������4.3�ϵ�ִ�����
	OnClickListener mButton_function_listener_20140726163906 = new OnClickListener() {
		@Override
		public void onClick(View v) {
//			String cmd = "getprop";
//			String cmd = "ls -l";
//			String cmd = "am instrument -w com.tencent.qqlive.test/android.test.InstrumentationTestRunner";
			String cmd = "am instrument --user 0 -w com.tencent.qqlive.test/android.test.InstrumentationTestRunner //";

			try {
				Process p_start_test_service = Runtime.getRuntime().exec(cmd);
				AndroidUtilFunc.PL("process run over");
				AndroidUtilFunc.PL("is process = null "
						+ (p_start_test_service == null) + "\n process hash = "
						+ p_start_test_service.hashCode());
				BufferedReader in = new BufferedReader(new InputStreamReader(
						p_start_test_service.getInputStream()));
				BufferedReader err = new BufferedReader(new InputStreamReader(
						p_start_test_service.getErrorStream()));
				String line = "";

				while ((line = in.readLine()) != null) {
					AndroidUtilFunc.PL(line);
				}
				in.close();

				while ((line = err.readLine()) != null) {
					AndroidUtilFunc.PL(line);
				}
				err.close();
				AndroidUtilFunc.PL("line read over");
				p_start_test_service.destroy();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	// 2014-06-27 17:32:32 testService
	Intent svrTestIntent = new Intent(
			"com.sephiroth.zzc.android_component.service.UserMemService");
	// Intent svrTestIntent = new Intent(this, UseMemService.class);

	Intent svrTestIntentServiceIntent = new Intent(
			"com.sephiroth.zzc.android_component.service.UserMemIntentService");

	// ����һ�� ServiceConnection ����
	final ServiceConnection connection = new ServiceConnection() {

		public void onServiceDisconnected(ComponentName name) {
			AndroidUtilFunc.PL("onServiceDisconnected");
			iService = null;
		}

		public void onServiceConnected(ComponentName name, IBinder service) {
			AndroidUtilFunc.PL("onServiceConnected");
			iService = (IUseMemService) service;
		}
	};

	/*
	 * Action
	 */
//	private static final String ACTION_RECV_MSG = "com.linc.intent.action.RECEIVE_MESSAGE";
//	private static final String ACTION_OTHER_MSG = "com.linc.intent.action.OTHER_MESSAGE";
//
//	/*
//	 * Message
//	 */
//	private static final String MESSAGE_IN = "message_input";
//	private static final String MESSAGE_OUT = "message_output";

	IUseMemService iService;
	IBinder iBinder;

	OnClickListener mButton_function_listener_20140627114042 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// svrTestIntent = new Intent(
			// "com.sephiroth.zzc.android_component.service.UseMemService");
			// svrTestIntent.putExtra(MESSAGE_IN, "intent service test");
			// startService(svrTestIntent);
			if (iService != null) {
				iService.startThread();
				AndroidUtilFunc.PL("startThread");
			} else {
				AndroidUtilFunc.PL("iService==null");
			}

		}
	};

	OnClickListener mButton_function_listener_20140627173254 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// stopService(svrTestIntent);
			if (iService != null) {
				iService.stopThread();
				AndroidUtilFunc.PL("stopThread");
			} else {
				AndroidUtilFunc.PL("iService==null");
			}

		}
	};

	OnClickListener mButton_function_listener_20140628150643 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			bindService(svrTestIntent, connection, BIND_AUTO_CREATE);
		}
	};

	OnClickListener mButton_function_listener_20140628150700 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			unbindService(connection);
			iService = null;
		}
	};

	OnClickListener mButton_function_listener_20140628154653 = new OnClickListener() {
		@Override
		public void onClick(View v) {
		}
	};

//	OnClickListener mButton_function_listener_20140628174859 = new OnClickListener() {
//		@Override
//		public void onClick(View v) {
//			// svrTestIntent = new Intent(
//			// "com.sephiroth.zzc.android_component.service.UseMemService");
//			// svrTestIntent.putExtra(MESSAGE_IN, "intent service test");
//			// startService(svrTestIntentServiceIntent);
//			Intent msgIntent = new Intent(MainActivity.this,
//					UseMemIntentService.class);
//
//			msgIntent.putExtra(MESSAGE_IN, "test intent service");
//			startService(msgIntent);
//		}
//	};

	OnClickListener mButton_function_listener_20140628174959 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// svrTestIntent = new Intent(
			// "com.sephiroth.zzc.android_component.service.UseMemService");
			// svrTestIntent.putExtra(MESSAGE_IN, "intent service test");
			stopService(svrTestIntentServiceIntent);
		}
	};
	// 2014-06-27 17:32:32 testService

	OnClickListener mButton_function_listener_20140312203803 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			intent = new Intent();
			// ComponentName componentName = new ComponentName(
			// "com.tencent.qqlive",
			// "com.tencent.qqlive.component.userinfo.LoginActivity");
			ComponentName componentName = new ComponentName(
					"com.tencent.qqlive",
					"com.tencent.qqlive.model.recommend.RecommendActivity");

			intent.setComponent(componentName);
			// intent.setAction(Intent.CATEGORY_DEFAULT);
			startActivity(intent);
		}
	};

	OnClickListener mButton_function_listener_20140312203804 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			intent = new Intent();
			// ComponentName componentName = new ComponentName(
			// "com.tencent.qqlive",
			// "com.tencent.qqlive.model.videoinfo.VidioDetailActivity");
			ComponentName componentName = new ComponentName(
					"com.tencent.qqlive",
					"com.tencent.qqlive.model.videolist.VideoListActivity");

			intent.setAction(Intent.CATEGORY_DEFAULT);
			intent.setComponent(componentName);
			startActivity(intent);
		}
	};

	OnClickListener mButton_function_listener_20140312203805 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			intent = new Intent();
			ComponentName componentName = new ComponentName(
					"com.tencent.qqlive",
					"com.tencent.qqlive.activity.VideoListActivity");

			intent.setComponent(componentName);
			startActivity(intent);
		}
	};

	OnClickListener mButton_function_listener_20140312203806 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			intent = new Intent();
			ComponentName componentName = new ComponentName(
					"com.tencent.qqlive",
					"com.tencent.qqlive.model.search.SearchActivity");

			intent.setComponent(componentName);
			startActivity(intent);
		}
	};

	// 2014-03-12 20:29:56 test intent

	// 2014-02-19 15:25:19 test shell
	void testFunction20140219152519() {
		mButton_01_function
				.setOnClickListener(mButton_01_function_listener_20140219152519);
	}

	OnClickListener mButton_01_function_listener_20140219152519 = new OnClickListener() {
		@SuppressWarnings("unused")
		@Override
		public void onClick(View v) {
			ProcessBuilder pb = new ProcessBuilder("/system/bin/sh");
			pb.directory(new File("/"));// ����shell�ĵ�ǰĿ¼��
			try {
				Process proc = pb.start();
				// ��ȡ������������ͨ������ȡSHELL�������
				BufferedReader in = new BufferedReader(new InputStreamReader(
						proc.getInputStream()));
				BufferedReader err = new BufferedReader(new InputStreamReader(
						proc.getErrorStream()));
				// ��ȡ�����������ͨ������SHELL�������
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(proc.getOutputStream())), true);
				out.println("pwd");
				out.println("su root");// ִ����һ��ʱ�ᵯ���Ի������³���Ҫ���������Ȩ��...����Ҫ���û�ȷ�ϡ�
				// out.println("cd /data/data");//���Ŀ¼��ϵͳ��Ҫ����rootȨ�޲ſ��Է��ʵġ�
				// out.println("ls -l");//�������������г���ǰ��װ��APK�������ļ����Ŀ¼����˵����������ROOTȨ�ޡ�
				String cmd = "cp -r /data/data/com.tencent.qqlive /sdcard/testpath/test";
				// Process p_start_test_service = Runtime.getRuntime().exec(
				// cmd);
				// out.println("cp -r /data/data/com.tencent.qqlive /sdcard/testpath/test");
				// out.println(cmd);
				out.println("ls");
				out.println("exit");
				proc.waitFor();
				String line;
				while ((line = in.readLine()) != null) {
					System.out.println(line);
				}
				while ((line = err.readLine()) != null) {
					System.out.println(line);
				}
				in.close();
				out.close();
				proc.destroy();
			} catch (Exception e) {
				System.out.println("exception:" + e);
			}
		}
	};

	// 2014-02-19 15:25:19 test shell

	// 2013-12-27 9:52:00 test dumphprof
	void testFunction20131227095200() {
		mButton_01_function
				.setOnClickListener(mButton_01_function_listener20131227095200);
	}

	OnClickListener mButton_01_function_listener20131227095200 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String package_name = "se.bright.android.agent";
			int pid = PackageUtil.getPidByPName(mActivityManager, package_name);
			String file_path = "/sdcard/hprof_" + package_name + "_"
					+ TimeManager.getTimeForFile();
			String cmd_dumphprof = "am dumpheap " + pid + " " + file_path;
			AndroidUtilFunc.PL(cmd_dumphprof);
			try {
				Process p = Runtime.getRuntime().exec(cmd_dumphprof);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						p.getInputStream()));
				String line = "";

				while ((line = in.readLine()) != null) {
					AndroidUtilFunc.PL(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
				AndroidUtilFunc.PL(e.toString());
			}
		}
	};

	// 2013-12-27 9:52:00 test dumphprof
	// 2013-12-25 11:54:30 test intent uri
	void testFunction2013122795342() {
		mButton_01_function
				.setOnClickListener(mButton_01_function_listener2013122795342);
	}

	OnClickListener mButton_01_function_listener2013122795342 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Uri uri = Uri
					.parse("tenvideo2://?action=1&cover_id=twt6ahdnsy0uqdd&demandVersion=2.6.0");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
	};
	// 2013-12-25 11:54:30 test intent uri

	// 2013-12-25 11:53:45 test intent
	// void testFunction(){
	// mButton_01_function.setOnClickListener(mButton_01_function_listener);
	// mButton_02_function.setOnClickListener(mButton_02_function_listener);
	// mButton_03_function.setOnClickListener(mButton_03_function_listener);
	// mButton_04_function.setOnClickListener(mButton_04_function_listener);
	// }
	//
	// OnClickListener mButton_01_function_listener = new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// intent =new Intent();
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.search.SearchResultNewActivity");
	// ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.setting.SettingAboutActivity");
	//
	// intent.setComponent(componentName);
	// // intent.setAction(Intent.CATEGORY_DEFAULT);
	// startActivity(intent);
	// }
	// };
	//
	// OnClickListener mButton_02_function_listener = new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// intent =new Intent();
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.search.SearchResultNewActivity");
	// ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.component.userinfo.LoginActivityLandscape");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.setting.MultiScreenScanResultActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.recommend.RecommendNewActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.search.SearchActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.search.CacheChoiceActivity");
	// intent.setComponent(componentName);
	// // intent.setAction(Intent.CATEGORY_DEFAULT);
	// startActivity(intent);
	// }
	// };
	//
	// OnClickListener mButton_03_function_listener = new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// intent =new Intent();
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.search.SearchResultNewActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.recommend.RecommendNewActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.com.tencent.appwallsdk.activity.QQAppWallActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.setting.MultiScreenScanResultActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.videoinfo.CacheChoiceActivity");
	// ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.activity.VideoListActivity");
	//
	// intent.setComponent(componentName);
	// // intent.setAction(Intent.CATEGORY_DEFAULT);
	// startActivity(intent);
	//
	// }
	// };
	//
	// OnClickListener mButton_04_function_listener = new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// intent =new Intent();
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.search.SearchResultNewActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.setting.MultiScreenScanResultActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.recommend.RecommendNewActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.search.CacheChoiceActivity");
	// ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.videolist.VideoListActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.model.setting.MultiScreenDownloadActivity");
	// // ComponentName componentName = new ComponentName("com.tencent.qqlive",
	// "com.tencent.qqlive.com.tencent.appwallsdk.activity.QQAppWallActivity");
	//
	// intent.setComponent(componentName);
	// // intent.setAction(Intent.CATEGORY_DEFAULT);
	// startActivity(intent);
	// }
	// };
	// 2013-12-25 11:53:45 test intent

	// 2013-12-23 17:39:55 test shake
	// //��Ӧ������
	// private SensorManager mSensorManager;
	//
	// //����
	// private Vibrator vibrator;
	//
	// void testFunction() {
	// // 1���Ӳ����Ϣ
	// mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	// vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
	// // activityManager = (ActivityManager)
	// // getSystemService(ACTIVITY_SERVICE);
	//
	// // 2 �жϵ�ǰ�ֻ��Ƿ�����ٶȸ�Ӧ�������������ֱ�ӽ���������������
	// List<Sensor> sensors = mSensorManager
	// .getSensorList(Sensor.TYPE_ACCELEROMETER);
	// if (sensors != null)
	// if (sensors.size() == 0)
	// return;
	//
	// // 4ע�������¼�
	// mSensorManager.registerListener(mSensorListener,
	// mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	// SensorManager.SENSOR_DELAY_NORMAL);
	// }
	//
	//
	//
	// // 3���ɸ�Ӧ�����¼�
	// SensorEventListener mSensorListener = new SensorEventListener() {
	// @Override
	// public void onAccuracyChanged(Sensor sensor, int accuracy) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// // ��Ӧ�������ı�
	// @Override
	// public void onSensorChanged(SensorEvent event) {
	// // // TODO Auto-generated method stub
	// // int sensorType = event.sensor.getType();
	// //
	// // // ��ȡҡһҡ����ֵ
	// // // int
	// // //
	// //
	// shakeSenseValue=Integer.parseInt(getResources().getString(R.string.shakeSenseValue));
	// // int shakeSenseValue = 50;
	// // // values[0]:X�ᣬvalues[1]��Y�ᣬvalues[2]��Z��
	// // float[] values = event.values;
	// //
	// // if (sensorType == Sensor.TYPE_ACCELEROMETER) {
	// // if ((Math.abs(values[0]) > shakeSenseValue
	// // || Math.abs(values[1]) > shakeSenseValue || Math
	// // .abs(values[2]) > shakeSenseValue)) {
	// // // �����¼���ִ�д�Ӧ����Ϊ
	// // vibrator.vibrate(500);
	// // AndroidUtilFunc.PL("shake once!!");
	// // }
	// // }
	//
	// // ��������Ϣ�ı�ʱִ�и÷���
	// float[] values = event.values;
	// float x = values[0]; // x�᷽����������ٶȣ�����Ϊ��
	// float y = values[1]; // y�᷽����������ٶȣ���ǰΪ��
	// float z = values[2]; // z�᷽����������ٶȣ�����Ϊ��
	// AndroidUtilFunc.PL("x�᷽����������ٶ�" + x + "��y�᷽����������ٶ�" + y
	// + "��z�᷽����������ٶ�" + z);
	// // һ����������������������ٶȴﵽ40�ʹﵽ��ҡ���ֻ���״̬��
	// int medumValue = 19;// ���� i9250��ô�ζ����ᳬ��20��û�취��ֻ����19��
	// if (Math.abs(x) > medumValue || Math.abs(y) > medumValue
	// || Math.abs(z) > medumValue) {
	// vibrator.vibrate(200);
	// AndroidUtilFunc.PL("shake once!!");
	// }
	// }
	// };
	//
	// protected void onResume() {
	// super.onResume();
	// if (mSensorManager != null) {// ע�������
	// mSensorManager.registerListener(mSensorListener,
	// mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	// SensorManager.SENSOR_DELAY_NORMAL);
	// // ��һ��������Listener���ڶ������������ô��������ͣ�����������ֵ��ȡ��������Ϣ��Ƶ��
	// }
	// }
	//
	// protected void onPause() {
	// super.onPause();
	// if (mSensorManager != null) {// ȡ��������
	// mSensorManager.unregisterListener(mSensorListener);
	// }
	// }
	// 2013-12-23 17:39:55 test shake

	// 2013-12-19 9:37:45 test intent
	OnClickListener mButton_01_function_listener20131223173406 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			intent = new Intent();
			ComponentName componentName = new ComponentName(
					"com.tencent.qqlive",
					"com.tencent.qqlive.model.recommend.RecommendNewActivity");
			// ComponentName componentName = new
			// ComponentName("com.tencent.qqlive",
			// "com.tencent.qqlive.activity.WelcomeActivity");
			// intent.setClassName("com.tencent.qqlive",
			// "com.tencent.qqlive.activity.WelcomeActivity");
			intent.setComponent(componentName);
			startActivity(intent);
		}
	};

	OnClickListener mButton_02_function_listener20131223173410 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			intent = new Intent();
			// ComponentName componentName = new
			// ComponentName("com.tencent.qqlive",
			// "com.tencent.qqlive.model.recommend.RecommendNewActivity");
			ComponentName componentName = new ComponentName(
					"com.tencent.qqlive",
					"com.tencent.qqlive.activity.WelcomeActivity");
			// intent.setClassName("com.tencent.qqlive",
			// "com.tencent.qqlive.activity.WelcomeActivity");
			intent.setComponent(componentName);
			startActivity(intent);
		}
	};
	// 2013-12-19 9:37:45 test intent

	// 2013-12-19 9:37:07 test uid
	OnClickListener mButton_function_listener_2013121994202 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// String mProcessName="com.tencent.qqlive";
			//
			// // String cmd_gt_init =
			// "am broadcast -a com.tencent.wstt.gt.baseCommand.startTest --es procName \""
			// // + mProcessName + "\" --ei procId " +
			// PackageUtil.getPidByPName(mActivityManager, mProcessName);
			//
			// String cmd_gt_init =
			// "am broadcast -a com.tencent.wstt.gt.baseCommand.startTest --es procName \"com.tencent.qqlive\" --ei procId 21077";
			// UtilFunc.PL(cmd_gt_init);
			// try {
			// Process p = Runtime.getRuntime().exec(cmd_gt_init);
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			int uid = PackageUtil.getUidByPName(mActivityManager,
					"com.tencent.qqlive");
			AndroidUtilFunc.PL(uid);
			mText_01.setText("com.tencent.qqlive uid = " + uid);
		}
	};
	// 2013-12-19 9:37:07 test uid

	// 2013-12-2 18:49:24 leonzhong
	OnClickListener mButton_function_listener_2013121994155 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// String mProcessName="com.tencent.qqlive";
			//
			// // String cmd_gt_init =
			// "am broadcast -a com.tencent.wstt.gt.baseCommand.startTest --es procName \""
			// // + mProcessName + "\" --ei procId " +
			// PackageUtil.getPidByPName(mActivityManager, mProcessName);
			//
			// String cmd_gt_init =
			// "am broadcast -a com.tencent.wstt.gt.baseCommand.startTest --es procName \"com.tencent.qqlive\" --ei procId 21077";
			// UtilFunc.PL(cmd_gt_init);
			// try {
			// Process p = Runtime.getRuntime().exec(cmd_gt_init);
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			intent = new Intent()
					.setAction("com.tencent.wstt.gt.baseCommand.startTest")
					.putExtra("procName", "com.tencent.qqlive")
					.putExtra("procId", 21077);
			sendBroadcast(intent);
		}
	};

	OnClickListener mButton_function_listener_2013121994115 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//
			// String cmd_startmemlog =
			// "am broadcast -a com.tencent.wstt.gt.baseCommand.sampleData --ei mem 1";
			// UtilFunc.PL(cmd_startmemlog);
			// try {
			// Process p = Runtime.getRuntime().exec(cmd_startmemlog);
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			intent = new Intent().setAction(
					"com.tencent.wstt.gt.baseCommand.sampleData").putExtra(
					"mem", 1);
			sendBroadcast(intent);
		}
	};

	OnClickListener mButton_function_listener_2013121994102 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// String mProcessName="com.tencent.qqlive";
			//
			// String cmd_stopmemlog =
			// "am broadcast -a com.tencent.wstt.gt.baseCommand.sampleData --ei mem 0";
			// UtilFunc.PL(cmd_stopmemlog);
			// try {
			// Process p = Runtime.getRuntime().exec(cmd_stopmemlog);
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			intent = new Intent().setAction(
					"com.tencent.wstt.gt.baseCommand.sampleData").putExtra(
					"mem", 0);
			sendBroadcast(intent);
		}
	};

	OnClickListener mButton_function_listener_2013121994121 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// String mProcessName="com.tencent.qqlive";
			//
			// String cmd_savememlog =
			// "am broadcast -a com.tencent.wstt.gt.baseCommand.endTest --es saveFileName \"mem_test_12013-11-24-11-44-13\"";
			// UtilFunc.PL(cmd_savememlog);
			// try {
			// Process p = Runtime.getRuntime().exec(cmd_savememlog);
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			intent = new Intent().setAction(
					"com.tencent.wstt.gt.baseCommand.endTest").putExtra(
					"saveFileName", "mem_ahahahahah");
			sendBroadcast(intent);
		}
	};
	// 2013-12-2 18:49:24 leonzhong

	OnClickListener mButton_function_listener_2013121994140 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			AndroidUtilFunc.PL(PackageUtil.getPidByPName(mActivityManager,
					"com.tencent.qqlive"));
			PackageUtil.printProcessList(mActivityManager);
		}
	};

	// ����instrument���ԣ����ҽ�����̨���������ļ�
	OnClickListener mButton_function_listener_2013121994145 = new OnClickListener() {
		@SuppressWarnings("unused")
		@Override
		public void onClick(View v) {
			AndroidUtilFunc.PL("start");

			// Ҫִ�е�������
			// String cmd="ls";
			// String
			// cmd="am instrument -w com.tencent.qqlive.test/android.test.InstrumentationTestRunner 1>/mnt/sdcard/GT/test_2013-11-23-151255.log 2>&1";
			String cmd_start_test_service = "am instrument -w com.tencent.qqlive.test/android.test.InstrumentationTestRunner";
			String cmd_startgt = "am start -n com.tencent.wstt.gt/com.tencent.wstt.gt.activity.SplashActivity";

			try {
				Process p_startgt = Runtime.getRuntime().exec(cmd_startgt);
				AndroidUtilFunc.PL("gt started");
				// Thread.sleep(3000);

				Process p_start_test_service = Runtime.getRuntime().exec(
						cmd_start_test_service);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						p_start_test_service.getInputStream()));
				String line = "";

				File f = new File(AndroidUtil.LOG_FILE_ROOT_PATH
						+ AndroidUtil.LOG_NAME + TimeManager.getTimeForFile()
						+ AndroidUtil.ROBO_LOG_EXNAME);

				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}

				f.createNewFile();

				BufferedWriter output = new BufferedWriter(new FileWriter(f));

				while ((line = in.readLine()) != null) {
					AndroidUtilFunc.PL(line);
					output.write(line + "\n");
				}

				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
