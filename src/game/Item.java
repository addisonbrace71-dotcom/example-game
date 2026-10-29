package game;
import java.io.Serializable;
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private boolean heavy;
    public Item(String n) {
        name = n;
        heavy = false;
    }
    public Item(String n, boolean h) {
        name = n;
        heavy = h;
    }
    public String getName() {
        return name;
    }
    public void setName(String n) {
        name = n;
    }
    public boolean isHeavy() {
        return heavy;
    }
    public void use() {
        Game.print("You cannot use this item.");
    }
    public String toString() {
        return name;
    }
}


	
	
	