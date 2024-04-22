package com.ltnc.be.port.facade;

import com.ltnc.be.rest.request.UpsertMedicalRecordRequest;

public interface MedicalRecordFacade {
    void saveMedicalRecord(UpsertMedicalRecordRequest request);

    void updateRecord(Long recordId, UpsertMedicalRecordRequest request);

    void deleteRecord(Long recordId);
}
