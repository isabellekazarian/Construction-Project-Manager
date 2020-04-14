import java.time.LocalDate;
import java.util.Scanner;



public class PoisedProjectManager {

    /**
     * Method gets address input from user
     * @return address
     */
    public static Address getAddressFromUser(Scanner inputScan, String request){
        System.out.println(request);
        System.out.print("House or street number only: ");
        int street_num = inputScan.nextInt(); inputScan.nextLine(); // consume newline char

        System.out.print("Street name: ");
        String street_name = inputScan.nextLine();

        System.out.print("City: ");
        String city = inputScan.nextLine();

        System.out.print("State: ");
        String state = inputScan.nextLine();

        System.out.print("Zip Code: ");
        int zip = inputScan.nextInt(); inputScan.nextLine(); // consume newline char

        Address address = new Address (street_num, street_name, city, state, zip);

        return address;
    }

    /**
     * Method gets person info input from user
     * @param inputScan, job_title
     * @return newPerson
     */
    public static Person getPersonInfo(Scanner inputScan, String job_title){

        System.out.println("Please enter " + job_title + " information:");

        System.out.print("Last Name: ");
        String last_name = inputScan.nextLine();

        System.out.print("First Name: ");
        String first_name = inputScan.nextLine();

        System.out.print("Phone number (10 digit, numbers only): ");
        long phone_num = inputScan.nextLong(); inputScan.nextLine(); // consume newline char

        System.out.print("Email address: ");
        String email = inputScan.nextLine();

        Address person_address = getAddressFromUser(inputScan,"\nEnter business address information for " + job_title + ":");

        return new Person (first_name, last_name, job_title, phone_num, email, person_address);
    }

    /**
     * Method gets date from user
     * @param date_request_label : ex. Deadline, Due Date, Enter Completion Date
     */
    public static LocalDate getDateFromUser(Scanner inputScan, String date_request_label){
        System.out.print(date_request_label + " (YYYYMMDD): ");
        String input_string = inputScan.nextLine();

        // Split input into year/month/day
        int year  = Integer.parseInt(input_string.substring(0,4));
        int month = Integer.parseInt(input_string.substring(4,6));
        int day   = Integer.parseInt(input_string.substring(6,8));

        return LocalDate.of(year,month,day);
    }

    /**
     * Method creates new project by getting user input
     * @return project
     */
    public static Project createNewProject(Scanner inputScan) {

        // ------- Get Project Details -----------------
        System.out.println("Welcome to the project wizard!");
        System.out.print("To begin, please enter project name: ");
        String proj_name = inputScan.nextLine();

        System.out.println("\nTo create project, please enter the following:");
        System.out.print("Project type (e.g., House, Store): ");
        String build_type = inputScan.nextLine();

        System.out.print("ERF Number: ");
        String erf_num = inputScan.nextLine();

        System.out.print("Total Cost: ");
        double total_fee = inputScan.nextDouble(); inputScan.nextLine(); // consume newline char

        System.out.print("Amount paid to date: ");
        double amt_paid = inputScan.nextDouble(); inputScan.nextLine(); // consume newline char

        // Get date & address
        LocalDate deadline   = getDateFromUser(inputScan,"Deadline");
        System.out.println("");
        Address proj_address = getAddressFromUser(inputScan, "Please enter the following location information:");
        System.out.println("");

        // Get contact details
        Person customer   = getPersonInfo(inputScan,"customer");
        System.out.println("");
        Person contractor = getPersonInfo(inputScan,"contractor");
        System.out.println("");
        Person architect  = getPersonInfo(inputScan,"architect");

        // If no project name provided, use build type and customer surname
        if (proj_name.isEmpty()){
            proj_name = build_type + " " + customer.last_name;
        }

        return new Project (proj_name, build_type, proj_address, erf_num, total_fee, amt_paid, deadline, architect, contractor, customer);

    }

