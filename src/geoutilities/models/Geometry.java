package geoutilities.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import geoutilities.Models.Location;

public class Geometry {

	@SerializedName("location")
	@Expose
	private Location location;
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}
