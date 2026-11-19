package game;

public class Milo extends NPC {

    private int convoNumber;

    public Milo() {
        super("milo");
        convoNumber = 0;
    }

    @Override
    public void talk() {
        switch (convoNumber) {
            case 0:
                conversation0();
                break;
            case 1:
                conversation1();
                break;
            case 2:
                conversation2();
                break;
            case 3:
                conversation3();
                break;
            default:
                say("Thanks for talking to me! Bye!");
                break;
        }
    }

    private void conversation0() {
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
                convoNumber = 1;
                break;
            case 2:
                say("Because I have the biggest "
                        + "chair in the house!");
                convoNumber = 2;
                break;
            case 3:
                say("I had cookies, but I ate "
                        + "all of them. Sorry!");
                convoNumber = 3;
                break;
            default:
                say("I have no idea what "
                        + "you're talking about!");
                break;
        }
    }

    private void conversation1() {
        say("Are you going to help me find my glasses?");

        String[] responses = {
            "Yes, I will help you look!",
            "Where did you last see them?",
            "No, I have to go."
        };

        int option = getResponse(responses);

        switch (option) {
            case 1:
                say("Thank you! You're a great friend!");
                convoNumber = 4;
                break;
            case 2:
                say("I think I left them in the library!");
                convoNumber = 4;
                break;
            case 3:
                say("Okay, maybe next time!");
                convoNumber = 4;
                break;
            default:
                say("I didn't understand that.");
                break;
        }
    }

    private void conversation2() {
        say("Do you think I am the boss now?");

        String[] responses = {
            "Yes, you are definitely the boss!",
            "Maybe we can both be the boss.",
            "No, I am the boss!"
        };

        int option = getResponse(responses);

        switch (option) {
            case 1:
                say("I knew you would agree with me!");
                convoNumber = 4;
                break;
            case 2:
                say("Okay, we can share the title!");
                convoNumber = 4;
                break;
            case 3:
                say("No way! This is my house!");
                convoNumber = 4;
                break;
            default:
                say("I didn't understand that.");
                break;
        }
    }

    private void conversation3() {
        say("Are you still looking for snacks?");

        String[] responses = {
            "Yes, do you have anything else?",
            "Can we go buy some snacks?",
            "No thanks, I am not hungry anymore."
        };

        int option = getResponse(responses);

        switch (option) {
            case 1:
                say("I have some chips in the kitchen!");
                convoNumber = 4;
                break;
            case 2:
                say("Great idea! Let's go!");
                convoNumber = 4;
                break;
            case 3:
                say("Okay! More snacks for me!");
                convoNumber = 4;
                break;
            default:
                say("I didn't understand that.");
                break;
        }
    }
}


