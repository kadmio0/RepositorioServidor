package com.balance.repository;

import com.balance.model.Band;
import com.balance.model.PulseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by da_20 on 31/5/2017.
 */
@Repository
public interface PulseHistoryRepository extends JpaRepository<PulseHistory, Integer> {
}