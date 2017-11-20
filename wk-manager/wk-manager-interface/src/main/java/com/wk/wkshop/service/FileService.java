package com.wk.wkshop.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {

    Map<String,Object> uploadImage(MultipartFile multipartFile) throws Exception;

    Map<String,Object> uploadVideo(MultipartFile multipartFile) throws Exception;

}
