package com.balance.repository;

import com.balance.model.Role;
import com.balance.model.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by KEVIN on 26/05/2017.
 */
@Repository("terminalRepository")
public interface TerminalRepository extends JpaRepository<Terminal, Integer> {
}
