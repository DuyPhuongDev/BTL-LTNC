package com.ltnc.be.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpsertMedicineRequest extends BaseRequest {
    private String name;
    private String medicineType;
    private String medicalUseType;
    private String price;
    private String ingredient;
}
