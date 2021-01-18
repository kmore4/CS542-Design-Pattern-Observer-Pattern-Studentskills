package studentskills.mytree;

import java.util.List;

import studentskills.operation.Operation;

public interface ObserverI{
	
	public void update(Operation op, int bn, String fn, String ln, String maj, double g, List<String> skill,
			StudentRecord left1, StudentRecord right1, List<StudentRecord> replicas);
}
