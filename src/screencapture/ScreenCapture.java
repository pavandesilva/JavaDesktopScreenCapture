package screencapture;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;


public class ScreenCapture extends javax.swing.JFrame implements ActionListener{
    public ScreenCapture(){ //Constructor
        System.out.println("Constructor");
        frame();
    }
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int frameHeight =800;
    int frameWidth =500;
    int captureHeight =50;
    int captureWidth =100;
    int captureX = (frameWidth-captureWidth)/2;
    int captureY = (frameHeight - 100);
    JLayeredPane jLayeredPanel;
    JLabel screen;
    JButton jButton1;
    
    
    private void frame(){
        System.out.println("Frame()");
        jLayeredPanel = new JLayeredPane();
        screen = new JLabel();
        jButton1 = new JButton();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        //JFrame
        System.out.println("JFrame");
        setTitle("Capture");
        setBounds(0,0,frameWidth,frameHeight);
        setLayout(null);
        setResizable(false);
        
        //JLabel
        System.out.println("JLabel");
        screen = new JLabel();
        screen.setBounds(20, 20,1600, 800);
        add(screen);

        
        //capture Button
        System.out.println("Capture Button");
        jButton1 = new JButton("Capture");
        jButton1.setBounds(captureX, captureY,captureWidth, captureHeight);
        jButton1.addActionListener(this);
        add(jButton1);

        
        setVisible(true);
        }
    
    public static void main(String[] args){
        System.out.println("Main Method");
        ScreenCapture cap = new ScreenCapture();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String format = "jpg";
            String fileName = "FullScreenshot." +format;
            
            Robot r = new Robot();

            Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bi = r.createScreenCapture(screenSize);
            
            ImageIcon i =new ImageIcon(bi);
            
            screen.setIcon(i);
            
            ImageIO.write(bi, format, new File(fileName));
            System.out.println("Screenshot Captured");
            screen = new JLabel(new ImageIcon(bi));
        
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
}