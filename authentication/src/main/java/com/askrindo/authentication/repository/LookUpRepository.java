package com.askrindo.authentication.repository;

import com.askrindo.authentication.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface LookUpRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    @Query(value = "select ml.lookup_key  from m_lookup ml where ml.lookup_group = :group", nativeQuery = true)
    List<String> getListLookUpKeyByLookupGroup(String group);
}
