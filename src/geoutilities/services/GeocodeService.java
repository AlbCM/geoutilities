package geoutilities.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import geoutilities.Models.GeocodeResponse;

public class GeocodeService {

	private static final String baseUrl = "http://maps.google.com/maps/api/geocode/json?";

	public static String getCoordinatesFromAddress(String address) {

		String fullUrl = baseUrl.concat("address=" + address);
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(fullUrl);
			HttpResponse response = client.execute(request);

			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String bufferLine, responseBody = "";
			while ((bufferLine = br.readLine()) != null) {
				responseBody += bufferLine;
			}
			client.getConnectionManager().shutdown();
			// parse Json to Java Class
			Gson gson = new GsonBuilder().create();
			GeocodeResponse obj = gson.fromJson(responseBody, GeocodeResponse.class);

			return "Address: " + obj.getResults().get(0).getFormattedAddress() + "\n" + "Lat: "
					+ obj.getResults().get(0).getGeometry().getLocation().getLat() + "\n Lng: "
					+ obj.getResults().get(0).getGeometry().getLocation().getLng();
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	public static String getAddressFromCoordinates(String coordinates) {

		String fullUrl = baseUrl.concat("latlng=" + coordinates + "&" + "sensor=false");

		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(fullUrl);
			HttpResponse response = client.execute(request);

			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String bufferLine, responseBody = "";
			while ((bufferLine = br.readLine()) != null) {
				responseBody += bufferLine;
			}
			client.getConnectionManager().shutdown();
			// parse Json to Java Class
			Gson gson = new GsonBuilder().create();
			GeocodeResponse obj = gson.fromJson(responseBody, GeocodeResponse.class);

			return "Address: " + obj.getResults().get(0).getFormattedAddress() + "\n";
		} catch (Exception e) {
			return e.getMessage();
		}

	}

}
