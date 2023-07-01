/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleur.Action;

import controleur.Point;
import java.util.ArrayList;
import vue.MaFenetre;

/**
 *
 * @author MILIA Juvaldo Wisman
 */
public class FreehandAction extends Action {
    private ArrayList<Point> freehandPoints;

    public FreehandAction(ArrayList<Point> freehandPoints) {
        this.freehandPoints = freehandPoints;
    }

    @Override
    public void perform() {
        MaFenetre.canvas.addFreehandPoints(freehandPoints);
    }

    @Override
    public void undo() {
        MaFenetre.canvas.removeFreehandPoints(freehandPoints);
        MaFenetre.canvas.repaint();
    }

    @Override
    public void redo() {
        MaFenetre.canvas.addFreehandPoints(freehandPoints);
    }
    
    public ArrayList<Point> getFreehandPoints() {
        return this.freehandPoints;
    }
}

