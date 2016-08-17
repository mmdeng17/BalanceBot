
public abstract class RLSystem {
	protected State m_currState;
	private double m_timeStep;
	
	public State pstateGetCurrState() {
		return m_currState;
	}
	
	double dTransition(Action pactCurrAction) {
		return 0.0;
	}
}
