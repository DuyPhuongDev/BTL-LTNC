package com.ltnc.be.controller.common;

import com.ltnc.be.dto.file.response.FileResponse;
import com.ltnc.be.dto.file.response.PresignedUrlUploadResponse;
import com.ltnc.be.service.common.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Tag(name = "File Controller")
@RestController
@RequestMapping("public/api/v1/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final S3Service s3Service;

//    @Operation(summary = "Get Presigned Url")
//    @GetMapping("/{fileKey}/presigned-url")
//    public ResponseEntity<String> getPresignedUrl(@PathVariable(name = "fileKey") String fileKey) {
//        log.info("getPresignedUrl (check in controller): fileKey : {}", fileKey);
//
//        try {
//            String presignedUrl = s3Service.getPresignedUrlDownload(fileKey);
//            return new ResponseEntity<>(presignedUrl, HttpStatus.OK);
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return null;
//        }
//    }

    @Operation(summary = "Upload & Get PreSigned URL")
    @PostMapping(value = "/presigned-url", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<FileResponse> uploadAndGetPresignedUrl(@RequestPart(name = "file") MultipartFile file) throws FileNotFoundException {
        log.info("uploadAndGetPresignedUrl (check in controller): file : {}", file);
        FileResponse response = s3Service.upload(file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get Upload URL")
    @GetMapping("/upload-url")
    public ResponseEntity<PresignedUrlUploadResponse> getUploadUrl(@RequestParam String filename) {
        log.info("getUploadUrl (check in controller): filename : {}", filename);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(s3Service.getUploadUrl(filename));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    @Operation(summary = "Download file")
    @GetMapping("/{fileKey}/download")
    public ResponseEntity<byte[]> getDownloadUrl(@PathVariable(name = "fileKey") String fileKey) {
        log.info("getDownloadUrl (check in controller): fileKey : {}", fileKey);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", MediaType.ALL_VALUE);
            headers.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileKey, StandardCharsets.UTF_8));
            byte[] bytes = s3Service.downloadFile(fileKey);
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bytes);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

}
