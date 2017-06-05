package com.balance.service;

import com.balance.model.BandModel;

/**
 * Created by KEVIN on 05/06/2017.
 */
public interface BandModelService {
    void saveBandModel(BandModel BandModel);
    Iterable<BandModel> listAllBandModels();
    BandModel getBandModelById(Integer id);
    void deleteBandModel(Integer id);
}
