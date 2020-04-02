package com.gui;

import javax.swing.*;

import static java.awt.BorderLayout.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel = new ConfigPanel(this);
    ControlPanel controlPanel = new ControlPanel(this);
    DrawingPanel canvas = new DrawingPanel(this);
    ShapesPanel shapesPanel = new ShapesPanel(this);

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create the components
        canvas = new DrawingPanel(this);

        //arrange the components in the container (frame)
        //JFrame uses a BorderLayout by default
        add(canvas, CENTER); //this is BorderLayout.CENTER
        add(configPanel, NORTH);
        add(controlPanel, SOUTH);
        add(shapesPanel, WEST);


        //invoke the layout manager
        pack();
    }
}
