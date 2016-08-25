
public abstract class Actor {
	protected RLSystem m_prlsCurrSystem;
	protected State m_pstateCurrState;
	protected double m_dTotalReward;
	protected double m_dCurrTime;
	protected double m_dTimeStep;
	
	public Actor() {
		m_prlsCurrSystem = null;
		m_pstateCurrState = null;
		m_dTotalReward = 0;
		m_dCurrTime = 0;
		m_dTimeStep = 1;
	}
	
	public void Restart() {
		m_dCurrTime = 0;
	}
	
	public void Reset() {
		m_dTotalReward = 0;
		m_dCurrTime = 0;
	}
	
	public double DGetCurrTime() {
		return m_dCurrTime;
	}
	
	public double DGetTimeStep() {
		return m_dTimeStep;
	}
	
	public void SetTimeStep(double dTimeStep) {
		m_dTimeStep = dTimeStep;
	}
	
	public void IncrementCurrTime() {
		m_dCurrTime += m_dTimeStep;
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
