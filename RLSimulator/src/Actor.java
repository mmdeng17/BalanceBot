
public abstract class Actor {
	protected State m_pstateCurrState;
	protected double m_dTotalReward;
	protected RLSystem m_prlsCurrSystem;
	
	public Actor() {
		m_pstateCurrState = null;
		m_dTotalReward = 0;
		m_prlsCurrSystem = null;
	}
	
	public State PStateGetCurrState() {
		return m_pstateCurrState;
	}
	
	public void SetCurrState(State pstateCurrState) {
		m_pstateCurrState = pstateCurrState;
	}
	
	public RLSystem PRlsGetCurrSystem() {
		return m_prlsCurrSystem;
	}
	
	public void SetCurrSystem(RLSystem pRlsCurrSystem) {
		m_prlsCurrSystem = pRlsCurrSystem;
	}
	
	public double DGetTotalReward() {
		return m_dTotalReward;
	}
	
	public void AddReward(double dNewReward) {
		m_dTotalReward += dNewReward;
	}
	
}
