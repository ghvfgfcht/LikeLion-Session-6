package com.example.account.repository;

import com.example.account.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Members, Long> {
    Optional<Members> findByUserId(String userId);
    Optional<Members> findById(Long id);
    Optional<Members> findByPassword(String password);
}