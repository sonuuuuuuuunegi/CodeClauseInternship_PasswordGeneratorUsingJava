package codeclause;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PasswordGeneratorApp extends JFrame {
    private JTextField passwordField;
    private JCheckBox includeUppercase, includeLowercase, includeDigits, includeSpecialChars;
    private JButton generateButton;

    public PasswordGeneratorApp() {
        setTitle("Password Generator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));

        includeUppercase = new JCheckBox("Include Uppercase");
        includeLowercase = new JCheckBox("Include Lowercase");
        includeDigits = new JCheckBox("Include Digits");
        includeSpecialChars = new JCheckBox("Include Special Characters");

        generateButton = new JButton("Generate Password");
        passwordField = new JTextField();
        passwordField.setEditable(false);

        mainPanel.add(includeUppercase);
        mainPanel.add(includeLowercase);
        mainPanel.add(includeDigits);
        mainPanel.add(includeSpecialChars);
        mainPanel.add(generateButton);

        add(mainPanel, BorderLayout.CENTER);
        add(passwordField, BorderLayout.SOUTH);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
    }

    private void generatePassword() {
        String characters = "";
        if (includeUppercase.isSelected()) characters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (includeLowercase.isSelected()) characters += "abcdefghijklmnopqrstuvwxyz";
        if (includeDigits.isSelected()) characters += "0123456789";
        if (includeSpecialChars.isSelected()) characters += "!@#$%^&*()_-+=<>?";

        if (characters.isEmpty()) {
            passwordField.setText("Select at least one option.");
            return;
        }

        int length = 12; // You can change the length as needed
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            password.append(randomChar);
        }

        passwordField.setText(password.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PasswordGeneratorApp app = new PasswordGeneratorApp();
                app.setVisible(true);
            }
        });
    }
}

