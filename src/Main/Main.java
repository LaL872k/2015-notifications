/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import Notifactions.Time;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Arthur
 */
public class Main {
    private final String version = "1";
    private ArrayList<Notifaction> notis;
    
    public Main(){
        startingInfo();
        Scanner scan = new Scanner(System.in);
        notis = new ArrayList<Notifaction>();
        
        // main loop
        while (true){
            String beforeMessage = "<User>: ";
            System.out.print(beforeMessage);
            String message = scan.nextLine();
            System.out.println(message);
            // the ifs
            if (message.equals("list")){
                list();
            }else if (message.equals("create")){
                create(scan);
            }else if (message.equals("view")){
                view();
            }else if (message.equals("exit")){
                break;
            }
        }
        
        for (int q = 0; q < notis.size(); q++){
            notis.get(q).exit();
        }
        
        scan.close();
        
    }
    
    public void create(Scanner scan){
        System.out.println("1 : Time based notifaction\n"
                + "menu : return to menu");
        
        String answer;
        
        while (true){
            System.out.print("Type: ");
            answer = scan.nextLine();

            if (answer.startsWith("admin")){
                answer = answer.replaceFirst("admin", "");
                if (answer.equals("1")){
                    Time newNoti = new Time(true);
                    newNoti.getInfo(scan);
                    notis.add(newNoti);
                    break;
                }
            }

            //time
            if (answer.equals("1")){
                Time newNoti = new Time(false);
                newNoti.getInfo(scan);
                notis.add(newNoti);
                break;
            }
            
            if (answer.equals("menu")){
                break;
            }
        }
    }
    
    public void list(){
        System.out.println("___LIST_OF_COMMANDS___");
        System.out.println("\"list\" for a list of commands.\n"
                + "\"view\" to view all current notifications.\n"
                + "\"create\" to create a new notification.\n"
                + "\"exit\" to exit the app.");
    }
    
    public void view(){
        System.out.println(notis.size());
        for (int q = 0; q < notis.size(); q++){
            notis.get(q).viewData();
        }
    }
    
    public void startingInfo(){
        System.out.println("Arthur's Notifaction Center V.[" + version + "]");
        System.out.println("Type \"list\" for a list of commands.");
    }
    
    public static void main(String[] args){
        new Main();
    }
}
