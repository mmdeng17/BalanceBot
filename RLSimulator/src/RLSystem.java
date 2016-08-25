import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class RLSystem {
	protected int m_cDim;
	protected Set<Action> m_prgactActionSet;
	
	public RLSystem() {
		m_cDim = 0;
		m_prgactActionSet = null;
	}
	
	public void Init() {
		m_prgactActionSet = new HashSet<Action>();
	}
	
	public int CGetNumDim() {
		return m_cDim;
	}
	
	public void SetNumDim(int cDim) {
		m_cDim = cDim;
	}
	
	public boolean FTransition(RLActor pactorRLActor, Action pactCurrAction) {
		if (!this.FActionIsValid(pactCurrAction))
			return false;
		return FTransitionFcn(pactorRLActor, pactCurrAction);
	}
	
	protected boolean FTransitionFcn(RLActor pactorRLActor, Action pactCurrAction) {
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
