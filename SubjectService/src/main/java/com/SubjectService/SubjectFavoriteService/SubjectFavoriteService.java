package com.SubjectService.SubjectFavoriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectFavoriteService {

    @Autowired
    private SubjectFavoriteRepository subjectFavoriteRepository;

    public SubjectFavorite addSubjectFavorite(int subjectId, int userId){
        return subjectFavoriteRepository.save(new SubjectFavorite(subjectId, userId));
    }
}
