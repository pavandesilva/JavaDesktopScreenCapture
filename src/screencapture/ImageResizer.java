
package screencapture;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ImageResizer extends javax.swing.JFrame implements ActionListener,Runnable{
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int frameHeight =d.height;
    int frameWidth =d.width;
    int captureHeight =50;
    int captureWidth =100;
    int captureX = (frameWidth-captureWidth)/4;
    int capture2X = ((frameWidth-captureWidth)/4)*3;
    int captureY = (frameHeight-150);
    JButton jButton1,jButton2;
    JLabel screen;
    boolean capture;
    int count;
    String format = "jpg";
    Robot robot;
    Rectangle screenSize;
    
    public static BufferedImage setImageSize(BufferedImage img, int nWidth, int nHeight){
        Image tmp = img.getScaledInstance(nWidth, nHeight, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(nWidth, nHeight,BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        
        return dimg;
    }
    void components(){
        //JFrame
        setTitle("Capture");
        setBounds(0,0,frameWidth,frameHeight);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //capture start Button
        System.out.println("Start Button");
        jButton1 = new JButton("Start");
        jButton1.setBounds(captureX, captureY,captureWidth, captureHeight);
        jButton1.setHorizontalAlignment(JTextField.CENTER);
        jButton1.addActionListener(this);
        add(jButton1);
        
        //capture stop Button
        System.out.println("Stop Button");
        jButton2 = new JButton("Stop");
        jButton2.setBounds(capture2X, captureY,captureWidth, captureHeight);
        jButton2.setHorizontalAlignment(JTextField.CENTER);
        jButton2.addActionListener(this);
        add(jButton2);
        
        //JLabel
        System.out.println("JLabel");
        screen = new JLabel();
        screen.setBounds(20, 20,1600, 800);
        add(screen);
        
        setVisible(true);
    }
    void ScreenCapture(){
        try{
            robot = new Robot();
            screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            
            while (capture){
                String fileName = "img"+ Math.random() +format;
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
    public ImageResizer(){
        components();
        
    }
    public static void main(String[] args){
        System.out.println("Main Method");
        ImageResizer cap = new ImageResizer();
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Start")){
        capture = true;
        screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        ScreenCapture();
        }
        if(e.getActionCommand().equals("Stop")){
        capture = false;
        }
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
