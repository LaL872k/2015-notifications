/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.util.Scanner;

/**
 *
 * @author Arthur
 */
public class Notifaction {
    protected String name;
    protected String assignment;
    protected boolean notified = false;
    protected int notifaction_type;
    
    public Notifaction(){
        
    }
    
    public void exit(){}
    
    public void notifiGetInfo(Scanner scan){
        System.out.print("Name: ");
        name = scan.nextLine();
        
        System.out.print("Assignment: ");
        assignment = scan.nextLine();
    }
    
    public void getInfo(Scanner scan){
        
    }
    
    protected void popup() {
        notified = true;
    }
    
    public void notiViewData(){
        System.out.println("_____"+name+"_____\nassignment="+assignment);
    }
    
    public void viewData(){
        
    }
    
}
