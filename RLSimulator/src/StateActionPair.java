
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
	
	public boolean equals(Object pobjOther) {
		if (pobjOther.getClass().equals(this.getClass()))
			return (this.compareTo((StateActionPair) pobjOther)==0);
		else
			return false;
	}
	
	public String toString() {
		return "(" + m_pstateCurrState.toString() + ", " + m_pactCurrAction.toString() + ")";
	}

	@Override
	public int compareTo(StateActionPair psapOtherSAP) {
		if (m_pstateCurrState.equals(psapOtherSAP.PStateGetState()))
			return m_pactCurrAction.compareTo(psapOtherSAP.PActGetAction());
		else
			return m_pstateCurrState.compareTo(psapOtherSAP.PStateGetState());
	}
}
