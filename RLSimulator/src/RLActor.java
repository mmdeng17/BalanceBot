import java.util.List;

public class RLActor extends Actor {
	protected double[][] m_rgdQFcn;
	protected List<Action> m_prgactActionSet;
	
	public RLActor() {
		super();
		m_prgactActionSet = null;
		m_rgdQFcn = null;
	}
	
	public void Init() {
		
	}
	
	public Action PActGetPolicy() {
		return null;
	}
}
