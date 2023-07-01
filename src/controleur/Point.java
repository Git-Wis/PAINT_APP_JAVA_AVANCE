/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleur;

import java.awt.Color;

/**
 *
 * @author MILIA Juvaldo Wisman
 */
public class Point {
    public int x;
    public int y;
    private int Stroke;
    private Color color;

    public Point(int x, int y, Color color , int Stroke) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.Stroke = Stroke;
    }

    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public int getStroke() {
        return this.Stroke;
    } 
    
    
}
