
public abstract class RLBot {
	RLSystem m_prlsystem;
	
	// function to get current state from sensors
	State pstateGetState(){
		return null;
	}

	// function to get reward
	double dGetReward(State pstateCurrState, Action pactCurrAction, State pstateNextState){
		return 0.0;
	}

	// function to get the current action based on value function etc.
	Action pactGetAction(State pstateCurrState){
		return null;
	}

	// function to perform action based on current state/value estimate
	void PerformOptimalAction(){
		
	}
	
	// function to perform action
	void PerformAction(Action pactCurrAction){
		
	}

	// function to update value estimate
	void UpdateValueFcn(){
		
	}
}
