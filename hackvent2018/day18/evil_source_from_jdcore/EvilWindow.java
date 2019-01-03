/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  hackvent2018.evil.EvilAction
 *  hackvent2018.evil.EvilHandler
 *  hackvent2018.evil.EvilImages
 *  hackvent2018.evil.EvilType
 *  hackvent2018.evil.EvilWindow
 */
package hackvent2018.evil;

import hackvent2018.evil.EvilAction;
import hackvent2018.evil.EvilHandler;
import hackvent2018.evil.EvilImages;
import hackvent2018.evil.EvilType;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EvilWindow {
    private JFrame frame = new JFrame("Evilist (Click on Image)");
    private JLabel label = new JLabel();
    private JPanel jp = new JPanel();

    public EvilWindow() {
        EvilImages.preloadImages();
        this.frame.setDefaultCloseOperation(3);
        this.frame.setBackground(Color.BLACK);
        this.jp.setBackground(Color.BLACK);
        this.label.setBackground(Color.BLACK);
        this.setIcon(EvilImages.getIcon((EvilType)EvilType.QUESTION));
        this.jp.add(this.label);
        this.frame.getContentPane().add(this.jp);
        this.frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation(dim.width / 2 - this.frame.getSize().width / 2, dim.height / 2 - this.frame.getSize().height / 2 - 150);
        this.frame.setVisible(true);
        this.label.addMouseListener((MouseListener)new EvilHandler(this));
    }

    public void ask() {
        EvilAction ea = new EvilAction();
        this.setIcon(EvilImages.getIcon((EvilType)EvilType.QUESTION));
        Object[] buttons = ea.getMenu();
        int answer = JOptionPane.showOptionDialog(null, "Are you evil?", "Evilist", -1, 3, null, buttons, buttons[0]);
        ImageIcon ii = ea.respond1(answer);
        this.setIcon(ii);
        ea.respond2(answer);
    }

    private void setIcon(ImageIcon ii) {
        this.label.setIcon(ii);
    }
}
