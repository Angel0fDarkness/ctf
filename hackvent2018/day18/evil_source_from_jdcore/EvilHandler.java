/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  hackvent2018.evil.EvilHandler
 *  hackvent2018.evil.EvilWindow
 */
package hackvent2018.evil;

import hackvent2018.evil.EvilWindow;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EvilHandler
implements MouseListener {
    private EvilWindow ew;

    public EvilHandler(EvilWindow ew) {
        this.ew = ew;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.ew.ask();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
