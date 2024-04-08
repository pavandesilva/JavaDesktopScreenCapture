
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
import javax.swing.JTextField;


 public class captureTest extends javax.swing.JFrame implements ActionListener,Runnable{
    public captureTest(){ //Constructor
        System.out.println("Constructor");
        frame();
    }
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int frameHeight =500;
    int frameWidth =500;
    int captureHeight =50;
    int captureWidth =100;
    int captureX = (frameWidth-captureWidth)/2;
    int captureY = (frameHeight - captureHeight)/2-100;
    int CCX = (frameWidth-captureWidth)/4;
    int CCY = captureY + 60;
    int CNoX = (((frameWidth-captureWidth)/4)*3);
    int captureId =0;
    JLayeredPane jLayeredPanel;
    JLabel screen, captureCount,captureNo;
    JButton jButton1;
    boolean stat = false;
    
    
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
        
        //capture Button
        System.out.println("Capture Button");
        jButton1 = new JButton("Capture");
        jButton1.setBounds(captureX, captureY,captureWidth, captureHeight);
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
        captureTest cap = new captureTest();
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stat = true;
        do{
        try{
            String format = "jpg";
            String fileName = String.valueOf(captureId) +format;
            
            Robot r = new Robot();

            Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bi = r.createScreenCapture(screenSize);
            
            ImageIcon i =new ImageIcon(bi);
            
            screen.setIcon(i);
            
            ImageIO.write(bi, format, new File(fileName));
            ++captureId;
            System.out.println("Screenshot Captured");
            String pics = String.valueOf(captureId);
            captureNo.setText(pics);
            screen = new JLabel(new ImageIcon(bi));
            try{
                Thread.sleep(500);
            }catch(Exception er){
                System.out.println(er);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        }while(stat==true);
    }

    @Override
    public void run() {
}


}