package com.signService.sign_service.repository;

import com.signService.sign_service.model.entity.SignDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignDocumentRepository extends JpaRepository<SignDocument, Long> {

    @Query("SELECT s FROM SignDocument s WHERE s.documentHashCode = :hashcode")
    public Optional<SignDocument> findByDocumentHashcode(@Param("hashcode") String hashcode);
}
