package com.cil.Repository;

import com.cil.Global.GlobalConstant;
import com.cil.Model.OutPushConfigEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bob on 2017.10.30.
 */

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class TestOutPushConfigRepository {
    @Autowired
    private OutPushConfigRepository repository;

    @Test
    public void TestFindAllByStatus() throws Exception {
        List<OutPushConfigEntity> entities = repository.findAllByStatus(GlobalConstant.OUT_CONFIG_OPEN);
        for (int i = 0; i < entities.size(); i++) {
            OutPushConfigEntity entity = entities.get(i);
            System.out.println("insCode:" + entity.getInsCode());
            System.out.println("merCode:" + entity.getMerCode());
        }
    }

}