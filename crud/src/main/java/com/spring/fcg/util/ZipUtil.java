package com.spring.fcg.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipUtil {

	private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);

	/**
	 * 使用GBK编码可以避免压缩中文文件名乱码
	 */
	private static final String CHINESE_CHARSET = "GBK";

	/**
	 * 文件读取缓冲区大小
	 */
	private static final int CACHE_SIZE = 1024;

	private ZipUtil() {
		// 私用构造主法.因为此类是工具类.
	}

	/**
	 * <p>
	 * 压缩文件
	 * </p>
	 * 
	 * @param sourceFolder
	 *            需压缩文件 或者 文件夹 路径
	 * @param zipFilePath
	 *            压缩文件输出路径
	 * @throws Exception
	 */
	public static void zip(String sourceFolder, String zipFilePath) throws Exception {
		logger.debug("开始压缩 [" + sourceFolder + "] 到 [" + zipFilePath + "]");
		OutputStream out = new FileOutputStream(zipFilePath);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		ZipOutputStream zos = new ZipOutputStream(bos);
		// 解决中文文件名乱码
		File file = new File(sourceFolder);
		String basePath = null;
		if (file.isDirectory()) {
			basePath = file.getPath();
		} else {
			basePath = file.getParent();
		}
		zipFile(file, basePath, zos);
		zos.closeEntry();
		zos.close();
		bos.close();
		out.close();
		logger.debug("压缩 [" + sourceFolder + "] 完成！");
	}

	/**
	 * <p>
	 * 压缩文件
	 * </p>
	 * 
	 * @param sourceFolders
	 *            一组 压缩文件夹 或 文件
	 * @param zipFilePath
	 *            压缩文件输出路径
	 * @throws Exception
	 */
	public static void zip(String[] sourceFolders, String zipFilePath) throws Exception {
		OutputStream out = new FileOutputStream(zipFilePath);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		ZipOutputStream zos = new ZipOutputStream(bos);
		// 解决中文文件名乱码

		for (int i = 0; i < sourceFolders.length; i++) {
			logger.debug("开始压缩 [" + sourceFolders[i] + "] 到 [" + zipFilePath + "]");
			File file = new File(sourceFolders[i]);
			String basePath = null;
			basePath = file.getParent();
			zipFile(file, basePath, zos);
		}

		zos.closeEntry();
		zos.close();
		bos.close();
		out.close();
	}

	/**
	 * <p>
	 * 递归压缩文件
	 * </p>
	 * 
	 * @param parentFile
	 * @param basePath
	 * @param zos
	 * @throws Exception
	 */
	private static void zipFile(File parentFile, String basePath, ZipOutputStream zos) throws Exception {
		File[] files = new File[0];
		if (parentFile.isDirectory()) {
			files = parentFile.listFiles();
		} else {
			files = new File[1];
			files[0] = parentFile;
		}
		String pathName;
		InputStream is;
		BufferedInputStream bis;
		byte[] cache = new byte[CACHE_SIZE];
		for (File file : files) {
			if (file.isDirectory()) {
				logger.debug("目录：" + file.getPath());

				basePath = basePath.replace('\\', '/');
				if (basePath.substring(basePath.length() - 1).equals("/")) {
					pathName = file.getPath().substring(basePath.length()) + "/";
				} else {
					pathName = file.getPath().substring(basePath.length() + 1) + "/";
				}

				zos.putNextEntry(new ZipEntry(pathName));
				zipFile(file, basePath, zos);
			} else {
				pathName = file.getPath().substring(basePath.length());
				pathName = pathName.replace('\\', '/');
				if (pathName.substring(0, 1).equals("/")) {
					pathName = pathName.substring(1);
				}

				logger.debug("压缩：" + pathName);

				is = new FileInputStream(file);
				bis = new BufferedInputStream(is);
				zos.putNextEntry(new ZipEntry(pathName));
				int nRead = 0;
				while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
					zos.write(cache, 0, nRead);
				}
				bis.close();
				is.close();
			}
		}
	}

	/**
	 * 解压zip文件
	 * 
	 * @param zipFileName
	 *            待解压的zip文件路径，例如：c:\\a.zip
	 * 
	 * @param outputDirectory
	 *            解压目标文件夹,例如：c:\\a\
	 */
	public static void unZip(String zipFileName, String outputDirectory) throws Exception {
		logger.debug("开始解压 [" + zipFileName + "] 到 [" + outputDirectory + "]");
		ZipFile zipFile = new ZipFile(zipFileName);

		try {

			Enumeration<?> e = zipFile.entries();

			ZipEntry zipEntry = null;

			createDirectory(outputDirectory, "");

			while (e.hasMoreElements()) {

				zipEntry = (ZipEntry) e.nextElement();

				logger.debug("解压：" + zipEntry.getName());

				if (zipEntry.isDirectory()) {

					String name = zipEntry.getName();

					name = name.substring(0, name.length() - 1);

					File f = new File(outputDirectory + File.separator + name);

					f.mkdir();

					logger.debug("创建目录：" + outputDirectory + File.separator + name);

				} else {

					String fileName = zipEntry.getName();

					fileName = fileName.replace('\\', '/');

					if (fileName.indexOf("/") != -1) {

						createDirectory(outputDirectory, fileName.substring(0, fileName.lastIndexOf("/")));

						fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());

					}

					File f = new File(outputDirectory + File.separator + zipEntry.getName());

					f.createNewFile();

					InputStream in = zipFile.getInputStream(zipEntry);

					FileOutputStream out = new FileOutputStream(f);

					byte[] by = new byte[1024];

					int c;

					while ((c = in.read(by)) != -1) {

						out.write(by, 0, c);

					}

					in.close();

					out.close();

				}

			}
			logger.debug("解压 [" + zipFileName + "] 完成！");

		} catch (Exception ex) {

			System.out.println(ex.getMessage());

		} finally {
			zipFile.close();
		}

	}

	/**
	 * 创建目录
	 * 
	 * @param directory
	 * @param subDirectory
	 */
	private static void createDirectory(String directory, String subDirectory) {

		String dir[];

		File fl = new File(directory);

		try {

			if (subDirectory == "" && fl.exists() != true) {

				fl.mkdir();

			} else if (subDirectory != "") {

				dir = subDirectory.replace('\\', '/').split("/");

				for (int i = 0; i < dir.length; i++) {

					File subFile = new File(directory + File.separator + dir[i]);

					if (subFile.exists() == false)

						subFile.mkdir();

					directory += File.separator + dir[i];

				}

			}

		} catch (Exception ex) {

			System.out.println(ex.getMessage());

		}

	}

	/**
	 * 无需解压直接读取Zip文件和文件内容
	 * 
	 * @param file
	 *            文件
	 * @throws Exception
	 */
	public static void readZipFile(String file) throws Exception {
		java.util.zip.ZipFile zipFile = new java.util.zip.ZipFile(file);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		java.util.zip.ZipInputStream zin = new java.util.zip.ZipInputStream(in);
		java.util.zip.ZipEntry ze;
		long size = 0L;
		BufferedReader br = null;
		String line = "";
		while ((ze = zin.getNextEntry()) != null) {
			if (ze.isDirectory()) {
			} else {
				logger.info("file - " + ze.getName() + " : " + ze.getSize() + " bytes");
				size = ze.getSize();
				if (size > 0) {
					br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(ze)));
					while ((line = br.readLine()) != null) {
						System.out.println(line);
					}
					br.close();
				}
				System.out.println();
			}
		}
		zin.closeEntry();
	}

	/**
	 * 
	 * @title: delFile
	 * @Description: 删除文件
	 * @author: FuChunGuang
	 * @Data: 2018年8月7日下午8:20:58
	 * @param path
	 * @param filename
	 *            void
	 * @throws:
	 * @version: V1.0
	 */
	public static void delFile(String path, String filename) {
		File file = new File(path + "/" + filename);
		if (file.exists() && file.isFile()) {
			file.delete();
		}
	}

	/**
	 * 
	 * @title: delDir
	 * @Description: 删除目录
	 * @author: FuChunGuang
	 * @Data: 2018年8月7日下午8:25:42
	 * @param path
	 *            void
	 * @throws:
	 * @version: V1.0
	 */
	public static void delDir(String path) {
		File dir = new File(path);
		if (dir.exists()) {
			File[] tmp = dir.listFiles();
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i].isDirectory()) {
					delDir(path + "/" + tmp[i].getName());
				} else {
					tmp[i].delete();
				}
			}
			dir.delete();
		}
	}

	/**
	 * 
	 * @title: createDir
	 * @Description: 创建目录
	 * @author: FuChunGuang
	 * @Data: 2018年8月7日下午8:32:04
	 * @param path
	 *            路径 void
	 * @throws:
	 * @version: V1.0
	 */
	public static void createDir(String path) {
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();
	}

	/**
	 * 
	 * @title: createFile
	 * @Description: 创建文件
	 * @author: FuChunGuang
	 * @Data: 2018年8月7日下午8:31:51
	 * @param path
	 *            路径
	 * @param filename
	 *            文件名
	 * @throws IOException
	 *             void
	 * @throws:
	 * @version: V1.0
	 */
	public static void createFile(String path, String filename) throws IOException {
		File file = new File(path + "/" + filename);
		if (!file.exists())
			file.createNewFile();
	}

	/**
	 * 
	 * @title: readTxtZipFile
	 * @Description: 无需解压读取txt
	 * @author: FuChunGuang
	 * @Data: 2018年8月8日下午1:10:36
	 * @param file
	 * @return
	 * @throws Exception
	 *             Object
	 * @throws:
	 * @version: V1.0
	 */
	public static Object readTxtZipFile(String file) throws Exception {
		// 获取压缩文件
		ZipFile zipFile = new ZipFile(file);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in);
		// 文件对象
		ZipEntry ze;
		long size = 0L;
		BufferedReader br = null;
		String line = "";
		String[] content = new String[150];
		while ((ze = zin.getNextEntry()) != null) {
			if (ze.isDirectory()) {
			} else {
				if (ze.getName().endsWith(".txt")) {
					logger.info("file - " + ze.getName() + " : " + ze.getSize() + " bytes");
					size = ze.getSize();
					if (size > 0) {
						br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(ze), "gbk"));
						// 读取一行数据
						while ((line = br.readLine()) != null) {
							// 解析
							content = line.split("@@");
							System.out.println(line);
							System.out.println(content[0]);
							System.out.println(content[1]);
						}
						br.close();
					}
				} else {
					System.out.println("格式错误");
				}
			}
		}
		zin.closeEntry();
		return "";
	}

	/**
	 * 
	 * @title: readFile
	 * @Description: 读取txt
	 * @author: FuChunGuang
	 * @Data: 2018年8月8日下午7:23:09
	 * @param path
	 * @param fileName
	 * @return String
	 * @throws:
	 * @version: V1.0
	 */
	public static void readFile(String path, String fileName) {
		String[] content = new String[150];
		try {
			File f = new File(path + fileName);
			if (f.isFile() && f.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(f), "gbk");
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					// 解析
					content = line.split("@@");
					System.out.println(line);
					System.out.println(content[0]);
					System.out.println(content[1]);
				}
				read.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		try {
			// 不解压直接读取文件内容
			// readZipFile("e:/新建文本文档.zip");
			// readTxtZipFile("E:\\wsbasjb\\wsbasjb.zip");
			// readTxtZipFile("E:\\wsbasjb\\新建文本文档.zip");
			// 删除文件
			// delFile("E:","新建文件夹");
			// 删除目录
			// delDir("E:\\新建文件夹");
			// 压缩文件
			// String sourceFolder = "C:/Users/FuChunGuang/Documents/2016年考试.docx";
			// String zipFilePath = "C:\\Users\\FuChunGuang\\Documents\\2016年考试.zip";
			// ZipUtil.zip(sourceFolder, zipFilePath);

			// 压缩文件夹
			// String sourceFolder = "D:/fsc1";
			// String zipFilePath = "D:/fsc1.zip";
			// ZipUtil.zip(sourceFolder, zipFilePath);

			// 压缩一组文件
			// String [] paths =
			// {"C:/Users/FuChunGuang/Documents/2016年考试.docx","C:/Users/FuChunGuang/Documents/mybatis-spring文档.doc","C:/Users/FuChunGuang/Documents/ORACLE-SQL语句学习教程.doc"};
			// zip(paths, "D:/abc.zip");
			// 解压缩
			unZip("E:\\demo\\wsbasjb.zip", "E:\\demo\\");
			readFile("E:\\wsbasjb\\", "kGfxxTbzc.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}