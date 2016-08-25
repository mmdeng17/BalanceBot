import java.util.Map;
import java.util.TreeMap;

public class DiscreteRLSystem extends RLSystem {
	Map<State, State> m_rgstateStates;
	
	public DiscreteRLSystem() {
		super();
	}
	
	public void Init() {
		super.Init();
		m_rgstateStates = new TreeMap<State, State>();
	}
}
