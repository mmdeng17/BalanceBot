import java.util.Map;

public class RLActor extends Actor {
	protected Map<StateActionPair, Double> m_mpsapdQFcn;
	
	public RLActor() {
		super();
		m_mpsapdQFcn = null;
	}
	
	public void Init() {
		
	}
	
	public Action PActGetPolicy() {
		return null;
	}
}
