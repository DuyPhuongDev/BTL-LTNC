package com.ltnc.be.rest.request;

import com.ltnc.be.domain.employee.DutyType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertEmployeeRequest extends BaseRequest{
    @NotBlank private String username;
    @NotBlank private String password;
    private String email;
    private String phone;
    private String address;
    private DutyType dutyType;
    private String fullName;
}
