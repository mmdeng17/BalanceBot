import java.util.List;
import java.util.Set;

public abstract class RLSystem {
	protected State m_pstateCurrState;
	protected int m_cNumStates;
	protected double m_dTimeStep;
	protected Actor m_pactorActor;
	protected List<State> m_prgstateAbsorbingStates;
	protected Set<Action> m_prgactActionSet;
	
	public RLSystem() {
		m_pstateCurrState = null;
		m_cNumStates = 0;
		m_dTimeStep = -1;
		m_pactorActor = null;
		m_prgstateAbsorbingStates = null;
		m_prgactActionSet = null;
	}

	public State pstateGetCurrState() {
		return m_pstateCurrState;
	}
	
	public boolean FTransition(Action pactCurrAction) {
		if (!this.FActionIsValid(pactCurrAction))
			return false;
		if (!FTransitionFcn(pactCurrAction))
			return false;
		return true;
	}
	
	protected boolean FTransitionFcn(Action pactCurrAction) {
		return false;
	}
	
	public double DGetRewardFcn(State pstatePrevState, Action pactCurrAction, State pStateCurrState) {
		return 0.0;
	}
	
	public boolean FActionIsValid(Action pactCurrAction) {
		return m_prgactActionSet.contains(pactCurrAction);
	}
	
	public Set<Action> pactGetActionSet() {
		return m_prgactActionSet;
	}
	
	public void AddActionToSet(Action pactNewAction) {
		m_prgactActionSet.add(pactNewAction);
	}
}
