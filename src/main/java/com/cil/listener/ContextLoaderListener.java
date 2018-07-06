package com.cil.listener;

import com.cil.Global.GlobalConfig;
import com.cil.handler.DesktopPushServerHandler;
import org.apache.mina.core.session.ExpiringSessionRecycler;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by bob on 2017.08.17.
 */
public class ContextLoaderListener implements ServletContextListener {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // ** Acceptor设置
        NioDatagramAcceptor acceptor = new NioDatagramAcceptor();
        acceptor.getFilterChain()
                .addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
        // 设置MINA2的IoHandler实现类
        acceptor.setHandler(new DesktopPushServerHandler());
        // 设置会话超时时间（单位：毫秒），不设置则默认是10秒
        acceptor.setSessionRecycler(new ExpiringSessionRecycler(60 * 1000));

        // ** UDP通信配置
        DatagramSessionConfig dcfg = acceptor.getSessionConfig();
        dcfg.setReuseAddress(true);
        // 设置输入缓冲区的大小，压力测试表明：调整到2048后性能反而降低
        dcfg.setReceiveBufferSize(1024);
        // 设置输出缓冲区的大小，压力测试表明：调整到2048后性能反而降低
        dcfg.setSendBufferSize(1024 * 100);

        // ** UDP服务端开始侦听
        try {
            acceptor.bind(new InetSocketAddress(Integer.valueOf(GlobalConfig.getValue("deskPort"))));
            logger.info("UPD 服务监听端口：" + GlobalConfig.getValue("deskPort"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }
}
