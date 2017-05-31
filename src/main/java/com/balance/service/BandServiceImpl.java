package com.balance.service;

import com.balance.model.Band;
import com.balance.model.Band;
import com.balance.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by KEVIN on 30/05/2017.
 */
@Service("BandService")
public class BandServiceImpl implements BandService{
    @Autowired
    private BandRepository bandRepository;

    @Override
    public void saveBand(Band Band) {
        bandRepository.save(Band);
    }

    @Override
    public Iterable<Band> listAllBands() {
        return bandRepository.findAll();
    }

    @Override
    public Band getBandById(Integer id) {
        return bandRepository.findOne(id);
    }

    @Override
    public void deleteBand(Integer id) {
            bandRepository.delete(id);
    }


}
