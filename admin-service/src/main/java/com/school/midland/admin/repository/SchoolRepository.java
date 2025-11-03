package com.school.midland.admin.repo;

import com.school.midland.admin.models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    Optional<List<School>> findByCountry(String Country);
    Optional<List<School>> findByState(String State);
    Optional<List<School>> findByCity(String city);
    Optional<School> findBySchoolCode(String schoolCode);
    Optional<List<School>> findByCountryAndStateAndCity(String country,String state,String city);
    boolean existsByEmail(String email);

    boolean existsBySchoolName(String schoolName);

}