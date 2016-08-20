
public class StateActionPair implements Comparable<StateActionPair>{
	protected State m_pstateCurrState;
	protected Action m_pactCurrAction;
	
	public StateActionPair() {
		m_pstateCurrState = null;
		m_pactCurrAction = null;
	}
	
	public StateActionPair(State pstateCurrState, Action pactCurrAction) {
		m_pstateCurrState = pstateCurrState;
		m_pactCurrAction = pactCurrAction;
	}
	
	public State PStateGetState() {
		return m_pstateCurrState;
	}
	
	public void SetState(State pstateCurrState) {
		m_pstateCurrState = pstateCurrState;
	}
	
	public Action PActGetAction() {
		return m_pactCurrAction;
	}
	
	public void SetAction(Action pactCurrAction) {
		m_pactCurrAction = pactCurrAction;
	}

	@Override
	public int compareTo(StateActionPair psapOtherSAP) {
		if (this.PStateGetState().equals(psapOtherSAP.PStateGetState()) && this.PActGetAction().equals(psapOtherSAP.PActGetAction()))
			return 0;
		else if (!this.PStateGetState().equals(psapOtherSAP.PStateGetState()))
			return this.PStateGetState().compareTo(psapOtherSAP.PStateGetState());
		else
			return this.PActGetAction().compareTo(psapOtherSAP.m_pactCurrAction);
	}
	
	public boolean equals(Object pobjOther) {
		if (pobjOther.getClass().equals(this.getClass()))
			return (this.compareTo((StateActionPair) pobjOther)==0);
		else
			return false;
	}
}
