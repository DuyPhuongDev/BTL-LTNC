package com.ltnc.be.dto.file.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PresignedUrlUploadResponse {

    private String url;
    private String fileKey;
    private String fileName;

}
