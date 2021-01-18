
package studentskills.mytree;

import java.util.ArrayList;
import java.util.List;

import studentskills.operation.Operation;
import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;

public class StudentRecord implements SubjectI, ObserverI, Cloneable{
	
	private static final Operation Operation = null;
	private int bNumber;
	private String firstName, lastName, major;
	private double gpa;
	private List<String> skills;
	private List<StudentRecord> replicas;
	private StudentRecord left, right;
	private DebugLevel debugLevel;
	
	/**
	 * StudentRecord constructor stores values to appropriate
	 * fileds and calls myLogger
	 *
	 * @return 	NULL
	 * 
	 */
	public StudentRecord(int bn, String fn, String ln, String maj, double g, List<String> skill) {
		bNumber = bn;
		firstName = fn;
		lastName = ln;
		major = maj;
		gpa = g;
		skills = new ArrayList<String>();
		skills = skill;
		left = right = null;
		replicas = new ArrayList<StudentRecord>();
		this.debugLevel = DebugLevel.STUDENT_RECORD;
		MyLogger.writeMessage("in StudentRecord class constructor", debugLevel);
	}
	
	/**
	 * get BNumber of provided StudentRecord
	 *
	 * @return 	int  bNumber
	 * 
	 */
	public int getBNumber() {
		return bNumber;
	}
	
	
	/**
	 * set BNumber of provided StudentRecord
	 *
	 * @return 	NULL
	 * 
	 */
	public void setBNumber(int num) {
		bNumber = num;
	}
	
	
	/**
	 * get First Name of provided StudentRecord
	 *
	 * @return	String  firstName
	 * 
	 */
	public String getFirstName() {
		return firstName;
	}
	
	
	/**
	 * set First Name of provided StudentRecord
	 *
	 * @return	NULL
	 * 
	 */
	public void setFirstName(String name) {
		firstName = name;
	}
	
	
	/**
	 * get Last Name of provided StudentRecord
	 *
	 * @return	String  lastName
	 * 
	 */
	public String getLastName() {
		return lastName;
	}
	
	
	/**
	 * set Last Name of provided StudentRecord
	 *
	 * @return	NULL
	 * 
	 */
	public void setLastName(String name) {
		lastName = name;
	}
	
	
	/**
	 * get major of provided StudentRecord
	 *
	 * @return	String  major
	 * 
	 */
	public String getMajor() {
		return major;
	}
	
	
	/**
	 * set major of provided StudentRecord
	 *
	 * @return	NULL
	 * 
	 */
	public void setMajor(String maj) {
		major = maj;
	}
	
	
	/**
	 * get gpa of provided StudentRecord
	 *
	 * @return	double  gpa
	 * 
	 */
	public double getGpa() {
		return gpa;
	}
	
	
	/**
	 * set gpa of provided StudentRecord
	 *
	 * @return	NULL
	 * 
	 */
	public void setGpa(double g) {
		gpa = g;
	}
	
	
	/**
	 * get list of skills of provided StudentRecord
	 *
	 * @return	List<String>  skills
	 * 
	 */
	public List<String> getskills() {
		return skills;
	}
	
	
	/**
	 * set skills of provided StudentRecord
	 *
	 * @return	NULL
	 * 
	 */
	public void setSkill(List<String> s) {
		skills = s;
	}
	
	
	/**
	 * adds given skill to an existing skill(if does not exist 
	 * initially) list for given StudentRecord
	 *
	 * @return	Boolean
	 * 
	 */
	public Boolean addskill(String skillName) {
		if (skills.contains(skillName) == false) {
			skills.add(skillName);
			return true;
		}
		return false;
	}
	
	
	/**
	 * replaces existing old skill with new provided for 
	 * given StudentRecord
	 *
	 * @return	Boolean
	 * 
	 */
	public boolean updateskill(String origskill, String newskill) {
		if (skills.contains(origskill) == true) {
			int index = skills.indexOf(origskill);
			skills.set(index,newskill);
			return true;
		}
		return false;
	}
	
	
	/**
	 * get Left child node
	 *
	 * @return	StudentRecord
	 * 
	 */
	public StudentRecord getLeftNode() {
		return left;
	}
	
	
	/**
	 * set Left child node
	 *
	 * @return	null
	 * 
	 */
	public void setLeftNode(StudentRecord l) {
		this.left = l;
	}
	
	
	/**
	 * get Right child node
	 *
	 * @return	StudentRecord
	 * 
	 */
	public StudentRecord getRightNode() {
		return right;
	}
	
	
	/**
	 * set Right child node
	 *
	 * @return	null
	 * 
	 */
	public void setRightNode(StudentRecord r) {
		this.right = r;
	}


	@Override
	public void removeObserver(ObserverI o) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * adds observers to current observer's list
	 *
	 * @return	null
	 * 
	 */
	@Override
	public void registerObserver(ObserverI o) {
		// TODO Auto-generated method stub
		
		replicas.add((StudentRecord) o);
	}

	
	/**
	 * determine if the call on the observer is for INSERT or MODIFY.
	 * If it is an INSERT, use the latest values. for skills, 
	 * insert only adds to the existing set, and not delete any existing.
	 * If it is a MODIFY, the origValue replaced with modifiedValue.
	 *
	 * @return	null
	 * 
	 */
	@Override
	public void update(Operation op, int bn, String fn, String ln, String maj, double g, List<String> skill, StudentRecord left1, StudentRecord right1, List<StudentRecord> replica) {
		// TODO Auto-generated method stub
		if(op.equals(Operation.INSERT)) {
			this.bNumber = bn;
			this.firstName = fn;
			this.lastName = ln;
			this.major = maj;
			this.gpa = g;
			this.left = left1;
			this.right = right1;
			for (String temp : skill) {
				this.addskill(temp);
			}
		}
		if(op.equals(Operation.MODIFY)) {
			this.bNumber = bn;
			this.firstName = fn;
			this.lastName = ln;
			this.major = maj;
			this.gpa = g;
			this.left = left1;
			this.right = right1;
			for (String temp : skill) {
				this.addskill(temp);
			}
		}
	}

	
	/**
	 * notify all the observers
	 *
	 * @return	null
	 * 
	 */
	@Override
	public void notifyObservers(Operation op) {
		// TODO Auto-generated method stub
		int size =  replicas.size();
		for(int i = 0; i < size; i++)
		{
				ObserverI observer = (ObserverI)replicas.get(i);
				observer.update(op, this.bNumber, this.firstName, this.lastName, this.major, this.gpa, this.skills, this.left, this.right, this.replicas);
		}
	}
	
	
	/**
	 * clone a StudentRecord
	 *
	 * @return	StudentRecord Object
	 * 
	 */
	public StudentRecord clone()
	{
		return new StudentRecord(bNumber, firstName, lastName, major, gpa, skills);
	}
	
	
	/**
     * Default toString, needed for debugging here.
     * @return String
     */
    public String toString() {
        return ("debugLevel: " + debugLevel);
    }
}