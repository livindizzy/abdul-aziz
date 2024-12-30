package com.askrindo.transaction.repository;

import com.askrindo.transaction.model.entity.MikroRumahku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MikroRumahKuRepository extends JpaRepository<MikroRumahku, UUID>, JpaSpecificationExecutor<MikroRumahku> {

    @Query(value = "select mr.nomor_sertifikat from mikro_rumahku mr order by mr.created_date desc fetch first row only", nativeQuery = true)
    String getLastCertificate();
}
