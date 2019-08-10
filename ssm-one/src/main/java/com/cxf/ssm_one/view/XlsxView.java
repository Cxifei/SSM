package com.cxf.ssm_one.view;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author always_on_the_way
 * @date 2019-06-26
 */
public class XlsxView extends AbstractXlsxView {

    /**
     * Application-provided subclasses must implement this method to populate
     * the Excel workbook document, given the model.
     *
     * @param model    the model Map
     * @param workbook the Excel workbook to populate
     * @param request  in case we need locale etc. Shouldn't look at attributes.
     * @param response in case we need to set cookies. Shouldn't write to it.
     */
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

//        获取model中的数据
        Object user = model.get("user");
//        通过反射获取类型
        Class<?> aClass = user.getClass();
//        通过反射获取属性和值
        Field[] declaredFields = aClass.getDeclaredFields();
//        创建工作表sheet
        Sheet sheet = workbook.createSheet("test");
//        遍历行
        for (int i = 0; i <= 1; i++) {
            //创建行，从0开始
            Row row = sheet.createRow(i);
            for (int j = 0; j < declaredFields.length; j++) {
                Cell cell = row.createCell(j);
                if (i == 0){
                    String fieldName = declaredFields[j].getName();
                    cell.setCellValue(fieldName);
                }else{
                    declaredFields[j].setAccessible(true);
                    //get(user)等价于user.name  user.age...因为属性为private，所以必须加权限为true，也就是上面那一句
                    Object o = declaredFields[j].get(user);
                    if (o != null){
                        cell.setCellValue(o.toString());
                    }else {
                        cell.setCellValue("");
                    }
                }
            }

//          将一个类型的数据复制到另一个类，前提是两个类的属性要一致，spring内置的工具类
            BeanUtils.copyProperties("","");

        }

//        继承的AbstractXlsxView类中已有下面这几步，所以不用写

//        ServletOutputStream out = response.getOutputStream();
//        //保存Excel文件
//        workbook.write(out);
//        //关闭文件流
//        out.close();


    }
}
