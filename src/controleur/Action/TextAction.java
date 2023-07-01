/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleur.Action;

import controleur.Point;

/**
 *
 * @author MILIA Juvaldo Wisman
 */
public class TextAction extends Action {
    private String text;
    private Point position;

    public TextAction(String text, Point position) {
        this.text = text;
        this.position = position;
    }

    @Override
    public void perform() {
        // Logique pour effectuer l'action de texte
    }

    @Override
    public void undo() {
        // Logique pour annuler l'action de texte
    }

    @Override
    public void redo() {
        // Logique pour rétablir l'action de texte
    }
}
