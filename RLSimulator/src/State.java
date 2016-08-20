
public abstract class State implements Comparable<State> {
	protected boolean m_fIsAbsorbing;
	
	public boolean FIsAbsorbing() {
		return m_fIsAbsorbing;
	}
	
	public void SetIsAbsorbing(boolean fIsAbsorbing) {
		m_fIsAbsorbing = fIsAbsorbing;
	}
}
