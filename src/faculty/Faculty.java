package faculty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Faculty {

    protected static class Specialization {
        String name;
        int budgetaryCapacity;
        int taxCapacity;
        int taxPrice;

        int num_of_budgetary = 0;   // number of students that are part of the budgetary plan
        int num_of_tax = 0;         // number of students that are part of the tax plan

        public Specialization() {
            setSpecialization();
        }

        public void setSpecialization()
        {
            Scanner myInput = new Scanner(System.in);

            System.out.println("\n--- Set specialization ---\n");
            System.out.println("Enter name:");
            this.name = myInput.nextLine();

            System.out.println("Enter number of designated slots for budgetary programme: ");
            this.budgetaryCapacity = myInput.nextInt();

            System.out.println("Enter number of designated slots for tax programme: ");
            this.budgetaryCapacity = myInput.nextInt();

            System.out.println("Enter tax price: ");
            this.taxPrice = myInput.nextInt();
        }

        public void supplementBudgetaryCapacity(int amount) {
            this.budgetaryCapacity += amount;
            this.taxCapacity -= amount;
        }

        public void supplementTaxCapacity(int amount) {
            this.budgetaryCapacity -= amount;
            this.taxCapacity += amount;
        }

        public void modifyCapacity(int budgetaryCapacityAmount, int taxCapacityAmount) {
            this.budgetaryCapacity += budgetaryCapacityAmount;
            this.taxCapacity += taxCapacityAmount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBudgetaryCapacity() {
            return budgetaryCapacity;
        }

        public void setBudgetaryCapacity(int budgetaryCapacity) {
            this.budgetaryCapacity = budgetaryCapacity;
        }

        public int getTaxCapacity() {
            return taxCapacity;
        }

        public void setTaxCapacity(int taxCapacity) {
            this.taxCapacity = taxCapacity;
        }

        public int getTaxPrice() {
            return taxPrice;
        }

        public void setTaxPrice(int taxPrice) {
            this.taxPrice = taxPrice;
        }
    }

    String name;
    int numOfSpecializations;
    List<Specialization> specializations = new ArrayList<Specialization>();

    private static Faculty faculty;

    private Faculty(String name, int numOfSpecializations) {
        this.name = name;
        this.numOfSpecializations = numOfSpecializations;
        for(int i = 0; i < numOfSpecializations; i++)
        {
            specializations.add(new Specialization());
        }
    }

    public static Faculty getFaculty(String name, int numOfSpecializations) {
        if (faculty == null)
            faculty = new Faculty(name, numOfSpecializations);
        return faculty;
    }
}
