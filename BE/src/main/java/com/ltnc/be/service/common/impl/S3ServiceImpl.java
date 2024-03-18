package com.ltnc.be.service.common.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.ltnc.be.dto.file.response.FileResponse;
import com.ltnc.be.dto.file.response.PresignedUrlUploadResponse;
import com.ltnc.be.service.common.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3ServiceImpl implements S3Service {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.s3.publicUrl}")
    private String publicUrl;

    private final AmazonS3 s3;

    @Override
    public String getFileName(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).trim();
    }

    @Override
    public PresignedUrlUploadResponse getUploadUrl(String filename) {
        Date expiredDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(expiredDate);
        calendar.add(Calendar.MINUTE, 15);

        String originalFilename = filename.trim();
        originalFilename = originalFilename.replaceAll("\\s+", "");
        String fileKey = LocalDateTime.now() + "_" + originalFilename;

        log.info("fileKey {}", fileKey);

        String presignedUrlUpload = s3.generatePresignedUrl(bucketName, fileKey, calendar.getTime(), HttpMethod.PUT).toString();

        return PresignedUrlUploadResponse.builder()
                .url(presignedUrlUpload)
                .fileKey(fileKey)
                .fileName(filename)
                .build();
    }

    // return fileKey
    @Override
    public String uploadFile(MultipartFile file) throws FileNotFoundException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        String originalFilename = file.getOriginalFilename().trim();
        originalFilename = originalFilename.replaceAll("\\s+", "");
        String fileKey = LocalDateTime.now() + "_" + originalFilename;
        File fileObj = convertMultiPartToFile(file);
        InputStream targetStream = new FileInputStream(fileObj);
        s3.putObject(new PutObjectRequest(bucketName, fileKey, targetStream, objectMetadata));
        fileObj.delete();
        return fileKey;
    }

    @Override
    public FileResponse upload(MultipartFile file) throws FileNotFoundException {
        String fileKey = this.uploadFile(file);
        String presignedUrl = this.getPresignedUrlDownload(fileKey);
        return new FileResponse(presignedUrl, fileKey, getFileName(file));
    }

    @Override
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);
        return listObjectsV2Result.getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .toList();
    }

    @Override
    public byte[] downloadFile(String fileKey) {
        S3Object object = s3.getObject(bucketName, fileKey);
        S3ObjectInputStream objectContent = object.getObjectContent();

        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPresignedUrlDownload(String fileKey) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        cal.add(Calendar.DATE, 7);
//        return s3.generatePresignedUrl(bucketName, fileKey, cal.getTime(), HttpMethod.GET).toString();
        return String.format("%s/%s", publicUrl, fileKey);
    }

    private File convertMultiPartToFile(MultipartFile file) {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convFile;
    }

    @Override
    public void deleteFile(String fileKey) {
        s3.deleteObject(bucketName, fileKey);
    }

}