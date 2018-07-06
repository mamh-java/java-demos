package com.cil.Global;

/**
 * 常量和参数表
 */
public class GlobalConstant {

    // 公共参数
    public static final String dbName = "Foxbill";

    public static final String PRINT_STATUS_SUCCESS = "1";
    public static final String PRINT_STATUS_TIMEOUT = "2";

    public static final String DESK_RESPONSE_SUCCESS = "00";
    public static final String DESK_RESPONSE_FAILED = "01";
    public static final String DESK_TERM_NORMAL = "0";
    public static final String DESK_TERM_TIMEOUT = "1";
    public static final String DESK_MSG_TYPE_PRINT = "PrintMsg";
    public static final String DESK_MSG_TYPE_IDLE_PRINT = "IdleMsg";
    public static final String DESK_MSG_TYPE_TASTY = "TastyMsg";
    public static final String DESK_MSG_TYPE_IDLE_TASTY = "TastyIdleMsg";
    public static final int[] DESK_RESEND_INTERVAL = new int[]{10, 30, 120, 3600, 10800, 21600};

    public static final String MSG_SOURCE_KAFKA = "kafka";
    public static final String MSG_SOURCE_TASTY = "tasty";

    public static final String OUT_CONFIG_OPEN = "0";
    public static final String OUT_CONFIG_CLOSED = "1";
    public static final String OUT_RESP_CODE = "responseCode";
    public static final String OUT_RESP_CODE_SUCCESS = "000";
    public static final int[] OUT_RESEND_INTERVAL = new int[]{30, 300, 3600, 10800, 21600};

    /**
     * 系统级参数相关
     */
    public static final String REQUEST_APP_KEY = "appkey"; // 应用 key
    public static final String REQUEST_APP_SIG = "sig"; // 请求参数签名
    public static final String MSG_DIR_PUSH = "PushSystem";
    public static final String MSG_VERSION = "1.0";

    // 打印信息重发应答
    public static final String DESK_RESEND_SUCCESS = "00";
    public static final String DESK_RESEND_NO_MSG = "01";
    public static final String DESK_RESEND_NO_MERCODE = "02";
    public static final String DESK_RESEND_NO_TERMCODE = "03";
    public static final String DESK_RESEND_FREQUENT = "04";
    public static final String DESK_RESEND_SIGN_ERROR= "05";
    public static final String DESK_RESEND_FORMAT_ERROR = "06";
    public static final String DESK_RESEND_PROC_ERROR = "07";
}
