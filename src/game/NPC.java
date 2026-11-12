package game;
import java.io.Serializable;
import javax.swing.JOptionPane;
public class NPC implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    public NPC(String name) {
        this.name = name.toLowerCase();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name.toLowerCase();
    }
    public void say(String dialog) {
        Game.print(name.toUpperCase());
        Game.print(dialog);
    }
    public void talk() {
        Game.print("You can't talk to " + name + ".");
    }
    public int getResponse(String[] responses) {
        String options = "";
        for (int i = 0; i < responses.length; i++) {
            String line = (i + 1) + ") " + responses[i];
            Game.print(line);
            options += line + "\n";
        }
        String input = JOptionPane.showInputDialog(
            null,
            options + "\nChoose an option:"
        );
        if (input == null) {
            return -1;
        }
        try {
            int choice = Integer.parseInt(input.trim());
            if (choice >= 1 && choice <= responses.length) {
                return choice;
            }
        } catch (NumberFormatException ex) {
            return -1;
        }
        return -1;
    }
}


