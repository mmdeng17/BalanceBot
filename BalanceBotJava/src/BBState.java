
public class BBState extends State {
	private double m_dXPos;
	private double m_dYPos;
	private double m_dVel;
	private double m_dAcc;
	
	public BBState() {
		m_dXPos = 0;
		m_dYPos = 0;
		m_dVel = 0;
		m_dAcc = 0;
	}
	
	public BBState(double[] rgdStateRg) {
		m_dXPos = rgdStateRg[0];
		m_dYPos = rgdStateRg[1];
		m_dVel = rgdStateRg[2];
		m_dAcc = rgdStateRg[3];
	}
	
	double dGetXPos() {
		return m_dXPos;
	}
	
	double dGetYPos() {
		return m_dYPos;
	}
	
	double dGetVel() {
		return m_dVel;
	}
	
	double dGetAcc() {
		return m_dAcc;
	}
	
	double[] rgdGetStateAsRg() {
		double[] out = new double[4];
		out[0] = m_dXPos; out[1] = m_dYPos; out[2] = m_dVel; out[3] = m_dAcc;
		return out;
	}
	
	void SetStateFromRg(double[] rgdStateRg) {
		m_dXPos = rgdStateRg[0];
		m_dYPos = rgdStateRg[1];
		m_dVel = rgdStateRg[2];
		m_dAcc = rgdStateRg[3];
	}
}
