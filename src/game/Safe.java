package game;
public class Safe extends Item {
    private boolean opened;
    private boolean notebookFound;
    public Safe() {
        super("safe", true);
        opened = false;
        notebookFound = false;
    }
    @Override
    public void open() {
        if (opened) {
            Game.print("The safe is already open.");
            return;
        }
        opened = true;
        Game.print("You open the safe.");
        if (!notebookFound) {
            Notebook notebook = new Notebook();
            Game.getCurrentRoom().addItem(notebook);
            notebookFound = true;
            Game.print("You found a notebook!");
            Game.print("You can now take the notebook.");
        } else {
            Game.print("There is nothing new in the safe.");
        }
    }
    @Override
    public void close() {
        if (!opened) {
            Game.print("The safe is already closed.");
        } else {
            opened = false;
            Game.print("You close the safe.");
        }
    }
}
