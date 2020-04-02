package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    boolean deletedItems = false;//pentru a sti daca trebuie sa redesenam toata lista sau doar ultimul element din lista
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    List<Color> colorList = new ArrayList<>();//lista de culori pentru a retine si culoarea formei la acelasi index

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }

    private void drawShape(int x, int y) {
        Random randCol = new Random();
        float r = randCol.nextFloat();
        float g = randCol.nextFloat();
        float b = randCol.nextFloat();
        float a = randCol.nextFloat();
        Color randomColor = new Color(r, g, b, a);
        Random rand = new Random();
        int radius = (int) frame.configPanel.sizeField.getValue();
        int sides = (int) frame.configPanel.sidesField.getValue();//get the value from UI (in ConfigPanel)
        String chosenColor = frame.configPanel.colorCombo.getSelectedItem().toString();
        Color color;
        if (chosenColor.equals("Black")) {
            color = new Color(0, 0, 0);
        } else {
            color = randomColor; //create a transparent random Color.}
        }
        String chosenShape = frame.shapesPanel.shapeCombo.getSelectedItem().toString();
        if (chosenShape.equals("Polygon")) {
            Shape polygon = new RegularPolygon(x, y, radius, sides);
            frame.shapesPanel.shapes.add(polygon);
            frame.shapesPanel.updateShapes();
            colorList.add(color);
            frame.shapesPanel.revalidate();
        }
        if (chosenShape.equals("Square")) {
            Shape square = new Rectangle(x - radius, y - radius, radius * 2, radius * 2);
            frame.shapesPanel.shapes.add(square);
            frame.shapesPanel.updateShapes();
            colorList.add(color);
            frame.shapesPanel.revalidate();

        }
        if (chosenShape.equals("Hexagon")) {
            Shape hexagon = new RegularPolygon(x, y, radius, 6);
            frame.shapesPanel.shapes.add(hexagon);
            frame.shapesPanel.updateShapes();
            colorList.add(color);
            frame.shapesPanel.revalidate();
        }
        if (chosenShape.equals("Circle")) {
            Shape circle = new Ellipse2D.Float(x - radius, y - radius, radius * 2, radius * 2);
            frame.shapesPanel.shapes.add(circle);
            frame.shapesPanel.updateShapes();
            frame.shapesPanel.revalidate();
            colorList.add(color);
        }


    }

    @Override
    public void update(Graphics g) {
    } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        int index;
        if (frame.shapesPanel.shapes.size() != 0) {
            if (deletedItems) {
                index = 0;//redesenam intreaga lista care acum nu mai are elementul sters
                deletedItems = false;
            } else {
                index = frame.shapesPanel.shapes.size() - 1;//daca nu a fost sters niciun element nu e necesar sa redesenam toata lista iar
            }
            while (index < frame.shapesPanel.shapes.size()) {
                frame.canvas.graphics.setColor(colorList.get(index));
                frame.canvas.graphics.fill(frame.shapesPanel.shapes.get(index));
                index++;
            }

        }
        g.drawImage(image, 0, 0, this);

    }
}


