package com.sit.cloudnative.VideoHistoryService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoHistoryRepository extends JpaRepository<VideoHistory,Integer> {
}
