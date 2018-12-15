package com.doctor.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExportExcel {

    public static void export(HttpServletResponse response) throws IOException {
        //1.在内存中创建一个excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //2.创建工作簿
        HSSFSheet sheet = hssfWorkbook.createSheet();

        //3.创建标题行(合并单元格)
        //这个就是合并单元格
        //参数说明：1：开始行 2：结束行  3：开始列 4：结束列
        //比如我要合并 第二行到第四行的    第六列到第八列     sheet.addMergedRegion(new CellRangeAddress(1,3,5,7));
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
        sheet.addMergedRegion(new CellRangeAddress(0,0,1,2));

        HSSFRow firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("序号");
        firstRow.createCell(1).setCellValue("用户信息");
        HSSFRow secondRow = sheet.createRow(1);
        secondRow.createCell(1).setCellValue("姓名");
        secondRow.createCell(2).setCellValue("手机号");

        //4.遍历数据,创建数据行
        for(int i=0; i<2; i++) {
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow row = sheet.createRow(lastRowNum + 1);
            row.createCell(0).setCellValue(i);
            row.createCell(1).setCellValue("people" + lastRowNum);
            row.createCell(2).setCellValue("mobile" + lastRowNum);
        }

        //5.获取输出流对象
        ServletOutputStream outputStream = response.getOutputStream();

        //9.设置信息头
        response.setHeader("Content-Disposition","attachment;filename=xxx.xls");
        //10.写出文件,关闭流
        hssfWorkbook.write(outputStream);
        hssfWorkbook.close();

    }
}
