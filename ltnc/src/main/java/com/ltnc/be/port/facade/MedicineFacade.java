package com.ltnc.be.port.facade;

import com.ltnc.be.rest.response.MedicineManagementResponse;

import java.util.List;

public interface MedicineFacade {
    public List<MedicineManagementResponse> getMedicines(Integer pageNo, Integer pageSize, String sortBy);
    public List<MedicineManagementResponse> getMedicinesByName(String name, Integer pageNo, Integer pageSize, String sortBy);
}
