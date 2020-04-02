package com.gui;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {

    final MainFrame frame;
    JLabel sidesLabel; // we’re drawing regular polygons
    JSpinner sidesField; // number of sides
    JLabel sizeLabel;
    JSpinner sizeField;
    JComboBox colorCombo; // the color of the shape

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the spinner
        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(3, 3, 100, 1));
        sidesField.setValue(3); //default number of sides
        sizeLabel = new JLabel("Size of the figure:");
        sizeField = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
        sizeField.setValue(50); //default number of sides
        //create the colorCombo, containing the values: Random and Black
        String colorComboValues[] = {"Random", "Black"};
        colorCombo = new JComboBox(colorComboValues);
        add(sidesLabel); //JPanel uses FlowLayout by default
        add(sidesField);
        add(colorCombo);
        add(sizeLabel);
        add(sizeField);
    }
}
