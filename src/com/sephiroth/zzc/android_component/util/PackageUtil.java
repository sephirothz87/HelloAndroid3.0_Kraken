package com.sephiroth.zzc.android_component.util;

import java.util.List;

import android.app.ActivityManager;
import android.util.Log;

public class PackageUtil {
	
	/**
	 * ȡ�ý����б�
	 * @param act_mgr	ActivityManager������Activity�ڻ�ȡ��ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	 * @return	�����б�
	 */
	public static List<ActivityManager.RunningAppProcessInfo> getAppProcessInfoList(
			ActivityManager act_mgr) {
		List<ActivityManager.RunningAppProcessInfo> appProcessList = act_mgr
				.getRunningAppProcesses();
		return appProcessList;
	}
	
	/**
	 * ��ӡ�������ƺͽ���id�б�
	 * @param act_mgr	ActivityManager������Activity�ڻ�ȡ��ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
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
	 * ����ָ�����������pid
	 * @param act_mgr	ActivityManager������Activity�ڻ�ȡ��ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	 * @param p_name	����������"com.tencent.qqlive"
	 * @return	����id,pid
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
	 * ����ָ�����������uid
	 * @param act_mgr	ActivityManager������Activity�ڻ�ȡ��ActivityManager act_mgr = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	 * @param p_name	����������"com.tencent.qqlive"
	 * @return	�û�id��uid
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
}
