import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


class Window extends JFrame implements java.awt.event.ActionListener {

    JPanel background = new JPanel();
    JPanel colorSquare = new JPanel();

    Color last;

    Timer timer = new Timer(1,this);
    JLabel rgbValue;

    // CREATES UI
    public Window() {
        setSize(1000, 720);
        timer.start();

        rgbValue = new JLabel(null,null,JLabel.HORIZONTAL);
        rgbValue.setBounds(0, 50, 1000, 100);
        rgbValue.setForeground(Color.WHITE);
        rgbValue.setFont(new Font("Monospaced",Font.PLAIN,50));

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Color");

        background.setBounds(0, 0, 1000, 720);
        background.setLayout(null);
        background.add(rgbValue);
        background.setBackground(new Color(10,10,10));

        colorSquare.setBounds(1000/2-200/2, 720/2-200/2, 200, 200);
        background.add(colorSquare);

        add(background);
        setVisible(true);
    }

    void update(int mouseX, int mouseY, Robot robot) {

        // GETTING COLOR OF THE PIXEL
        Color color = robot.getPixelColor(mouseX, mouseY);
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        // DISPLAYING THE COLOR
        colorSquare.setBackground(color);
        rgbValue.setForeground(Color.WHITE);
        rgbValue.setText("RGB: " + r + ", " + g + ", " + b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

public class launcher {
    static Robot robot;
    static Window window;

    public static void main(String[] args) {

        window = new Window();
        try {
            robot = new Robot();
        }catch(Exception e) {}
        
        // GETS MOUSE POSITION
        while (true) {
            try {
                Thread.sleep(10);
            }catch(Exception e) {}

            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;

            window.update(x,y,robot);
        }
    }
}