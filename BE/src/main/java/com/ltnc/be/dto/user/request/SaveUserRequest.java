package com.ltnc.be.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRequest {

    @Schema(example = "username123")
    @NotBlank(message = "Username file cannot be empty!")
    private String username;

    @Schema(example = "password123!")
    @NotBlank(message = "Password file cannot be empty!")
    private String password;

}
