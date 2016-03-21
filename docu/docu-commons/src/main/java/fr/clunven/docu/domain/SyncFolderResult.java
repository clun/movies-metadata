package fr.clunven.docu.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Check discrepancies between db and fs.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class SyncFolderResult {
    
    /** list1. */
    private List < String > notInDatabase = new ArrayList<>();
    
    /** list2. */
    private List < String > notOnFileSystem = new ArrayList<>();

    /** check validity. */
    public boolean isValid() {
        return notInDatabase.isEmpty() && notOnFileSystem.isEmpty();
    }
    
    /** {@inheritDoc} */
    public String toString() {
        StringBuilder sb = new StringBuilder();
       
        if (!notInDatabase.isEmpty()) {
            sb.append("=> Not In Database : ");
            for (String string : notInDatabase) {
                sb.append(string);
                sb.append(",");
            }
            sb.append("\r\n");
        }
        
        if (!notOnFileSystem.isEmpty()) {
            sb.append("=> Not On FileSystem : ");
            for (String string : notOnFileSystem) {
                sb.append(string);
                sb.append("\r\n");
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }
    
    
    /**
     * Getter accessor for attribute 'notInDatabase'.
     *
     * @return
     *       current value of 'notInDatabase'
     */
    public List<String> getNotInDatabase() {
        return notInDatabase;
    }

    /**
     * Setter accessor for attribute 'notInDatabase'.
     * @param notInDatabase
     * 		new value for 'notInDatabase '
     */
    public void setNotInDatabase(List<String> notInDatabase) {
        this.notInDatabase = notInDatabase;
    }

    /**
     * Getter accessor for attribute 'notOnFileSystem'.
     *
     * @return
     *       current value of 'notOnFileSystem'
     */
    public List<String> getNotOnFileSystem() {
        return notOnFileSystem;
    }

    /**
     * Setter accessor for attribute 'notOnFileSystem'.
     * @param notOnFileSystem
     * 		new value for 'notOnFileSystem '
     */
    public void setNotOnFileSystem(List<String> notOnFileSystem) {
        this.notOnFileSystem = notOnFileSystem;
    }

   
   
}
