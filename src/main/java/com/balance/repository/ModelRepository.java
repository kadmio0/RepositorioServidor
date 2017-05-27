package com.balance.repository;

import com.balance.model.Model;
import com.balance.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by KEVIN on 26/05/2017.
 */
@Repository("modelRepository")
public interface ModelRepository extends JpaRepository<Model, Integer> {
}
