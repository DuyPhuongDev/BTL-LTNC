package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.medicine.Medicine;
import com.ltnc.be.domain.medicineManagement.MedicineManagement;
import com.ltnc.be.dto.MedicineManagementDTO;
import com.ltnc.be.port.facade.MedicineFacade;
import com.ltnc.be.port.repository.MedicineManagementRepository;
import com.ltnc.be.port.repository.MedicineRepository;
import com.ltnc.be.rest.request.UpsertMedicineManagementRequest;
import com.ltnc.be.rest.request.UpsertMedicineRequest;
import com.ltnc.be.rest.response.MedicineManagementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MedicineFacadeImpl implements MedicineFacade {
    private final MedicineRepository medicineRepository;
    private final MedicineManagementRepository medicineManagementRepository;

    @Override
    public List<MedicineManagementResponse> getAllMedicineManagements(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var medicines = medicineManagementRepository.findAll(paging);
        if (medicines.hasContent()) {
            return medicines.getContent().stream()
                    .map(MedicineManagementDTO::fromDomain)
                    .map(MedicineManagementResponse::toMedicineManagementResponse)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<MedicineManagementResponse> getMedicinesManagementsByName(String name, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var medicines = medicineManagementRepository.findByName(name, paging);
        if (medicines.hasContent()) {
            return medicines.getContent().stream()
                    .map(MedicineManagementDTO::fromDomain)
                    .map(MedicineManagementResponse::toMedicineManagementResponse)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public List<Medicine> getMedicinesByName(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public void createMedicine(UpsertMedicineRequest medicineRequest) {
        Medicine medicine = new Medicine();
        medicineRepository.save(medicine);
    }

    @Override
    public void updateMedicine(Long medicineId, UpsertMedicineRequest medicineRequest) {
        Medicine existingMedicine = medicineRepository.findById(medicineId).orElse(null);
        if (existingMedicine != null) {
            existingMedicine.setName(medicineRequest.getName());
            medicineRepository.save(existingMedicine);
        }
    }

    @Override
    public void deleteMedicine(Long medicineId) {
        medicineRepository.deleteById(medicineId);
    }

    @Override
    public void createMedicineManagement(UpsertMedicineManagementRequest medicine) {
        MedicineManagement medicineManagement = new MedicineManagement();
        var medicineEntity = medicineRepository.findById(medicine.getMedicineId()).orElse(null);
        if (medicineEntity != null) {
            medicineManagement.setMedicine(medicineEntity);
        }
        medicineManagementRepository.save(medicineManagement);
    }

    @Override
    public void updateMedicineManagement(Long medicineId, UpsertMedicineManagementRequest medicine) {
        MedicineManagement existingMedicineManagement = medicineManagementRepository.findById(medicineId).orElse(null);
        if (existingMedicineManagement != null) {
            medicineManagementRepository.save(existingMedicineManagement);
        }
    }

    @Override
    public void deleteMedicineManagement(Long medicineId) {
        medicineManagementRepository.deleteById(medicineId);
    }
}
