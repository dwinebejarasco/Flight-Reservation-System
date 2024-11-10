package frsystem;

import java.util.Scanner;

public class Passenger {
    public void addPass(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("\nEnter First Name: ");
        String pfname = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String plname = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Birthdate (mm/dd/yy): ");
        String pbdate = sc.nextLine();
        System.out.print("Nationality: ");
        String pnat = sc.nextLine();
        System.out.print("Contact Number: ");
        String pcn = sc.nextLine();
        System.out.print("Status: ");
        String pstat = sc.nextLine();
        System.out.print("Passport Number: ");
        String ppassnum = sc.nextLine();    
        
        String sql = "INSERT INTO passenger (p_pfname, p_plname, p_age, p_pbdate, p_pnat, p_pcn, p_pstat, p_ppassnum) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        conf.addRecord(sql, pfname, plname, age, pbdate, pnat, pcn, pstat, ppassnum);
    }
    
    public void viewPass(){
        String Query = "SELECT * FROM passenger";
        String[] Headers = {"Passenger ID", "First Name", "Last Namet", "Age", "Birthdate", "Nationality", "Contact Number", "Status", "Passport Number"};
        String[] Columns = {"pass_id", "p_pfname", "p_plname", "p_age", "p_pbdate", "p_pnat", "p_pcn", "p_pstat", "p_ppassnum"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
    }
    
    public void updatePass(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
               
        System.out.print("\nEnter Passenger ID to Update: ");
        int id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT pass_id FROM passenger WHERE pass_id = ?", id)) == 0){
        System.out.println("Selected Passenger ID doesn't exist!");
        System.out.print("Enter Passenger ID again: ");
        id = sc.nextInt();
    } 
        sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Contact Number: ");
        String pcn = sc.nextLine();
        System.out.print("New Status: ");
        String pstat = sc.nextLine();
        
        String sql = "UPDATE passenger SET p_age = ?, p_pcn = ?, p_pstat = ? WHERE pass_id = ?";
        conf.updateRecord(sql, age, pcn, pstat, id);
    }
    
    public void deletePass(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("\nEnter Passenger ID to Delete: ");
        int id = sc.nextInt();

        while((conf.getSingleValue("SELECT pass_id FROM passenger WHERE pass_id = ?", id)) == 0){
        System.out.println("Selected Passenger ID doesn't exist!");
        System.out.print("Enter Passenger ID again: ");
        id = sc.nextInt();
    }

        String sql = "DELETE FROM passenger WHERE pass_id = ?";
        conf.deleteRecord(sql, id);
    }
    
    public void passRec(){
        Scanner sc = new Scanner(System.in);
        Passenger pg = new Passenger();
        
        int choice;
        boolean exit = true;
        
        do {
            System.out.println("\n------------ SELECTION CHOICE -------------");
            System.out.println("1. Add Passenger");
            System.out.println("2. View Passenger");
            System.out.println("3. Update Passenger");
            System.out.println("4. Delete Passenger");
            System.out.println("5. Exit");
            System.out.println("---------------------------------------------------");
            System.out.print("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    pg.addPass();
                    break;
                    
                case 2:
                    System.out.println("\n----- PASSENGER INFORMATION ------");
                    pg.viewPass();
                    break;
                    
                case 3:
                    System.out.println("\n----- PASSENGER INFORMATION ------");
                    pg.viewPass();
                    pg.updatePass();
                    break;
                    
                case 4:
                    System.out.println("\n----- PASSENGER INFORMATION ------");
                    pg.viewPass();
                    pg.deletePass();
                    break;
                    
                case 5:
                    System.out.print("Exit selected...type yes to continue: ");
                        String resp = sc.next();
                        if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                        }
                    break;

                    default:
                        System.out.println("Choice Error");
            }
        }while(exit); 
    }
}
