package com.balance.repository;

import com.balance.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by KEVIN on 30/05/2017.
 */
@Repository("bandRepository")
public interface BandRepository extends JpaRepository<Band, Integer> {
}
