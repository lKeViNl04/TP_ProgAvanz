package ar.edu.davinci.Frontend.Main;


import ar.edu.davinci.Frontend.Manager.FormsManager;
import ar.edu.davinci.Frontend.Toast.Notifications;
import ar.edu.davinci.Frontend.UI.Login;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public Application() {
        init();
    }

    private void init() {
        setTitle("Pokévici");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 800));
        setLocationRelativeTo(null);
        setContentPane(new Login());
        Notifications toast = new Notifications();
        toast.setJFrame(this);
        FormsManager.getInstance().initApplication(this);

    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("UI.THEMES");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(()-> new Application().setVisible(true));
    }

}
