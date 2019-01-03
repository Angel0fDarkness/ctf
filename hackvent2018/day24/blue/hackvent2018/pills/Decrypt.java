package hackvent2018.pills;

import hackvent2018.pills.Cipher3;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;

public class Decrypt {
    public static void main(String[] args) throws IOException {
	    byte[] iv = "45288109".getBytes();
            byte[] k = new byte[16];
            System.arraycopy(iv, 0, k, 0, 8);
            System.arraycopy(iv, 0, k, 8, 8);
            Cipher3 c = new Cipher3();
            c.setupKey(k);
            c.setupIV(iv);
            byte[] f = Files.readAllBytes(new File("flag_encrypted").toPath());
	    byte[] fc = c.crypt(f);
	    Files.write(new File("flag").toPath(), fc, new OpenOption[0]);

    }
}

