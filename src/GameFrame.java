import javax.swing.*;
import java.util.Objects;

public class GameFrame extends JFrame{

    Game panel;

    GameFrame() {
        panel = new Game();
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
