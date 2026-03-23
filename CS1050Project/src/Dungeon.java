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

        if (currentRoomIndex == maxRooms - 1) {
            Room finalRoom = new Room();
            finalRoom.setEnemy(new BossEnemy("Ancient Dragon", 150, 20, 5, 500, "sprites/dragon"));
            rooms.add(finalRoom);
            System.out.println("\nAn intense heat radiates from the door ahead...");
        } else if (currentRoomIndex < maxRooms) {
            rooms.add(new Room());
        }
    }
}