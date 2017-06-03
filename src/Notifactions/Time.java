/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Notifactions;

import Main.Constant;
import Main.Notifaction;
import Main.Popup;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Arthur
 */
public class Time extends Notifaction{
    private int day, month, year, hour, minute;
    private Updater update;
    private Popup pop;
    private boolean admin;
    
    public Time(boolean admin){
        this.admin = admin;
        notifaction_type = 1;
    }
    
    public void exit(){
        update.running = false;
        if (pop!=null){
            pop.destroy();
        }
    }
    
    public void popup(){
        notified = true;
        //play music
        
        //pop-up screen
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        // notification panel
        JLabel title = new JLabel(name);
        JLabel info = new JLabel(assignment);
        JLabel time = new JLabel("("+getTimePretty()+")");
        
        JButton exit = new JButton();
        JButton information = new JButton();
        
        final Font titleF = new Font("Sanserif", Font.BOLD, 18);
        final Font infoF = new Font("SanSerif", Font.PLAIN, 12);
        final Font timeF = new Font("SanSerif", Font.ITALIC, 12);
        
        title.setBounds(5, 5, 90, 20);
        title.setFont(titleF);
        
        info.setBounds(5, 25, 190, 30);
        info.setFont(infoF);
        
        time.setBounds(95, 5, 50, 20);
        time.setFont(timeF);
        
        exit.setBounds(160, 5, 25, 25);
        exit.setIcon(new ImageIcon(new ImageIcon("Images/exit.jpeg").getImage()));
        exit.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                pop.destroy();
            }
        });
        
        information.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                pop.destroy();
                
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(null);
                
                JLabel infoTitle = new JLabel();
                JTextArea infoInformation = new JTextArea();
                
                infoTitle.setBounds(5, 5, 490, 20);
                infoTitle.setText(name);
                infoTitle.setFont(titleF);
                
                infoInformation.setBounds(5, 30, 490, 365);
                infoInformation.setText(assignment);
                infoInformation.setEditable(false);
                infoInformation.setFont(infoF);
                
                infoPanel.add(infoTitle);
                infoPanel.add(infoInformation);
                
                Popup p = new Popup(infoPanel, new Point(0,0), new Dimension(500, 400), true);
                
            }
        });
        
        information.setBounds(160, 35, 25, 25);
        information.setIcon(new ImageIcon(new ImageIcon("Images/info.jpeg").getImage()));
        
        panel.add(title);
        panel.add(info);
        panel.add(time);
        panel.add(exit);
        panel.add(information);
        
        Dimension moniter = Toolkit.getDefaultToolkit().getScreenSize();
        
        pop = new Popup(panel, new Point(moniter.width-200, 0), new Dimension(200, 65), 15, false);
    }
    
    public void getInfo(Scanner scan){
        
        if (!admin){
            notifiGetInfo(scan);

            Date d = new Date();
            System.out.println("Current Day: " + d.getHours() + ":" + d.getMinutes() + " (" + (d.getMonth()+1) + "/" + d.getDate() + "/" + (d.getYear()+1900) + ")");

            System.out.print("Month: ");
            month = Integer.parseInt(scan.nextLine());

            System.out.print("Day: ");
            day = Integer.parseInt(scan.nextLine());

            System.out.print("Year: ");
            year = Integer.parseInt(scan.nextLine());

            System.out.print("Hour: ");
            hour = Integer.parseInt(scan.nextLine());

            System.out.print("Minute: ");
            minute = Integer.parseInt(scan.nextLine());
        } else {
            
            System.out.print("information: ");
            String allInfo = scan.nextLine();
            
            //ex. HW;do stuff;
            
            String[] allStuff = allInfo.split(";");
            for (int q = 0; q < allStuff.length; q++){
                
                if (q+1 == Constant.TIMED_ADMIN_NAME_REQUEST){
                    name = allStuff[q];
                } else if (q+1 == Constant.TIMED_ADMIN_ASSIGNMENT_REQUEST){
                    assignment = allStuff[q];
                } else if (q+1 == Constant.TIMED_ADMIN_MONTH_REQUEST){
                    month = Integer.parseInt(allStuff[q]);
                } else if (q+1 == Constant.TIMED_ADMIN_DAY_REQUEST){
                    day = Integer.parseInt(allStuff[q]);
                } else if (q+1 == Constant.TIMED_ADMIN_YEAR_REQUEST){
                    year = Integer.parseInt(allStuff[q]);
                } else if (q+1 == Constant.TIMED_ADMIN_HOUR_REQUEST){
                    hour = Integer.parseInt(allStuff[q]);
                }else if (q+1 == Constant.TIMED_ADMIN_MINUTE_REQUEST){
                    minute = Integer.parseInt(allStuff[q]);
                }
            }
        }
        
        update = new Updater();
    }
    
    public void viewData(){
        notiViewData();
        System.out.println("remind time="+hour+":"+minute+" ("+month + "/" + day + "/" + year+")");
    }
    
    public String getTimePretty(){
        String h;
        boolean pm;
        if (hour>12){
            pm = true;
            h = Integer.toString(hour-12);
        }else{
            pm = false;
            h = Integer.toString(hour);
        }
        
        String m;
        if (minute<10){
            m = "0"+Integer.toString(minute);
        }else{
            m = Integer.toString(minute);
        }
        
        if (pm){
            return h+":"+m+"pm";
        }
        return h+":"+m+"am";
        
    }
    
    private class Updater implements Runnable{
        
        public boolean running = true;
        
        public Updater(){
            Thread thread = new Thread(this);
            thread.start();
        }
        
        public void run() {
            while (running){
                Date d = new Date();
                if (d.getHours() == hour && d.getMinutes() == minute && (d.getMonth()+1) == month && d.getDate() == day && (d.getYear()+1900) == year && notified == false){
                    popup();
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {}
            }
        }
    }
    
}
