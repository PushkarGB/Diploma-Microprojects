//Email class file
import java.util.*;

public class Email {
  private String firstName;
  private String lastName;
  private String deptartment;
  private String password;
  private String email;
  private int passwordLength = 8;
  private String altEmail;
  private int mailBoxCapacity = 500;
  private String comSuffix = "PGBCrtnCompany.com";

  public Email() {
    // defualt construtor
  }

  // constructor to save a email
  public Email(String fname,String lname,String email, String pass, String dept, String altemail) {
    this.firstName = fname;
    this.lastName = lname;
    this.email = email;
    this.password = pass;
    this.altEmail = altemail;
    this.deptartment = dept;
  }

  // constructor to receive first name and last name
  public Email(String fname, String lname) {
    this.firstName = fname;
    this.lastName = lname;

    // method to call the department -- return the department
    this.deptartment = takeDepartment();

    // Creating a unique key for the emails(max 4 digits)
    String timestamp = Long.toString(System.currentTimeMillis());
    String uniqueKey = timestamp.substring(timestamp.length() - 4); // taking last 4 digits of timestamp
    // Combine elements to form email

    email = firstName.toLowerCase() + lastName.toLowerCase() + uniqueKey + "@" + deptartment + "." + comSuffix;
    System.out.println("Your Email is : " + this.email);
    // method to generate the password --return password
    this.password = randomPassword(this.passwordLength);
    System.out.println("Your Password is : " + this.password);

  }

  // ASk for the department
  private String takeDepartment() {
    System.out.println("DEPARTMENT CODES : \n1 For Accounting \n2 For Development \n3 For Sales \n4 For None");
    System.out.println("Enter the department : ");
    Scanner in = new Scanner(System.in);
    int deptChoice = in.nextInt();
    in.nextLine();
    // in.close();
    if (deptChoice == 1)
      return "Accounting";
    else if (deptChoice == 2)
      return "Development";
    else if (deptChoice == 3)
      return "Sales";
    else
      return " ";
  }

  // Generate a random password
  private String randomPassword(int passLength) {
    String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijlklmnopqrstuvwxyz@#$_*1234567890";
    char[] password = new char[passLength];
    for (int i = 1; i < passLength; i++) {
      int rnd = (int) (Math.random() * passwordSet.length());
      password[i] = passwordSet.charAt(rnd);
    }
    return new String(password);
  }

  // Set the MailBoX Capacity
  public void setMailBoxCapacity(int capacity) {
    this.mailBoxCapacity = capacity;
  }

  // CHange the password
  public void changePassword(String newPassword) {
    this.password = newPassword;
  }

  // Set Alternate Email
  public void setAlternateEmail(String altEmail) {
    this.altEmail = altEmail;
  }

  // Show info
  public String showInfo() {
    return "Display Name : " + this.firstName + " " + this.lastName + "\nDepartment Code : " + this.deptartment + "\n"
        + "Company Email : " + this.email + "\nMailBox Capacity : " + mailBoxCapacity + "mb\n";
  }

  // Get Methods
  public String getName(){
    return this.firstName + ","+ this.lastName;
  }

  public int getMailBoxCapacity() {
    return mailBoxCapacity;
  }

  public String getAlternatEmail() {
    return altEmail;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getDepartment() {
    return deptartment;
  }

  public void setDepartment(String newDepartment) {
    this.deptartment = newDepartment;
  }

}