
package screencapture;

import java.awt.Font;

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
import javax.swing.JTextField;


public class continuousCaptureTest extends javax.swing.JFrame implements ActionListener,Runnable{

    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int frameHeight =500;
    int frameWidth =500;
    int captureHeight =50;
    int captureWidth =100;
    int captureX = (frameWidth-captureWidth)/4;
    int captureY = (frameHeight - captureHeight)/2-100;
    int CCX = (frameWidth-captureWidth)/4;
    int CCY = frameHeight -90;
    int CNoX = (((frameWidth-captureWidth)/4)*3);
    int captureId =0;
    JLayeredPane jLayeredPanel;
    JLabel screen, captureCount,captureNo;
    JButton jButton1;
    boolean stat = false;
    boolean capture;
    int count;
    String format = "jpg";
    Robot robot;
    Rectangle screenSize;
    
    private void frame(){       

        //JFrame
        System.out.println("JFrame");
        setTitle("Capture");
        setBounds(0,0,frameWidth,frameHeight);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
         //JLabel
        System.out.println("JLabel");
        screen = new JLabel();
        screen.setBounds(20, 20,1600, 800);
        add(screen);
        
        //capture Button
        System.out.println("Capture Button");
        jButton1 = new JButton("Capture");
        jButton1.setBounds(captureX, frameHeight- captureY-10,captureWidth, captureHeight);
        jButton1.setHorizontalAlignment(JTextField.CENTER);
        jButton1.addActionListener(this);
        add(jButton1);
        
        //capture Count
        System.out.println("Capture Count");
        captureCount = new JLabel("captureCount");
        captureCount.setBounds(CCX, CCY, 100, 50);
        captureCount.setHorizontalAlignment(JTextField.CENTER);
        add(captureCount);
        
        //No of captures
        System.out.println("Number of Capture");
        captureNo = new JLabel("0");
        captureNo.setBounds(CNoX, CCY, 100, 50);
        captureNo.setHorizontalAlignment(JTextField.CENTER);
        add(captureNo);
        
        setVisible(true);
        }
    
    public static void main(String[] args){
        System.out.println("Main Method");
        continuousCaptureTest cap = new continuousCaptureTest();
    
    }
        public continuousCaptureTest(){ //Constructor
        System.out.println("Constructor");
        frame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stat = true;
        screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    }
    void ScreenCapture(){
        try{
            Robot r = new Robot();
            screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            
            while (capture){
                String fileName = "img/abc"+ Math.random() +format;
                BufferedImage bi = robot.createScreenCapture(screenSize);
                bi = ImageResizer.setImageSize(bi, screen.getWidth(), screen.getHeight());
                ImageIcon i = new ImageIcon(bi);
                screen.setIcon(i);
                ImageIO.write(bi,"jpg", new File(fileName));
                ++count;
                System.out.println("Image count :"+count);
            }
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public void run() {
}


}