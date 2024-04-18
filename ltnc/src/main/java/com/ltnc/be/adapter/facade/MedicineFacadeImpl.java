package com.ltnc.be.adapter.facade;

import com.ltnc.be.port.facade.MedicineFacade;
import com.ltnc.be.port.repository.MedicineRepository;
import com.ltnc.be.dto.MedicineManagementDTO;
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

    @Override
    public List<MedicineManagementResponse> getMedicines(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var medicines = medicineRepository.findAll(paging);
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
    public List<MedicineManagementResponse> getMedicinesByName(String name, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var medicines = medicineRepository.findByName(name, paging);
        if (medicines.hasContent()) {
            return medicines.getContent().stream()
                    .map(MedicineManagementDTO::fromDomain)
                    .map(MedicineManagementResponse::toMedicineManagementResponse)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
