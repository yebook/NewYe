package com.kermitye.yesdk.error;

/**
 * Created by kermitye
 * Date: 2018/5/28 14:27
 * Desc:
 */
public class ApiException extends RuntimeException {
    public static final int SERVER_ERROR_UNKNOW = 1001;

    private int code;

    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getErrorCode() {
        return code;
    }

}
