package frsystem;;

import java.util.Scanner;

public class Flight {
    public void addFlight(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("\nAirline: ");
        String fairl = sc.nextLine();
        System.out.print("Flight Numer: ");
        String fnum = sc.nextLine();
        System.out.print("Departure Airport: ");
        String depAir = sc.nextLine();
        System.out.print("Arrival Airport: ");
        String arrAir = sc.nextLine();
        System.out.print("Departure Date And Time: ");
        String depDateT = sc.nextLine();
        System.out.print("Arrival Date And Time : ");
        String arrDateT = sc.nextLine();
        System.out.print("Flight Status: ");
        String fstatus = sc.nextLine();
        
        String sql = "INSERT INTO flight (f_airl, f_fnum, f_depAir, f_arrAir, f_depDateT, f_arrDateT, f_fstatus) VALUES (?, ?, ?, ?, ?, ?, ?)";
        conf.addRecord(sql, fairl, fnum, depAir, arrAir, depDateT, arrDateT, fstatus);
    }
    
    public void addPlane(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("\nEnter Plane Name: ");
        String pname = sc.nextLine();
        System.out.print("Plane Type: ");
        String ptype = sc.nextLine();
        System.out.print("Passenger Capacity: ");
        int passCap = sc.nextInt();
        sc.nextLine();
        System.out.print("Plane Status: ");
        String pstat = sc.nextLine();
        
        String sql = "INSERT INTO plane (p_pname, p_ptype, p_passCap, p_pstat) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, pname, ptype, passCap, pstat);
    }
    
    public void viewFlight(){
        String Query = "SELECT * FROM flight";
        String[] Headers = {"Flight ID", "Flight Number", "Departure Airport", "Arrival Airport", "Departure Date and Time", "Arrival Date and Time", "Flight Status"};
        String[] Columns = {"f_id", "f_fnum", "f_depAir", "f_arrAir", "f_depDateT", "f_arrDateT", "f_fstatus"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
    }
    
    public void viewPlane(){
        String Query = "SELECT * FROM plane";
        String[] Headers = {"Plane ID", "Plane Name", "Plane Type", "Passenger Capacity", "Plane Status"};
        String[] Columns = {"p_id", "p_pname", "p_ptype", "p_passCap", "p_pstat"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
    }
    
    public void updateFlight(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
               
        System.out.print("\nEnter Flight ID to Update: ");
        int id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT f_id FROM flight WHERE f_id = ?", id)) == 0){
        System.out.println("Selected Flight ID doesn't exist!");
        System.out.print("Enter Flight ID again: ");
        id = sc.nextInt();
    } 
        sc.nextLine();
        System.out.print("Enter new Departure Date and Time: ");
        String depDateT = sc.nextLine();;
        System.out.print("New Arrival Date and Time: ");
        String arrDateT = sc.nextLine();;
         
        String sql = "UPDATE flight SET f_depDateT = ?, f_arrDateT = ? WHERE f_id = ?";
        conf.updateRecord(sql, depDateT, arrDateT, id);
    }
    
        public void updatePlane(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
               
        System.out.print("\nEnter Plane ID to Update: ");
        int id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT p_id FROM plane WHERE p_id = ?", id)) == 0){
        System.out.println("Selected Plane ID doesn't exist!");
        System.out.print("Enter Plane ID again: ");
        id = sc.nextInt();
    } 
        System.out.print("Enter new Passenger Capacity: ");
        int passCap = sc.nextInt();
        sc.nextLine();
        System.out.print("New Plane Status: ");
        String pstat = sc.nextLine();
         
        String sql = "UPDATE plane SET p_passCap = ?, p_pstat = ? WHERE p_id = ?";
        conf.updateRecord(sql, passCap, pstat, id);
    }
    
    public void deleteFlight(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("\nEnter Flight ID to Delete: ");
        int id = sc.nextInt();

        while((conf.getSingleValue("SELECT f_id FROM flight WHERE f_id = ?", id)) == 0){
        System.out.println("Selected Flight ID doesn't exist!");
        System.out.print("Enter Flight ID again: ");
        id = sc.nextInt();
    }

        String sql = "DELETE FROM flight WHERE f_id = ?";
        conf.deleteRecord(sql, id);
    }
    
    public void deletetPlane(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("\nEnter Plane ID to Delete: ");
        int id = sc.nextInt();

        while((conf.getSingleValue("SELECT p_id FROM plane WHERE p_id = ?", id)) == 0){
        System.out.println("Selected Plane ID doesn't exist!");
        System.out.print("Enter Plane ID again: ");
        id = sc.nextInt();
    }

        String sql = "DELETE FROM plane WHERE p_id = ?";
        conf.deleteRecord(sql, id);
    }
    
    public void flightRec(){
        Scanner sc = new Scanner(System.in);
        Flight fl = new Flight();
        
        int choice;
        boolean exit = true;
        
        do {
            System.out.println("\n------------ SELECTION CHOICE -------------");
            System.out.println("1. Add Plane");
            System.out.println("2. Add Flight");
            System.out.println("3. View Plane and Flight");
            System.out.println("4. Update Plane and Flight");
            System.out.println("5. Delete Plane and Flight");
            System.out.println("6. Exit");
            System.out.println("---------------------------------------------------");
            System.out.print("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    fl.addPlane();
                    break;
                    
                case 2:
                    fl.addFlight();
                    break;
                    
                case 3:
                    System.out.println("\n----- PLANE INFORMATION ------");
                    fl.viewPlane();
                    System.out.println("\n----- FLIGHT INFORMATION --------");
                    fl.viewFlight();
                    break;
                    
                case 4:
                    System.out.println("\nPLANE INFORMATION");
                    fl.viewPlane();

                    System.out.print("\nUpdate Plane (yes/no)?: ");
                    String upPlane = sc.next();
                    while(upPlane.equals("yes")){
                        fl.updatePlane();
                        System.out.println("\nPLANE INFORMATION");
                        fl.viewPlane();
                        break;
                    }
                    
                    System.out.println("\nFLIGHT INFORMATION");
                    fl.viewFlight();

                    System.out.print("\nUpdate Flight (yes/no)?: ");
                    String upFlight = sc.next();
                    while(upFlight.equals("yes")){
                        fl.updateFlight();
                        System.out.println("\nFLIGHT INFORMATION");
                        fl.viewFlight();
                        break;
                    }
                        
                    break;
                    
                case 5:
                    System.out.println("\nPLANE INFORMATION");
                    fl.viewPlane();

                    System.out.print("\nDelete Plane (yes/no)?: ");
                    String delPlane = sc.next();
                    while(delPlane.equals("yes")){
                        fl.deletetPlane();
                        System.out.println("\nPLANE INFORMATION");
                        fl.viewPlane();
                        break;
                    }
                    
                    System.out.println("\nFLIGHT INFORMATION");
                    fl.viewFlight();

                    System.out.print("\nDelete Flight (yes/no)?: ");
                    String delFlight = sc.next();
                    while(delFlight.equals("yes")){
                        fl.deleteFlight();
                        System.out.println("\nFLIGHT INFORMATION");
                        fl.deleteFlight();
                        break;
                    }
                    break;
                    
                case 6:
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
