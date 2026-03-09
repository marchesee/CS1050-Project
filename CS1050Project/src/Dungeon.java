import java.util.ArrayList;
import java.util.List;

public class Dungeon {

    private List<Room> rooms;
    private int currentRoomIndex;
    private final int maxRooms = 10; // Max of 10 rooms in a dungeon

    public Dungeon() {
        rooms = new ArrayList<>();
        rooms.add(new Room());
        currentRoomIndex = 0;
    }

    public Room getCurrentRoom() {
        return rooms.get(currentRoomIndex);
    }

    public void nextRoom() {

        currentRoomIndex++;

        if (currentRoomIndex >= rooms.size()) {
            if (rooms.size() < maxRooms) {
            	rooms.add(new Room());
            } else {
            	// End of dungeon
            	// Something after the dungeon?
            	System.out.println("You have completed the dungeon! End your journey here or advance into the unknown...");
            }
        }
    }
}