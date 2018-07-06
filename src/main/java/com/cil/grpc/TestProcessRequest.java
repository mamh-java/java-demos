package com.cil.grpc;

import com.cil.Global.GlobalCache;
import com.cil.Global.GlobalConstant;
import com.cil.Model.OutPushConfigEntity;
import com.cil.Repository.OutPushConfigRepository;
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
public class TestProcessRequest {
    @Autowired
    private OutPushConfigRepository outPushConfigRepository;

    @Test
    public void TestSendToOut() throws Exception {
        try {
            SendRequest request = SendRequest.newBuilder()
                    .setMerCode("123456")
                    .setInscd("00000001")
                    .setMerName("bobTest")
                    .build();

            ProcessRequest processRequest = new ProcessRequest();
            processRequest.sendToOut(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}