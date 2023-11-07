package GameModel;

import java.io.Serializable;
import java.util.*;

/**
 * The PathTable class keeps track of all the exits or locations
 * a player can go from a room.
 */
public class PathTable implements Serializable {

    /**
     * A list of all the paths that exists from a room.
     */
    public List<Path> pathTable;

    /**
     * PathTable constructor
     */
    public PathTable() {
        this.pathTable = new ArrayList<>();
    }

    /**
     * This method adds an exit or path
     * to the table.
     *
     * @param entry A Path which keeps track of a
     *             particular exit from a room.
     */
    void addDirection(Path entry) {
        pathTable.add(entry);
    }


    /**
     * Getter method for path table.
     *
     * @return this.pathTable
     */
    public List<Path> getDirection(){ return this.pathTable; }

    /**
     * Pretty print the table.
     */
    public void printTable(){
        for (Path m: this.pathTable) {
            m.printPath();
        }
    }

    /**
     * Determine if a given command is an option in the current table
     *
     * @param direction the option to assess
     * @return true if option exists in MotionTable, else false
     */
    public boolean optionExists(String direction) {
        for (Path m : this.pathTable) {
            if (m.getDirection().equals(direction)) return true;
        }
        return false;
    }

}