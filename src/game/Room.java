package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Room implements Serializable {
	private static final long serialVersionUID = 1L;
	public static ArrayList<Room> rooms = new ArrayList<Room>();
	private String name;
	private Room[] exits;
	private HashMap<String, Item> items;
	private boolean locked;

	public Room(String n) {
		name = n;
		exits = new Room[6];
		items = new HashMap<String, Item>();
		locked = false;
		rooms.add(this);
	}

	public String getName() {
		return name;
	}

	public boolean getLocked() {
		return locked;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean l) {
		locked = l;
	}

	public String getDescription() {
		return Game.roomDescriptions.get(name);
	}

	public void setDescription(String d) {
		Game.roomDescriptions.put(name, d);
	}

	public void setExit(int direction, Room room) {
		exits[direction] = room;
	}

	public Room getExit(int direction) {
		return exits[direction];
	}

	public void addItem(Item item) {
		items.put(item.getName(), item);
	}

	public Item getItem(String name) {
		return items.get(name);
	}

	public Item removeItem(String name) {
		return items.remove(name);
	}

	public String getItemNames() {
		if (items.isEmpty()) {
			return "none";
		}
		return String.join(", ", items.keySet());
	}

	public String toString() {
		return name + "\n" + getDescription() + "\nItems here: " + getItemNames();
	}
}
