package ar.edu.davinci.Frontend.UI;

import ar.edu.davinci.Backend.DAO.*;
import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Backend.User.User;
import ar.edu.davinci.Frontend.Manager.SessionManager;
import ar.edu.davinci.Frontend.Manager.FormsManager;
import ar.edu.davinci.Frontend.Toast.Notifications;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class TrainerSelection extends JPanel {
    private Notifications toast;
    private User usuario;
    private Trainer trainerSeleccionado;

    public TrainerSelection() {
        this.toast = new Notifications();
        this.usuario = SessionManager.getInstance().getUser();
        init();
    }

    public void init() {


        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        ButtonGroup grupoOpciones = new ButtonGroup();

        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "arc:30;" +
                        "[light]background:darken(@background,3%);" +
                        "[dark]background:lighten(@background,3%)");

        JLabel lbTitle = new JLabel("Selecciona un entrenador:");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.putClientProperty(FlatClientProperties.STYLE,
                "font:bold +10");
        panel.add(lbTitle);

        UserDAO userDAO = new UserImplMysql();
        TrainerDAO trainerDAO = new TrainerImplMysql();

        for (Trainer trainer : usuario.getTrainers()) {

            JPanel panelTrainer = new JPanel(new MigLayout("wrap 2", "[grow][grow]"));

            JRadioButton botonOpcion = new JRadioButton("<html><b><span style='font-size:10px;'>Entrenador: " + trainer.getName() + "</span></b>" +
                    "<br><b>Nacionalidad:</b> " + trainer.getNationality() + "&nbsp; <b>Genero:</b>  " + trainer.getGender() + "&nbsp; <b>edad:</b>  " + trainer.getFechaNacimientoInt() + "</html>");


            // Guardar el trainer seleccionado
            botonOpcion.addActionListener(e -> trainerSeleccionado = trainer);


            JButton btnDeleteTrainer = new JButton("eliminar");
            btnDeleteTrainer.addActionListener(e -> {
                trainerDAO.deleteTrainer(trainer.getTrainerId());
                usuario.getTrainers().remove(trainer);
                toast.show(Notifications.Type.INFO, "Entrenador eliminado exitosamente");
                FormsManager.getInstance().showForm(new TrainerSelection());
            });

            panelTrainer.add(botonOpcion, "grow");
            panelTrainer.add(btnDeleteTrainer, "grow");
            grupoOpciones.add(botonOpcion);
            panel.add(panelTrainer);
        }


        // Agregar componentes al panel principal
        panel.add(createTrainerButton(), "center");
        panel.add(confirmButton(), "center");
        add(panel, "wrap");
        add(logOutButton(), "wrap");

    }

    private Component confirmButton() {

        JButton btnConfirmar = new JButton("Confirmar selección");
        btnConfirmar.addActionListener(e -> {
            if (trainerSeleccionado == null) {
                toast.show(Notifications.Type.ERROR, "Por favor, selecciona un entrenador.");
            } else {

                PokemonDAO pokemonDAO = new PokemonImplMysql();

                try {
                    trainerSeleccionado.getPokemons().clear();
                    trainerSeleccionado.addPokemon(pokemonDAO.getPokemonsByTrainerId(trainerSeleccionado.getTrainerId()));
                    SessionManager.getInstance().setTrainer(trainerSeleccionado);
                    FormsManager.getInstance().showForm(new Menu());
                    toast.show(Notifications.Type.SUCCESS, "Entrenador seleccionado: " + trainerSeleccionado.getName());
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });
        return btnConfirmar;
    }


    private Component createTrainerButton() {

        JButton btnConfirmar = new JButton("Crear Nuevo Entrenador");
        btnConfirmar.addActionListener(e -> {
            if (CheckTrainerLimit(usuario.getUserId())) {
                FormsManager.getInstance().showForm(new NewTrainerForm());
                toast.show(Notifications.Type.SUCCESS, "Prepárate para dar vida a un nuevo entrenador.");
            } else {
                toast.show(Notifications.Type.ERROR, "Has alcanzado el límite de entrenadores permitidos (3).");
            }
        });
        return btnConfirmar;
    }

    private Component logOutButton() {

        JButton btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.addActionListener(e -> {

            SessionManager.getInstance().clearSession();

            FormsManager.getInstance().showForm(new Login());

            toast.show(Notifications.Type.SUCCESS, "Sesión cerrada exitosamente.");
        });
        return btnCerrarSesion;
    }

    private boolean CheckTrainerLimit(int userId) {
        TrainerDAO trainerDAO = new TrainerImplMysql();
        return trainerDAO.getTrainerCountByUserId(userId) < 3;
    }


}