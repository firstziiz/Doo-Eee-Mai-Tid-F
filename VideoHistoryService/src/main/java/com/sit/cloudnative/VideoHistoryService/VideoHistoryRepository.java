package com.sit.cloudnative.VideoHistoryService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoHistoryRepository extends JpaRepository<VideoHistory,Integer> {

    List<VideoHistory> findByIdUserId(int userId);
}
