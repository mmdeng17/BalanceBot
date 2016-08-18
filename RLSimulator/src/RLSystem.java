import java.util.List;

public abstract class RLSystem {
	protected State m_pstateCurrState;
	protected int m_cNumStates;
	protected double m_dTimeStep;
	protected Actor m_pactorActor;
	protected List<State> m_prgstateAbsorbingStates;
	
	public RLSystem() {
		m_pstateCurrState = null;
		m_cNumStates = 0;
		m_dTimeStep = -1;
		m_pactorActor = null;
		m_prgstateAbsorbingStates = null;
	}

	public State pstateGetCurrState() {
		return m_pstateCurrState;
	}
	
	public boolean FCanTransition(Action pactCurrAction) {
		return false;
	}
	
	public void Transition(Action pactCurrAction) {
	}
	
	public double DGetRewardFcn(State pstatePrevState, Action pactCurrAction, State pStateCurrState) {
		return 0.0;
	}
}
