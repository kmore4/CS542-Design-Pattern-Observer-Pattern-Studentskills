
// FIXME: replace XYZ with the package name for the assignment
package studentskills.util;

public class MyLogger{

    public static enum DebugLevel { FILE_PROCESSOR, NONE, STUDENT_RECORD, RESULTS, TREE_HELPER, STUDENT_HELPER };

    private static DebugLevel debugLevel;


    // FIXME: Add switch cases for all the levels
    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
	case 5: debugLevel = DebugLevel.STUDENT_HELPER; break;
	case 4: debugLevel = DebugLevel.TREE_HELPER; break;
	case 3: debugLevel = DebugLevel.RESULTS; break;
	case 2: debugLevel = DebugLevel.STUDENT_RECORD; break;
	case 1: debugLevel = DebugLevel.FILE_PROCESSOR; break;
	default: debugLevel = DebugLevel.NONE; break;
	}
    }

    
    /**
	 * set debug value
	 *
	 * @return 	NULL
	 * 
	 */
    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    
    /**
	 * writes given message to console
	 *
	 * @return 	NULL
	 * 
	 */
    public static void writeMessage (String message, DebugLevel levelIn ) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    /**
     * Default toString, needed for debugging here.
     * @return String
     */
    
    public String toString() {
	return "The debug level has been set to the following " + debugLevel;
    }
}
