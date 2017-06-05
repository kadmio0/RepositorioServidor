package com.balance.repository;

import com.balance.model.BandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by KEVIN on 05/06/2017.
 */
@Repository("bandModelRepository")
public interface BandModelRepository extends JpaRepository<BandModel, Integer> {

}
