package com.spring.fcg.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;

public class ExcelUtil {
	/**
	 * 
	 *@title:	exportExcel
	 *@Description:	导出excel(xls)，单sheet页
	 *@author:	FuChunGuang
	 *@Data:	2018年7月17日下午7:27:38
	 *@param list 要导出的数据 泛型实体类
	 *@param title 表格名称
	 *@param sheetName sheet页名字
	 *@param pojoClass 实体类
	 *@param fileName 文件名
	 *@param isCreateHeader 是否创建表头
	 *@param response 响应
	 *void
	 *@throws:
	 *@version:	V1.0
	 */
	public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
			boolean isCreateHeader, HttpServletResponse response) {
		ExportParams exportParams = new ExportParams(title, sheetName);
		exportParams.setCreateHeadRows(isCreateHeader);
		defaultExport(list, pojoClass, fileName, response, exportParams);

	}
	/**
	 * 
	 *@title:	exportExcel
	 *@Description:	导出excel(xls)，单sheet页
	 *@author:	FuChunGuang
	 *@Data:	2018年7月17日下午7:16:36
	 *@param list 要导出的数据 泛型实体类
	 *@param title  表格名称
	 *@param sheetName  sheet页名字
	 *@param pojoClass 实体类
	 *@param fileName 文件名
	 *@param response 响应
	 *void
	 *@throws:
	 *@version:	V1.0
	 */
	public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
			HttpServletResponse response) {
		defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
	}
	/**
	 * 
	 *@title:	exportExcel
	 *@Description:	导出excel(xls)，单sheet页
	 *@author:	FuChunGuang
	 *@Data:	2018年7月17日下午7:26:03
	 *@param list  数据
	 *@param fileName 文件名
	 *@param response 响应
	 *void
	 *@throws:
	 *@version:	V1.0
	 */
	public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
		defaultExport(list, fileName, response);
	}
	/**
	 * 
	 *@title:	默认导出excel方法
	 *@Description:	默认
	 *@author:	FuChunGuang
	 *@Data:	2018年7月17日下午7:35:24
	 *@param list 数据
	 *@param pojoClass 实体类
	 *@param fileName 文件名
	 *@param response  响应
	 *@param exportParams  导出参数
	 *void
	 *@throws:
	 *@version:	V1.0
	 */
	private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
			ExportParams exportParams) {
		Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
		if (workbook != null)
			;
		downLoadExcel(fileName, response, workbook);
	}
	/**
	 * 
	 *@title:	downLoadExcel
	 *@Description:	下载 excel
	 *@author:	FuChunGuang
	 *@Data:	2018年7月17日下午7:37:37
	 *@param fileName 文件名
	 *@param response 响应
	 *@param workbook 默认HSSF(xls)
	 *void
	 *@throws:
	 *@version:	V1.0
	 */
	private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("content-Type", "application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	/**
	 * 
	 *@title:	defaultExport
	 *@Description:	TODO(用一句话描述方法的用途)
	 *@author:	FuChunGuang
	 *@Data:	2018年7月17日下午7:47:20
	 *@param list  数据
	 *@param fileName 文件名
	 *@param response 响应
	 *void
	 *@throws:
	 *@version:	V1.0
	 */
	private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
		Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
		if (workbook != null)
			;
		downLoadExcel(fileName, response, workbook);
	}
	/**
	 * 
	 *@title:	importExcel
	 *@Description:	解析excel
	 *@author:	FuChunGuang
	 *@Data:	2018年7月17日下午8:07:56
	 *@param filePath  文件路径
	 *@param titleRows 表格标题行数,默认0
	 *@param headerRows 表头行数,默认1
	 *@param pojoClass 实体类
	 *@return
	 *List<T> 解析后的数据
	 *@throws:
	 *@version:	V1.0
	 */
	public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
		if (StringUtils.isBlank(filePath)) {
			return null;
		}
		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;
		try {
			list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("模板不能为空");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}
	/**
	 * 
	 *@title:	importExcel
	 *@Description:	解析excel
	 *@author:	FuChunGuang
	 *@Data:	2018年7月17日下午8:10:49
	 *@param file  文件
	 *@param titleRows  表格标题行数,默认0
	 *@param headerRows 表头行数,默认1
	 *@param pojoClass 实体类
	 *@return 
	 *List<T>  解析后的数据
	 *@throws:
	 *@version:	V1.0
	 */
	public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows,
			Class<T> pojoClass) {
		if (file == null) {
			return null;
		}
		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;
		try {
			list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("excel文件不能为空");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}
}
