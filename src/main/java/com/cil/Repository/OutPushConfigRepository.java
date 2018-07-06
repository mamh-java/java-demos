package com.cil.Repository;

import com.cil.Model.OutPushConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bob on 2017.08.16.
 */
@Repository
public interface OutPushConfigRepository extends JpaRepository<OutPushConfigEntity,Integer> {
    List<OutPushConfigEntity> findAllByStatus(String status);
}
