package com.sephiroth.zzc.android_component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;

public class AssetsManager {

	/**
	 * 
	 * 复制Assets目录下的文件到指定目录
	 * 无视目标文件是否存在，一律覆盖
	 * 
	 * 例如根目录下的文件a.txt，拷贝到sd卡并命名为aa.log
	 * copyFileTo("a.txt","/sdcard/path/aa.log",context)
	 * 
	 * 复制子目录下的文件/path/a.txt，拷贝到sd卡并命名为aa.log
	 * copyFileTo("path/a.txt","sdcard/aa.log",context)
	 * 
	 * @param assetsPath
	 *            assets目录中的文件路径
	 * @param desPath
	 *            目标文件路径
	 * @param myContext
	 *            调用的Context
	 * @return true 复制成功 false 复制失败
	 */
	public static boolean copyFileTo(String assetsPath, String desPath,
			Context context) {

		File f = new File(desPath);

		if (!f.getParentFile().exists()) {
			// 目标文件所在目录不存在
			if (!f.getParentFile().mkdirs()) {
				android.util.Log
						.d("AssetsManager copyFileTo", "目标目录不存在，创建目录失败");
				return false;
			}
		}

		// 复制文件
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;

		try {

			in = context.getResources().getAssets().open(assetsPath);

			out = new FileOutputStream(f);
			byte[] buffer = new byte[1024];

			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 复制Assets目录下的文件到指定目录
	 * 当目标文件存在是，根据是否覆参数决定
	 * 
	 * 例如根目录下的文件a.txt，拷贝到sd卡并命名为aa.log
	 * copyFileTo("a.txt","/sdcard/path/aa.log",context)
	 * 
	 * 复制子目录下的文件/path/a.txt，拷贝到sd卡并命名为aa.log
	 * copyFileTo("path/a.txt","sdcard/aa.log",context)
	 * 
	 * @param assetsPath
	 *            assets目录中的文件路径
	 * @param desPath
	 *            目标文件路径
	 * @param context
	 *            调用的Context
	 * @param isOverride
	 *            是否覆盖原有文件
	 * @return true 复制成功 false 复制失败
	 */
	public static boolean copyFileTo(String assetsPath, String desPath,
			Context context,boolean isOverride){
		
		if(!isOverride&&new File(desPath).exists()){
			android.util.Log
			.d("AssetsManager copyFileTo", "不覆盖目标文件且目标文件存在，不进行拷贝，返回false");
			return false;
		}else{
			//否则均执行强行拷贝逻辑
			return copyFileTo(assetsPath,desPath,context);
		}
	}
}
