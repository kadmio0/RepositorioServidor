package com.balance.service;

import com.balance.model.BandModel;
import com.balance.repository.BandModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by KEVIN on 05/06/2017.
 */

@Service
public class BandModelServiceImpl implements BandModelService{
    @Autowired
    private BandModelRepository bandModelRepository;


    @Override
    public void saveBandModel(BandModel BandModel) {
        bandModelRepository.save(BandModel);
    }

    @Override
    public Iterable<BandModel> listAllBandModels() {
        return bandModelRepository.findAll();
    }

    @Override
    public BandModel getBandModelById(Integer id) {
        return bandModelRepository.findOne(id);
    }

    @Override
    public void deleteBandModel(Integer id) {
        bandModelRepository.delete(id);
    }
}
