package com.sit.cloudnative.MaterialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> getAllMaterials(){ return materialRepository.findAll();}
    public List<Material> getMaterialsBySubjectId(int subjectId){return materialRepository.findBySubjectId(subjectId);}
    public Material getMaterialByFileName(String fileName){return materialRepository.findByFileName(fileName);}
    public Material addMaterial(Material material) {
        return materialRepository.save(material);
    }
    public void deleteMaterial(Material material){
        materialRepository.delete(material);
    }
}
