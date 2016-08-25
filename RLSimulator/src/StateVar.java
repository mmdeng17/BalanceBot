public class StateVar<T> {
	String m_stName;
	T m_ptValue;
	
	public StateVar() {
		m_stName = null;
		m_ptValue = null;
	}
	
	public StateVar(String stName, T ptValue) {
		m_stName = stName;
		m_ptValue = ptValue;
	}
	
	public String PstGetName() {
		return m_stName;
	}
	
	public void SetName(String stName) {
		m_stName = stName;
	}
	
	public T PtGetValue() {
		return m_ptValue;
	}
	
	public void SetValue(T ptValue) {
		m_ptValue = ptValue;
	}
	
	public boolean equals(Object pobjOther) {
		if (!this.getClass().equals(pobjOther.getClass()))
			return false;
		StateVar<T> psvOther = (StateVar<T>) pobjOther;
		if (!m_stName.equals(psvOther.PstGetName()))
			return false;
		return true;
		// return m_ptValue.equals(psvOther.PtGetValue());
	}
}
