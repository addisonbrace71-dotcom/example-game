package game;
public class World {

	public static Room buildWorld() {
		Room livingRoom = new Room("Living Room");
		Room office = new Room("Office");
		Room kitchen = new Room("Kitchen");
		Room bedroom = new Room("Bedroom");
		Room attic = new Room("Attic");
		Room garage = new Room("Garage");
		

        livingRoom.setExit(0,kitchen);  // east
        livingRoom.setExit(1,office);   // west
        livingRoom.setExit(2, bedroom);  // north
        
        office.setExit(0, livingRoom);   // east
        

        kitchen.setExit(0,garage);      // east
        kitchen.setExit(1,livingRoom);  // west	
        

        bedroom.setExit(3,livingRoom);  // south
        bedroom.setExit(4,attic);       // up
        attic.setExit(5,bedroom);       // down
        

        garage.setExit(1,kitchen);      // west
        
		return livingRoom;
		
		
		}
	public static void main(String[] args) {
		Room startingRoom = buildWorld();
		System.out.println("World built successfully! Starting room: " + startingRoom.getName());
	}
}
