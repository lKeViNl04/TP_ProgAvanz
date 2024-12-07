package ar.edu.davinci.Frontend.UI;

import ar.edu.davinci.Backend.DAO.UserDAO;
import ar.edu.davinci.Backend.DAO.UserImplMysql;
import ar.edu.davinci.Backend.User.User;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import ar.edu.davinci.Frontend.Component.PasswordStrengthStatus;
import ar.edu.davinci.Frontend.Manager.FormsManager;
import ar.edu.davinci.Frontend.Toast.Notifications;

import javax.swing.*;
import java.awt.*;


public class Register extends JPanel {
    private Notifications toast;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtEmail;
    private JTextField txtNick;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton cmdRegister;
    private PasswordStrengthStatus passwordStrengthStatus;

    public Register() {
        this.toast = new Notifications();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtNick = new JTextField();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();
        passwordStrengthStatus = new PasswordStrengthStatus();
        cmdRegister = new JButton("Registrarse");

        cmdRegister.addActionListener(e -> {

            if (isEmptyFields()) {
                toast.show(Notifications.Type.ERROR, "Por favor, completa todos los campos.");
            } else {
                // Validar si el Nick es únic
                // Validación de email
                if (isValidEmail(txtEmail.getText())) {
                    if (isMatchPassword()) {
                        if (isNickUnique(txtNick.getText())) {
                            User usercreate = new User(txtFirstName.getText(), txtLastName.getText(), txtEmail.getText(), txtNick.getText(), String.valueOf(txtPassword.getPassword()));

                            UserDAO userDAO = new UserImplMysql();

                            userDAO.saveUser(usercreate);
                            FormsManager.getInstance().showForm(new Login());
                            toast.show(Notifications.Type.SUCCESS, "El usuario a sido Registrado");

                        } else {
                            toast.show(Notifications.Type.ERROR, "El Nick ya está en uso. Por favor, elige otro.");
                        }
                    } else {
                        toast.show(Notifications.Type.ERROR, "Las contraseñas no coinciden. ¡Inténtalo de nuevo!");
                    }
                } else {
                    toast.show(Notifications.Type.ERROR, "Por favor, ingresa un correo electrónico válido.");
                }
            }
        });

        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;" +
                        "[light]background:darken(@background,3%);" +
                        "[dark]background:lighten(@background,3%)");

        txtFirstName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nombre");
        txtLastName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Apellido");
        txtNick.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese tu Nick");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu correo electrónico");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu contraseña");
        txtConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Vuelva a introducir su contraseña");
        txtPassword.putClientProperty(FlatClientProperties.STYLE,
                "showRevealButton:true");
        txtConfirmPassword.putClientProperty(FlatClientProperties.STYLE,
                "showRevealButton:true");

        cmdRegister.putClientProperty(FlatClientProperties.STYLE,
                "[light]background:darken(@background,10%);" +
                        "[dark]background:lighten(@background,10%);" +
                        "borderWidth:0;" +
                        "focusWidth:0;" +
                        "innerFocusWidth:0");

        JLabel lbTitle = new JLabel("Bienvenido a nuestra aplicación Pokevinci");
        JLabel description = new JLabel("Únase a nosotros para jugar, explorar y hacer nuevos amigos. ¡Regístrese ahora y comience a Jugar!");
        lbTitle.putClientProperty(FlatClientProperties.STYLE,
                "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE,
                "[light]foreground:lighten(@foreground,30%);" +
                        "[dark]foreground:darken(@foreground,30%)");

        passwordStrengthStatus.initPasswordField(txtPassword);

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Nombre completo"), "gapy 10");
        panel.add(txtFirstName, "split 2");
        panel.add(txtLastName);
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(new JLabel("Nick"));
        panel.add(txtNick);
        panel.add(new JLabel("Email"));
        panel.add(txtEmail);
        panel.add(new JLabel("Contraseña"), "gapy 8");
        panel.add(txtPassword);
        panel.add(passwordStrengthStatus, "gapy 0");
        panel.add(new JLabel("Confirmar Contraseña"), "gapy 0");
        panel.add(txtConfirmPassword);
        panel.add(cmdRegister, "gapy 20");
        panel.add(createLoginLabel(), "gapy 10");
        add(panel);
    }


    private Component createLoginLabel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "background:null");
        JButton cmdLogin = new JButton(" Iniciar Sesion");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE,
                "border:3,3,3,3");
        cmdLogin.setContentAreaFilled(false);
        cmdLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdLogin.addActionListener(e -> {
            FormsManager.getInstance().showForm(new Login());
        });
        JLabel label = new JLabel("Ya tienes una cuenta?");
        label.putClientProperty(FlatClientProperties.STYLE,
                "[light]foreground:lighten(@foreground,30%);" +
                        "[dark]foreground:darken(@foreground,30%)");
        panel.add(label);
        panel.add(cmdLogin);
        return panel;
    }

    public boolean isMatchPassword() {
        String password = String.valueOf(txtPassword.getPassword());
        String confirmPassword = String.valueOf(txtConfirmPassword.getPassword());
        return password.equals(confirmPassword);
    }

    private boolean isValidEmail(String email) {
        // Expresión regular para validar el formato de un correo electrónico
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }


    private boolean isEmptyFields() {
        return txtFirstName.getText().isEmpty() ||
                txtLastName.getText().isEmpty() ||
                txtNick.getText().isEmpty() ||
                txtEmail.getText().isEmpty() ||
                String.valueOf(txtPassword.getPassword()).isEmpty() ||
                String.valueOf(txtConfirmPassword.getPassword()).isEmpty();
    }

    private boolean isNickUnique(String nick) {
        UserDAO userDAO = new UserImplMysql();
        return userDAO.NickUnique(nick);
    }

}






