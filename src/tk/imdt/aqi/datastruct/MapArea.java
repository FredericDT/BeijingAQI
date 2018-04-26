package tk.imdt.aqi.datastruct;

public class MapArea {
	public Position southWest = null;
	public Position northEast = null;
	//39.7515451,116.1160148,40.0915172,116.6854968
	public MapArea(Position southWest, Position northEast) {
		this.southWest = southWest;
		this.northEast = northEast;
	}
	@Override
	public String toString() {
		return this.southWest.toString() + "," + this.northEast.toString();
	}
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = result * prime;
		result += this.southWest != null ? this.southWest.hashCode() : 0;
		result += this.northEast != null ? this.northEast.hashCode() : 0;
		return result;
	}
}
