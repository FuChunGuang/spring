package com.spring.fcg.util;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZipTest {
	@Test
	public void test1() {
        //加密.
        Zip4jUtil zip4jUtil  = new Zip4jUtil();
        zip4jUtil.setSrcPath("D:\\test\\src.zip");//不只是可以加密zip的,加密普通文件夹也可以,文件也可
        zip4jUtil.setDstPath("D:\\test\\dst.zip");
        zip4jUtil.setPassword("123");
        zip4jUtil.encrypt();
 
        //解密.
        zip4jUtil.setSrcPath("D:\\test.zip");
        zip4jUtil.setDstPath("D:\\test\\");
        zip4jUtil.setPassword("123456");
        zip4jUtil.decrypt();
	}
//	@Test
	public void test2() {
		try {  
	           //创建zip文件  
	            ZipFile zipFile = new ZipFile("d:/test.zip");  
	               
	            //增加文件到zip中  
	            ArrayList<File> filesToAdd = new ArrayList<File>();  
	            filesToAdd.add(new File("d:/test1.txt"));  
	            filesToAdd.add(new File("d:/test2.txt"));  
	               
	            //初始化各类参数  
	            ZipParameters parameters = new ZipParameters();  
	//设置压缩模式  
	            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);   
	               
	            //DEFLATE_LEVEL_FASTEST     - Lowest compression level but higher speed of compression  
	            //DEFLATE_LEVEL_FAST        - Low compression level but higher speed of compression  
	            //DEFLATE_LEVEL_NORMAL  - Optimal balance between compression level/speed  
	            //DEFLATE_LEVEL_MAXIMUM     - High compression level with a compromise of speed  
	            //DEFLATE_LEVEL_ULTRA       - Highest compression level but low speed  
	            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);  
	               
	             //设置加密标志  
	            parameters.setEncryptFiles(true);  
	               
	            //设置aes加密  
	            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);  
	               
	            //AES_STRENGTH_128 - For both encryption and decryption  
	            //AES_STRENGTH_192 - For decryption only  
	            //AES_STRENGTH_256 - For both encryption and decryption  
	  
	          
	            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);  
	               
	            //设置密码  
	            parameters.setPassword("123456");  
	               
	         
	            zipFile.addFiles(filesToAdd, parameters);  
	        }  
	        catch (ZipException e)  
	        {  
	            e.printStackTrace();  
	        }  
	}
}
