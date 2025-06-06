import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException, InterruptedException {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/res/logo/7.png")));
        JFrame frame = new JFrame("Slot Machine");
        Panel panel = new Panel();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);
    }
}