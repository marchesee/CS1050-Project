import java.util.ArrayList;
import java.util.List;

public class Dungeon {

    private List<Room> rooms;
    private int currentRoomIndex;

    public Dungeon() {

        rooms = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            rooms.add(new Room());
        }

        currentRoomIndex = 0;
    }

    public Room getCurrentRoom() {
        return rooms.get(currentRoomIndex);
    }

    public void nextRoom() {

        currentRoomIndex++;

        if (currentRoomIndex >= rooms.size()) {
            currentRoomIndex = 0;
        }
    }
}