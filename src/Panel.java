import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Panel extends JPanel implements ActionListener, ChangeListener {

    int slot1 = 1;
    int slot2 = 2;
    int slot3 = 2;
    int[] slots = new int[3];

    Image[] frames;
    int currentFrame = 0;
    boolean animationActive = false;

    final int PANEL_WIDTH = 500;
    final int PANEL_HEIGHT = 500;

    int money = 750;

    JLabel betNum;
    JSlider bet;
    JLabel moneyNum;
    Image slotMachine;
    Image slot1icon;
    Image slot2icon;
    Image slot3icon;
    JButton pullButton;
    Timer timer;


    Panel() {
        animationFrames();

        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(new Color(240,235,210));
        this.setLayout(null);

        try {
            InputStream is = getClass().getResourceAsStream("/res/fonts/Minecraft.ttf");
            assert is != null;
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            betNum = new JLabel();
            betNum.setLayout(null);
            betNum.setFont(customFont);
            betNum.setBounds(235,440,400,40);
            this.add(betNum);

            moneyNum = new JLabel();
            moneyNum.setLayout(null);
            moneyNum.setFont(customFont);
            moneyNum.setBounds(225,15,400,40);
            moneyNum.setText("Money: " + money);
            this.add(moneyNum);

        } catch (Exception e) {
            System.out.println("Crashed");
        }

        pullButton = new JButton();
        pullButton.setLayout(null);
        pullButton.addActionListener(this);
        pullButton.setOpaque(false);
        pullButton.setContentAreaFilled(false);
        pullButton.setBorderPainted(false);
        pullButton.setBounds(278,160,50,50);
        this.add(pullButton);

        bet = new JSlider(0, money,0);
        bet.addChangeListener(this);
        bet.setLayout(null);
        bet.setBounds(200,470,100,15);
        bet.setValue(1);
        this.add(bet);
   }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(pullButton)) {
            if (money > 0) {
                animationActive = true;

                timer = new Timer(100, e -> {

                    if (currentFrame < frames.length - 1) {
                        currentFrame++;
                        repaint();
                    } else {
                        timer.stop();
                        animationActive = false;
                        currentFrame = 0;
                        repaint();
                        showSlots();
                    }
                });
                timer.start();
            }
        }
    }


    public void showSlots() {

        Random rand = new Random();

        slot1 = rand.nextInt(5) + 1;
        slot2 = rand.nextInt(5) + 1;
        slot3 = rand.nextInt(5) + 1;

        slots[0] = slot1;
        slots[1] = slot2;
        slots[2] = slot3;

        Arrays.sort(slots);
        updateSlots();

        if (slots[0] == 5 && slots[0] == slots[1] && slots[0] == slots[2]) {
            money += bet.getValue() * 50;
            bet.setMaximum(money);
        } else if (slots[0] == 4 && slots[0] == slots[1] && slots[0] == slots[2]) {
            money += bet.getValue() * 25;
            bet.setMaximum(money);
        } else if ((slots[0] == 1 && slots[0] == slots[1] && slots[1] == slots[2]) ||
                (slots[0] == 2 && slots[0] == slots[1] && slots[1] == slots[2]) ||
                (slots[0] == 3 && slots[0] == slots[1] && slots[1] == slots[2])) {
            money += bet.getValue() * 10;
            bet.setMaximum(money);
        } else if (slots[0] == 1 && slots[1] == 2 && slots[2] == 3) {
            money += bet.getValue() * 2;
            System.out.println(money);
            bet.setMaximum(money);
        } else {
            money -= bet.getValue();
            bet.setMaximum(money);
        }
        moneyNum.setText("Money: " + money);
    }

    public void animationFrames() {

        //SLOT1ICON GET THE RES PATH WITH THE SLOT1 INT
        slotMachine = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/slotMachine/slot.png"))).getImage();

        frames = new Image[12];
        for (int i = 0; i < 12; i++) {
            frames[i] = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/slotMachine/slotFrame" + i + ".png"))).getImage();
        }
        timer = new Timer(100, this);
    }

    public void updateSlots() {
        slot1icon= new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/slots/"+slot1+".png"))).getImage();
        slot2icon= new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/slots/"+slot2+".png"))).getImage();
        slot3icon= new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/slots/"+slot3+".png"))).getImage();

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(animationActive) {
            g2.drawImage(frames[currentFrame],50,50,null);

        }else {
            g2.drawImage(slotMachine, 50, 50, null);
            g2.drawImage(slot1icon,176,170,null);
            g2.drawImage(slot2icon,206,189,null);
            g2.drawImage(slot3icon,236,206,null);

        }
    }

    @Override
    public void stateChanged (ChangeEvent changeEvent){
        if (bet.getValue() == 0) {
            bet.setValue(1);
        }
        betNum.setText("Bet: " + bet.getValue());
    }


}
