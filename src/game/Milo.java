package game;
public class Milo extends NPC {
    public Milo() {
        super("milo");
    }
    @Override
    public void talk() {
        say("Welcome to my house! I'm Milo, "
            + "the boss around here.");
        String[] responses = {
            "Can you help me find something?",
            "Why do you think you're the boss?",
            "Do you have any snacks?"
        };
        int option = getResponse(responses);
        switch (option) {
            case 1:
                say("I could help, but I lost "
                    + "my glasses three days ago!");
                break;
            case 2:
                say("Because I have the biggest "
                    + "chair in the house!");
                break;
            case 3:
                say("I had cookies, but I ate "
                    + "all of them. Sorry!");
                break;
            default:
                say("I have no idea what "
                    + "you're talking about!");
                break;
        }
    }
}

