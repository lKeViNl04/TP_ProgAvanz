package ar.edu.davinci.Frontend.UI;

import ar.edu.davinci.Backend.DAO.TrainerDAO;
import ar.edu.davinci.Backend.DAO.TrainerImplMysql;
import ar.edu.davinci.Backend.DAO.UserDAO;
import ar.edu.davinci.Backend.DAO.UserImplMysql;
import ar.edu.davinci.Backend.Excepion.InvalidCredentialsException;
import ar.edu.davinci.Backend.User.User;
import ar.edu.davinci.Frontend.Manager.SessionManager;
import ar.edu.davinci.Frontend.Manager.FormsManager;
import ar.edu.davinci.Frontend.Toast.Notifications;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import java.awt.Cursor;
import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {

    private Notifications toast;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public Login() {
        this.toast = new Notifications();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Iniciar Sesion");

        btnLogin.addActionListener(e -> {


            if (isEmptyFields()) {
                toast.show(Notifications.Type.ERROR, "Por favor, completa todos los campos.");
            } else {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                UserDAO userDAO = new UserImplMysql();
                TrainerDAO trainerDAO = new TrainerImplMysql();

                try {
                    User user = userDAO.validateUser(username, password);
                    user.addEntrenadores(trainerDAO.getTrainersByUserId(user.getUserId()));
                    SessionManager.getInstance().setUser(user);

                    FormsManager.getInstance().showForm(new TrainerSelection());
                    toast.show(Notifications.Type.INFO, "!Bienvenido a Pokevinci! " + username);

                } catch (InvalidCredentialsException ex) {
                    toast.show(Notifications.Type.ERROR, ex.getMessage());
                }

            }
        });


        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;" +
                        "[light]background:darken(@background,3%);" +
                        "[dark]background:lighten(@background,3%)");

        txtPassword.putClientProperty(FlatClientProperties.STYLE,
                "showRevealButton:true");
        btnLogin.putClientProperty(FlatClientProperties.STYLE,
                "[light]background:darken(@background,10%);" +
                        "[dark]background:lighten(@background,10%);" +
                        "borderWidth:0;" +
                        "focusWidth:0;" +
                        "innerFocusWidth:0");

        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su Usuario");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su Contraseña");

        JLabel lbTitle = new JLabel("¡Bienvenido de nuevo!");
        JLabel description = new JLabel("Inicie sesión para acceder a su cuenta");
        lbTitle.putClientProperty(FlatClientProperties.STYLE,
                "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE,
                "[light]foreground:lighten(@foreground,30%);" +
                        "[dark]foreground:darken(@foreground,30%)");


        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Nick"), "gapy 8");
        panel.add(txtUsername);
        panel.add(new JLabel("Contraseña"), "gapy 8");
        panel.add(txtPassword);
        panel.add(btnLogin, "gapy 10");
        panel.add(createSignupLabel(), "gapy 10");
        add(panel);
    }

    private Component createSignupLabel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "background:null");
        JButton cmdRegister = new JButton("Registrarse");
        cmdRegister.putClientProperty(FlatClientProperties.STYLE,
                "border:3,3,3,3");
        cmdRegister.setContentAreaFilled(false);
        cmdRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdRegister.addActionListener(e -> {
            FormsManager.getInstance().showForm(new Register());
        });
        JLabel label = new JLabel("No tienes una cuenta?");
        label.putClientProperty(FlatClientProperties.STYLE,
                "[light]foreground:lighten(@foreground,30%);" +
                        "[dark]foreground:darken(@foreground,30%)");
        panel.add(label);
        panel.add(cmdRegister);
        return panel;
    }

    private boolean isEmptyFields() {
        return txtUsername.getText().isEmpty() ||
                txtPassword.getText().isEmpty();
    }


}