    /**
     * Method prints menu giving the user the option to edit a person's info
     * @param inputScan
     * @param person - architect, contractor, or customer
     */
    public static void updatePersonInfo (Scanner inputScan, Person person){

        String updateInput = "";

        do {
            // Print menu
            System.out.println("\nCurrent " + person.getTitle() + " Details: " + "\n");
            System.out.println("1    - " + person.getFullName());
            System.out.println("2    - " + person.getPhoneNum());
            System.out.println("3    - " + person.getEmail());
            System.out.println("4    - " + person.getAddress());
            System.out.println("0    - Exit");

            // Get user input
            System.out.print("\nEnter a line number to edit, or 0 to exit:  ");
            updateInput = inputScan.nextLine().toLowerCase();
            System.out.println("");

            // Update name
            if(updateInput.equals("1")){
                System.out.print("Enter first name:  ");
                String newFirst = inputScan.nextLine();

                System.out.print("Enter last name:   ");
                String newLast = inputScan.nextLine();

                person.updateName(newFirst,newLast);
                System.out.println("Name updated successfully.");
            }

            // Update Phone
            else if (updateInput.equals("2")){
                System.out.print("Enter new phone number:  ");
                long newPhone = inputScan.nextLong(); inputScan.nextLine();

                person.updatePhone(newPhone);
                System.out.println("Phone number updated successfully.");
            }

            // Update email
            else if (updateInput.equals("3")){
                System.out.print("Enter new email address:  ");
                String newEmail = inputScan.nextLine();

                person.updateEmail(newEmail);
                System.out.println("Email address updated successfully.");
            }

            // Update address
            else if (updateInput.equals("4")){
                Address newAddress = getAddressFromUser(inputScan, "Please provide updated address information:");
                person.updateAddress(newAddress);
                System.out.println("Address updated successfully.");
            }

            // Bad input
            else if(!updateInput.equals("0")) {
                System.out.println("Error:  Please enter a valid menu option.\n");
            }


        } while(!updateInput.equals("0"));
    }

// -------------------------------------------------------------------------------------


    public static void main (String[] args){

        String menuInput = "";
        Scanner inputScan = new Scanner (System.in); // Create scanner object

        Project userProject = createNewProject(inputScan);


        System.out.print(userProject.toString() + "\n\n");  //delete

        // ------------------------ Main menu loop -----------------------------
        do {
            System.out.println("\n-------- Project Menu --------");
            System.out.println("Project: " + userProject.getProjName() + "\n");
            System.out.println("1    - Change Deadline");
            System.out.println("2    - Update Amount Paid");
            System.out.println("3    - Update Contractor Information");
            System.out.println("4    - Finalize Project");
            System.out.println("0    - Exit");

            System.out.print("\nPlease choose:  ");
            menuInput = inputScan.nextLine().toLowerCase();
            System.out.println("");

            // Change deadline
            if(menuInput.equals("1")){
                System.out.println("Current deadline: " + userProject.deadline);

                // Get user input & update
                LocalDate newDeadline = getDateFromUser(inputScan, "Enter new deadline");
                userProject.changeDueDate(newDeadline);

                System.out.println("\nNew deadline saved successfully.");
            }

            // Update amount paid
            else if(menuInput.equals("2")){

                // Print current balance
                System.out.println("Total project fee:  $" + String.format("%.2f", userProject.total_fee));
                System.out.println("Total amount paid:  $" + String.format("%.2f", userProject.amt_paid));
                System.out.println("Remaining balance:  $" + String.format("%.2f", userProject.amtLeftToPay()));

                // Get user input & update
                System.out.print("\nEnter amount to pay:  ");
                double amtToPay = inputScan.nextDouble(); inputScan.nextLine();
                userProject.updateAmountPaid(amtToPay);

                // Print updated balance
                System.out.println("\nTotal amount paid:  $" + String.format("%.2f", userProject.amt_paid));
                System.out.println("Remaining balance:  $" + String.format("%.2f", userProject.amtLeftToPay()));

            }

            // Update contractor info
            else if(menuInput.equals("3")) updatePersonInfo(inputScan, userProject.contractor);


            // Finalize Project
            else if(menuInput.equals("4")){

                // Finalize
                if(!userProject.isFinalized()){
                    userProject.finalizeProj();
                    userProject.date_completed = LocalDate.now(); // Update date completed
                    System.out.println("Project finalized successfully.");
                }

                // If project already finalized
                else{
                    System.out.println("Error:  Project has already been finalized.");
                    System.out.println("Date completed:  " + userProject.date_completed);
                }
            }


            else if(!menuInput.equals("0")) System.out.println("Error:  Please enter a valid menu option.\n");

        } while(!menuInput.equals("0"));


        System.out.print("\n\n" + userProject.toString() + "\n"); //delete

        System.out.println("\nGoodbye!");

    }

}
