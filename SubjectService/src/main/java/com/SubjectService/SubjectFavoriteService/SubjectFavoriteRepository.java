package com.SubjectService.SubjectFavoriteService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectFavoriteRepository extends JpaRepository<SubjectFavorite, Integer> {

}