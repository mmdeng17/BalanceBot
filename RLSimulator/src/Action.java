
public class Action {
	protected String m_stName;
	
	public Action() {
		m_stName = null;
	}
	
	public Action(String stName) {
		m_stName = stName;
	}
	
	public String StGetName() {
		return m_stName;
	}
	
	public void SetName(String stName) {
		m_stName = stName;
	}
}
