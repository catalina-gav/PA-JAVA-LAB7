package com.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ShapesPanel extends JPanel {
    List<Shape> shapes = new ArrayList<>();
    JLabel shapeLabel; // we’re drawing regular polygons
    JComboBox shapeCombo;
    JComboBox shapeList;
    JButton deleteBtn = new JButton("Delete Shape");
    List<String> shapeId = new ArrayList<>();


    final MainFrame frame;

    public ShapesPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public void updateShapes() {
        shapeId.add(shapes.get(shapes.size() - 1).toString());
        shapeList.addItem(shapeId.toArray());
    }


    private void init() {
        deleteBtn.addActionListener(this::delete);
        add(deleteBtn);
        setLayout(new GridLayout(20, 1));

        shapeLabel = new JLabel("Choose a shape to draw:");
        shapeList = new JComboBox(shapeId.toArray());
        add(shapeList);
        String colorComboValues[] = {"Polygon", "Circle", "Hexagon", "Square"};
        shapeCombo = new JComboBox(colorComboValues);
        add(shapeLabel);
        add(shapeCombo);
    }

    private void delete(ActionEvent e) {
        if (shapes.size() == 0) {
            System.out.println("Nu exista elemente desenate pe care sa le puteti sterge");
        } else {

            BufferedImage newImage = new BufferedImage(frame.canvas.W, frame.canvas.H, BufferedImage.TYPE_INT_ARGB);
            frame.canvas.image = newImage;
            frame.canvas.graphics = newImage.createGraphics();
            frame.canvas.graphics.setColor(Color.WHITE); //fill the image with white
            frame.canvas.graphics.fillRect(0, 0, frame.canvas.W, frame.canvas.H);
            int index = shapeList.getSelectedIndex();
            shapes.remove(index);
            frame.canvas.colorList.remove(index);
            shapeList.removeItemAt(index);
            frame.canvas.deletedItems = true;
            frame.repaint();
        }

    }
}