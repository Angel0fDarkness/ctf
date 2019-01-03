/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  hackvent2018.evil.Evil
 *  hackvent2018.evil.EvilImages
 *  hackvent2018.evil.EvilType
 *  hackvent2018.evil.NotEvil
 *  hackvent2018.evil.Question
 *  hackvent2018.evil.Sad
 */
package hackvent2018.evil;

import hackvent2018.evil.Evil;
import hackvent2018.evil.EvilType;
import hackvent2018.evil.NotEvil;
import hackvent2018.evil.Question;
import hackvent2018.evil.Sad;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class EvilImages {
    private static List<ImageIcon> iiList = new ArrayList();

    public static void preloadImages() {
        iiList.add(new ImageIcon(NotEvil.b));
        iiList.add(new ImageIcon(Sad.b));
        iiList.add(new ImageIcon(Question.b));
        iiList.add(new ImageIcon(Evil.b));
    }

    public static ImageIcon getIcon(EvilType evilType) {
        return (ImageIcon)iiList.get(evilType.ordinal());
    }
}
