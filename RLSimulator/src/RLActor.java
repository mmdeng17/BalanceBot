import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

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
		m_mpsapdQFcn = new TreeMap<StateActionPair, Double>();
	}
	
	public void Restart() {
		super.Restart();
	}
	
	public void Reset() {
		super.Reset();
		m_mpsapdQFcn = new TreeMap<StateActionPair, Double>();
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
	
	public double DGetQFromSAP(StateActionPair psapCurrSAP) {
		if (!m_mpsapdQFcn.containsKey(psapCurrSAP))
			m_mpsapdQFcn.put(psapCurrSAP, m_dDefaultQ);
		return m_mpsapdQFcn.get(psapCurrSAP);
	}
	
	public Action PActGetPolicy() {
		List<Action> prgactMaxAction = new ArrayList<Action>();
		double dMaxQ = Double.NEGATIVE_INFINITY;
		double dCurrQ;
		Set<Action> prgactActionSet = m_prlsCurrSystem.pactGetActionSet();
		for (Iterator<Action> pitActIt = prgactActionSet.iterator(); pitActIt.hasNext(); ) {
			StateActionPair psapPossNextSAP = new StateActionPair(m_pstateCurrState,pitActIt.next());
			
			if (!m_mpsapdQFcn.containsKey(psapPossNextSAP))
				m_mpsapdQFcn.put(psapPossNextSAP, m_dDefaultQ);
			dCurrQ = m_mpsapdQFcn.get(psapPossNextSAP);
			
			if (dCurrQ>dMaxQ) {
				dMaxQ = dCurrQ;
				prgactMaxAction.clear();
				prgactMaxAction.add(psapPossNextSAP.PActGetAction());
			}
			else if (dCurrQ==dMaxQ) {
				prgactMaxAction.add(psapPossNextSAP.PActGetAction());
			}
		}
		
		// break ties randomly
		Random prandRandGen = new Random();
		int iRandAct = prandRandGen.nextInt(prgactMaxAction.size());
		return prgactMaxAction.get(iRandAct);
	}
	
	public void UpdateQFcn(StateActionPair psapCurrSAP, double dCurrReward, State pstateNextState) {
		double dCurrQ;
		if (!m_mpsapdQFcn.containsKey(psapCurrSAP))
			m_mpsapdQFcn.put(psapCurrSAP, m_dDefaultQ);
		dCurrQ = m_mpsapdQFcn.get(psapCurrSAP);
		
		StateActionPair psapNextSap = PsapGetNextSAP(pstateNextState);
		double dTDErr;
		if (!psapNextSap.PStateGetState().FIsAbsorbing())
			dTDErr = dCurrReward+m_dGamma*m_mpsapdQFcn.get(psapNextSap)-m_mpsapdQFcn.get(psapCurrSAP);
		else
			dTDErr = dCurrReward-m_mpsapdQFcn.get(psapCurrSAP);
		
		double dUpdateQ = dCurrQ+m_dAlpha*dTDErr;
		m_mpsapdQFcn.put(psapCurrSAP, dUpdateQ);
	}
	
	protected StateActionPair PsapGetNextSAP(State pstateNextState) {
		double dCurrRew = m_dDefaultQ;
		double dMaxRew = Double.NEGATIVE_INFINITY;
		List<StateActionPair> prgsapMaxSAPs = new ArrayList<StateActionPair>();
		StateActionPair psapNextSAP = null;
		Set<Action> prgactActionSet = m_prlsCurrSystem.pactGetActionSet();
		
		for (Iterator<Action> pitActIt = prgactActionSet.iterator(); pitActIt.hasNext(); ) {
			StateActionPair psapPossNextSAP = new StateActionPair(pstateNextState,pitActIt.next());
			if (!m_mpsapdQFcn.containsKey(psapPossNextSAP))
				m_mpsapdQFcn.put(psapPossNextSAP, m_dDefaultQ);
			dCurrRew = m_mpsapdQFcn.get(psapPossNextSAP);
			
			if (dCurrRew>dMaxRew) {
				dMaxRew = dCurrRew;
				prgsapMaxSAPs.clear();
				prgsapMaxSAPs.add(psapPossNextSAP);
			}
			else if (dCurrRew==dMaxRew) {
				prgsapMaxSAPs.add(psapPossNextSAP);
			}
		}
		
		// break ties randomly
		Random prandRandGen = new Random();
		int iRandAct = prandRandGen.nextInt(prgsapMaxSAPs.size());
		return prgsapMaxSAPs.get(iRandAct);
	}
	
	public String toString() {
		StringBuilder psbToString = new StringBuilder();
		psbToString.append("Actor settings:  ");
		psbToString.append("Alpha: ");
		psbToString.append(m_dAlpha);
		psbToString.append("  Gamma: ");
		psbToString.append(m_dGamma);
		psbToString.append("  Default Q: ");
		psbToString.append(m_dDefaultQ);
		psbToString.append("\nCurr State: ");
		psbToString.append(m_pstateCurrState.toString());
		return psbToString.toString();
	}
	
//	public String StPrintQFcn() {
//		StringBuilder psbQFcn = new StringBuilder();
//		RLSystem prlsCurrSystem = m_prlsCurrSystem;
//		State[] prgstateStateList = prlsCurrSystem.PrgstateGetStates().toArray(new State[prlsCurrSystem.CGetNumStates()]);
//		for (int iState = 0; iState<prgstateStateList.length; iState++) {
//			State pstateCurrState = prgstateStateList[iState];
//			for (Iterator<Action> pitActIt = prlsCurrSystem.pactGetActionSet().iterator(); pitActIt.hasNext(); ) {
//				Action pactNextAct = pitActIt.next();
//				StateActionPair psapCurrSAP = new StateActionPair(pstateCurrState,pactNextAct);
//				psbQFcn.append(psapCurrSAP.toString());
//				psbQFcn.append(": ");
//				
//				if (!m_mpsapdQFcn.containsKey(psapCurrSAP))
//					m_mpsapdQFcn.put(psapCurrSAP, m_dDefaultQ);
//					
//				psbQFcn.append(this.DGetQFromSAP(psapCurrSAP));
//				psbQFcn.append("\n");
//			}
//		}
//		
//		return psbQFcn.toString();
//	}
}
