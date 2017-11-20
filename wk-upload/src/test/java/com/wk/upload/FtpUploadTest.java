package com.wk.upload;

import com.wk.wkshop.common.util.FtpUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class FtpUploadTest {
    //@Test
    public void testFtpUpload() throws IOException {
        //创建FTPClient客户端
        FTPClient ftpClient = new FTPClient();
        //创建FTP连接
        ftpClient.connect("10.31.161.13", 21);
        //登录
        ftpClient.login("ftpuser", "wk925918wk");
        //读取本地文件
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\gakki\\IMG_1281.GIF"));
        //配置上传参数
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//二进制
        //上传文件
        ftpClient.storeFile("ga.GIF", fileInputStream);
        //关闭连接
        fileInputStream.close();
        ftpClient.logout();
    }

    //@Test
    public void testFtpUtils() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(new File("d:\\dog.jpg"));
        System.out.println(FtpUtils.uploadFile("10.31.161.13", 21, "ftpuser", "wk925918wk", "/home/ftpuser/www/images", "/2017/11/16", "dogson2.jpg", inputStream));
    }
}
