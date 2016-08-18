enum Dir {Up, Down, Left, Right};

public class GridAction extends Action {
	private int m_iAction;
	private Dir m_action;
	
	public GridAction() {
		super();
		m_iAction = -1;
		m_action = null;
	}

	public int IGetIAction() {
		return m_iAction;
	}
	
	public Dir PDirGetActionAsDir() {
		return m_action;
	}
	
	public void SetAction(int iAction) {
		m_iAction = iAction;
		switch(m_iAction) {
		case 0:
			m_action = Dir.Up;
		case 1:
			m_action = Dir.Down;
		case 2:
			m_action = Dir.Left;
		case 3:
			m_action = Dir.Right;
		default:
			m_iAction = -1;
			m_action = null;
			throw new IllegalArgumentException("Action index out of bounds.");
		}
	}
	
	public void SetAction(Dir pdirAction) {
		m_action = pdirAction;
		switch(m_action) {
		case Up:
			m_iAction = 0;
		case Down:
			m_iAction = 1;
		case Left:
			m_iAction = 2;
		case Right:
			m_iAction = 3;
		}
	}
}
