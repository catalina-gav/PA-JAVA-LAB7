package com.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton exitBtn = new JButton("Exit");
    JButton resetBtn = new JButton("Reset");
    JButton loadBtn = new JButton("Load");



    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {

        setLayout(new GridBagLayout());
        saveBtn.addActionListener(this::save);
        exitBtn.addActionListener(this::exit);
        resetBtn.addActionListener(this::reset);
        loadBtn.addActionListener(this::load);
        add(saveBtn);
        add(resetBtn);
        add(exitBtn);
        add(loadBtn);
    }

    private void save(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(frame.canvas.image, "PNG", new File(fileChooser.getSelectedFile().getAbsolutePath()));
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    private void exit(ActionEvent e) {
        frame.setVisible(false);
        System.exit(0);
        frame.shapesPanel.shapes.clear();
        frame.canvas.colorList.clear();
        frame.shapesPanel.shapeList.removeAllItems();
    }

    private void reset(ActionEvent e) {
        final int W = 800, H = 600;
        BufferedImage newImage = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        frame.canvas.image = newImage;
        frame.canvas.graphics = newImage.createGraphics();
        frame.canvas.graphics.setColor(Color.WHITE); //fill the image with white
        frame.canvas.graphics.fillRect(0, 0, W, H);
        frame.shapesPanel.shapes.clear();
        frame.canvas.colorList.clear();
        frame.shapesPanel.shapeList.removeAllItems();
        frame.repaint();
    }

    private void load(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Specify a file to open");
        int userSelection = fileChooser.showOpenDialog(frame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage myPicture = ImageIO.read(new File(fileChooser.getSelectedFile().getAbsolutePath()));
                reset(e);
                frame.canvas.image = myPicture;
                frame.canvas.graphics = myPicture.createGraphics();
                frame.canvas.paintComponent(frame.canvas.graphics);
                frame.repaint();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

    }
}
