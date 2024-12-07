package ar.edu.davinci.Frontend.UI;

import ar.edu.davinci.Backend.BattleStep.BattleStep;
import ar.edu.davinci.Backend.DAO.PokemonDAO;
import ar.edu.davinci.Backend.DAO.PokemonImplMysql;
import ar.edu.davinci.Backend.DAO.TrainerDAO;
import ar.edu.davinci.Backend.DAO.TrainerImplMysql;
import ar.edu.davinci.Backend.Excepion.PokemonNotFoundException;
import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Frontend.Manager.FormsManager;
import ar.edu.davinci.Frontend.Manager.SessionManager;
import ar.edu.davinci.Frontend.Toast.Notifications;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FindWildPokemon extends JPanel {
    private Notifications toast;
    private Trainer trainer;
    private Pokemon pokemonWild;

    // Agregado: constante para la cantidad máxima de pokémon (mejora mantenimiento)
    private static final int MAX_POKEMONS = 5;

    public FindWildPokemon() {
        this.toast = new Notifications();
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

        JLabel lbTitle = new JLabel("¡Te has encontrado con un Pokémon salvaje!");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:bold +10");

        JLabel lbSubTitle = new JLabel("Resultados del combate contra el Pokémon salvaje:");
        lbSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbSubTitle.putClientProperty(FlatClientProperties.STYLE, "font:bold +5");

        Panel.add(lbTitle);
        Panel.add(lbSubTitle);
        Panel.add(fightWildPokemon());

        add(Panel, "wrap");
        add(back(), "align left, wrap");
    }

    private Component fightWildPokemon() {
        JPanel fightPanel = new JPanel();
        fightPanel.setLayout(new BoxLayout(fightPanel, BoxLayout.Y_AXIS));

        PokemonDAO pokemonDAO = new PokemonImplMysql();
        TrainerDAO trainerDAO = new TrainerImplMysql();

        try {
            pokemonWild = pokemonDAO.getPokemonsWild();

            JLabel pokemonWildSpecies = new JLabel("Pokémon Salvaje: " + pokemonWild.getEspecie());
            JLabel pokemonWildType = new JLabel("Tipo: " + pokemonWild.getTipo().getNombre());
            JLabel pokemonWildHp = new JLabel("HP: " + pokemonWild.getVida());

            fightPanel.add(pokemonWildSpecies);
            fightPanel.add(pokemonWildType);
            fightPanel.add(pokemonWildHp);
            fightPanel.add(new JSeparator());

            List<BattleStep> battleinfo = trainerDAO.battleWildPokemon(trainer, pokemonWild, pokemonDAO);


            for (BattleStep bs : battleinfo) {
                JLabel infoAtaque = new JLabel("<html>El Pokémon " + bs.getAtacante() + " ataca al Pokémon " + bs.getAtacado() +
                        " y hace " + bs.getDano() + " de daño.</html> \n");
                fightPanel.add(infoAtaque);

                if (bs.getAutoDano() > 0) {
                    JLabel infoautodanio = new JLabel("<html>El Pokémon " + bs.getAtacante() + " por atacar al Pokémon " + bs.getAtacado() +
                            " recibe " + bs.getAutoDano() + " de daño.</html> \n");
                    fightPanel.add(infoautodanio);
                }

                JLabel infoEstado = new JLabel("<html>Vida de " + bs.getAtacante() + " es " + bs.getVidaAtacante() + "<br>" +
                        "Vida de " + bs.getAtacado() + " es " + bs.getVidaAtacado() + "</html> \n");
                fightPanel.add(infoEstado);
                fightPanel.add(new JSeparator());
            }

            if (pokemonWild.isDead()) {
                if (trainer.capturePokemon(pokemonWild)) {
                    pokemonDAO.setPokemonToTrainer(pokemonWild, trainer.getTrainerId());
                    toast.show(Notifications.Type.INFO, "¡Ganaste! Captura exitosa");
                } else {
                    toast.show(Notifications.Type.INFO, "¡Ganaste! Captura Denegada. (max pokémon " + MAX_POKEMONS + ")"); // Modificado
                }
            }

            if (!trainer.alive()) {
                toast.show(Notifications.Type.INFO, "No te quedan más pokémon vivos.");
            }

        } catch (PokemonNotFoundException p) {
            toast.show(Notifications.Type.ERROR, p.getMessage());
        } catch (Exception e) {
            toast.show(Notifications.Type.ERROR, "Ocurrió un error inesperado: " + e.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(fightPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Modificado
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
