package studentskills.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import studentskills.mytree.StudentRecord;
import studentskills.mytree.TreeHelper;
import studentskills.util.MyLogger.DebugLevel;
import studentskills.validations.EmptyFileException;
import studentskills.validations.EmptyLineException;
import studentskills.validations.FileNotExistException;
import studentskills.validations.InvalidInputFormatException;

/**
 * @author Kasturi Tarachand More
 *
 */

/**
 * The StudentHelper processes each line of the file
 */

public class StudentHelper {
	
	private DebugLevel debugLevel;

	/**
	 * Constructor of StudentHelper class used only for debugging
	 *
	 * @param	NULL
	 * @return 	NULL	   
	 */
	public StudentHelper() {
		this.debugLevel = DebugLevel.STUDENT_HELPER;
		MyLogger.writeMessage("in StudentHelper class constructor", debugLevel);
	}
	
	/**
	 * check if given file exist or not
	 *
	 * @param	String		filename
	 * @return 	Boolean
	 * 
	 */
	private static boolean checkIfFileExists(String inputfile) {
		if (inputfile == null)
			return false;
		File file = new File(inputfile);
		return file.exists();
	}
	
	
	/**
	 * File existence checking
	 *
	 * @param	String		filename
	 * @return 	NULL
	 * @exception FileNotExistException
	 */
	public void checkFile(String string) throws FileNotExistException {
		try {
			if(!checkIfFileExists(string)){
				throw new FileNotExistException(string + " does not exist.!");
			}
		} catch (FileNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}	
	}
	
	
	/**
	 * Reads input file line by line and make appropriate calls
	 *
	 * @return 	NULL
	 * 
	 */
	public void insertFileProcessor(FileProcessor fp, TreeHelper bstTree, Results rs) {
		// TODO Auto-generated method stub
		String line = "";
		int count = 0;
		
		int bn = 0;
		String fn="",ln="",maj="";
		double g=0;
		try {
		
		while ((line = fp.poll()) != null)
		{
			count++;
			if (line.isEmpty()){
				rs.storeErr("Line" + count + " is NULL...!!!");
				throw new EmptyLineException("Line is NULL...!!!");
			}
			else {
				List<String> ski = new ArrayList<String>();
				if(line.contains(",")) {
					String[] output = line.split(",");
					if(output[0].contains(":")) {
						String[] out1 = output[0].split(":");
						bn = Integer.parseInt(out1[0]);
						if(out1[0].length() != 4 || bn<1000 || bn >= 10000) {
							rs.storeErr(out1[0] + " : Wrong BNumber..!!");
							throw new InvalidInputFormatException("Wrong BNumber..!!");
						}
						fn = out1[1];
						ln = output[1];
						g = Double.parseDouble(output[2]);
						maj = output[3];
						for (int i = 4; i<output.length;i++) {
							ski.add(output[i]);
						}
					}
					bstTree.insert(bn,fn,ln,maj,g,ski, rs);
					
				}
				else {
					rs.storeErr(line + " Wrong Input String Format..!!");
					throw new InvalidInputFormatException("Wrong Input String Format..!!");
				}
			}
		}
		if (count == 0) {
			rs.storeErr("File is Empty...!!!");
			throw new EmptyFileException("File is Empty...!!!");
		}
		
		} catch (InvalidInputFormatException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (EmptyLineException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (EmptyFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	
	/**
	 * Reads modification file line by line and make appropriate calls
	 *
	 * @return 	NULL
	 * 
	 */
	public void modifyFileProcessor(FileProcessor modFile, TreeHelper bstTree, Results rs) {
		// TODO Auto-generated method stub
		String line = "";
		int replicaNum = 0;
		int bn = 0;
		int count = 0;
		String[] out1 = null;
		try {
		while ((line = modFile.poll()) != null)
		{
			count++;
			if (line.isEmpty()){
				rs.storeErr("Line" + count + " is NULL...!!!");
				throw new EmptyLineException("Line is NULL...!!!");
			}
			else {
				if(line.contains(",")) {
					String[] output = line.split(",");
					replicaNum = Integer.parseInt(output[0]);
					bn = Integer.parseInt(output[1]);
					if(output[2].contains(":")) {
						out1 = output[2].split(":");
						if(out1.length == 1) {
							rs.storeErr(output[2] + " request for modified value is empty..!!");
						}
						else {
							bstTree.modifyNode(replicaNum,bn,out1[0],out1[1], rs);
						}
					}
				}
				else {
					rs.storeErr(line + " Wrong Input String Format in modify File..!!");
					throw new InvalidInputFormatException("Wrong Input String Format in modify File..!!");
				}
			}
		}
		if (count == 0) {
			rs.storeErr("Mod File is Empty...!!!");
			throw new EmptyFileException("Mod File is Empty...!!!");
		}
		} catch (InvalidInputFormatException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (EmptyLineException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (EmptyFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
	}


	/**
	 * calls the result object to generate output stream and display 
	 * output on console as well as store in a given file
	 *
	 * @return 	NULL
	 * 
	 */
	public void displayResult(TreeHelper bst, String out1, String out2, String out3, Results rs) {
		// TODO Auto-generated method stub
		rs.setOutputString("");
	
		StudentRecord sr1 = bst.getRootTree1();
		
		StudentRecord sr2 = bst.getRootTree2();
		
		StudentRecord sr3 = bst.getRootTree3();
		
		rs.setOutputFileName1(out1);
		bst.printNodes(sr1, rs);
		rs.writeToFile();
		rs.writeToStdout();
		
		rs.setOutputFileName1(out2);
		rs.setOutputString("");
		bst.printNodes(sr2, rs);
		rs.writeToFile();
		rs.writeToStdout();
		
		rs.setOutputFileName1(out3);
		rs.setOutputString("");
		bst.printNodes(sr3, rs);
		rs.writeToFile();
		rs.writeToStdout();
	}

	
	/**
     * Default toString, needed for debugging here.
     * @return null
     */
    public String toString() {
        return null;
    }


}