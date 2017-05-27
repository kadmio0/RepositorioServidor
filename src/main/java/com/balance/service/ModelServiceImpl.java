package com.balance.service;

import com.balance.model.Model;
import com.balance.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by KEVIN on 26/05/2017.
 */
@Service("modelService")
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public void saveModel(Model model) {
        modelRepository.save(model);
    }

    @Override
    public Iterable<Model> listAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public Model getModelById(Integer id) {
        return modelRepository.findOne(id);
    }

    @Override
    public void deleteModel(Integer id) {
        modelRepository.delete(id);
    }
}
