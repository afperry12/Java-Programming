import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;

public class LavaLamp implements ActionListener {

    int red = 40, green = 130, blue = 160;
    JPanel jPanel = new JPanel();
    boolean isChangingColor = true;

    public int fixValues(int rgbNumber) {
        rgbNumber = Math.abs(rgbNumber);
        if (rgbNumber > 255) {
            rgbNumber -= 255;
        }
        return rgbNumber;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("ColorMode")) {
            if (isChangingColor) {
                isChangingColor = false;
                System.out.println(red + "," + green + "," + blue);
            } else if (!isChangingColor) {
                isChangingColor = true;
            }
        } else if (ae.getActionCommand().equals("Timer") && isChangingColor) {
            red = red + (int) (Math.random() * 5) - 2;
            green = green + (int) (Math.random() * 5) - 2;
            blue = blue + (int) (Math.random() * 5) - 2;
            red = fixValues(red);
            green = fixValues(green);
            blue = fixValues(blue);
            jPanel.setBackground(new Color(red, green, blue));
        }
    }

    public void SetupGraphics() {
        JFrame jFrame = new JFrame("LavaLamp");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setFocusable(false);

        jPanel = new JPanel();
        Dimension computerScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jPanel.setPreferredSize(computerScreenSize);
        jPanel.setBackground(new Color(red, green, blue));

        JButton jButton = new JButton("Button");
        jButton.addActionListener(this);
        jButton.setActionCommand("ColorMode");
        jPanel.add(jButton);

        jFrame.add(jPanel);
        jFrame.pack();
        jFrame.setVisible(true);

        Timer timer = new Timer(100, this);
        timer.setActionCommand("Timer");
        timer.start();

    }

    public static void main(String[] args) {
        LavaLamp lavaLamp = new LavaLamp();
        lavaLamp.SetupGraphics();
    }
}