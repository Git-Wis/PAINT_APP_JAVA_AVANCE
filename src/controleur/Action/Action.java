/*
 * class abstrait action qui vas contenir tout les actions dans un pile
 * 
 */
package controleur.Action;

/**
 *
 * @author MILIA Juvaldo Wisman
 */
public abstract class Action {
    public abstract void perform();

    public abstract void undo();

    public abstract void redo();
}
