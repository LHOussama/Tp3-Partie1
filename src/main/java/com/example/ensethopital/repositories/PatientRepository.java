package com.example.ensethopital.repositories;
import com.example.ensethopital.entites.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("select p from Patient p where p.nom like :x")
    Page<Patient> chercher(@Param("x") String Keyword,Pageable pageable);
    Page<Patient> findByNomContaining(String chaine, Pageable pageable);
    List<Patient> findByNomContaining(String keyWord);
}
