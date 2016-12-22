package com.zyt.web.publics.utils;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class DatagramPacketUtil {
  public static DatagramPacket toDatagram(String s, InetAddress destIA,int destPort) {
        byte[] buf = s.getBytes();
        return new DatagramPacket(buf, buf.length, destIA, destPort);
    }

    public static String toString(DatagramPacket p) {
        return new String(p.getData(), 0, p.getLength());
    }
}
