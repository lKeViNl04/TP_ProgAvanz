package ar.edu.davinci.Frontend.UI;

import ar.edu.davinci.Backend.Pokemon.Pokemon;
import ar.edu.davinci.Backend.Trainer.Trainer;
import ar.edu.davinci.Frontend.Manager.SessionManager;
import ar.edu.davinci.Frontend.Manager.FormsManager;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShowPokemons extends JPanel {

    private Trainer trainer;

    public ShowPokemons() {
        this.trainer = SessionManager.getInstance().getTrainer();
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


        JLabel lbTitle = new JLabel("Lista de pokemones que tenes:");
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.putClientProperty(FlatClientProperties.STYLE,
                "font:bold +10");
        panel.add(lbTitle);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Especie");
        tableModel.addColumn("Poder");
        tableModel.addColumn("Vida");
        tableModel.addColumn("Energia");

        for (Pokemon pokemon : trainer.getPokemons()) {
            Object[] row = { pokemon.getTipo().getNombre(), pokemon.getEspecie(), pokemon.getPoder(), pokemon.getVida(), pokemon.getEnergia()};
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel, "wrap");
        add(back(), "align left, wrap");


    }

    private Component back() {
        JButton btnCerrarSesion = new JButton("<-");
        btnCerrarSesion.addActionListener(e -> {
            FormsManager.getInstance().showForm(new Menu());
        });
        return btnCerrarSesion;
    }


}
