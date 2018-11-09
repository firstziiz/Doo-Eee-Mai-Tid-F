package com.SubjectService.SubjectFavoriteService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectFavoriteRepository extends JpaRepository<SubjectFavorite, Integer> {
    List<SubjectFavorite> findByUserId(Long userId);
}