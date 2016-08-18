
public class GridState extends State {
	private int m_iXCoord;
	private int m_iYCoord;
	
	public GridState() {
		super();
		m_iXCoord = 0;
		m_iYCoord = 0;
	}
	
	public GridState(int iXCoord, int iYCoord) {
		if (iXCoord<0)
			throw new IllegalArgumentException("Invalid x coordinate.");
		if (iYCoord<0)
			throw new IllegalArgumentException("Invalid y coordinate.");
	
		m_iXCoord = iXCoord;
		m_iYCoord = iYCoord;
	}
	
	public int IGetXCoord() {
		return m_iXCoord;
	}
	
	public int IGetYCoord() {
		return m_iYCoord;
	}
	
	public int compareTo(GridState pstateOtherState) {
		// TODO: figure out function that also takes into account magnitude of differences in x and y coords
		if (this.IGetXCoord()==pstateOtherState.IGetXCoord() && this.IGetYCoord()==pstateOtherState.IGetYCoord())
			return 0;
		
		if (this.IGetXCoord()<pstateOtherState.IGetXCoord())
			return -1;
		else if (this.IGetXCoord()>pstateOtherState.IGetXCoord())
			return 1;
		else {
			if (this.IGetYCoord()<pstateOtherState.IGetYCoord())
				return -1;
			else
				return 1;
		}
	}
	
	public boolean equals(GridState pstateOtherState) {
		return (this.compareTo(pstateOtherState)==0);
	}
}
