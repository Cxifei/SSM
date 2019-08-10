package com.cxf.ssm_one.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;

/**
 * @author always_on_the_way
 * @date 2019-06-27
 */
@Controller
public class FileController {

    @RequestMapping(value = "/fileUpload")
    public String fileUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        //获取项目地址
        String realPath = request.getServletContext().getRealPath("/upload");
//        判断该目录是否存在
        File newFile = new File(realPath);
        if (!newFile.exists()){
//          如果目录不存在，则创建
            newFile.mkdirs();

        }
//        获取输入流
        InputStream inputStream = file.getInputStream();

        FileOutputStream out = new FileOutputStream(newFile + File.separator + file.getOriginalFilename());
//        把输入流中数据写入到输出流中，这里写1024字节好像不够大，上传的文件会有损坏无法正常打开，所以用下面的方式
        byte[] buf = new byte[(int) file.getSize()];
//        read()一次只读一个字节
//        int read1 = inputStream.read();
//        read(bytes)给定字节数读取
//        int read = inputStream.read(bytes);
        int len = 0;
        while ((len = inputStream.read(buf)) != -1){
            out.write(buf,0,len);
            out.flush();
        }
//      下面两句代码也可以实现文件输出流，这也是一种方式，不过不推介这种方式
//      out.write(file.getBytes());
//      out.flush();
        out.close();
        inputStream.close();
        model.addAttribute("path","/upload/"+ file.getOriginalFilename());
        return "imageView";
    }


    @RequestMapping(value = "/fileUpload2")
    public String fileUpload2(MultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        //获取项目地址
        String realPath = request.getServletContext().getRealPath("/upload");
//        判断该目录是否存在
        File newFile = new File(realPath);
        if (!newFile.exists()){
//          如果目录不存在，则创建
            newFile.mkdirs();
        }

//        上传文件（springmvc内置的，一句话搞定，提高效率，上传速度要比上面的普通方法快几十倍，推荐使用！！！）
        file.transferTo(new File(newFile + File.separator + file.getOriginalFilename()));

        model.addAttribute("path","/upload/"+ file.getOriginalFilename());

        return "imageView";
    }


    @RequestMapping(value = "/fileUploadMulti")
    public String fileUploadMulti(HttpServletRequest request, Model model,MultipartFile[] files) throws IOException {

        for (MultipartFile file : files) {
            //获取项目地址
            String realPath = request.getServletContext().getRealPath("/upload");
    //       判断该目录是否存在
            File newFile = new File(realPath);
            if (!newFile.exists()){
    //          如果目录不存在，则创建
                newFile.mkdirs();
            }

    //      上传文件（springmvc内置的，一句话搞定，提高效率）
            file.transferTo(new File(newFile + File.separator + file.getOriginalFilename()));

            model.addAttribute("path","/upload/"+ file.getOriginalFilename());
        }

        return "imageView";
    }

    @RequestMapping(value = "/fileUpload3",method = RequestMethod.POST)
    public String fileUpload3(HttpServletRequest request) throws IOException {

        //获取项目地址
        String realPath = request.getServletContext().getRealPath("/upload");
        //       判断该目录是否存在
        File newFile = new File(realPath);
        if (!newFile.exists()){
            //          如果目录不存在，则创建
            newFile.mkdirs();
        }

//        获取多部分解析器
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getServletContext());
//        判断请求是否包含multipart/form-data
        if (resolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取该请求下的所有文件域
            Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
            while (fileNames.hasNext()){
                String fileName = fileNames.next();
                System.out.println(fileName);
                MultipartFile file = multipartHttpServletRequest.getFile(fileName);
                if (file.getSize() > 0 && file != null){
//                    上传文件（springmvc内置的，一句话搞定，提高效率）
                    file.transferTo(new File( realPath+ File.separator + file.getOriginalFilename()));
                }
            }
        }
        return "imageView";

    }



    @RequestMapping(value = "/fileDownload")
    public void fileDownload(HttpServletRequest request,HttpServletResponse response) throws IOException {

//      中文文件名上传以后图片无法正常显示，因为浏览器对中文文件名会进行二次编码而导致文件名不对而无法正常显示
//      尽量避免图片文件名使用中文，否则浏览器无法正常解析
        String fileName = "彭于晏.jpeg";
        //存放文件的目录
        String realPath = request.getServletContext().getRealPath("/upload");
        File file = new File(realPath, fileName);
        if (file.exists()){
            //获取输出流
            ServletOutputStream outputStream = response.getOutputStream();
            InputStream in = new BufferedInputStream(new FileInputStream(file));

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            in.close();
            outputStream.close();
        }
    }



    @RequestMapping(value = "/fileDownload2")
    public ResponseEntity fileDownload2(HttpServletRequest request) throws Exception {
        String fileName = "1234.mp4";
        //存放文件的目录
        String realPath = request.getServletContext().getRealPath("/upload");
        File file = new File(realPath, fileName);
        if (file.exists()){

            InputStream in = new BufferedInputStream(new FileInputStream(file));
//            创建字节大小
            byte[] bytes = new byte[in.available()];
            in.read(bytes);

//            设置响应的头信息
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition","attachment;filename="+fileName);

            return new ResponseEntity(bytes,headers,HttpStatus.OK);

        }
        return null;

    }


}
