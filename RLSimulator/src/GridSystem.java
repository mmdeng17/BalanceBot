
public class GridSystem extends RLSystem {
	private GridState[][] m_prggsStateGrid;
	private boolean[][] m_prgfStateValidGrid;
	private int m_cXSize;
	private int m_cYSize;
	
	public GridSystem() {
		super();
		m_prggsStateGrid = null;
		m_prgfStateValidGrid = null;
		m_cXSize = 0;
		m_cYSize = 0;
	}
	
	public void Init(int cXSize,int cYSize) {
		int[] rgiASXCoord = {cXSize-1};
		int[] rgiASYCoord = {cYSize-1};
		Init(cXSize, cYSize, 0, 0, rgiASXCoord, rgiASYCoord);
	}
	
	public void Init(int cXSize, int cYSize, int iStartXCoord, int iStartYCoord, int[] rgiASXCoord, int[] rgiASYCoord) {
		if (cXSize<0)
			throw new IllegalArgumentException("Invalid grid x size.");
		if (cYSize<0)
			throw new IllegalArgumentException("Invalid grid y size.");
		
		m_cXSize = cXSize;
		m_cYSize = cYSize;
		this.m_cNumStates = m_cXSize*m_cYSize;
		m_prggsStateGrid = new GridState[m_cXSize][m_cYSize];
		m_prgfStateValidGrid = new boolean[m_cXSize][m_cYSize];
		
		for (int iX = 0; iX<m_cXSize; iX++) {
			for (int iY = 0; iY<m_cYSize; iY++) {
				m_prggsStateGrid[iX][iY] = new GridState(iX,iY);
				m_prgfStateValidGrid[iX][iY] = true;
			}
		}
		
		for (int iASX = 0; iASX<rgiASXCoord.length; iASX++) {
			for (int iASY = 0; iASY<rgiASYCoord.length; iASY++) {
				m_prggsStateGrid[iASX][iASY].m_fIsAbsorbing = true;
				m_prgstateAbsorbingStates.add(m_prggsStateGrid[iASX][iASY]);
			}
		}
		
		m_pstateCurrState = m_prggsStateGrid[iStartXCoord][iStartYCoord];
		
		m_pactorActor = new GridActor((GridState) m_pstateCurrState);
		
		m_prgactActionSet.add(new Action("Up"));
		m_prgactActionSet.add(new Action("Down"));
		m_prgactActionSet.add(new Action("Left"));
		m_prgactActionSet.add(new Action("Right"));
	}
	
	public boolean FTransition(Action pactCurrAction) {
		if (super.FTransition(pactCurrAction))
			return true;
		
		// super method will check if action is valid, so here action should be valid
		return FTransitionFcn(pactCurrAction);
	}
	
	protected boolean FTransitionFcn(Action pactCurrAction) {
		// we already know whether or not action is valid from FTransition()
		State pstateCurrState = m_pstateCurrState;
		int iXCoord = ((GridState) m_pstateCurrState).IGetXCoord();
		int iYCoord = ((GridState) m_pstateCurrState).IGetYCoord();
		int iXChange = 0;
		int iYChange = 0;
		
		switch(pactCurrAction.StGetName()) {
		case "Up":
			iYChange = -1;
		case "Down":
			iYChange = 1;
		case "Left":
			iXChange = -1;
		case "Right":
			iXChange = 1;
		}
		
		if ((iXCoord+iXChange)<0 || (iXCoord+iXChange)>m_cXSize)
			return false;
		if ((iYCoord+iYChange)<0 || (iYCoord+iYChange)>m_cYSize)
			return false;
		if (!m_prgfStateValidGrid[iXCoord+iXChange][iYCoord+iYChange])
			return false;
			
		m_pstateCurrState = m_prggsStateGrid[iXCoord+iXChange][iYCoord+iYChange];
		State pstateNextState = m_pstateCurrState;
		
		m_pactorActor.SetCurrState(m_pstateCurrState);
		m_pactorActor.AddReward(DGetRewardFcn(pstateCurrState, pactCurrAction, pstateNextState));
		return true;
	}
	
	public double DGetRewardFcn(State pstatePrevState, Action pactCurrAction, State pstateCurrState) {
		double dCurrReward = 0.0;
		for (int iAS = 0; iAS<m_prgstateAbsorbingStates.size(); iAS++) {
			if (((GridState) pstateCurrState).equals((GridState) m_prgstateAbsorbingStates.get(iAS)))
				dCurrReward = 1;
		}
		
		return dCurrReward;
	}
}
