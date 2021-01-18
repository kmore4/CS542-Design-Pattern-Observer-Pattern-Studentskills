package studentskills.mytree;

import studentskills.operation.Operation;

public interface SubjectI
{
	
	public void removeObserver(ObserverI o);
	
	public void notifyObservers(Operation op);

	void registerObserver(ObserverI o);
}
