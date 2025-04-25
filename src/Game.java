import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Game extends JFrame implements ChangeListener, ActionListener, Runnable {

    boolean pulled = false;

    int slot1;
    int slot2;
    int slot3;
    int[] slots = new int[3];

    int money = 500;

    Panel panel;
    JSlider bet;
    JLabel betNum;
    JButton button;

    Thread gameThread;

    Game() {

        panel = new Panel();
        panel.setPreferredSize(new Dimension(500,500));

        this.setTitle("Slot Machine");

        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        ImageIcon image = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/res/7.png")));
        this.setIconImage(image.getImage());


        bet = new JSlider(0,money,0);
        bet.addChangeListener(this);
        bet.setBounds(190,425,100,15);

        betNum = new JLabel();
        betNum.setText("Bet: " + bet.getValue());
        betNum.setBounds(170,400,50,40);

        button  = new JButton();
        button.addActionListener(this);
        button.setLayout(null);
        button.setBounds(10,10,240,135);


        this.add(panel);
        this.add(button);
        this.add(bet);
        this.add(betNum);


        this.setVisible(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        betNum.setText("Bet: " + bet.getValue());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Random rand = new Random();

        if (actionEvent.getSource().equals(button)) {

            slot1 = rand.nextInt(5)+1;
            slot2 = rand.nextInt(5)+1;
            slot3 = rand.nextInt(5)+1;
            slots[0] = slot1;
            slots[1] = slot2;
            slots[2] = slot3;
            Arrays.sort(slots);

            System.out.println(slot1+""+slot2+""+slot3);


            if (money > 0) {
                if (slots[0] == 5 && slots[0] == slots[1] && slots[0] == slots[2]) {
                    System.out.println("777 Bet 50x");
                    money += bet.getValue() * 50;
                    bet.setMaximum(money);
                } else if (slots[0] == 4 && slots[0] == slots[1] && slots[0] == slots[2]) {
                    System.out.println("All 3 Bells Bet 25x");
                    money += bet.getValue() * 25;
                    bet.setMaximum(money);
                } else if ((slots[0] == 1 && slots[0] == slots[1] && slots[1] == slots[2]) ||
                        (slots[0] == 2 && slots[0] == slots[1] && slots[1] == slots[2]) ||
                        (slots[0] == 3 && slots[0] == slots[1] && slots[1] == slots[2])) {
                    System.out.println("All Same Fruit Bet 10x");
                    money += bet.getValue() * 10;
                    bet.setMaximum(money);
                } else if (slots[0] == 1 && slots[1] == 2 && slots[2] == 3) {
                    System.out.println("All Fruit Bet 2x");
                    money += bet.getValue() * 2;
                    System.out.println(money);
                    bet.setMaximum(money);
                } else {
                    System.out.println("You Lost");
                    money -= bet.getValue();
                    bet.setMaximum(money);
                }
            }else {
                System.out.println("You Lost All Your Money, Go Cry");
            }

        }
    }

    @Override
    public void run() {
        while (gameThread != null) {

        }
    }
}
