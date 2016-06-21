package fr.clunven.docu.domain;

import java.util.Set;
import java.util.TreeSet;

/**
 * Check discrepancies between db and fs.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class ComparaisonFsDb {
    
    /** list1. */
    private Set < String > notInDatabase = new TreeSet<>();
    
    /** list2. */
    private Set < String > notOnFileSystem = new TreeSet<>();

    /** check validity. */
    public boolean isValid() {
        return notInDatabase.isEmpty() && notOnFileSystem.isEmpty();
    }
    
    /** {@inheritDoc} */
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("\r\n");
        sb.append("-------------------------------------");
        sb.append("\r\n");
        if (!notInDatabase.isEmpty()) {
            sb.append(" o Not found in database : ");
            for (String string : notInDatabase) {
                sb.append(string);
                sb.append(", ");
            }
            sb.append("\r\n");
        }
        
        if (!notOnFileSystem.isEmpty()) {
            sb.append(" o Not found on FileSystem : ");
            for (String string : notOnFileSystem) {
                sb.append(string);
                sb.append(", ");
            }
            sb.append("\r\n");
        } 
        sb.append("-------------------------------------");
        sb.append("\r\n");
        return sb.toString();
    }
    
    /**
     * Getter accessor for attribute 'notInDatabase'.
     *
     * @return
     *       current value of 'notInDatabase'
     */
    public Set<String> getNotInDatabase() {
        return notInDatabase;
    }

    /**
     * Setter accessor for attribute 'notInDatabase'.
     * @param notInDatabase
     * 		new value for 'notInDatabase '
     */
    public void setNotInDatabase(Set<String> notInDatabase) {
        this.notInDatabase = notInDatabase;
    }

    /**
     * Getter accessor for attribute 'notOnFileSystem'.
     *
     * @return
     *       current value of 'notOnFileSystem'
     */
    public Set<String> getNotOnFileSystem() {
        return notOnFileSystem;
    }

    /**
     * Setter accessor for attribute 'notOnFileSystem'.
     * @param notOnFileSystem
     * 		new value for 'notOnFileSystem '
     */
    public void setNotOnFileSystem(Set<String> notOnFileSystem) {
        this.notOnFileSystem = notOnFileSystem;
    }
   
}
