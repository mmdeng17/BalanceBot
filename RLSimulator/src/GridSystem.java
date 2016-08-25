import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GridSystem extends DiscreteRLSystem {
	protected int m_cXSize;
	protected int m_cYSize;
	protected Map<Point,State> m_mpptstateStateGrid;
	
	public GridSystem() {
		super();
		m_cXSize = 0;
		m_cYSize = 0;
	}
	
	public void Init(int cXSize,int cYSize) {
		int[] rgiASXCoord = {cXSize-1};
		int[] rgiASYCoord = {cYSize-1};
		Init(cXSize, cYSize, 0, 0, rgiASXCoord, rgiASYCoord);
	}
	
	public void Init(int cXSize, int cYSize, int iStartXCoord, int iStartYCoord, int[] rgiASXCoord, int[] rgiASYCoord) {
		super.Init();
		
		if (cXSize<0)
			throw new IllegalArgumentException("Invalid grid x size.");
		if (cYSize<0)
			throw new IllegalArgumentException("Invalid grid y size.");
		
		m_cXSize = cXSize;
		m_cYSize = cYSize;
		m_cDim = 2;
		m_mpptstateStateGrid = new HashMap<Point, State>();
		
		for (int iX = 0; iX<m_cXSize; iX++) {
			for (int iY = 0; iY<m_cYSize; iY++) {
				State pstateNewState = PStateCreateState(iX, iY);
				m_rgstateStates.put(pstateNewState, pstateNewState);
				m_mpptstateStateGrid.put(new Point(iX, iY), pstateNewState);
			}
		}
		
		for (int iASX = 0; iASX<rgiASXCoord.length; iASX++) {
			for (int iASY = 0; iASY<rgiASYCoord.length; iASY++) {
				State pstateAbsState = m_mpptstateStateGrid.get(new Point(rgiASXCoord[iASX], rgiASYCoord[iASY]));
				pstateAbsState.m_fIsAbsorbing = true;
			}
		}
				
		m_prgactActionSet.add(new Action("Up"));
		m_prgactActionSet.add(new Action("Down"));
		m_prgactActionSet.add(new Action("Left"));
		m_prgactActionSet.add(new Action("Right"));
	}
	
	public State PStateCreateState(int iX, int iY) {
		State pstateNewState = new State();
		pstateNewState.Init(this);
		pstateNewState.SetStateValueFromIDim(0, iX);
		pstateNewState.SetStateValueFromIDim(1, iY);
		return pstateNewState;
	}
	
	public int CGetXSize() {
		return m_cXSize;
	}
	
	public int CGetYSize() {
		return m_cYSize;
	}
	
	public State PstateGetStateAtXY(int iX, int iY) {
		return m_mpptstateStateGrid.get(new Point(iX, iY));
	}
	
	public boolean FTransition(RLActor pactorRLActor, Action pactCurrAction) {
		if (super.FTransition(pactorRLActor, pactCurrAction))
			return true;
		
		if (!this.FActionIsValid(pactCurrAction))
			return false;

		return FTransitionFcn(pactorRLActor, pactCurrAction);
	}
	
	protected boolean FTransitionFcn(RLActor pactorRLActor, Action pactCurrAction) {
		// we already know whether or not action is valid from FTransition()
		State pstateCurrState = pactorRLActor.PStateGetCurrState();
		int iXCoord = (int) pstateCurrState.DGetStateValueFromIDim(0);
		int iYCoord = (int) pstateCurrState.DGetStateValueFromIDim(1);
		int iXChange = 0;
		int iYChange = 0;
		
		switch(pactCurrAction.StGetName()) {
		case "Up":
			iYChange = -1;
			break;
		case "Down":
			iYChange = 1;
			break;
		case "Left":
			iXChange = -1;
			break;
		case "Right":
			iXChange = 1;
		}
		
		if ((iXCoord+iXChange)<0 || (iXCoord+iXChange)>=m_cXSize)
			return false;
		if ((iYCoord+iYChange)<0 || (iYCoord+iYChange)>=m_cYSize)
			return false;
		State pstateNextState = m_mpptstateStateGrid.get(new Point(iXCoord+iXChange, iYCoord+iYChange));
		if (!pstateNextState.FIsValid())
			return false;
		
		pactorRLActor.SetCurrState(pstateNextState);
		double dCurrRew = DGetRewardFcn(pstateCurrState, pactCurrAction, pstateNextState);
		pactorRLActor.AddReward(dCurrRew);
		pactorRLActor.IncrementCurrTime();
		pactorRLActor.UpdateQFcn(new StateActionPair(pstateCurrState, pactCurrAction), dCurrRew, pstateNextState);
		
		return true;
	}
	
	public double DGetRewardFcn(State pstatePrevState, Action pactCurrAction, State pstateCurrState) {
		double dCurrReward = -1;
		if (pstateCurrState.FIsAbsorbing())
			dCurrReward = 0;
		
		return dCurrReward;
	}
	
	public String toString() {
		return toString(0, 0);
	}
		
	public String toString(int iActorX, int iActorY) {
		StringBuilder psbOutSB = new StringBuilder();
		
		for (int iY = 0; iY<m_cYSize; iY++) {
			for (int iX = 0; iX<m_cXSize; iX++) {
				if (iX==iActorX && iY==iActorY)
					psbOutSB.append("|o");
				// TODO: fix this
				else if (m_mpptstateStateGrid.get(new Point(iX, iY)).FIsAbsorbing())
					psbOutSB.append("|x");
				else
				psbOutSB.append("| ");
			}
			if (iY==(m_cXSize-1))
				psbOutSB.append("|");
			else
				psbOutSB.append("|\n");
		}
		
		return psbOutSB.toString();
	}
	
}
