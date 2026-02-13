package com.example.Student.Student.Repository;

import com.example.Student.Student.DTOClasse.JournalDto;
import com.example.Student.Student.Entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Integer> {
    List<Journal> findByUserId(Integer userId);
    Optional<Journal> findByIdAndUserId(Integer journalId, Integer userId);


}
