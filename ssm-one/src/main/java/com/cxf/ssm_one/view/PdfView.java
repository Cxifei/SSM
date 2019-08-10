package com.cxf.ssm_one.view;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author always_on_the_way
 * @date 2019-06-26
 */
public class PdfView extends AbstractPdfView {
    /**
     * Subclasses must implement this method to build an iText PDF document,
     * given the model. Called between {@code Document.open()} and
     * {@code Document.close()} calls.
     * <p>Note that the passed-in HTTP response is just supposed to be used
     * for setting cookies or other HTTP headers. The built PDF document itself
     * will automatically get written to the response after this method returns.
     *
     * @param model    the model Map
     * @param document the iText Document to add elements to
     * @param writer   the PdfWriter to use
     * @param request  in case we need locale etc. Shouldn't look at attributes.
     * @param response in case we need to set cookies. Shouldn't write to it.
     * @throws Exception any exception that occurred during document building
     * @see Document#open()
     * @see Document#close()
     */
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        指定编码
        BaseFont baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);
//        获取model中的数据
        Object user = model.get("user");
//        通过反射获取类型
        Class<?> aClass = user.getClass();
//        通过反射获取属性和值
        Field[] declaredFields = aClass.getDeclaredFields();
        PdfPTable pTable = new PdfPTable(declaredFields.length);
//        遍历行
        for (int i = 0; i <= 1; i++) {
            for (Field declaredField : declaredFields) {
                if (i == 0){
                    //获取属性的名称
                    String fieldName = declaredField.getName();
                    pTable.addCell(fieldName);
                }else {
                    //设置私有属性可以访问
                    declaredField.setAccessible(true);
                    Object o = declaredField.get(user);
                    if (o != null){
                        pTable.addCell(new Phrase(new Chunk(o.toString(),font)));
                    }else {
                        pTable.addCell("haha");
                    }

                }
            }
        }
        document.add(pTable);
    }
}
