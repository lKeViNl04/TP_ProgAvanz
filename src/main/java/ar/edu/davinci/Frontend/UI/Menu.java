package ar.edu.davinci.Frontend.UI;

import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Backend.User.User;
import ar.edu.davinci.Frontend.Manager.SessionManager;
import ar.edu.davinci.Frontend.Manager.FormsManager;
import ar.edu.davinci.Frontend.Toast.Notifications;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private Notifications toast;
    private User usuario;
    private Trainer trainer;

    public Menu() {
        this.toast = new Notifications();
        this.usuario = SessionManager.getInstance().getUser();
        this.trainer = SessionManager.getInstance().getTrainer();
        init();
    }

    public void init() {

        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));

        JPanel Panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
        Panel.putClientProperty(FlatClientProperties.STYLE,
                "arc:30;" +
                        "[light]background:darken(@background,3%);" +
                        "[dark]background:lighten(@background,3%)");

        JLabel lbTitle = new JLabel("Menu");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.putClientProperty(FlatClientProperties.STYLE,
                "font:bold +10");

        JLabel lbSubTitle = new JLabel("Seleccione una acción para continuar con la experiencia del juego:");
        lbSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbSubTitle.putClientProperty(FlatClientProperties.STYLE,
                "font:bold +5");


        Panel.add(lbTitle);
        Panel.add(lbSubTitle);
        Panel.add(battleTrainer());
        Panel.add(findWildPokemon());
        Panel.add(showPokemons());

        JPanel bottomPanel = new JPanel(new MigLayout("fillx, insets 0", "[left][center][right]", ""));
        bottomPanel.add(back(), "cell 0 0, align left");
        bottomPanel.add(logOutButton(), "cell 2 0, align right");

        add(Panel, "wrap");
        add(bottomPanel, "growx, wrap");

    }

    private Component battleTrainer() {

        JButton btnbattleTrainer = new JButton("Enfrentar otro Entrenador");
        btnbattleTrainer.addActionListener(e -> {
            FormsManager.getInstance().showForm(new BattleTrainer());
        });

        return btnbattleTrainer;
    }


    private Component findWildPokemon() {

        JButton btnfindWildPokemon = new JButton("Capturar Pokemon Salvaje");
        btnfindWildPokemon.addActionListener(e -> {
            FormsManager.getInstance().showForm(new FindWildPokemon());
        });

        return btnfindWildPokemon;
    }


    private Component showPokemons() {
        JButton btnshowPokemons = new JButton("Mostrar Lista de Pokemones");
        btnshowPokemons.addActionListener(e -> {
            FormsManager.getInstance().showForm(new ShowPokemons());
        });

        return btnshowPokemons;
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

    private Component back() {
        JButton btnCerrarSesion = new JButton("<-");
        btnCerrarSesion.addActionListener(e -> {
            FormsManager.getInstance().showForm(new TrainerSelection());
        });
        return btnCerrarSesion;
    }


}
