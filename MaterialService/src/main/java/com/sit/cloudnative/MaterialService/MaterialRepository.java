package com.sit.cloudnative.MaterialService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material,String> {
    List<Material> findBySubjectId(int subjectId);
    Material findByFileName(String fileName);
}
