package test1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class BankGUI extends JFrame {

    // Arrays للبيانات نفس الفكرة
    private String[] names = new String[1000];
    private int[] accountNumbers = new int[1000];
    private double[] balances = new double[1000];
    private int count = 0;

    public BankGUI() {
        // تحميل البيانات من الملف عند التشغيل
        loadDataFromFile();

        // إعدادات النافذة الرئيسية
        setTitle("Banking System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1, 10, 10));

        // الأزرار
        JButton btnCreate = new JButton("1. Create New Account");
        JButton btnDeposit = new JButton("2. Deposit Money");
        JButton btnWithdraw = new JButton("3. Withdraw Money");
        JButton btnCheck = new JButton("4. Check Account Details");
        JButton btnDisplayAll = new JButton("5. Display All Accounts");
        JButton btnExit = new JButton("6. Save & Exit");

        // إضافة الأزرار للنافذة
        add(new JLabel("==== BANKING SYSTEM MENU ====", SwingConstants.CENTER));
        add(btnCreate);
        add(btnDeposit);
        add(btnWithdraw);
        add(btnCheck);
        add(btnDisplayAll);
        add(btnExit);

        // أكشن زر إنشاء حساب
        btnCreate.addActionListener(e -> createAccount());

        // أكشن زر عرض الكل
        btnDisplayAll.addActionListener(e -> displayAll());

        // أكشن زر الخروج والحفظ
        btnExit.addActionListener(e -> {
            saveDataToFile();
            System.exit(0);
        });
    }

    private void createAccount() {
        String name = JOptionPane.showInputDialog(this, "Enter Your Name:");
        if (name != null && !name.trim().isEmpty()) {
            String balanceStr = JOptionPane.showInputDialog(this, "Enter Initial Balance:");
            try {
                double balance = Double.parseDouble(balanceStr);
                int accNum = 1000 + count;

                names[count] = name;
                balances[count] = balance;
                accountNumbers[count] = accNum;
                count++;

                JOptionPane.showMessageDialog(this, "Account Created Successfully!\nYour Account Number is: " + accNum);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid balance amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayAll() {
        if (count == 0) {
            JOptionPane.showMessageDialog(this, "No accounts available.");
            return;
        }
        StringBuilder allData = new StringBuilder("All Accounts:\n\n");
        for (int i = 0; i < count; i++) {
            allData.append("Acc #: ").append(accountNumbers[i])
                   .append(" | Name: ").append(names[i])
                   .append(" | Balance: ").append(balances[i]).append("\n");
        }
        JOptionPane.showMessageDialog(this, allData.toString());
    }

    private void loadDataFromFile() {
        File file = new File("bank_data.txt");
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String[] data = fileScanner.nextLine().split(",");
                    accountNumbers[count] = Integer.parseInt(data[0]);
                    names[count] = data[1];
                    balances[count] = Double.parseDouble(data[2]);
                    count++;
                }
            } catch (Exception e) {
                System.out.println("Error loading file!");
            }
        }
    }

    private void saveDataToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("bank_data.txt"))) {
            for (int i = 0; i < count; i++) {
                pw.println(accountNumbers[i] + "," + names[i] + "," + balances[i]);
            }
            JOptionPane.showMessageDialog(this, "Data Saved Successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BankGUI().setVisible(true);
        });
    }
}