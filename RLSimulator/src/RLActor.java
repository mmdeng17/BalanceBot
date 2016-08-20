import java.util.Set;

public class RLActor extends Actor {
	protected double[][] m_rgdQFcn;
	
	
	public RLActor() {
		super();
		m_rgdQFcn = null;
	}
	
	public void Init() {
		
	}
	
	public Action PActGetPolicy() {
		return null;
	}
}
