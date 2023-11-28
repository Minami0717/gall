package com.minami.gall.common.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtils {
    public static String getStartIp(HttpServletRequest request) throws UnknownHostException {
        String ipAddress = request.getRemoteAddr();
        if(ipAddress.equals("0:0:0:0:0:0:0:1")) { ipAddress = InetAddress.getLocalHost().getHostAddress(); }

        String[] ipArr = ipAddress.split("[.]");
        return String.format("%s.%s", ipArr[0], ipArr[1]);
    }
}
