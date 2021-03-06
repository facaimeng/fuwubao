/**
 * www.yiji.com Inc.
 * Copyright (c) 2012 All Rights Reserved.
 */
package com.cbai.common.utils;

import java.security.MessageDigest;

public class MD5Util {

	public static String getMD5_16(String plainText) {
		String md5_16bit = null;
		try {
			md5_16bit = getMD5_32(plainText).substring(8, 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5_16bit;
	}

	public static String getMD5_32(String plainText) {
		String md5_32bit = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			md5_32bit = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5_32bit;
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.getMD5_16("1234567"));
		System.out.println(MD5Util.getMD5_32("123456"));
	}

}
