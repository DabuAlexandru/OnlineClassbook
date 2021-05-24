package onlineClassbook.menu;

import onlineClassbook.models.faculty.Faculty;
import onlineClassbook.service.csv.Audit;
import onlineClassbook.service.csv.ReaderWriter;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    protected final Faculty faculty;
    protected final ReaderWriter readerWriter;
    protected final Audit audit;
    protected final Scanner myInput;
    protected int option;

    public Menu() throws IOException {
        faculty = Faculty.getFaculty("FMI");
        readerWriter = ReaderWriter.getReaderWriter();
        audit = Audit.getAudit();
        faculty.addSpecialization("Mathematics", 100, 50, 1000);
        faculty.addSpecialization("Informatics", 200, 50, 1500);
        myInput = new Scanner(System.in);
        option = 0;
    }

    public void principalMenu() throws IOException {
        System.out.println("Method in " + this.getClass().getSimpleName() + " is not available!!!");
    }

    public void chooseMenu() throws IOException {
        System.out.println("==== Choose the context in which you would like to run (CSV or DB) ====");
        System.out.print("Your choice: ");
        String choice = myInput.next().toLowerCase();
        printSpaces(3);
        if(choice.equals("db")) {
            DBMenu dbMenu = new DBMenu();
            dbMenu.principalMenu();
        } else {
            if(!choice.equals("csv")) {
                System.out.println("invalid input ::: switching to CSV context by default!!!");
                printSpaces(3);
            }
            CSVMenu csvMenu = new CSVMenu();
            csvMenu.principalMenu();
        }
    }

    protected static void printSpaces(int n) {
        for(int i = 0; i < n; i++){
            System.out.print('\n');
        }
    }
}
