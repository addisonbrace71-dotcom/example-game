package game;
import java.io.*;
import java.util.*;
public class Game {
    private static Room currentRoom = World.buildWorld();
    private static GameGUI gui;
    private static ArrayList<Item> inventory =
            new ArrayList<Item>();
    public static HashMap<String, String> roomDescriptions =
            new HashMap<String, String>();
    public static void print(Object obj) {
        if (gui != null) {
            gui.print(obj.toString());
        } else {
            System.out.println(obj);
        }
    }
    public static Room getCurrentRoom() {
        return currentRoom;
    }
    public static void addInventoryItem(Item item) {
        inventory.add(item);
    }
    public static boolean hasItem(Item item) {
        return inventory.contains(item);
 
    }
    public static void loadRoomDescriptions() {
        try (Scanner fileScan =
                new Scanner(new File("Description.txt"))) {
            roomDescriptions.clear();
            while (fileScan.hasNextLine()) {
                String roomName =
                        fileScan.nextLine().trim();
                if (!roomName.isEmpty()) {
                    String description = "";
                    while (fileScan.hasNextLine()) {
                        String line = fileScan.nextLine();
                        if (line.trim().equals("#")) {
                            break;
                        }
                        description += line + "\n";
                    }
                    roomDescriptions.put(
                            roomName, description.trim());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void takeItem(String name) {
        Item item = currentRoom.getItem(name);
        if (item == null) {
            print("There is no " + name + " here.");
        } else if (item.isHeavy()) {
            print("You can't lift the " + item + "!");
        } else {
            inventory.add(item);
            currentRoom.removeItem(name);
            print("You take the " + item
                    + " and carry it with you!");
        }
    }
    public static void showInventory() {
        if (inventory.isEmpty()) {
            print("You are carrying nothing.");
        } else {
            print("You are carrying:");
            for (Item item : inventory) {
                print(item);
            }
        }
    }
    public static Item findItem(String name) {
        Item item = currentRoom.getItem(name);
        if (item != null) {
            return item;
        }
        for (Item inventoryItem : inventory) {
            if (inventoryItem.getName()
                    .equalsIgnoreCase(name)) {
                return inventoryItem;
            }
        }
        return null;
    }
    public static void openItem(String name) {
        Item item = findItem(name);
        if (item == null) {
            print("There is no " + name + " here.");
        } else {
            item.open();
        }
    }
    public static void closeItem(String name) {
        Item item = findItem(name);
        if (item == null) {
            print("There is no " + name + " here.");
        } else {
            item.close();
        }
    }
    public static void readItem(String name) {
        Item item = findItem(name);
        if (item == null) {
            print("You cannot find the " + name + ".");
        } else {
            item.read();
        }
    }
    public static void useItem(String name) {
        Item item = findItem(name);
        if (item == null) {
            print("You do not have the " + name + ".");
        } else {
            item.use();
        }
    }
    public static void talkNPC (String name) {
    	NPC npc = currentRoom.getNPC(name);
    	if(npc == null){
    		print("There is no " + name + "here to talk to");
    	} else {
    		npc.talk();
    	}
    }
    public static void travel(String direction) {
        int exit = -1;
        switch (direction.toLowerCase()) {
            case "east":
                exit = 0;
                break;
            case "west":
                exit = 1;
                break;
            case "north":
                exit = 2;
                break;
            case "south":
                exit = 3;
                break;
            case "up":
                exit = 4;
                break;
            case "down":
                exit = 5;
                break;
            default:
                print("That is not a direction.");
                return;
        }
        Room nextRoom = currentRoom.getExit(exit);
        if (nextRoom == null) {
            print("You cannot go that way.");
        } else if (nextRoom.isLocked()) {
            print("That room is locked.");
        } else {
            currentRoom = nextRoom;
            print(currentRoom);
        }
    }
    public static void processCommand(String command) {
        command = command.trim().toLowerCase();
        if (command.isEmpty()) {
            return;
        }
        String[] parts = command.split("\\s+", 2);
        String action = parts[0];
        String itemName = "";
        if (parts.length > 1) {
            itemName = parts[1].trim();
        }
        switch (action) {
            case "look":
                print(currentRoom);
                break;
            case "inventory":
                showInventory();
                break;
            case "take":
                if (itemName.isEmpty()) {
                    print("Take what?");
                } else {
                    takeItem(itemName);
                }
                break;
            case "go":
                if (itemName.isEmpty()) {
                    print("Go where?");
                } else {
                    travel(itemName);
                }
                break;
            case "open":
                if (itemName.isEmpty()) {
                    print("Open what?");
                } else {
                    openItem(itemName);
                }
                break;
            case "close":
                if (itemName.isEmpty()) {
                    print("Close what?");
                } else {
                    closeItem(itemName);
                }
                break;
            case "read":
                if (itemName.isEmpty()) {
                    print("Read what?");
                } else {
                    readItem(itemName);
                }
                break;
            case "use":
                if (itemName.isEmpty()) {
                    print("Use what?");
                } else {
                    useItem(itemName);
                }
                break;
            case "talk":
            	if(itemName.startsWith("to")) {
            		itemName = itemName.substring(3).trim();
            	}
            	if(itemName.isEmpty()) {
            		print ("Talk to whom?");
            	} else {
            		talkNPC(itemName);
            	}
            	break;
            case "help":
                print("Commands:");
                print("look");
                print("inventory");
                print("take [item]");
                print("go [direction]");
                print("open [item]");
                print("close [item]");
                print("read [item]");
                print("use [item]");
                print("talk [character]");
                print("exit");
                break;
            case "exit":
                print("Thanks for playing!");
                break;
            default:
                print("I don't understand that command.");
                break;
        }
    }
    public static void runGame() {
        loadRoomDescriptions();
        Scanner input = new Scanner(System.in);
        print("Welcome to the game!");
        print(currentRoom);
        while (true) {
            System.out.print("> ");
            String command = input.nextLine();
            if (command.trim().equalsIgnoreCase("exit")) {
                print("Thanks for playing!");
                break;
            }
            processCommand(command);
        }
    }
    public static void main(String[] args) {
        runGame();
    }
}

