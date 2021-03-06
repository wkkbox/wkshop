package com.wk.wkshop.service.impl;

import com.wk.wkshop.common.util.FtpUtils;
import com.wk.wkshop.common.util.IDUtils;
import com.wk.wkshop.common.util.PropKit;
import com.wk.wkshop.service.FileService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public Map<String, Object> uploadImage(MultipartFile multipartFile) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //读取FTP配置文件信息
        String name = "ftp.properties";
        String host = PropKit.use(name).get("ftp.address");
        int port = PropKit.use(name).getInt("ftp.port");
        String username = PropKit.use(name).get("ftp.username");
        String password = PropKit.use(name).get("ftp.password");
        String basePath = PropKit.use(name).get("ftp.basePath");
        //创建文件路径：基础路径+文件路径+文件名+扩展名
        String filePath = new DateTime().toString("/yyyy/MM/dd");
        //获取原有的文件名，包含扩展名
        String originalFilename = multipartFile.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newName = IDUtils.genImageName() + fileType;
        InputStream inputStream = multipartFile.getInputStream();
        //执行上传操作，返回布尔值
        boolean bool = FtpUtils.uploadFile(host, port, username, password, basePath, filePath, newName, inputStream);
        System.out.println("+++++++++++++++++++++++++++++++++++++++" + bool);
        if (bool) {
            map.put("state", "SUCCESS");
            map.put("title", newName);
            map.put("original", originalFilename);
            map.put("type", fileType);
            map.put("url", filePath + "/" + newName);//"imageUrlPrefix": "http://manager.dhc.com/images"/* 图片访问路径前缀 */
            map.put("size", multipartFile.getSize());
        }
        return map;
    }

    @Override
    public Map<String, Object> uploadVideo(MultipartFile multipartFile) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //读取FTP配置文件信息
        String name = "ftp.properties";
        String host = PropKit.use(name).get("ftp.address");
        int port = PropKit.use(name).getInt("ftp.port");
        String username = PropKit.use(name).get("ftp.username");
        String password = PropKit.use(name).get("ftp.password");
        String basePath = PropKit.use(name).get("ftp.basePath");
        //创建文件路径：基础路径+文件路径+文件名+扩展名
        String filePath = new DateTime().toString("/yyyy/MM/dd");
        //获取原有的文件名，包含扩展名
        String originalFilename = multipartFile.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newName = IDUtils.genImageName() + fileType;
        InputStream inputStream = multipartFile.getInputStream();
        //执行上传操作，返回布尔值
        boolean bool = FtpUtils.uploadFile(host, port, username, password, basePath, filePath, newName, inputStream);
        System.out.println("######################" + bool);
        if (bool) {
            map.put("state", "SUCCESS");
            map.put("original", originalFilename);
            map.put("size", multipartFile.getSize());
            map.put("title", newName);
            map.put("type", fileType);
            map.put("url", filePath + "/" + newName);//"imageUrlPrefix": "http://manager.dhc.com/images"/* 图片访问路径前缀 */
        }
        return map;
    }
}
