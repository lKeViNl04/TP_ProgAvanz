package ar.edu.davinci.Frontend.UI;

import ar.edu.davinci.Backend.DAO.PokemonDAO;
import ar.edu.davinci.Backend.DAO.PokemonImplMysql;
import ar.edu.davinci.Backend.DAO.TrainerDAO;
import ar.edu.davinci.Backend.DAO.TrainerImplMysql;
import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Pokemon.Type.Fire;
import ar.edu.davinci.Backend.Pokemon.Type.Plant;
import ar.edu.davinci.Backend.Pokemon.Type.Water;
import ar.edu.davinci.Backend.Trainer.Gender;
import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Backend.User.User;
import ar.edu.davinci.Frontend.Manager.FormsManager;
import ar.edu.davinci.Frontend.Manager.SessionManager;
import ar.edu.davinci.Frontend.Toast.Notifications;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class NewTrainerForm extends JPanel {
    private User myUser;
    private String gender;
    private Pokemon pokemonstart;
    private Notifications toast;
    private JTextField txtFirstName;
    private JTextField txtNationality;
    private JSpinner date;
    private JRadioButton jrMale;
    private JRadioButton jrFemale;
    private JRadioButton jrAgenero;
    private ButtonGroup groupGender;
    private JRadioButton jrBulbasur;
    private JRadioButton jrCharmander;
    private JRadioButton jrSquirtle;
    private ButtonGroup groupPokemon;
    private JButton cmdNewTrainer;

    public NewTrainerForm() {
        myUser = SessionManager.getInstance().getUser();
        toast = new Notifications();
        init();
    }

    public void init() {

        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtFirstName = new JTextField();
        txtNationality = new JTextField();

        date = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(date, "yyyy-MM-dd");
        date.setEditor(dateEditor);

        cmdNewTrainer = new JButton("Crear Entrenador");


        cmdNewTrainer.addActionListener(e -> {
            PokemonDAO pokemonDAO = new PokemonImplMysql();
            TrainerDAO trainerDao = new TrainerImplMysql();
            if (isEmptyFields()) {
                toast.show(Notifications.Type.ERROR, "Por favor, completa todos los campos.");
            } else {
                Date selectedDate = (Date) date.getValue();
                Trainer newTrainer = new Trainer(txtFirstName.getText(), selectedDate, txtNationality.getText(), Gender.valueOf(gender));
                Trainer trainer = trainerDao.setTrainerToUser(newTrainer, myUser.getUserId());

                pokemonDAO.setPokemonToTrainer(pokemonstart, trainer.getTrainerId());

                toast.show(Notifications.Type.SUCCESS, "¡Entrenador y Pokémon inicial creados con éxito!");

                myUser.getTrainers().add(trainer);
                FormsManager.getInstance().showForm(new TrainerSelection());


            }
        });

        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "arc:20;" +
                        "[light]background:darken(@background,3%);" +
                        "[dark]background:lighten(@background,3%)"
        );

        txtFirstName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nombre");
        txtNationality.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nacionalidad");
        cmdNewTrainer.putClientProperty(FlatClientProperties.STYLE,
                "[light]background:darken(@background,10%);" +
                        "[dark]background:lighten(@background,10%);" +
                        "borderWidth:0;" +
                        "focusWidth:0;" +
                        "innerFocusWidth:0"
        );

        JLabel lbTitle = new JLabel("Crea a tu Entrenador Pokémon");
        JLabel description = new JLabel("Configura tu entrenador a tu gusto y prepárate para ser el mejor en el mundo Pokémon.");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE,
                "[light]foreground:lighten(@foreground,30%);" +
                        "[dark]foreground:darken(@foreground,30%)"
        );


        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Nombre y Nacionalidad"), "gapy 10");
        panel.add(txtFirstName, "split 2");
        panel.add(txtNationality);
        panel.add(new JLabel("Genero"), "gapy 8");
        panel.add(createGenderPanel());
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(date);
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(createPokemonPanel());
        panel.add(cmdNewTrainer, "gapy 20");
        add(panel, "wrap");
        add(back(), "align left, wrap");


    }

    private Component createGenderPanel() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "background:null");
        jrMale = new JRadioButton("Masculino");
        jrFemale = new JRadioButton("Femenino");
        jrAgenero = new JRadioButton("Agenero");
        groupGender = new ButtonGroup();
        groupGender.add(jrMale);
        groupGender.add(jrFemale);
        groupGender.add(jrAgenero);
        jrMale.setSelected(true);

        jrMale.addActionListener(e -> {
            gender = jrMale.getText();
        });
        jrFemale.addActionListener(e -> {
            gender = jrFemale.getText();
        });
        jrAgenero.addActionListener(e -> {
            gender = jrAgenero.getText();
        });

        panel.add(jrMale);
        panel.add(jrFemale);
        panel.add(jrAgenero);
        return panel;
    }

    private Component createPokemonPanel() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "background:null");
        jrCharmander = new JRadioButton("Charmander");
        jrSquirtle = new JRadioButton("Squirtle");
        jrBulbasur = new JRadioButton("Bulbasur");
        groupPokemon = new ButtonGroup();

        groupPokemon.add(jrCharmander);
        groupPokemon.add(jrSquirtle);
        groupPokemon.add(jrBulbasur);
        jrBulbasur.setSelected(true);


        jrCharmander.addActionListener(e -> {
            pokemonstart = new Pokemon(new Fire(), "Charmander", 45);
        });
        jrSquirtle.addActionListener(e -> {
            pokemonstart = new Pokemon(new Water(), "Squirtle", 55);
        });
        jrBulbasur.addActionListener(e -> {
            pokemonstart = new Pokemon(new Plant(), "Bulbasur", 25);
        });

        panel.add(jrCharmander);
        panel.add(jrSquirtle);
        panel.add(jrBulbasur);
        return panel;
    }

    private boolean isEmptyFields() {

        Date selectedDate = (Date) date.getValue();
        Date defaultDate = new Date(0);

        boolean isDateEmpty = selectedDate.equals(defaultDate);

        return txtFirstName.getText().isEmpty() ||
                txtNationality.getText().isEmpty() ||
                isDateEmpty || gender.isEmpty() || gender == null || pokemonstart== null ;
    }


    private Component back() {
        JButton btnCerrarSesion = new JButton("<-");
        btnCerrarSesion.addActionListener(e -> {
            FormsManager.getInstance().showForm(new TrainerSelection());
        });
        return btnCerrarSesion;
    }

}
