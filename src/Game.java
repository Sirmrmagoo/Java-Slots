import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class Game extends JPanel implements Runnable, ActionListener {

    boolean pulled = false;

    int slot1;
    int slot2;
    int slot3;
    int[] slots = new int[3];

    JButton button;
    JLabel jLabel;
    Thread gameThread;

    Game() {

        this.setPreferredSize(new Dimension(500,500));
        this.setLayout(null);

        //Adds and sets variables for the button
        button  = new JButton();
        button.setSize(45,45);
        button.setLocation(280,155);
        button.addActionListener(this);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
        button.setFocusPainted(true);

        this.add(button);
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.dispose();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Random rand = new Random();

        if (actionEvent.getSource().equals(button)) {

            slot1 = rand.nextInt(5)+1;
            slot2 = rand.nextInt(5)+1;
            slot3 = rand.nextInt(5)+1;
            System.out.println(slot1+""+slot2+""+slot3);

            slots[0] = slot1;
            slots[1] = slot2;
            slots[2] = slot3;

            Arrays.sort(slots);
            System.out.println(Arrays.toString(slots));

            if (slots[0] == 5 && slots[0] == slots[1] && slots[0] == slots[2]) {
                System.out.println("777 Bet 50x");
            } else if (slots[0] == 4 && slots[0] == slots[1] && slots[0] == slots[2]) {
                System.out.println("All 3 Bells Bet 25x");
            } else if ((slots[0] == 1 && slots[0] == slots[1] && slots[1] == slots[2]) ||
                    (slots[0] == 2 && slots[0] == slots[1] && slots[1] == slots[2]) ||
                    (slots[0] == 3 && slots[0] == slots[1] && slots[1] == slots[2])) {
                System.out.println("All Same Fruit Bet 10x");
            } else if (slots[0] == 1 && slots[1] == 2 && slots[2] == 3) {
                System.out.println("All Fruit Bet 2x");
            } else {
                System.out.println("You Lost");
            }

        }
    }

    @Override
    public void run() {
        while (gameThread != null) {

        }
    }
}
