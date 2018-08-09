package com.spring.fcg.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TxtUtil {
	private static final Logger logger =LoggerFactory.getLogger(TxtUtil.class);
	public static void main(String[] args) throws Exception {
		writeTxt();
	}
	public static void writeTxt() {
		String nextLine="\r\n";  
		FileWriter fw;
		File file = new File("D://1.txt");
		long startTime=0L;   //获取开始时间
		long endTime=0l; //获取结束时间
		try {
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			startTime=System.currentTimeMillis();   //获取开始时间
			for (int i = 0; i < 2; i++) {
				bw.write("星星@@月亮@@太阳"+nextLine);
			}
			endTime=System.currentTimeMillis(); //获取结束时间
			logger.debug("程序运行时间： "+(endTime-startTime)+"ms");
			bw.flush();
			bw.close();
		} catch (IOException e) {// TODO Auto-generated catch blocke.printStackTrace();}
		}
	}
	public static void readTxt() {
		
		
	}
}
