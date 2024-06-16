package de.schurer;

import de.schurer.cartesian.CartesianFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 800;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CartesianFrame cartesianFrame = new CartesianFrame();
            cartesianFrame.showUI();
        });
    }

}