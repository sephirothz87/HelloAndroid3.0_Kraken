package com.sephiroth.zzc.android_component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class NoUse {
	final static int UNIT = 1024;

	public static void CreateSpeFileByKB(int kb, String path) throws IOException {
		File f = new File(path);
		if (!f.exists()) {
			f.getParentFile().mkdirs();
			f.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(f);
		byte[] buffer = new byte[kb * UNIT]; // 直接使用内存创建
		fos.write(buffer);
		fos.close();
	}

	public static void CreateSpeFileByMB(int mb, String path) throws IOException {
		File f = new File(path);
		if (!f.exists()) {
			f.getParentFile().mkdirs();
			f.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(f);
		for (int i = 0; i < mb; i++) {
			byte[] buffer = new byte[UNIT * UNIT]; // 1次1M
			fos.write(buffer);
		}
		fos.close();
	}

	public static void CreateSpeFileByGB(int gb, String path) throws IOException {
		File f = new File(path);
		if (!f.exists()) {
			f.getParentFile().mkdirs();
			f.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(f);
		for (int i = 0; i < gb * (UNIT / 4); i++) {
			byte[] buffer = new byte[4 * UNIT * UNIT]; // 1次4M
			fos.write(buffer);
		}
		fos.close();
	}
}
