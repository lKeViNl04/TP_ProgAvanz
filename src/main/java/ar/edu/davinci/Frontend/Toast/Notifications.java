package ar.edu.davinci.Frontend.Toast;

import javax.swing.*;

public class Notifications {
    public enum Type {
        INFO, SUCCESS, WARNING, ERROR
    }

    private JFrame parentFrame;

    private static Notifications instance;

    private Notifications() {

    }

    public static Notifications getInstance() {
        if (instance == null) {
            instance = new Notifications();
        }
        return instance;
    }

    public void setJFrame(JFrame frame) {
        this.parentFrame = frame;
    }


    public void show(Type type, String message) {
        // Crear un JOptionPane con estilos y mensajes personalizados
        SwingUtilities.invokeLater(() -> {
            JOptionPane optionPane = new JOptionPane(
                    message,
                    getMessageType(type),
                    JOptionPane.DEFAULT_OPTION
            );

            JDialog dialog = optionPane.createDialog("Notification");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            // Personalización de apariencia
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);

            // Cerrar el diálogo automáticamente después de 3 segundos
            Timer timer = new Timer(3000, e -> dialog.dispose());
            timer.setRepeats(false);
            timer.start();
        });
    }

    private int getMessageType(Type type) {
        switch (type) {
            case SUCCESS:
                return JOptionPane.INFORMATION_MESSAGE;
            case WARNING:
                return JOptionPane.WARNING_MESSAGE;
            case ERROR:
                return JOptionPane.ERROR_MESSAGE;
            default:
                return JOptionPane.PLAIN_MESSAGE;
        }
    }
}
