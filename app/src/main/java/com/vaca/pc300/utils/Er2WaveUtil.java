package com.vaca.pc300.utils;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Er2WaveUtil {
    public static float byteTomV(byte a, byte b) {
        if (a == (byte) 0xff && b == (byte) 0x7f)
            return 0f;

        int n = ((a & 0xFF) | (short) (b << 8));

//        float mv = (float) (n*12.7*1800*1.03)/(10*227*4096);
        float mv = (float) (n * (1.0035 * 1800) / (4096 * 178.74));
//        float mv = (float) (n * 0.002467);

        return mv;
    }

    public static float byteTomV2(byte a, byte b) {
        int n = ((a & 0xFF) | (short) (b << 8));
        float mv = (float) (n * (1.0035 * 1800) / (4096 * 178.74));
        return mv;
    }

    public static float byteTomV4(byte a, byte b) {
        int n = ((a & 0xFF) | (short) (b << 8));
        float mv = (float) (n * (1.08 * 3000) / (4096 * 284));
        return mv;
    }
    public static double byteTomV4x(short j) {
        int n = j;
        return (float) (n * (1.0035 * 1800) / (4096 * 178.74));
    }

 //   public static float wu=0.655f;

 /*   public static float byteTomV3(byte a, byte b) {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.put(a);
        bb.put(b);
        short shortVal = bb.getShort(0);
        float mv = (float) (shortVal / (405.35062829));
        return mv;
    }*/


    public static short getShort(byte a, byte b) {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.put(a);
        bb.put(b);
        short shortVal = bb.getShort(0);
        return shortVal;
    }

}
