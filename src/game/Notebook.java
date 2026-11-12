
package game;
public class Notebook extends Item {
    private boolean passwordFound;
    public Notebook() {
        super("notebook");
        passwordFound = false;
    }
    @Override
    public void read() {
        if (!Game.hasItem(this)) {
            Game.print("You must take the notebook first.");
            return;
        }
        Game.print("You read the notebook.");
        if (!passwordFound) {
            Game.print("The notebook contains "
                    + "the computer password!");
            Item password = new Item("password");
            Game.addInventoryItem(password);
            passwordFound = true;
            Game.print("The password has been "
                    + "added to your inventory.");
        } else {
            Game.print("You already found the password.");
            Game.print("No new password was added.");
        }
    }
}



