/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  hackvent2018.evil.EvilAction
 *  hackvent2018.evil.EvilEvent
 *  hackvent2018.evil.EvilImages
 *  hackvent2018.evil.EvilType
 *  hackvent2018.evil.NotEvil
 */
package hackvent2018.evil;

import hackvent2018.evil.EvilEvent;
import hackvent2018.evil.EvilImages;
import hackvent2018.evil.EvilType;
import hackvent2018.evil.NotEvil;
import java.util.Arrays;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class EvilAction {
    private byte[] b = new byte[]{-64, 15, 15, 10, 82, 79, 76, 67, 76};

    public String[] getMenu() {
        for (String s : System.getenv().keySet()) {
            if (!Arrays.equals(this.b, this.xor(s.getBytes(), NotEvil.b))) continue;
            String[] t = new String[]{"No", "Go away", "Yes"};
            return t;
        }
        String[] u = new String[]{"No", "Go away"};
        return u;
    }

    public ImageIcon respond1(int answer) {
        switch (answer) {
            case 0: {
                return EvilImages.getIcon((EvilType)EvilType.NOTEVIL);
            }
            case 1: {
                return EvilImages.getIcon((EvilType)EvilType.SAD);
            }
            case 2: {
                for (String s : System.getenv().keySet()) {
                    if (!Arrays.equals(this.b, this.xor(s.getBytes(), NotEvil.b))) continue;
                    return EvilImages.getIcon((EvilType)EvilType.EVIL);
                }
                return EvilImages.getIcon((EvilType)EvilType.NOTEVIL);
            }
        }
        return EvilImages.getIcon((EvilType)EvilType.SAD);
    }

    public void respond2(int answer) {
        if (answer == 2) {
            for (String s : System.getenv().keySet()) {
                if (!Arrays.equals(this.b, this.xor(s.getBytes(), NotEvil.b))) continue;
                Object[] buttons2 = new String[]{"Cool"};
                JOptionPane.showOptionDialog(null, EvilEvent.eventResult(), "Evilist", -1, 1, null, buttons2, buttons2[0]);
            }
        }
    }

    private byte[] xor(byte[] c, byte[] b) {
        byte[] x = new byte[c.length];
        int i = 0;
        while (i < c.length) {
            x[i] = (byte)(c[i] ^ b[i]);
            ++i;
        }
        return x;
    }
}
