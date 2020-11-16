package com.xinyuan.ExcelTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class TestExport {
    public static void main(String[] args) {
        ExportExcel<Object> ex = new ExportExcel<Object>();
        String[] headers = { "学号"};
        List<Object[]> dataset = new ArrayList<Object[]>();
        dataset.add(new Object[]{"1"});
        dataset.add(new Object[]{"2"});
        dataset.add(new Object[]{"3"});
        dataset.add(new Object[]{"4"});
        dataset.add(new Object[]{"5"});
        try {
            OutputStream out = new FileOutputStream("C:\\Users\\yaoli\\Desktop\\test2.xls");
            ex.exportMessageExcelFile("测试POI导出EXCEL文档", headers, dataset, out, "yyyy-MM-dd");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
