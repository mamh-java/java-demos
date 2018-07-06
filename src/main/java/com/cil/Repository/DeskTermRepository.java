package com.cil.Repository;

import com.cil.Model.DeskTermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bob on 2017.08.16.
 */
@Repository
public interface DeskTermRepository extends JpaRepository<DeskTermEntity,Integer> {
    List<DeskTermEntity> findAllByStatus(String status);
}
