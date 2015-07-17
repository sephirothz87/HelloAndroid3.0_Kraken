package com.sephiroth.zzc.android_component.util;

import com.sephiroth.zzc.java_common.service.Logger;

public class AndroidUtil {
	public final static String COMMON_VERSION_CODE="3.0.0007";
	public final static String COMMON_VERSION_NAME="Kraken";
	public final static String COMPONENT_SIGN="leonzhong";
	
	public final static String LOG_TAG="AndroidComponent";
	public final static String LOG_FILE_ROOT_PATH="/mnt/sdcard/testpath/";
	public final static String LOG_NAME="Kraken";
	

	public final static String ROBO_LOG_EXNAME=".log";

	public static Logger Logger=new Logger(LOG_FILE_ROOT_PATH+LOG_NAME+ROBO_LOG_EXNAME,LOG_TAG);
}