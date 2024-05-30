package de.schurer;

import de.schurer.cartesian.CartesianFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                CartesianFrame cartesianFrame = new CartesianFrame();
                cartesianFrame.showUI();
            }
        });
    }

}