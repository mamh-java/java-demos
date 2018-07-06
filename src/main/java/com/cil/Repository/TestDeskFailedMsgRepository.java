package com.cil.Repository;

import com.cil.Model.DeskFailedMsgEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by bob on 2017.10.30.
 */

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class TestDeskFailedMsgRepository {
    @Autowired
    private DeskFailedMsgRepository repository;

    @Test
    @Rollback(false)
    public void TestSaveAndFlush() throws Exception {
        for (int i = 0; i < 100; i++) {
            DeskFailedMsgEntity entity = new DeskFailedMsgEntity();
            entity.setMerCode("1010101010");
            entity.setTermCode("10000001");
            entity.setMsgSeqNum("1010101010orerNum" + i);
            entity.setMsgStr("{\"orderNum\":\"orderNum" + i + "\"}");
            entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            System.out.println(repository.saveAndFlush(entity));
        }

    }

    @Test
    public void TestFindFirstByMerCodeAndTermCodeAndMsgSeqNum() throws Exception {
        DeskFailedMsgEntity entity = repository.findFirstByMerCodeAndTermCodeAndMsgSeqNum("1010101010", "10000001", "1010101010orerNum1");
        System.out.println("msgStr:" + entity.getMsgStr());
    }

    @Test
    public void TestFindAllByMerCodeAndTermCode() throws Exception {
        Pageable pageable = new PageRequest(0, 3);

        List<DeskFailedMsgEntity> list = repository.findAllByMerCodeAndTermCode("1010101010", "10000001", pageable);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("msgStr:" + list.get(i).getMsgStr());
        }
    }

    @Test
    public void TestDeleteByCreateTimeBefore() throws Exception {
        System.out.println(repository.deleteByCreateTimeBefore(new Timestamp(System.currentTimeMillis())));
    }

    @Test
    @Rollback(false)
    public void TestDeleteAllByMsgSeqNum() throws Exception {
        System.out.println(repository.deleteByMsgSeqNum("0000000000000661515037910516"));
    }
}