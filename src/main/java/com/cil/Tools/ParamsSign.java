/**
 * File Name：ParamsSign.java
 * <p>
 * Copyright Cardinfolink Corporation 2015
 * All Rights Reserved
 */
package com.cil.Tools;

import com.alibaba.fastjson.JSONObject;
import com.cil.Global.GlobalConstant;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class ParamsSign {
    //	private static final Log logger = LogFactory.getLog(ParamsSign.class);
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    /**
     *
     * paramsSign(将请求参数进行签名)
     * 签名流程： 将除 ”sign” 以外的所有参数按 key 进行字典升序后，以 “key1=value1&key2=value2…” 的形式拼接起来，将拼接后的明文进行HMAC-SHA1加密
     * TODO(这里描述这个方法的使用方法 – 可选)
     * TODO(这里描述这个方法的注意事项 – 可选)
     * @param  @return    设定文件
     * @return String    DOM对象
     * @Exception 异常对象
     * @since CodingExample　Ver(编码范例查看) 1.0.0
     */
    public static String paramsSign(Map<String, String[]> params) {
        String appkey = params.get(GlobalConstant.REQUEST_APP_KEY)[0]; // 应用 key
        Map<String, String[]> treeMap = new TreeMap<String, String[]>(); // 有序 map 用来承载所有参数&值
        treeMap.putAll(params);
        StringBuilder stringBuilder = new StringBuilder(""); // 用来封装要签名的明文信息

        int sigParams = 0; // 可以进行签名的参数的个数统计
        Iterator<Map.Entry<String, String[]>> entries = treeMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String[]> entry = entries.next();
            if (entry.getKey().equals(GlobalConstant.REQUEST_APP_SIG)) { // 除了 sig 以外的其他参数都要进行签名
                continue;
            }
            if (entry.getValue().length > 1) { // 一个参数多个值的情况，比如 term/models/ids 接口
                /**
                 * 算法以 term/models/ids 接口为例
                 * 请求参数：term_model_id=77&term_model_id=75&term_model_id=76&term_model_id=74
                 * 先根据值进行排序，也就是74、 75、 76、 77
                 * 然后再进行拼接：term_model_id=74&term_model_id=75&term_model_id=76&term_model_id=77
                 * 注意：相同的值只签名一次，比如term_model_id=77&term_model_id=75&term_model_id=77&term_model_id=74
                 * 拼接后的明文是：term_model_id=74&term_model_id=75&term_model_id=77
                 */
                List<String> list = Arrays.asList(entry.getValue());
                Set<String> set = new TreeSet<String>(list);
                for (String param : set) {
                    if (sigParams > 0) { // 不是第一个参数，以 & 开始
                        stringBuilder.append("&");
                    }
                    stringBuilder.append(entry.getKey());
                    stringBuilder.append("=");
                    stringBuilder.append(param);
                    sigParams++;
                }
            } else { // 一个参数只有一个值
                if (sigParams > 0) { // 不是第一个参数，以 & 开始
                    stringBuilder.append("&");
                }
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append(entry.getValue()[0]);
                sigParams++;
            }

        }
//		ParamsSign.logger.debug("stringBuilder=" + stringBuilder);

        return ParamsSign.hmacSha1(stringBuilder.toString(), appkey);
    }

    /**
     * hmacSha1(对明文进行 hmac sha1 加密)
     * TODO(这里描述这个方法适用条件 – 可选)
     * TODO(这里描述这个方法的执行流程 – 可选)
     * TODO(这里描述这个方法的使用方法 – 可选)
     * TODO(这里描述这个方法的注意事项 – 可选)
     * @param   value    输入的参数
     * 			key		加密 key
     * @param  @return    加密后的密文字符串
     * @return String    DOM对象
     * @Exception 异常对象
     * @since CodingExample　Ver(编码范例查看) 1.0.0
     */
    private static String hmacSha1(String value, String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, ParamsSign.HMAC_SHA1_ALGORITHM);

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes("utf-8"));

            // Convert raw bytes to Hex
            byte[] hexBytes = new Hex().encode(rawHmac);

            // Covert array of Hex bytes to a String
            return new String(hexBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //直接sha256加密,并去除"sig"
    public static String paramsSign(String jsonStr, String appKey, String rmKey) {
        int start = jsonStr.indexOf(",\"" + rmKey + "\"");
        int end = jsonStr.indexOf("\"", start + 8) + 1;
        jsonStr = jsonStr.replaceAll(jsonStr.substring(start, end), "");
        return Encrypt(jsonStr + appKey, "");
    }

    public static String paramsSign(Object object, String appKey) {
        String jsonStr = JSONObject.toJSONString(object);
        return Encrypt(jsonStr + appKey, "");
    }

    //hmacSha256加密
    public static String hmacSha256(String value, String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, ParamsSign.HMAC_SHA256_ALGORITHM);

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes("utf-8"));

            // Convert raw bytes to Hex
            byte[] hexBytes = new Hex().encode(rawHmac);

            // Covert array of Hex bytes to a String
            return new String(hexBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     *
     * @param strSrc
     *            要加密的字符串
     * @param encName
     *            加密类型
     * @return
     */
    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    public static void main(String[] args) {
        String str = "{\"transKey\":\"06015598     4682480301061714      0    \",\"acqInsCode\":\"0800042900   \",\"merCode\":\"SkyTest12345678\",\"recvInsCode\":\"06015598     \",\"virMerCode\":\"     002290058120001\",\"cardBrand\":\"MCC\",\"dccFlag\":\"0\",\"merType\":\"7011\",\"virMerType\":\"1520\",\"transCode\":\"PER\",\"issrInsCode\":\"0899999906   \",\"cardType\":\"2\",\"acqSettAmt\":\"260.000\",\"acqSettCurr\":\"156\",\"merSettAmt\":\"260.000\",\"merSettCurr\":\"156\",\"issrSettAmt\":\"260.000\",\"issrSettCurr\":\"156\",\"appCode\":\"001\"}585f71ab52fd40659bea27d6758f3ec4";
        System.out.println(Encrypt(str, ""));
    }
}
