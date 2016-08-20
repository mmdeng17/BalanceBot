
public class StateActionPair {
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
}
