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

        while (true) {

            String input = JOptionPane.showInputDialog(
                null,
                options + "\nChoose an option:"
            );

            if (input == null) {
                Game.print("Conversation cancelled.");
                return -1;
            }

            try {
                int choice = Integer.parseInt(input.trim());

                if (choice >= 1 && choice <= responses.length) {
                    return choice;
                }

                JOptionPane.showMessageDialog(
                    null,
                    "Please choose a number between 1 and "
                        + responses.length + "."
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    null,
                    "Invalid answer! Please enter a number."
                );
            }
        }
    }

    public void introduce() {
        say("Hello! My name is " + name + ".");
    }

    public void goodbye() {
        say("Thanks for talking to me! Bye!");
    }
}

