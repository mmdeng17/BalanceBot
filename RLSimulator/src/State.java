import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class State implements Comparable<State>{
	protected RLSystem m_prlsRLSystem;
	protected boolean m_fIsAbsorbing;
	protected boolean m_fIsValid;
	protected double[] m_rgdStateValues;
	
	public State() {
		m_prlsRLSystem = null;
		m_rgdStateValues = null;
		m_fIsAbsorbing = false;
		m_fIsValid = true;
	}
	
	public State(boolean fIsAbsorbing, boolean fIsValid) {
		m_prlsRLSystem = null;
		m_rgdStateValues = null;
		m_fIsAbsorbing = fIsAbsorbing;
		m_fIsValid = fIsValid;
	}
	
	public void Init() {
		if (m_prlsRLSystem==null)
			// TODO: create new exception
			throw new IllegalArgumentException("No valid system for state.");
		
		m_rgdStateValues = new double[m_prlsRLSystem.CGetNumDim()];
	}
	
	public void Init(RLSystem prlsRLSystem) {
		m_prlsRLSystem = prlsRLSystem;
		Init();
	}
	
	public RLSystem PrlsGetRLSystem() {
		return m_prlsRLSystem;
	}
	
	public boolean FIsAbsorbing() {
		return m_fIsAbsorbing;
	}
	
	public void SetIsAbsorbing(boolean fIsAbsorbing) {
		m_fIsAbsorbing = fIsAbsorbing;
	}
	
	public boolean FIsValid() {
		return m_fIsValid;
	}
	
	public void SetIsValid(boolean fIsValid) {
		m_fIsValid = fIsValid;
	}
	
	public double[] DGetStateValues() {
		return m_rgdStateValues;
	}
	
	public void SetStateValues(double[] rgdStateValues) {
		m_rgdStateValues = rgdStateValues;
	}
	
	public double DGetStateValueFromIDim(int iDim) {
		if (iDim<0 || iDim>=m_prlsRLSystem.CGetNumDim())
			throw new IllegalArgumentException("Dimension index out of bounds.");
		
		return m_rgdStateValues[iDim];
	}
	
	public void SetStateValueFromIDim(int iDim, double dStateValue) {
		if (iDim<0 || iDim>=m_prlsRLSystem.CGetNumDim())
			throw new IllegalArgumentException("Dimension index out of bounds.");
		
		m_rgdStateValues[iDim] = dStateValue;
	}

	public boolean equals (Object pobjOtherObject) {
		if (!pobjOtherObject.getClass().equals(this.getClass()))
			return false;
		return (this.compareTo((State) pobjOtherObject)==0);
	}

	@Override
	public int compareTo(State pstateOtherState) {
		for (int iDim = 0; iDim<m_prlsRLSystem.CGetNumDim(); iDim++) {
			double pobjValue1 = m_rgdStateValues[iDim];
			double pobjValue2 = pstateOtherState.DGetStateValueFromIDim(iDim);
			if (pobjValue1!=pobjValue2) {
				return (int) (pobjValue1-pobjValue2);
			}
		}
		return 0;
	}
	
	public String toString() {
		StringBuilder psbOutString = new StringBuilder();
		psbOutString.append("[");
		for (int iDim = 0; iDim<m_prlsRLSystem.CGetNumDim()-1; iDim++) {
			psbOutString.append(m_rgdStateValues[iDim]);
			psbOutString.append(", ");
		}
		psbOutString.append(m_rgdStateValues[m_prlsRLSystem.CGetNumDim()-1]);
		psbOutString.append("]");
		
		return psbOutString.toString();
	}
}
