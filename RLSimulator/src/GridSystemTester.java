
public class GridSystemTester {
	public static void main(String[] args) {
		GridSystem pgsGrid = new GridSystem();
		pgsGrid.Init(4, 3);
		System.out.println(pgsGrid.toString());
		System.out.println(pgsGrid.PRlactGetActor().PActGetPolicy());
	}
}
