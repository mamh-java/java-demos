package com.cil.Tools;

/**
 * Created by bob on 2017.10.26.
 */
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * 实现用于主机名验证的基接口。
 * 在握手期间，如果 URL 的主机名和服务器的标识主机名不匹配，则验证机制可以回调此接口的实现程序来确定是否应该允许此连接。
 */
public class MyHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
//        if("localhost".equals(hostname)){
//            return true;
//        } else {
//            return false;
//        }
        // 解决HTTPS hostname wrong 的问题
        return true;
    }
}