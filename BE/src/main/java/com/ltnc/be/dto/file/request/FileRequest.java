package com.ltnc.be.dto.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileRequest {

    @Schema(example = "fileKey")
    @NotBlank(message = "File key is required.")
    private String fileKey;

    @Schema(example = "fileName")
    @NotBlank(message = "File name is required.")
    private String fileName;

}
