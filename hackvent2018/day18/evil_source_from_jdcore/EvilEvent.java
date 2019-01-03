/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  hackvent2018.evil.Evil
 *  hackvent2018.evil.EvilEvent
 *  hackvent2018.evil.NotEvil
 *  hackvent2018.evil.Question
 *  hackvent2018.evil.Sad
 */
package hackvent2018.evil;

import hackvent2018.evil.Evil;
import hackvent2018.evil.NotEvil;
import hackvent2018.evil.Question;
import hackvent2018.evil.Sad;

/*
 * Exception performing whole class analysis ignored.
 */
public class EvilEvent {
    private static byte[] b;

    static {
        byte[] arrby = new byte[41];
        arrby[0] = -83;
        arrby[1] = 8;
        arrby[2] = 119;
        arrby[3] = 19;
        arrby[4] = 73;
        arrby[5] = 17;
        arrby[6] = 2;
        arrby[7] = 83;
        arrby[8] = 126;
        arrby[9] = 17;
        arrby[10] = 33;
        arrby[11] = 119;
        arrby[12] = 115;
        arrby[13] = 6;
        arrby[14] = 38;
        arrby[15] = 16;
        arrby[16] = 26;
        arrby[17] = 23;
        arrby[18] = 10;
        arrby[19] = 127;
        arrby[20] = 20;
        arrby[21] = 85;
        arrby[22] = 81;
        arrby[23] = 47;
        arrby[24] = 13;
        arrby[25] = 88;
        arrby[26] = 43;
        arrby[28] = 70;
        arrby[29] = 27;
        arrby[30] = -122;
        arrby[31] = 8;
        arrby[32] = 83;
        arrby[33] = 17;
        arrby[34] = 125;
        arrby[35] = 46;
        arrby[36] = 78;
        arrby[37] = 64;
        arrby[38] = 89;
        arrby[39] = 78;
        arrby[40] = 41;
        b = arrby;
    }

    public EvilEvent() {
    }

    static String eventResult() {
        byte[] x = EvilEvent.xor((byte[])b, (byte[])NotEvil.b, (int)0);
        x = EvilEvent.xor((byte[])x, (byte[])Evil.b, (int)100);
        x = EvilEvent.xor((byte[])x, (byte[])Sad.b, (int)200);
        x = EvilEvent.xor((byte[])x, (byte[])Question.b, (int)300);
        return new String(x);
    }

    private static byte[] xor(byte[] c, byte[] b, int offset) {
        byte[] x = new byte[c.length];
        int i = 0;
        while (i < c.length) {
            x[i] = (byte)(c[i] ^ b[i + offset]);
            ++i;
        }
        return x;
    }
}
