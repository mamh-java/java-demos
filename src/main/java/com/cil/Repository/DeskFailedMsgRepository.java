package com.cil.Repository;

import com.cil.Model.DeskFailedMsgEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by bob on 2017.08.16.
 */
@Repository
public interface DeskFailedMsgRepository extends JpaRepository<DeskFailedMsgEntity, Integer> {

    DeskFailedMsgEntity findFirstByMerCodeAndTermCodeAndMsgSeqNum(String merCode, String termCode, String msgSeqNum);

    List<DeskFailedMsgEntity> findAllByMerCodeAndTermCode(String merCode, String termCode, Pageable pageable);

    int countAllByMerCodeAndTermCodeAndUuid(String merCode, String termCode, String uuid);

    @Modifying
    @Transactional
    @Query("delete from DeskFailedMsgEntity entity where entity.createTime < :ts")
    int deleteByCreateTimeBefore(@Param("ts") Timestamp ts);

    @Modifying
    @Transactional
    @Query("delete from DeskFailedMsgEntity entity where entity.merCode = :merCode and entity.termCode =:termCode and entity.uuid = :uuid")
    int deleteAllByMerCodeAndTermCodeAndUuid(@Param("merCode") String merCode, @Param("termCode") String termCode, @Param("uuid") String uuid);

    @Modifying
    @Transactional
    @Query("delete from DeskFailedMsgEntity entity where entity.msgSeqNum = :msgSeqNum")
    int deleteByMsgSeqNum(@Param("msgSeqNum") String msgSeqNum);
}
