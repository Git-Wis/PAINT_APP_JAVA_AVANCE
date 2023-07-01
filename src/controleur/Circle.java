package controleur;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Circle implements Shape {
    private int radius;
    private Color color;
    private int x, y, brushSize;
    private boolean fill;

    public Circle(int x, int y, int radius, Color color, int brushSize) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.brushSize = brushSize;
        this.color = color;
        this.fill = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(brushSize));
        g2d.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);

        if (fill) {
            g2d.fill(this);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    @Override
    public Rectangle2D getBounds2D() {
        return new Rectangle2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    @Override
    public boolean contains(double x, double y) {
        return getBounds2D().contains(x, y);
    }

    @Override
    public boolean contains(Point2D p) {
        return contains(p.getX(), p.getY());
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return getBounds2D().intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return getBounds2D().contains(x, y, w, h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return contains(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius).getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius).getPathIterator(at, flatness);
    }
}
