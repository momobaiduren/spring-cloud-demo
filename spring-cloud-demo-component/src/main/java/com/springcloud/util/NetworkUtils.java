package com.springcloud.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author ZhangLong on 2019/11/1  4:56 下午
 * @version V1.0
 */
public final class NetworkUtils {

    private NetworkUtils(){}
    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;

            while(true) {
                NetworkInterface netInterface;
                do {
                    do {
                        do {
                            if (!allNetInterfaces.hasMoreElements()) {
                                return "";
                            }

                            netInterface = (NetworkInterface)allNetInterfaces.nextElement();
                        } while(netInterface.isLoopback());
                    } while(netInterface.isVirtual());
                } while(!netInterface.isUp());

                Enumeration addresses = netInterface.getInetAddresses();

                while(addresses.hasMoreElements()) {
                    ip = (InetAddress)addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception var4) {
            return "";
        }
    }

}
