package com.askrindo.authentication.repository;

import com.askrindo.authentication.model.entity.User;
import com.askrindo.authentication.model.projection.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {

    @Query(value = """
            select mu.username as username, mu."password" as passwordd, mr.role_name as roleName from m_user mu
            left join user_role ur on mu.id = ur.user_id
            left join m_role mr on ur.role_id = mr.id
            where mu.is_active = true and mu.username = :username and mu.password = :password
            """, nativeQuery = true)
    UserView getUserDataByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

}
