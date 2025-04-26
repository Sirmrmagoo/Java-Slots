import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Panel extends JPanel implements ActionListener, ChangeListener {

    int slot1 = 0;
    int slot2 = 0;
    int slot3 = 0;
    int[] slots = new int[3];

    final int PANEL_WIDTH = 500;
    final int PANEL_HEIGHT = 500;


    int money = 750;


    JLabel betNum;
    JSlider bet;
    Image slotIdle;
    JButton pullButton;
    Timer timer;

    Panel() {
        timer = new Timer(500,null);


        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(new Color(240,235,210));
        this.setLayout(null);

        slotIdle = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/slot.png"))).getImage();


        pullButton = new JButton("p");
        pullButton.setLayout(null);
        pullButton.addActionListener(this);
        pullButton.setBounds(60,60,120,40);
        this.add(pullButton);


        bet = new JSlider(0, money,0);
        bet.addChangeListener(this);
        bet.setLayout(null);
        bet.setBounds(10,10,100,15);
        this.add(bet);

        betNum = new JLabel();
        betNum.setText("Bet: " + bet.getValue());
        betNum.setLayout(null);
        betNum.setBounds(10,10,50,40);
        this.add(betNum);
   }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(pullButton)) {
            if (money > 0) {
                Random rand = new Random();
                slot1 = rand.nextInt(5) + 1;
                slot2 = rand.nextInt(5) + 1;
                slot3 = rand.nextInt(5) + 1;

                slots[0] = slot1;
                slots[1] = slot2;
                slots[2] = slot3;

                Arrays.sort(slots);

                System.out.println(slot1 + "" + slot2 + "" + slot3);

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
            } else {
                System.out.println("You Lost All Your Money, Go Cry");
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // paints background and child components properly
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(slotIdle, 50, 50, null); // draws under the button
    }

    @Override
    public void stateChanged (ChangeEvent changeEvent){

        betNum.setText("Bet: " + bet.getValue());
    }

    public void startGameThread () {
    }
}
