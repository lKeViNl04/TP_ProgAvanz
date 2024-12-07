package ar.edu.davinci.Frontend.UI;

import ar.edu.davinci.Backend.BattleStep.BattleStep;
import ar.edu.davinci.Backend.DAO.*;
import ar.edu.davinci.Backend.Excepion.PokemonNotFoundException;
import ar.edu.davinci.Backend.Excepion.UserNotFoundException;
import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Backend.User.User;
import ar.edu.davinci.Frontend.Manager.FormsManager;
import ar.edu.davinci.Frontend.Manager.SessionManager;
import ar.edu.davinci.Frontend.Toast.Notifications;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class BattleTrainer extends JPanel {

    private Notifications toast;
    private User myUser;
    private Trainer myTrainer;
    private User userEnemigo;
    private Trainer trainerEnemigo;

    public BattleTrainer() {
        this.toast = new Notifications();
        this.myUser = SessionManager.getInstance().getUser();
        this.myTrainer = SessionManager.getInstance().getTrainer();
        init();
    }

    public void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));

        JPanel Panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
        Panel.putClientProperty(FlatClientProperties.STYLE,
                "arc:30;" +
                        "[light]background:darken(@background,3%);" +
                        "[dark]background:lighten(@background,3%)");

        JLabel lbTitle = new JLabel("¡Te has encontrado con un Contricante!");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.putClientProperty(FlatClientProperties.STYLE,
                "font:bold +10");

        JLabel lbSubTitle = new JLabel("Resultados del combate contra el Usuario encontrado:");
        lbSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbSubTitle.putClientProperty(FlatClientProperties.STYLE,
                "font:bold +5");


        Panel.add(lbTitle);
        Panel.add(lbSubTitle);
        Panel.add(battleTrainerPokemon());

        add(Panel, "wrap");
        add(back(), "align left, wrap");

    }

    private Component battleTrainerPokemon() {
        JPanel fightPanel = new JPanel();
        fightPanel.setLayout(new BoxLayout(fightPanel, BoxLayout.Y_AXIS));

        UserDAO userDAO = new UserImplMysql();
        PokemonDAO pokemonDAO = new PokemonImplMysql();
        TrainerDAO trainerDAO = new TrainerImplMysql();

        try {
            Random random = new Random();

            userEnemigo = userDAO.getRandomUser(myUser.getUserId());
            userEnemigo.addEntrenadores(trainerDAO.getTrainersByUserId(userEnemigo.getUserId()));
            trainerEnemigo = userEnemigo.getTrainers().get(random.nextInt(userEnemigo.getTrainers().size()));
            trainerEnemigo.addPokemon(pokemonDAO.getPokemonsByTrainerId(trainerEnemigo.getTrainerId()));


            JLabel userNickEnemigo = new JLabel("Usuario enemigo: " + userEnemigo.getNickname());
            JLabel trainerNameEnemigo = new JLabel("Entrenador enemigo: " + trainerEnemigo.getName());

            fightPanel.add(userNickEnemigo);
            fightPanel.add(trainerNameEnemigo);
            fightPanel.add(new JSeparator());

            JLabel myUserNick = new JLabel("my Usuario: " + myUser.getNickname());
            JLabel myTrainerName = new JLabel("my Entrenador: " + myTrainer.getName());

            fightPanel.add(myUserNick);
            fightPanel.add(myTrainerName);
            fightPanel.add(new JSeparator());

            List<BattleStep> battleinfo = trainerDAO.battleTrainerPokemon(myTrainer, trainerEnemigo,pokemonDAO);

            for (BattleStep bs : battleinfo) {


                JLabel infoAtaque = new JLabel("<html>El Pokémon " + bs.getAtacante() + " ataca al Pokémon " + bs.getAtacado() +
                        " y hace " + bs.getDano() + " de daño.</html> \n");
                fightPanel.add(infoAtaque);

                if (bs.getAutoDano() > 0) {
                    JLabel infoautodanio = new JLabel("<html>El Pokémon " + bs.getAtacante() + "por atacar al Pokémon " + bs.getAtacado() +
                            " recibe " + bs.getAutoDano() + " de daño.</html> \n");
                    fightPanel.add(infoautodanio);
                }


                JLabel infoEstado = new JLabel("<html>Vida de " + bs.getAtacante() + " es " + bs.getVidaAtacante() + "<br>" +
                        "Vida de " + bs.getAtacado() + " es " + bs.getVidaAtacado() + "</html> \n");
                fightPanel.add(infoEstado);
                fightPanel.add(new JSeparator());
            }

            if (!trainerEnemigo.alive()) {
                toast.show(Notifications.Type.INFO, "¡Ganaste! Felicitaciones ");
            }

            if (!myTrainer.alive()) {
                toast.show(Notifications.Type.INFO, "No te quedan mas pokemones vivos.");
            }


        } catch (UserNotFoundException u) {
            toast.show(Notifications.Type.ERROR, u.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(fightPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        return scrollPane;

    }


    private Component back() {
        JButton btnCerrarSesion = new JButton("<-");
        btnCerrarSesion.addActionListener(e -> {
            FormsManager.getInstance().showForm(new Menu());
        });
        return btnCerrarSesion;
    }


}
