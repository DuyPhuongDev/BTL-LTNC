package com.ltnc.be.rest.controller;

import com.ltnc.be.annotation.IsAuthenticated;
import com.ltnc.be.annotation.IsAuthorizedAsAdmin;
import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.dto.MedicineManagementDTO;
import com.ltnc.be.port.facade.MedicineFacade;
import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.rest.response.EmployeeResponse;
import com.ltnc.be.rest.response.MedicineManagementResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicines")
@AllArgsConstructor
public class MedicineController {
    private final MedicineFacade medicineFacade;
    @GetMapping("/")
    @Operation(tags = "Medicine APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<MedicineManagementResponse>> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                                                          @RequestParam(defaultValue = "id") String sortBy,
                                                                          @RequestParam(defaultValue = "0") Boolean searchFlag,
                                                                          @RequestParam(required = false) String name) {
        if(searchFlag == Boolean.TRUE){
            return BaseResponse.of(medicineFacade.getMedicines(pageNo, pageSize, sortBy));
        }
        return BaseResponse.of(medicineFacade.getMedicinesByName(name, pageNo, pageSize, sortBy));
    }
}
