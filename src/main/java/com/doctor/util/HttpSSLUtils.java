/*
 * Copyright 2016 htouhui.com All right reserved. This software is the
 * confidential and proprietary information of htouhui.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with haitouhui.com.
 */
package com.doctor.util;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.GeneralSecurityException;

/**
 * @author redstarstar, star.hong@gmail.com
 * @version 1.0
 */
public class HttpSSLUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpSSLUtils.class);

    public static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory socketFactory = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            socketFactory = new SSLConnectionSocketFactory(sslContext, (s, sslSession) -> true);
        } catch (GeneralSecurityException e) {
            LOGGER.error("SSL|ERROR", e);
        }
        return socketFactory;
    }
}
