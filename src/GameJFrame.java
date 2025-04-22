import javax.swing.*;
import java.util.Objects;

public class GameJFrame extends JFrame{

    GameJPanel panel;

    GameJFrame() {
        panel = new GameJPanel();
        this.setTitle("Slot Machine");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/res/7.png")));
        this.setIconImage(image.getImage());

        this.add(panel);

        this.pack();
        this.setVisible(true);
    }


}
