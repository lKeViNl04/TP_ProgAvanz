package ar.edu.davinci.UI;
import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JTextField nameField;
    private JTextField surnameField;
    private JTextField ageField;

    public Login() {

        setTitle("Swing Form");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel surnameLabel = new JLabel("Surname:");
        surnameField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(surnameLabel);
        panel.add(surnameField);
        panel.add(ageLabel);
        panel.add(ageField);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               ;
            }
        });
        panel.add(sendButton);

        add(panel);

        JButton sendButton2 = new JButton("Send");
        sendButton2.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(sendButton2);

        JButton listAllButton = new JButton("Listar Todos");
        listAllButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
           }
        });
        panel.add(listAllButton);

        add(panel);
    }

    public static void main(String[] args) {
        Login form = new Login();
        form.setVisible(true);
    }

    }





