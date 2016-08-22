import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RLActor extends Actor {
	protected double m_dGamma;
	protected double m_dAlpha;
	protected double m_dDefaultQ;
	protected Map<StateActionPair, Double> m_mpsapdQFcn;
	
	public RLActor() {
		super();
		m_dGamma = 0;
		m_dAlpha = 0;
		m_dDefaultQ = 0;
		m_mpsapdQFcn = null;
	}
	
	public void Init() {
		m_mpsapdQFcn = new HashMap<StateActionPair, Double>();
	}
	
	public double DGetGamma() {
		return m_dGamma;
	}
	
	public void SetGamma(double dGamma) {
		m_dGamma = dGamma;
	}
	
	public double DGetAlpha() {
		return m_dAlpha;
	}
	
	public void SetAlpha(double dAlpha) {
		m_dAlpha = dAlpha;
	}
	
	public double DGetDefaultQ() {
		return m_dDefaultQ;
	}
	
	public void SetDefaultQ(double dDefaultQ) {
		m_dDefaultQ = dDefaultQ;
	}
	
	public Map<StateActionPair, Double> PMpsapdGetQFcn() {
		return m_mpsapdQFcn;
	}
	
	public Action PActGetPolicy() {
		Action pactMaxAction = null;
		double dMaxQ = Double.NEGATIVE_INFINITY;
		double dCurrQ;
		Set<Action> prgactActionSet = this.PRlsGetCurrSystem().pactGetActionSet();
		for (Iterator<Action> pitActIt = prgactActionSet.iterator(); pitActIt.hasNext(); ) {
			StateActionPair psapPossNextSAP = new StateActionPair(this.PStateGetCurrState(),pitActIt.next());
			
			if (m_mpsapdQFcn.containsKey(psapPossNextSAP))
				dCurrQ = m_mpsapdQFcn.get(psapPossNextSAP);
			else {
				m_mpsapdQFcn.put(psapPossNextSAP, m_dDefaultQ);
				dCurrQ = m_dDefaultQ;
			}
			
			if (dCurrQ>dMaxQ) {
				dMaxQ = dCurrQ;
				pactMaxAction = psapPossNextSAP.PActGetAction();
			}
		}
		
		return pactMaxAction;
	}
	
	public void UpdateQFcn(StateActionPair psapCurrSAP, double dCurrReward, State pstatenextState) {
		double dCurrQ;
		if (m_mpsapdQFcn.containsKey(psapCurrSAP))
			dCurrQ = m_mpsapdQFcn.get(psapCurrSAP);
		else
			dCurrQ = m_dDefaultQ;
		
		StateActionPair psapNextSap = PsapGetNextSAP(pstatenextState);
		double dTDErr = dCurrReward+m_dGamma*m_mpsapdQFcn.get(psapNextSap)-m_mpsapdQFcn.get(psapCurrSAP);
		m_mpsapdQFcn.put(psapCurrSAP, dCurrQ+m_dAlpha*dTDErr);
	}
	
	protected StateActionPair PsapGetNextSAP(State pstateNextState) {
		double dCurrRew = m_dDefaultQ;
		double dMaxRew = Double.NEGATIVE_INFINITY;
		StateActionPair psapNextSAP = null;
		Set<Action> prgactActionSet = this.PRlsGetCurrSystem().pactGetActionSet();
		for (Iterator<Action> pitActIt = prgactActionSet.iterator(); pitActIt.hasNext(); ) {
			StateActionPair psapPossNextSAP = new StateActionPair(pstateNextState,pitActIt.next());
			if (m_mpsapdQFcn.containsKey(psapPossNextSAP))
				dCurrRew = m_mpsapdQFcn.get(psapPossNextSAP);
			else {
				m_mpsapdQFcn.put(psapPossNextSAP, m_dDefaultQ);
				dCurrRew = m_dDefaultQ;
			}
			
			if (dCurrRew>dMaxRew) {
				dMaxRew = dCurrRew;
				psapNextSAP = psapPossNextSAP;
			}
		}
		
		return psapNextSAP;
	}

	@Override
	public int compareTo(Actor pactorOther) {
		return -1;
	}
	
	public boolean equals(Object pobjOther) {
		if (this.getClass().equals(pobjOther.getClass()))
			return this.compareTo((Actor) pobjOther)==0;
		else
			return false;
	}
}
