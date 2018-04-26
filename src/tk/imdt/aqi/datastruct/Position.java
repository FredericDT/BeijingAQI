package tk.imdt.aqi.datastruct;

public class Position {
	public Double latitude = null;
	public Double longtitude = null;
	public Position(Double latitude, Double longtitude){
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	@Override
	public String toString() {
		return this.latitude + "," + this.longtitude;
	}
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = result * prime + (this.latitude != null ? this.latitude.hashCode() : 0) + (this.longtitude != null ? this.longtitude.hashCode() : 0);
		return result;
	}
}
