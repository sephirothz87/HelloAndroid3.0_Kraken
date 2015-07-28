package com.sephiroth.zzc.android_component.util;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class PackageUtil {

	/**
	 * 取得进程列表
	 * 
	 * @param act_mgr ActivityManager对象，在Activity内获取：ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	 * @return 进程列表
	 */
	public static List<ActivityManager.RunningAppProcessInfo> getAppProcessInfoList(
			ActivityManager act_mgr) {
		List<ActivityManager.RunningAppProcessInfo> appProcessList = act_mgr
				.getRunningAppProcesses();
		return appProcessList;
	}

	/**
	 * 打印进程名称和进程id列表
	 * 
	 * @param act_mgr ActivityManager对象，在Activity内获取：ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	 */
	public static void printProcessList(ActivityManager act_mgr) {
		List<ActivityManager.RunningAppProcessInfo> appProcessList = getAppProcessInfoList(act_mgr);
		for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessList) {
			// UtilFunc.PL("pid = "+appProcessInfo.pid+" p_name = "+appProcessInfo.processName);
			Log.d("PackageUtil", "pid = " + appProcessInfo.pid + " p_name = "
					+ appProcessInfo.processName);
		}
	}

	/**
	 * 根据指定进程名获得RunningAppProcessInfo对象
	 * 
	 * @param act_mgr ActivityManager对象，在Activity内获取：ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	 * @return RunningAppProcessInfo对象，如果进程没有在运行，则返回null
	 */
	public static RunningAppProcessInfo getProcessInfoByPName(
			ActivityManager act_mgr, String p_name) {
		List<ActivityManager.RunningAppProcessInfo> appProcessList = getAppProcessInfoList(act_mgr);
		for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessList) {
			if (appProcessInfo.processName.equals(p_name)) {
				return appProcessInfo;
			}
		}
		return null;
	}

	/**
	 * 根据指定进程名获得pid
	 * 
	 * @param act_mgr ActivityManager对象，在Activity内获取：ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	 * @param p_name 进程名，如"com.tencent.qqlive"
	 * @return 进程id,pid
	 */
	public static int getPidByPName(ActivityManager act_mgr, String p_name) {
		int pid = -1;
		List<ActivityManager.RunningAppProcessInfo> appProcessList = getAppProcessInfoList(act_mgr);
		for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessList) {
			if (appProcessInfo.processName.equals(p_name)) {
				pid = appProcessInfo.pid;
				break;
			}
		}
		return pid;
	}

	/**
	 * 根据指定进程名获得uid
	 * 
	 * @param act_mgr ActivityManager对象，在Activity内获取：ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	 * @param p_name 进程名，如"com.tencent.qqlive"
	 * @return 用户id，uid
	 */
	public static int getUidByPName(ActivityManager act_mgr, String p_name) {
		int uid = -1;
		List<ActivityManager.RunningAppProcessInfo> appProcessList = getAppProcessInfoList(act_mgr);
		for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessList) {
			if (appProcessInfo.processName.equals(p_name)) {
				uid = appProcessInfo.uid;
				break;
			}
		}
		return uid;
	}

	/**
	 * 获取当前在前台运行进程的进程名
	 * 
	 * @param act_mgr ActivityManager对象，在Activity内获取：ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	 * @return 当前在前台运行进程的进程名
	 */
	public static String getTopProcess(ActivityManager act_mgr) {
		String res = "";
		List<ActivityManager.RunningAppProcessInfo> appProcessList = getAppProcessInfoList(act_mgr);
		for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessList) {
			if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				res = appProcessInfo.processName;
				return res;
			}
		}
		return res;
	}

	/**
	 * 判断对应进程是否是在前台运行
	 * 
	 * @param act_mgr ActivityManager对象，在Activity内获取：ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	 * @return 进程是否是在前台运行
	 */
	public static boolean isTopProcess(ActivityManager act_mgr,
			RunningAppProcessInfo info) {
		if (info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断指定进程是否是在前台运行
	 * 
	 * @param act_mgr ActivityManager对象，在Activity内获取：ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	 * @param p_name 进程名，如"com.tencent.qqlive"
	 * @return 进程是否是在前台运行
	 */
	public static boolean isTopProcess(ActivityManager act_mgr, String p_name) {

		List<ActivityManager.RunningAppProcessInfo> appProcessList = getAppProcessInfoList(act_mgr);
		for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessList) {
			if (appProcessInfo.processName.equals(p_name)) {
				if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * 根据包名取得启动Activity的Class名
	 * 
	 * @param pkgManager pkgManager对象，Service中调用getPackageManager()取得
	 * @param pkgName 包名
	 * @return　对应程序的启动Activity的Class名
	 */
	public static String getClassNameByPkgName(PackageManager pkgManager,
			String pkgName) {
		String clsName = null;

		PackageInfo packageinfo = null;
		try {
			packageinfo = pkgManager.getPackageInfo(pkgName, 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		if (packageinfo == null) {
			return null;
		}

		Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
		resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		resolveIntent.setPackage(packageinfo.packageName);

		List<ResolveInfo> resolveinfoList = pkgManager.queryIntentActivities(
				resolveIntent, 0);

		ResolveInfo resolveinfo = resolveinfoList.iterator().next();

		if (resolveinfo != null) {
			clsName = resolveinfo.activityInfo.name;
		}

		return clsName;
	}
}
