package frsystem;

import java.util.Scanner;

public class Booking {
    public void addBook(){
        Scanner sc = new Scanner(System.in);
        Flight fl = new Flight();
        Passenger pass = new Passenger();
        config conf = new config();
        
        System.out.println("FLIGHT INFORMATION");
        fl.viewFlight();
        System.out.print("PASSENGER INFORMATION");
        pass.viewPass();

        System.out.print("Select Passenger ID: ");
        int bpass = sc.nextInt();
        
        while((conf.getSingleValue("SELECT pass_id FROM passenger WHERE pass_id = ?", bpass)) == 0){
        System.out.println("Selected Passenger ID doesn't exist!");
        System.out.print("Enter Passenger ID again: ");
        bpass = sc.nextInt();
    }
        sc.nextLine();  
        System.out.print("Flight ID: ");
        int bflightID = sc.nextInt();
        
        while((conf.getSingleValue("SELECT f_id FROM flight WHERE f_id = ?", bflightID)) == 0){
        System.out.println("Selected Flight ID doesn't exist!");
        System.out.print("Enter Flight ID again: ");
        bflightID = sc.nextInt();
    }
        sc.nextLine();
        System.out.print("Booking Date: ");
        String bDate = sc.nextLine();
        System.out.print("Cabin Class: ");
        String bcabclass = sc.nextLine();
        System.out.print("Seat Number: ");
        String bseatNum = sc.nextLine();
        System.out.print("Seat Price: ");
        int bsetPri = sc.nextInt();
        sc.nextLine();
        String bpayStat = "Pending";
        System.out.print("Enter Payment Method: ");
        String bpaymet = sc.nextLine();
        System.out.print("Booking Confirmation Number: ");
        String conNum = sc.nextLine();
        String revStat = "Pending";
        
        int total = bsetPri;
        System.out.print("Total Cost: "+total);
        sc.nextLine();
        
        String sql = "INSERT INTO booking (pass_id, b_flightID, b_Date, b_cabclass, b_seatNum, bs_etPri, b_paymet, b_payStat, b_conNum, b_revStat, b_totalc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        conf.addRecord(sql, bpass, bflightID, bDate, bcabclass, bseatNum, bsetPri, bpaymet, bpayStat, conNum, revStat, total);
    }
    
    public void viewBook(){
        String Query = "SELECT passenger.pass_id, p_plname, p_pnat, p_ppassnum FROM booking INNER JOIN passenger ON passenger.pass_id = booking.pass_id";
        String[] Headers = {"Passenger ID", "Passenger Name", "Nationality", "Passport Number"};
        String[] Columns = {"pass_id", "p_plname", "p_pnat", "p_ppassnum"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
    }
    
    public void viewBook2(){
            String Query = "SELECT booking.b_id, booking.b_Date, booking.b_flightID, flight.f_airl, flight.f_fnum, flight.f_depAir, "
                   + "flight.f_arrAir, flight.f_depDateT, flight.f_arrDateT, flight.f_fstatus "
                   + "FROM booking INNER JOIN flight ON flight.f_id = booking.b_flightID";
        String[] Headers = {"Booking ID", "Booking Date", "Flight ID", "Airline", "Flight Number", "Departure Airport", "Arrival Airport", "Departure Date and Time", "Arrival Date and Time", "Flight Status"};
        String[] Columns = {"b_id", "b_Date", "b_flightID", "f_airl", "f_fnum", "f_depAir", "f_arrAir", "f_depDateT", "f_arrDateT", "f_fstatus"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
    }
    
    public void viewBook3(){
        String Query = "SELECT b_cabclass, b_seatNum, b_totalc, b_paymet, b_payStat, b_conNum, b_revStat FROM booking";
        String[] Headers = {"Class", "Seat Number", "Total Fare", "Payment Method","Payment Status", "Booking Confirmation Num", "Reservation Status"};
        String[] Columns = {"b_cabclass", "b_seatNum", "b_totalc", "b_paymet", "b_payStat", "b_conNum", "b_revStat"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
    }
    
    public void updateBook(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter Booking ID: ");
        int id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT b_id FROM booking WHERE b_id = ?", id)) == 0){
        System.out.println("Selected Booking ID doesn't exist!");
        System.out.print("Enter Booking ID again: ");
        id = sc.nextInt();
    }
        sc.nextLine();
        System.out.print("New Cabin Class: ");
        String bcabclass = sc.nextLine();
        System.out.print("New Seat Number: ");
        String bseatNum = sc.nextLine();
        System.out.print("New Payment Status: ");
        String bpayStat = sc.nextLine();
        System.out.print("New Reservation Status: ");
        String revStat = sc.nextLine();
        
        String sql = "UPDATE booking SET b_cabclass = ?, b_seatNum = ?, b_payStat = ?, b_revStat = ? WHERE b_id = ?";
        conf.updateRecord(sql, bcabclass, bseatNum, bpayStat, revStat, id);
    }
    
    public void deleteBook(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("\nEnter Booking ID to Delete: ");
        int id = sc.nextInt();

        while((conf.getSingleValue("SELECT b_id FROM booking WHERE b_id = ?", id)) == 0){
        System.out.println("Selected Booking ID doesn't exist!");
        System.out.print("Enter Booking ID again: ");
        id = sc.nextInt();
    }

        String sql = "DELETE FROM booking WHERE b_id = ?";
        conf.deleteRecord(sql, id);
    }
    
    public void bookingRec(){
        Scanner sc = new Scanner(System.in);
        Booking bk = new Booking();
        
        int choice;
        boolean exit = true;
        
        do {
            System.out.println("\n------------ SELECTION CHOICE -------------");
            System.out.println("1. Add Booking");
            System.out.println("2. View Booking");
            System.out.println("3. Update Booking");
            System.out.println("4. Delete Booking");
            System.out.println("5. Exit");
            System.out.println("---------------------------------------------------");
            System.out.print("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    bk.addBook();
                    break;
                    
                case 2:
                    System.out.println("\n------ Passenger Details ------");
                    bk.viewBook();
                    System.out.println("\n------ Flight Details ------");
                    bk.viewBook2();
                    bk.viewBook3();
                    break;
                    
                case 3:
                    System.out.println("\n------ Flight Details ------");
                    bk.viewBook2();
                    bk.viewBook3();
                    bk.updateBook();
                    System.out.println("\n------ Flight Details ------");
                    bk.viewBook2();
                    bk.viewBook3();
                    break;
                    
                case 4:
                    System.out.println("\n------ Flight Details ------");
                    bk.viewBook2();
                    bk.viewBook3();
                    bk.deleteBook();
                    System.out.println("\n------ Flight Details ------");
                    bk.viewBook2();
                    bk.viewBook3();
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
