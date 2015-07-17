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
	 * ����AssetsĿ¼�µ��ļ���ָ��Ŀ¼
	 * ����Ŀ���ļ��Ƿ���ڣ�һ�ɸ���
	 * 
	 * �����Ŀ¼�µ��ļ�a.txt��������sd��������Ϊaa.log
	 * copyFileTo("a.txt","/sdcard/path/aa.log",context)
	 * 
	 * ������Ŀ¼�µ��ļ�/path/a.txt��������sd��������Ϊaa.log
	 * copyFileTo("path/a.txt","sdcard/aa.log",context)
	 * 
	 * @param assetsPath
	 *            assetsĿ¼�е��ļ�·��
	 * @param desPath
	 *            Ŀ���ļ�·��
	 * @param myContext
	 *            ���õ�Context
	 * @return true ���Ƴɹ� false ����ʧ��
	 */
	public static boolean copyFileTo(String assetsPath, String desPath,
			Context context) {

		File f = new File(desPath);

		if (!f.getParentFile().exists()) {
			// Ŀ���ļ�����Ŀ¼������
			if (!f.getParentFile().mkdirs()) {
				android.util.Log
						.d("AssetsManager copyFileTo", "Ŀ��Ŀ¼�����ڣ�����Ŀ¼ʧ��");
				return false;
			}
		}

		// �����ļ�
		int byteread = 0; // ��ȡ���ֽ���
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
	 * ����AssetsĿ¼�µ��ļ���ָ��Ŀ¼
	 * ��Ŀ���ļ������ǣ������Ƿ񸲲�������
	 * 
	 * �����Ŀ¼�µ��ļ�a.txt��������sd��������Ϊaa.log
	 * copyFileTo("a.txt","/sdcard/path/aa.log",context)
	 * 
	 * ������Ŀ¼�µ��ļ�/path/a.txt��������sd��������Ϊaa.log
	 * copyFileTo("path/a.txt","sdcard/aa.log",context)
	 * 
	 * @param assetsPath
	 *            assetsĿ¼�е��ļ�·��
	 * @param desPath
	 *            Ŀ���ļ�·��
	 * @param context
	 *            ���õ�Context
	 * @param isOverride
	 *            �Ƿ񸲸�ԭ���ļ�
	 * @return true ���Ƴɹ� false ����ʧ��
	 */
	public static boolean copyFileTo(String assetsPath, String desPath,
			Context context,boolean isOverride){
		
		if(!isOverride&&new File(desPath).exists()){
			android.util.Log
			.d("AssetsManager copyFileTo", "������Ŀ���ļ���Ŀ���ļ����ڣ������п���������false");
			return false;
		}else{
			//�����ִ��ǿ�п����߼�
			return copyFileTo(assetsPath,desPath,context);
		}
	}
}
