import java.time.LocalDate;

public class Project {

    static int current_proj = 1;
    final LocalDate DATE_NULL = LocalDate.of(0000,01,01);


    // Attributes
    int proj_num;
    String proj_name;
    String build_type;
    Address address;
    String erf_num;
    double total_fee;
    double amt_paid;

    LocalDate deadline;
    LocalDate date_completed;
    LocalDate date_last_paid;

    Person architect;
    Person contractor;
    Person customer;

    boolean isFinalized;


    // Constructor
    public Project (String proj_name, String build_type, Address address, String erf_num, double total_fee, double amt_paid, LocalDate deadline, Person architect, Person contractor, Person customer){

        // Auto generated initializers
        this.proj_num = current_proj;
        this.date_completed = DATE_NULL;
        this.isFinalized = false;

        // Input
        this.proj_name  = proj_name;
        this.build_type = build_type;
        this.address    = address;
        this.erf_num    = erf_num;

        this.total_fee  = total_fee;
        this.amt_paid   = amt_paid;

        // Date last paid
        if(amt_paid != 0) this.date_last_paid = LocalDate.now();
        else this.date_last_paid = DATE_NULL;

        this.deadline   = deadline;

        this.architect  = architect;
        this.contractor = contractor;
        this.customer   = customer;


        // Increase project number for next call of constructor
        current_proj ++;
    }



    // Methods

    /**
     * Returns summary of all project data
     * @return String
     */
    public String toString() {

        String output  =  "Project name:    " + proj_name          + "\n" +
                "Project number:  " + proj_num           + "\n\n" +
                "Build Type:      " + build_type         + "\n" +
                "Address :        " + address.toString() + "\n" +
                "ERF number:      " + erf_num            + "\n\n" +

                "Customer:\n"   + customer.toString()   + "\n\n" +
                "Contractor:\n" + contractor.toString() + "\n\n" +
                "Architect:\n"  + architect.toString()  + "\n\n" +
                "Total Fee:       $" + String.format( "%.2f",total_fee)          + "\n" +
                "Remaining Fee:   $" + String.format( "%.2f",amtLeftToPay())     + "\n" + "\n";


        if(amt_paid > 0){
            output += "Date last paid:  "  + date_last_paid + "\n"; // if payment has been made
        }

            output += "Amount Paid:     $" + String.format( "%.2f",amt_paid)           + "\n\n" +

                "Deadline:        " + deadline           + "\n" +
                "Completed?:      ";

        if (isFinalized){
            output +=  "Yes" + "\n" +
                       "Date Completed:  " + date_completed;
        }

        else output += "No";

        return output;
    }

    /**
     * Returns project name
     * @return
     */
    public String getProjName() {return proj_name;}

    /**
     * Returns date last paid
     * @return
     */
    public LocalDate getDateLastPaid() {return date_last_paid;}


    /**
     * Returns true if project is overdue
     * @return isOverdue
     */
    public boolean isOverdue(){

        LocalDate todayDate = LocalDate.now();
        boolean isOverdue = false;

        if (todayDate.isAfter(deadline))  isOverdue = true;

        return isOverdue;
    }

    /**
     * Returns true if project is fully paid
     */
    public boolean isPaid(){
        boolean isPaid = false;
        if(amt_paid == total_fee) isPaid = true;
        return isPaid;
    }

    public boolean isFinalized(){ return isFinalized; }


    /**
     * Returns amount remaining to pay (invoice amount)
     * @return
     */
    public double amtLeftToPay(){
        return total_fee - amt_paid;
    }


    /**
     * Updates amount paid (additive)
     * @param amountToAdd
     */
    public void updateAmountPaid(double amountToAdd){
        amt_paid += amountToAdd;
        date_last_paid = LocalDate.now();
    }

    /**
     * Resets next project number to 1
     */
    public void resetCurrentProjNum() { current_proj = 1; }

    /**
     * Changes project due date
     * @param newDate
     */
    public void changeDueDate(LocalDate newDate){ deadline = newDate; }

    public void finalizeProj(){ isFinalized = true; }











}
