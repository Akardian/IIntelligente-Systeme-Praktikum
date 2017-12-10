package environment;

public class Tile {
	
	
	
	private final String name;
	
	private final int x;
	private final int y;

	private double qValueU;
	private double qValueR;
	private double qValueD;
	private double qValueL;
	
	private TileType type;
	
	public Tile(int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		
		name = "Tile_X" + x + "_Y" + y;
		
		qValueU = 0;
		qValueR = 0;
		qValueD = 0;
		qValueL = 0;
	}
	
	public void print() {
		if(type == TileType.EMPTY) {
			System.out.printf("[" + name + " U(%.2f) R(%.2f) D%.2f) L(%.2f)] ", qValueU, qValueR, qValueD, qValueL);
		} else if (type == TileType.CLIFF) {
			System.out.printf("[-------------------CLIFF-----------------] ");
		} else if (type == TileType.START) {
			System.out.printf("[-------------------START-----------------] ");
		} else if (type == TileType.END) {
			System.out.printf("[--------------------END------------------] ");
		} else {
			System.out.println("ERROR: No Tile Type.");
		}
		
	}

	@Override
	public String toString() {
		return "Tile [name=" + name + ", x=" + x + ", y=" + y + ", qValueU=" + qValueU + ", qValueR=" + qValueR
				+ ", qValueD=" + qValueD + ", qValueL=" + qValueL + ", type=" + type + "]";
	}
}

