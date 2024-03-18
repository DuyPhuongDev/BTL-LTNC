package com.ltnc.be.dto.file.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileResponse {

    private String presignedUrl;
    private String fileKey;
    private String fileName;

    public FileResponse(String presignedUrl, String fileKey, String fileName) {
        this.presignedUrl = presignedUrl;
        this.fileKey = fileKey;
        this.fileName = fileName;
    }

}
