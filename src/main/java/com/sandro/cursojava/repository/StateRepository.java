package com.sandro.cursojava.repository;

import com.sandro.cursojava.domain.City;
import com.sandro.cursojava.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    @Transactional(readOnly=true)
    public List<State> findAllByOrderByName();
}
