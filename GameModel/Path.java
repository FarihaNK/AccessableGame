package GameModel;

import java.io.Serializable;

/**
 * The Path class represents entries in a path table.
 * A path keeps track of information about an exit from a room.
 * This includes the direction of the exit and the room number.
 * If the path is blocked, the path keeps track of the
 * key required to move along this path.
 */
public class Path implements Serializable {

    /**
     * The direction of movement in the game.
     */
    private String direction;

    /**
     * The number of the room that this exit leads to.
     */
    private int destinationRoom;

    /**
     * The name of the object required to move along this path.
     */
    private String keyName;

    /**
     * This stores boolean to represent if the path is blocked.
     */
    private boolean isBlocked;

    /**
     * MotionTableEntry constructor.
     *
     * @param direction A string representation of a direction.
     * @param roomNumber A string representation of a room number.
     */
    public Path(String direction, String roomNumber) {
        this.direction = direction;
        this.destinationRoom = Integer.parseInt(roomNumber);
        this.keyName = null;
    }

    /**
     * MotionTableEntry constructor.
     *
     * @param direction A string representation of a direction.
     * @param roomNumber A string representation of a room number.
     * @param key A string representation of a key if the path is blocked.
     */
    public Path(String direction, String roomNumber, String key) {
        this.direction = direction;
        this.destinationRoom = Integer.parseInt(roomNumber);
        this.keyName = key;
        this.isBlocked = true;
    }

    /**
     * Returns the direction associated with this motion table entry.
     *
     * @return The direction to move in (e.g. "north", "south", etc.).
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * Returns the number of the room that this motion table entry leads to.
     *
     * @return The number of the destination room.
     */
    public int getDestinationRoom() {
        return this.destinationRoom;
    }

    /**
     * Returns the name of the object required to move along this path,
     * or null if no object is required.
     *
     * @return The name of the required object, or null if no object is required.
     */
    public String getKeyName() {
        return this.keyName;
    }

    /**
     * Returns if the path is blocked.
     *
     * @return True if the path is blocked, false otherwise.
     */
    public boolean getIsBlocked() {
        return this.isBlocked;
    }

    /**
     * Pretty print the Path
     */
    public void printPath() {
        System.out.println(this.direction + " " + this.destinationRoom + " " + this.keyName + " " + this.isBlocked);
    }
}
