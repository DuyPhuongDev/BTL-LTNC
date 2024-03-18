package com.ltnc.be.service.common;

import com.lgsi.lgsibe.dto.file.response.FileResponse;
import com.lgsi.lgsibe.dto.file.response.PresignedUrlUploadResponse;
import com.ltnc.be.dto.file.response.FileResponse;
import com.ltnc.be.dto.file.response.PresignedUrlUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface S3Service {

    String getFileName(MultipartFile file);

    PresignedUrlUploadResponse getUploadUrl(String filename);

    String uploadFile(MultipartFile file) throws FileNotFoundException;

    FileResponse upload(MultipartFile file) throws FileNotFoundException;

    List<String> listAllFiles();

    byte[] downloadFile(String filename);

    String getPresignedUrlDownload(String filename);

    void deleteFile(String fileKey);


}
