package com.sit.cloudnative.MaterialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    public Material getMaterialById(String materialId){
        return materialRepository.getOne(materialId);
    }

    public Material addMaterial(Material material) {
        return materialRepository.save(material);
    }
    public void deleteMaterial(Material material){
        materialRepository.delete(material);
    }
}
