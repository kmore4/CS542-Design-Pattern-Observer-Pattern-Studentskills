package studentskills.mytree;

import java.util.List;

import studentskills.operation.Operation;
import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;
import studentskills.validations.InvalidInputValueException;
import studentskills.util.Results;

public class TreeHelper{	
	
	private DebugLevel debugLevel;

	private StudentRecord root;
	private StudentRecord replicaNode1;
	private StudentRecord replicaNode2;
	final int treeID;
	
	/**
	 * StudentRecord constructor initialtes values to appropriate
	 * Fields and calls myLogger
	 *
	 * @return 	NULL
	 * 
	 */
	public TreeHelper(int num) {
		this.treeID = num;
		this.debugLevel = DebugLevel.TREE_HELPER;
		MyLogger.writeMessage("in TreeHelper class constructor", debugLevel);
		
		root = null;
		replicaNode1 = null;
		replicaNode2 = null;
	}
	
	
	/**
	 * get root StudentRecord/Node of Tree 1
	 *
	 * @return 	StudentRecord
	 * 
	 */
	public StudentRecord getRootTree1() {
		return root;
	}
	
	
	/**
	 * get root StudentRecord/Node of Tree 2
	 *
	 * @return 	StudentRecord
	 * 
	 */
	public StudentRecord getRootTree2() {
		return replicaNode1;
	}
	
	
	/**
	 * get root StudentRecord/Node of Tree 3
	 *
	 * @return 	StudentRecord
	 * 
	 */
	public StudentRecord getRootTree3() {
		return replicaNode2;
	}
	
	
	/**
	 * search for a StudentRecord in a tree 
	 * with respect to given root StudentRecord
	 *
	 * @return 	StudentRecord
	 * 
	 */
	private StudentRecord searchNode(StudentRecord root, int bNumber)
	{
		StudentRecord record = null;
		if(bNumber == root.getBNumber())
		{
			record = root;
		}
		
		else if(bNumber > root.getBNumber() && root.getRightNode() != null)
		{
			record = searchNode(root.getRightNode(), bNumber);
		}
		
		else if(bNumber < root.getBNumber() && root.getLeftNode() != null)
		{
			record = searchNode(root.getLeftNode(), bNumber);
		}
		return record;
	}
	
	
	/**
	 * inserts a StudentRecord in a tree and notify all observers
	 *
	 * @return 	NULL
	 * 
	 */
	public void insert(int bn, String fn, String ln, String maj, double g, List<String> skill, Results rs) throws CloneNotSupportedException
	{
		if(root == null)
		{
			if(skill.size()<=10)
			{
				root = new StudentRecord(bn, fn, ln, maj, g, skill);
			
				root.setSkill(skill);
				root.notifyObservers(Operation.INSERT);
				replicaNode1 = (StudentRecord) root.clone();
				replicaNode2 = (StudentRecord) root.clone();

				root.registerObserver(replicaNode1);
				root.registerObserver(replicaNode2);
				return;
				}
			else{
				rs.storeErr("a student cannot have more than 10 skills");
				return;
			}
		}
		StudentRecord current = searchNode(root,bn);
		if(current != null) {
			current.setFirstName(fn);
			current.setLastName(ln);
			current.setMajor(maj);
			current.setGpa(g);
			if(current.getskills().size() == 10) {
				rs.storeErr("Cannot add more than 10 skills");
				return;
			}
			else {
				int s = current.getskills().size();
				Boolean var = false;
				for(int i=0;i<skill.size() && s<10;i++) {
						var = current.addskill(skill.get(i));
						if(var == true)	s++;
				}
			current.notifyObservers(Operation.INSERT);
			return;
		}
		}
		current = root;
		StudentRecord parent = null;
		while(true){
			parent = current;
			if(bn == current.getBNumber()){
				if(skill.size() + current.getskills().size() > 10 || skill.size()>10) {
					rs.storeErr("Cannot add more than 10 skills");
					return;
				}
				else {
					for(int i=0;i<skill.size();i++) {
						current.addskill(skill.get(i));
					}
					current.notifyObservers(Operation.INSERT);
					return;
				}
				
				}
			if(bn < current.getBNumber()){				
				current = current.getLeftNode();
				if(current==null){
					if(skill.size()<=10)
					{
					StudentRecord replicaNode0 = new StudentRecord(bn, fn, ln, maj, g, skill);
					replicaNode0.setSkill(skill);
					parent.setLeftNode(replicaNode0);
					parent.notifyObservers(Operation.INSERT);
					StudentRecord replicaNode1 = replicaNode0.clone();
					StudentRecord replicaNode2 = replicaNode0.clone();
					parent.getLeftNode().registerObserver(replicaNode1);
					parent.getLeftNode().registerObserver(replicaNode2);
					return;
					}
					else {
						rs.storeErr("Cannot add more than 10 skills");
						return;
					}
				}
			}else{
				current = current.getRightNode();
				if(current==null){
					if(skill.size()<=10)
					{
					StudentRecord replicaNode0 = new StudentRecord(bn, fn, ln, maj, g, skill);
					parent.setRightNode(replicaNode0);
					parent.notifyObservers(Operation.INSERT);
					StudentRecord replicaNode1 = replicaNode0.clone();
					StudentRecord replicaNode2 = replicaNode0.clone();
					parent.getRightNode().registerObserver(replicaNode1);
					parent.getRightNode().registerObserver(replicaNode2);
					return;
					}
					else {
						rs.storeErr("Cannot add more than 10 skills");
						return;
					}
				}
			}
		}
		
	}
	
	
	/**
	 * modifies a StudentRecord in a tree and notify all observers
	 *
	 * @return 	NULL
	 * 
	 */
	public void modifyNode(int replicaNum, int bn, String oldVal, String newVal, Results rs) {
		// TODO Auto-generated method stub
		try {
		StudentRecord rootNode = null;
		if(replicaNum == 0)	rootNode = root;
		if(replicaNum == 1)	rootNode = replicaNode1;
		if(replicaNum == 2)	rootNode = replicaNode2;
		if(replicaNum > 2) {
			rs.storeErr("Replica number does not exist..!!");
			throw new InvalidInputValueException("Replica number does not exist..!!");
		}
			StudentRecord rec = searchNode(rootNode,bn);
			if(rec == null) {
				rs.storeErr("Student Record does not exist to modify..!!");
				return;
			}
			else {
				if(rec.getFirstName().equals(oldVal)) {
					rec.setFirstName(newVal);
				}
				else if (rec.getLastName().equals(oldVal)) {
					rec.setLastName(newVal);
				}
				else if( rec.getMajor().equals(oldVal)) {
					rec.setMajor(newVal);
				}
				else if(rec.updateskill(oldVal, newVal) == true) {
					//
				}
				
				else {
					rs.storeErr(oldVal + " Value need to be updated does not exist..!!");
					return;
				}
			}
			rec.notifyObservers(Operation.MODIFY);
		} catch(InvalidInputValueException e) {
			e.printStackTrace();
			System.exit(1);
		}	
	}
	
	
	/**
	 * Inorder tree traversal to display results in ascending order
	 *
	 * @return 	NULL
	 * 
	 */
	public static void printNodes(StudentRecord node, Results res)
	{
		if(node != null)
		{
			if(node.getLeftNode() != null)
				printNodes(node.getLeftNode(), res);
			
			res.store(node.getBNumber() + "\t" + node.getskills() + "\n");
			
			if(node.getRightNode() != null)
				printNodes(node.getRightNode(), res);
		}
	}
	
	
	/**
     * Default toString, needed for debugging here.
     * @return String
     */
    public String toString() {
        return ("Tree 1 Root: " + root + "\nTree 2 Root: " + replicaNode1 + "\nTree 3 Root: \" + replicaNode2");
    }

}