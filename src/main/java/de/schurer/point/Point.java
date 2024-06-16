package de.schurer.point;

import java.awt.geom.Point2D;

public class Point extends Point2D.Double {

    public Point(double x, double y) {
        super(x, y);
    }

    public double getCartesianX(double midX, double xDestinationPoint, double xScale) {
        return midX + x * ((xDestinationPoint) / xScale);
    }

    public double getCartesianY(double midY, double yDestinationPoint, double yScale) {
        return midY - y * ((yDestinationPoint) / yScale);
    }
}
