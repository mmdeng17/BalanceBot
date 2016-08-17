// interface for a reinforcement learning robot
class IRLBot

// function to get current state from sensors
State pstateGetState();

// function to get reward
Reward prewGetReward(State pstateCurrState, Action pactCurrAction, State pstateNextState);

// function to get the current action based on value function etc.
Action pactGetAction(State pstateCurrState);

// function to perform action based on current state/value estimate
void PerformOptimalAction();

void PerformAction(Action pactCurrAction);

// function to update value estimate
void UpdateValueFcn();

m_pqQFcn;
m_alpha;
m_gamma;