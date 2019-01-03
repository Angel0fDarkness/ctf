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

public class RedPill {
    public static void main(String[] args) throws IOException {
        
	byte[] b = new byte[] { (byte)0x89, (byte)0x50, (byte)0x4e, (byte)0x47, (byte)0x0d, (byte)0x0a, (byte)0x1a, (byte)0x0a };
	byte[] f = new byte[(b.length + 1) / 2];
	for (int i = 0; i < b.length; i++) {
            f[i / 2] = i % 2 == 0 ? (byte)(f[i / 2] | b[i] << 4) : (byte)(f[i / 2] | b[i] & 15);
        }

	byte[] e = new byte[] { (byte)0x59, (byte)0xc5, (byte)0xb2, (byte)0xab };
	for (int i = 0; i < 100000000; i++) {
	    String s = String.format("%08d", i);
            if ((i % 1000000) == 0) {
		System.out.println(s);
	    }
	    byte[] iv = s.getBytes();
            byte[] k = new byte[16];
            System.arraycopy(iv, 0, k, 0, 8);
            System.arraycopy(iv, 0, k, 8, 8);
            Cipher3 c = new Cipher3();
            c.setupKey(k);
            c.setupIV(iv);
	    byte[] f1 = new byte[f.length];
	    System.arraycopy(f, 0, f1, 0, f.length);
            byte[] fc = c.crypt(f1);
	    /*StringBuilder sb = new StringBuilder();
	    for ( byte fcb : fc ) {
	        sb.append(String.format("%02X", fcb));
	    }
	    System.out.println(sb.toString());*/
	    if (Arrays.equals(fc, e)) {
		System.out.println("Found PIN: " + s);
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

