package test1;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class main {

    public static void userpass(Scanner in) {

        System.out.println("\t\t\t\t\t\t============= Welcome to Banking System =============" + "\n");
        System.out.print("Enter your name please: ");
        String name = in.nextLine();
        System.out.println("Hello " + name + ", How are you?");
        System.out.print("Your Answer : ");
        in.next();
        boolean sucsess = true;
        while (sucsess) {
            in.nextLine();
            System.out.print("Enter Your Username: ");
            String username = in.nextLine();

            try {
                System.out.print("Enter Your Password: ");
                int Password = in.nextInt();
                in.nextLine();

                if (username.equals("admin") && Password == 123) {
                    sucsess = false;

                } else {
                    System.out.println("Your username or password is not correct please try again");
                    System.out.println("Press Enter ");
                }
            } catch (Exception e) {
                System.out.println("Error Dont use letters just numbers");

            }

        }
        System.out.println("\n");
        System.out.println("========================================");

        System.out.println("                Welcome");

    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        userpass(in);
        int x = 0;
        String[] names = new String[1000];
        int[] accountNumbers = new int[1000];
        double[] balances = new double[1000];
        int count = 0;

        File file = new File("bank_data.txt");

        if (file.exists()) {
            try {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] data = line.split(",");

                    accountNumbers[count] = Integer.parseInt(data[0]);
                    names[count] = data[1];
                    balances[count] = Double.parseDouble(data[2]);

                    count++;
                }
                fileScanner.close();
                // System.out.println("Loaded " + count + " accounts from bank_data.txt successfully!");
            } catch (Exception e) {
                System.out.println("Error loading saved data!");
            }
        }

        do {
            try {
                System.out.println("========================================");
                System.out.println("          BANKING SYSTEM MENU");
                System.out.println("========================================");
                System.out.println("1. Create New Account");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Check Account Details");
                System.out.println("5. Display All Accounts");
                System.out.println("6. Exit");
                System.out.println("Enter your choice (1-6):");
                x = in.nextInt();
            } catch (Exception e) {
            }
            switch (x) {
                case 1:
                    System.out.println("========================================");
                    System.out.println("          CREATING NEW ACCOUNT");
                    System.out.println("========================================");
                    boolean name = true;
                    while (name) {
                        int n = 1000 + count;
                        System.out.println("\n");
                        System.out.print("Enter your name please : ");
                        in.nextLine();
                        names[count] = in.nextLine();
                        try {
                            System.out.print("Enter your balance : ");
                            balances[count] = in.nextDouble();
                            name = false;
                            System.out.print("\n");
                            System.out.print("You are ready and your number is " + (n));
                            accountNumbers[count] = n;
                            count++;
                        } catch (Exception e) {
                            System.out.println("Error Dont use letters just numbers");
                            in.nextLine();
                        }

                    }
                    System.out.println("\n");
                    System.out.println("Press Enter ");
                    in.nextLine();
                    in.nextLine();

                    break;
                case 2:
                    System.out.println("========================================");
                    System.out.println("          DEPOSIT MONEY ");
                    System.out.println("========================================");
                    try {
                        System.out.print("Enter your Account Number : ");
                        int number = in.nextInt();
                        boolean found = false;
                        int foundnumber = -1;

                        for (int i = 0; i < accountNumbers.length; i++) {
                            if (number == accountNumbers[i]) {
                                System.out.println();
                                System.out.println("Number : " + accountNumbers[i] + "\t" + " Name : " + names[i] + "\t"
                                        + "Balance : " + balances[i]);

                                found = true;
                                System.out.println();
                                System.out.println("Account Found");
                                foundnumber = i;

                                break;

                            }
                        }
                        if (found) {
                            System.out.println();
                            try {
                                System.out.print("Enter Deposit Amount : ");
                                double amount = in.nextDouble();
                                if (amount > 0) {
                                    balances[foundnumber] += amount;
                                    System.out.println();
                                    System.out.println("Deposit successful! New Balance: " + balances[foundnumber]);
                                } else {
                                    System.out.println("Invalid Amount! Amount must be greater than 0.");
                                }
                            } catch (Exception e) {
                                System.out.println("Error: Please enter a valid number for amount!");
                            }
                            System.out.println();
                            System.out.println("Press Enter ");
                            in.nextLine();
                            in.nextLine();
                        } else {
                            System.out.println();
                            System.out.println("Account Not Found!");
                            System.out.println();
                            System.out.println("Press Enter ");
                            in.nextLine();
                            in.nextLine();
                        }
                    } catch (Exception e) {
                        System.out.println();
                        System.out.println("Error you should enter number !");
                        System.out.println();
                        in.nextLine();
                        System.out.println("Press Enter ");
                        in.nextLine();
                    }

                    break;
                case 3:

                    System.out.println("========================================");
                    System.out.println("          WITHDRAW MONEY ");
                    System.out.println("========================================");
                    try {
                        System.out.print("Enter your Account Number : ");
                        int number = in.nextInt();
                        boolean found = false;
                        int foundnumber = -1;

                        for (int i = 0; i < accountNumbers.length; i++) {
                            if (number == accountNumbers[i]) {
                                System.out.println();
                                System.out.println("Number : " + accountNumbers[i] + "\t" + " Name : " + names[i] + "\t"
                                        + "Balance : " + balances[i]);

                                found = true;
                                System.out.println();
                                System.out.println("Account Found");
                                foundnumber = i;

                                break;

                            }
                        }
                        if (found) {
                            System.out.println();
                            try {
                                System.out.print("Enter Withdraw Amount : ");
                                double amount = in.nextDouble();
                                if (amount > 0) {
                                    if (balances[foundnumber] - amount < 0) {
                                        System.out.println("Insufficient Balance! Your current balance is: "
                                                + balances[foundnumber]);
                                    } else {
                                        balances[foundnumber] -= amount;
                                        System.out
                                                .println("Withdraw successful! New Balance: " + balances[foundnumber]);
                                    }

                                } else {
                                    System.out.println("Invalid Amount! Amount must be greater than 0.");
                                }
                            } catch (Exception e) {
                                System.out.println("Error: Please enter a valid number for amount!");
                            }
                            System.out.println();
                            System.out.println("Press Enter ");
                            in.nextLine();
                            in.nextLine();
                        } else {
                            System.out.println();
                            System.out.println("Account Not Found!");
                            System.out.println();
                            System.out.println("Press Enter ");
                            in.nextLine();
                            in.nextLine();
                        }
                    } catch (Exception e) {
                        System.out.println();
                        System.out.println("Error you should enter number !");
                        System.out.println();
                        in.nextLine();
                        System.out.println("Press Enter ");
                        in.nextLine();
                    }

                    break;
                case 4:
                    System.out.println("========================================");
                    System.out.println("          CHECK ACCOUNT DETAILS");
                    System.out.println("========================================");
                    try {
                        System.out.print("Enter your Account Number : ");
                        int number = in.nextInt();
                        boolean found = false;

                        for (int i = 0; i < accountNumbers.length; i++) {
                            if (number == accountNumbers[i]) {
                                System.out.println();
                                System.out.println("Number : " + accountNumbers[i] + "\t" + " Name : " + names[i] + "\t"
                                        + "Balance : " + balances[i]);

                                found = true;

                                break;

                            }
                        }
                        if (found) {
                            System.out.println();
                            System.out.println("Press Enter ");
                            in.nextLine();
                            in.nextLine();
                        } else {
                            System.out.println();
                            System.out.println("Account Not Found!");
                            System.out.println();
                            System.out.println("Press Enter ");
                            in.nextLine();
                            in.nextLine();
                        }
                    } catch (Exception e) {
                        System.out.println();
                        System.out.println("Error you should enter number !");
                        System.out.println();
                        in.nextLine();
                        System.out.println("Press Enter ");
                        in.nextLine();
                    }

                    break;

                case 5:
                    System.out.println("========================================");
                    System.out.println("          DISPLAY All ACCOUNTS");
                    System.out.println("========================================");

                    if (count == 0) {
                        System.out.println("It is Empty");
                    } else {
                        System.out.println();
                        for (int i = 0; i < count; i++) {
                            System.out.println("Number : " + accountNumbers[i] + "\t" + " Name : " + names[i] + "\t"
                                    + "Balance : " + balances[i]);
                        }
                    }
                    System.out.println();
                    System.out.println("Press Enter ");
                    in.nextLine();
                    in.nextLine();

                    break;
                case 6:
                    System.out.println("========================================");
                    System.out.println("          SAVING DATA & EXITING          ");
                    System.out.println("========================================");

                    
                    try {
                        FileWriter fw = new FileWriter("bank_data.txt");
                        PrintWriter pw = new PrintWriter(fw);

                        for (int i = 0; i < count; i++) {
                            pw.println(accountNumbers[i] + "," + names[i] + "," + balances[i]);
                        }

                        pw.close();
                        System.out.println("Data saved successfully to bank_data.txt!");
                    } catch (Exception e) {
                        System.out.println("Error saving data to file: " + e.getMessage());
                    }

                    System.out.println("Thank you for using our Bank Application!");
                    
                    break;

                default:
                    System.out.println("Error you should enter between 1 to 6");
                    System.out.println("Press Enter");
                    in.nextLine();
                    in.nextLine();
                    break;
            }

        } while (x != 6);
        System.out.println("========================================");
        System.out.println("                BYE BYE ");
        System.out.println("========================================");

    }
}
