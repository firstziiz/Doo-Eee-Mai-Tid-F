package com.SubjectService.SubjectFavoriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.SubjectService.SubjectService.Subject;
import com.SubjectService.SubjectService.SubjectAdapter;

@Service
public class SubjectFavoriteService {

    @Autowired
    private SubjectFavoriteRepository subjectFavoriteRepository;

    @Autowired
    private SubjectAdapter subjectAdapter;

    public SubjectFavorite addSubjectFavorite(int subjectId, String userId){
        return subjectFavoriteRepository.save(new SubjectFavorite(subjectId, userId));
    }
    public List<Subject> getSubjectFavoriteByUserId(String userId){
        List<SubjectFavorite> subjectFavorites = subjectFavoriteRepository.findByUserId(userId);
        List<Subject> subjects = new ArrayList<Subject>();
        for (SubjectFavorite subjectFav : subjectFavorites) {
            int subjectId = subjectFav.getSubjectId();
            subjects.add(subjectAdapter.getSubjectById(subjectId));
        }
        return subjects;
    }
}
