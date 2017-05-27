package com.balance.service;

import com.balance.model.Model;

/**
 * Created by KEVIN on 26/05/2017.
 */
public interface ModelService {

    void saveModel(Model Model);
    Iterable<Model> listAllModels();
    Model getModelById(Integer id);
    void deleteModel(Integer id);

}
