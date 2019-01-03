/*
 * Decompiled with CFR 0_132.
 */
package hackvent2018.pills;

import hackvent2018.pills.Cipher3;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;

public class BluePill {
    public static void main(String[] args) throws IOException {
        
	byte[] b = new byte[] { (byte)0x89, (byte)0x50, (byte)0x4e, (byte)0x47, (byte)0x0d, (byte)0x0a, (byte)0x1a, (byte)0x0a };
	byte[] f = new byte[(b.length + 1) / 2];
	for (int i = 0; i < f.length; i++) {
        	byte x = (byte)(((b[2*i]&0xff)>>4)<<4);
		byte y = (byte)((b[2*i+1]&0xff)>>4);
		f[i] = (byte)(x | y);
	}
	byte[] iv = new byte[] { (byte)0, (byte)0, (byte)0, (byte)0, (byte)0xc3, (byte)0x6e, (byte)0xd4, (byte)0x01 };
	byte[] k = new byte[] { (byte)0x87, (byte)0x05, (byte)0x89, (byte)0xcd, (byte)0xa8, (byte)0x75, (byte)0x62, (byte)0xef, (byte)0x38, (byte)0x45, (byte)0xff, (byte)0xd1, (byte)0x41, (byte)0x37, (byte)0x54, (byte)0xd5 };

	byte[] e = new byte[] { (byte)0x1e, (byte)0xd5, (byte)0x3b, (byte)0xf4 };
	for (int i = 0xff361700; i > 0xb7af8b00; i--) {
	    iv[0] = (byte)(i&0xFF);
	    iv[1] = (byte)((i&0xFF00)>>8);
	    iv[2] = (byte)((i&0xFF0000)>>16);
	    iv[3] = (byte)((i&0xFF000000)>>24);
            
	    Cipher3 c = new Cipher3();
            c.setupKey(k);
            c.setupIV(iv);
	    byte[] f1 = new byte[f.length];
	    System.arraycopy(f, 0, f1, 0, f.length);
            byte[] fc = c.crypt(f1);
	    StringBuilder sb = new StringBuilder();
	    /*for ( byte fcb : fc ) {
	        sb.append(String.format("%02X", fcb));
	    }
	    System.out.println(sb.toString());*/
	    if (Arrays.equals(fc, e)) {
		System.out.println(String.format("Found PIN: 0x%08X", i));
		byte[] crypted = Files.readAllBytes(new File("flag_encrypted").toPath());
		Cipher3 c3 = new Cipher3();
		c3.setupKey(k);
		c3.setupIV(iv);
		byte[] decrypted = c3.crypt(crypted);
		Files.write(new File("flag").toPath(), decrypted, new OpenOption[0]);
		break;
	    }
	}
    }
}

