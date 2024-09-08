//Main Email APP file
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class EmailApp {
    static Vector<Email> emailList = new Vector<>(); // Collection to store emails
    static Scanner in = new Scanner(System.in);
    static final String FILENAME = "user_data.txt"; // File to store user data
     
    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);
        loadUserData();
        System.out.println("MENU : \n1.Login \n2.SignUp");
        int ch = in.nextInt();
        Email user = null; // To store the user's email object
        switch (ch) {
            case 1:
                System.out.println("Enter your Email: ");
                String userEmail = in.next();
                // Login function
                user = login(userEmail);
                if (user != null) {
                    System.out.println("Login successful!");
                    saveUserData();
                    MENU(user); // to provide access to getting and setting functions
                } else {
                    System.out.println("Login failed. Email not found.");
                }
                break;
            case 2:
                System.out.println("Enter Your name : ");
                System.out.print("First Name : ");
                String fname = in.next();
                System.out.print("Last Name : ");
                String lname = in.next();
                // Create a new email object for this name
                user = new Email(fname, lname);
                emailList.add(user); // Add user to email list
                System.out.println("Your account has been created successfully!");
                saveUserData();
                MENU(user); // Call MENU with user object
                break;
            default:
                System.out.println("Wrong Input(Choose either 1 or 2)!");
                break;
        }
        // in.close();
    }

    // Method to simulate login functionality
    private static Email login(String email) {
        // check if the email exists in our emailList and return the corresponding Email
        // object
        for (Email e : emailList) {
            if (e.getEmail().equals(email)) {
                System.out.println(e.getEmail());
                if (checkPassword(e))
                    return e;
                else {
                    System.out.println("Maxmimum Attempts fininshed..");
                    return null;
                }
            }
        }
        // else return NULL
        return null;
    }

    private static boolean checkPassword(Email e) {
        int MaxLoginAttempts = 3;
        Boolean grantAccess = false;
        // Scanner in = new Scanner(System.in);
        for (int i = 0; i < MaxLoginAttempts; i++) {
            System.out.println("Enter Password : ");
            String enteredPassword = in.next();
            if (enteredPassword.equals(e.getPassword())) {
                grantAccess = true;
                break;
            }
            System.out.println("Incorrect Password! , Try Again");
        }
        // in.close();
        return grantAccess;
    }

    protected static void MENU(Email user) {
        // Scanner sc = new Scanner(System.in);
        boolean logout = false;
        while (!logout) {
            System.out.println(
                    "MENU : \n1.Change Password \n2.Change Department \n3.Set Alternate Email \n4.See Alternate Email \n5.Show Email Info\n6.Logout");
            int choice = 0;
            try {
                choice = in.nextInt();
            } catch (Exception e) {
                System.out.println("Error in reading input");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("Enter new password: ");
                    String newPassword = in.next();
                    user.changePassword(newPassword);
                    System.out.println("Password changed successfully!");
                    break;
                case 2:
                    System.out.println("Enter new department: ");
                    String newDepartment = in.next();
                    user.setDepartment(newDepartment);
                    System.out.println("Department changed successfully!");
                    break;
                case 3:
                    System.out.println("Enter alternate email: ");
                    String altEmail = in.next();
                    user.setAlternateEmail(altEmail);
                    System.out.println("Alternate email set successfully!");
                    break;
                case 4:
                    String alt = user.getAlternatEmail();
                    if (alt != null && !alt.isEmpty()) {
                        System.out.println("Alternate Email: " + alt);
                    } else {
                        System.out.println("Alternate Email not set.");
                    }
                    break;
                case 5:
                    System.err.println(user.showInfo());
                    break;
                case 6:
                    logout = true;
                    System.out.println("Logged out successfully!");
                    saveUserData();
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
        // in.close();
    }


  // Method to load user data from file
  private static void loadUserData() {
    try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
        String line;
        while ((line = reader.readLine()) != null) {
            // Parse each line and create Email object
            String[] userData = line.split(",");
            if (userData.length == 6) {
                String fname = userData[0];
                String lname = userData[1];
                String email = userData[2];
                String password = userData[3];
                String department = userData[4];
                String alternateEmail = userData[5];
                emailList.add(new Email(fname,lname,email, password, department, alternateEmail));
            }
            else{
                System.out.println("Data not loaded.");
            }
        }
    } catch (IOException e) {
        // Handle file reading errors
        e.printStackTrace();
    }
}

// Method to save user data to file
private static void saveUserData() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
        for (Email email : emailList) {
            // Write each user's data to the file
            writer.write(email.getName().toUpperCase() + "," + email.getEmail() + "," + email.getPassword() + "," +
                    email.getDepartment() + "," + email.getAlternatEmail());
            writer.newLine();
        }
    } catch (IOException e) {
        // Handle file writing errors
        e.printStackTrace();
    }
}
}