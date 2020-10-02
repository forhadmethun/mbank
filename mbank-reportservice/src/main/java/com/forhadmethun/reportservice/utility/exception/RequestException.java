package com.forhadmethun.reportservice.utility.exception;

/**
 * @author Md Forhad Hossain
 * @since 01/10/20
 */

public class RequestException extends Exception {
    public RequestException(String error) {
        super(error);
    }
}
