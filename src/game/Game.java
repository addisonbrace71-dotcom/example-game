package game;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
public class Game {
    private static Room currentRoom = World.buildWorld () ;
    private static GameGUI gui;
    private static ArrayList<Item> inventory =
            new ArrayList<Item>();
    public static HashMap<String, String> roomDescriptions =
            new HashMap<String, String>();
    public static void print(Object obj) {
        gui.print(obj.toString());
    }
    public static void loadRoomDescriptions() {
        try (Scanner fileScan =
                new Scanner(new File("Description.txt"))) {
            roomDescriptions.clear();
            while (fileScan.hasNextLine()) {
                String roomName = fileScan.nextLine().trim();
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
    public static void travel(int direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            print("You can't go that way.");
        } else {
            currentRoom = nextRoom;
            print(currentRoom);
        }
    }
    public static void saveGame() {
        File saveFile = new File("concert.sav");
        try (ObjectOutputStream outStream =
                new ObjectOutputStream(
                        new FileOutputStream(saveFile))) {
        	
            outStream.writeObject(currentRoom);
            outStream.writeObject(inventory);
            outStream.writeObject(Room.rooms);
            
            outStream.writeObject(roomDescriptions);
            print("Game saved!");
        } catch (IOException ex) {
            print("Could not save the game: "
                    + ex.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public static void loadGame() {
        File saveFile = new File("concert.sav");
        try (ObjectInputStream inStream =
                new ObjectInputStream(
                        new FileInputStream(saveFile))) {
           
            Room savedRoom =
                    (Room) inStream.readObject();
            ArrayList<Item> savedInventory =
                    (ArrayList<Item>) inStream.readObject();
            ArrayList<Room> savedRooms =
                    (ArrayList<Room>) inStream.readObject();
            HashMap<String, String> savedDescriptions =
                    (HashMap<String, String>)
                            inStream.readObject();
           
            currentRoom = savedRoom;
            inventory = savedInventory;
            Room.rooms = savedRooms;
            roomDescriptions = savedDescriptions;
        } catch (FileNotFoundException ex) {
            print("No saved game was found.");
            return;
        } catch (IOException | ClassNotFoundException
                | ClassCastException ex) {
            print("Could not load the game: "
                    + ex.getMessage());
            return;
        }
        print("Game loaded!");
        print(currentRoom);
    }
    public static void processCommand(String command) {
        command = command.trim().toLowerCase(Locale.ROOT);
        if (command.equals("take")) {
            print("What do you want to take?");
        } else if (command.startsWith("take ")) {
            String name = command.substring(5).trim();
            if (name.startsWith("the ")) {
                name = name.substring(4).trim();
            }
            if (name.isEmpty()) {
                print("What do you want to take?");
            } else {
                takeItem(name);
            }
        } else if (command.equals("inventory")) {
            showInventory();
        } else if (command.equals("look")) {
            print(currentRoom);
        } else if (command.equals("east")) {
            travel(0);
        } else if (command.equals("west")) {
            travel(1);
        } else if (command.equals("north")) {
            travel(2);
        } else if (command.equals("south")) {
            travel(3);
        } else if (command.equals("up")) {
            travel(4);
        } else if (command.equals("down")) {
            travel(5);
        } else if (command.equals("save")) {
            saveGame();
        } else if (command.equals("load")) {
            loadGame();
        } else if (command.equals("help")) {
            print("Commands:");
            print("east, west, north, south, up, down");
            print("take item OR take the item");
            print("inventory, look, save, load, help, exit");
        } else {
            print("I don't understand that command.");
        }
    }
    public static void runGame() {
        Scanner input = new Scanner(System.in);
  
        Room.rooms.clear();
        inventory.clear();
        roomDescriptions.clear();
        currentRoom = World.buildWorld();
        print("Welcome to the game!");
        print("Type help to see the commands.");
        print(currentRoom);
        while (true) {
            print("\nEnter a command:");
            if (!input.hasNextLine()) {
                break;
            }
            String command = input.nextLine()
                    .trim().toLowerCase(Locale.ROOT);
            if (command.equals("exit")) {
                print("Thanks for playing!");
                break;
            }
            processCommand(command);
        }
    }
    public static void main(String[] args) {
    	loadRoomDescriptions();
    	 gui = new GameGUI();
    	 print (currentRoom) ;
    	 
    }
	public static Room getCurrentRoom() {
		return currentRoom;
	}
}


