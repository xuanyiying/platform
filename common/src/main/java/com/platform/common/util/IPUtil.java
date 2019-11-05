package com.platform.common.util;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangying
 * Created on 2019/11/1.
 */

public final class IPUtil {
    private static final String UNKNOWN = "unknown";
    private static final int IP_LENGTH = 15;
    private static final String LOCALHOST ="127.0.0.1";
    private static final String LOCALHOST_HEX = "0:0:0:0:0:0:0:1";
    private static Logger log = LoggerFactory.getLogger(IPUtil.class);
    private IPUtil() {
    }
   
    public static String getIpAddress() {
        HttpServletRequest request = WebUtil.getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if (checkIp(ip)) {
            if (checkIp(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (checkIp(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (checkIp(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (checkIp(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (checkIp(ip)) {
                ip = request.getRemoteAddr();
            }
            if (LOCALHOST.equals(ip) || LOCALHOST_HEX.equals(ip)) {
                InetAddress inet;
                try {
                    inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                } catch (Exception e) {
                    log.warn("Get client ip by request met exception,{}", e);
                }
            }
        } else if (ip.length() > IP_LENGTH) {
            String[] ips = ip.split(",");
            for (String strIp : ips) {
                if (!(UNKNOWN.equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    private static boolean checkIp(String ip) {
        return StringUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip);
    }
	

}
