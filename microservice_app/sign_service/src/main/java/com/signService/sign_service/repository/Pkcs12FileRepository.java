package com.signService.sign_service.repository;

import com.signService.sign_service.model.entity.Pkcs12File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Pkcs12FileRepository extends JpaRepository<Pkcs12File, Long> {

    @Query("SELECT p FROM Pkcs12File p WHERE p.alias = :alias AND p.password = :password")
    Optional<Pkcs12File> findByAliasAndPassword(
            @Param("alias") String alias,
            @Param("password") String password);
}
