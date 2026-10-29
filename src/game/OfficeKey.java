package game;

public class OfficeKey extends Item {
    private boolean usedKey;
    public OfficeKey() {
        super("office key");
        usedKey = false;
    }
    @Override
    public void use() {
        if (Game.getCurrentRoom().getName().equals("Living Room")) {
            if (usedKey) {
                Game.print("You already unlocked the office door!");
            } else {
                Game.getCurrentRoom().getExit(0).setLocked(false);
                usedKey = true;
                Game.print("You use the office key to unlock the office door!");
            }
        } else {
            Game.print("You don't see any way to use the office key here.");
        }
    }
}


