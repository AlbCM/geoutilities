package geoutilities.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import geoutilities.Models.Geometry;

public class Result {
	
	@SerializedName("formatted_address")
	@Expose
	private String formattedAddress;
	@SerializedName("geometry")
	@Expose
	private Geometry geometry;

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

}
