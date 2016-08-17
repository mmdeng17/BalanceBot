class BalanceBot

// function to get current state from sensors
State pStateGetState();

// function to perform action based on current state/value estimate
void PerformAction();

// function to update value estimate
void UpdateValueFcn();