
package game;
public class Tool extends Item {
    private boolean usedTool;
    public Tool() {
        super("tool");
        usedTool = false;
    }
    @Override
    public void use() {
        if (Game.getCurrentRoom().getName().equals("Bedroom")) {
            if (usedTool) {
                Game.print("You already opened the attic door!");
            } else {
                Game.getCurrentRoom().getExit(4).setLocked(false);
                usedTool = true;
                Game.print("Using the tool, you are able to reach the door in the ceiling and pull it open. A ladder falls, allowing you to climb up into the attic.");
            }
        } else {
            Game.print("You don't see any way to use the tool here.");
        }
    }
}

