
public class BalanceBotSystem extends RLSystem {
	
	public BalanceBotSystem() {
		double[] rgdTmp = {1,0,0,0};
		BBState pbbstateTmp = new BBState(rgdTmp);
		m_pstateCurrState = pbbstateTmp;
	}
	
	public BalanceBotSystem(double[] rgdStateRg) {
		BBState pbbstateTmp = new BBState(rgdStateRg);
		m_pstateCurrState = pbbstateTmp;
	}
	
	public void Transition(Action pactCurrAction) {
		double dCurrX, dCurrY, dCurrVel, dCurrAcc, dCurrAngle;
		BBAction pbbactCurrAction;
		
		pbbactCurrAction = (BBAction) pactCurrAction;
	}
}
