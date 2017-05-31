package com.balance.service;

import com.balance.model.Band;

/**
 * Created by KEVIN on 30/05/2017.
 */
public interface BandService {
    void saveBand(Band Band);
    Iterable<Band> listAllBands();
    Band getBandById(Integer id);
    void deleteBand(Integer id);

}
