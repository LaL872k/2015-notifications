/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Arthur
 */
public class Popup {
    private JPanel panel;
    private Point location;
    private long fadeAwayTime;
    private boolean fadeAway;
    private boolean pretty;
    private Dimension dim;
    
    private JFrame frame;
    private Counter counter;
    
    public Popup(JPanel panel, Point location, Dimension dim, boolean pretty){
        fadeAway = false;
        
        this.dim = dim;
        this.panel = panel;
        this.location = location;
        this.pretty = pretty;
        
        go();
        
    }
    
    public Popup(JPanel panel, Point location, Dimension dim, int fadeAwayTime, boolean pretty){
        fadeAway = true;
        
        this.dim = dim;
        this.panel = panel;
        this.location = location;
        this.fadeAwayTime = (fadeAwayTime * 1000000000l);
        this.pretty = pretty;
        
        go();
        
    }
    
    public void go(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setPreferredSize(dim);
        frame.add(panel);
        frame.setLocation(location);
        frame.setUndecorated(!pretty);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.pack();
        frame.setVisible(true);
        
        if (fadeAway){
            counter = new Counter();
        }
    }
    
    public void destroy(){
        if (counter != null){
            counter.running = false;
        }
        frame.setVisible(false);
    }
    
    public JFrame getJFrame(){
        return frame;
    }
    
    private class Counter implements Runnable{
        public boolean running = true;
        
        public Counter(){
            Thread thread = new Thread(this);
            thread.start();
        }
        
        public void run() {
            
            long lastTime = System.nanoTime();
            
            while (running){
                
                long currentTime = System.nanoTime();
                
                if (currentTime-lastTime >= fadeAwayTime){
                    destroy();
                }
                
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
    }
    
}
