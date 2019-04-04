/**
 * 
 */
package com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.models.Directions;

/**
 * @author dranandrao
 *
 */
public class StopsUtil {

	public ArrayList<Directions> getStops(int route, int direction) {
		ArrayList<Directions> stops = null;
		String stops_get_url = new StringBuffer("http://svc.metrotransit.org/NexTrip/Stops/").append(route + "/")
				.append(direction).toString();

		try {
			java.net.URL url = new java.net.URL(stops_get_url);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");// set request type.
			httpConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			/*
			 * setting content type to json, since we get html as default
			 * response.
			 */

			httpConnection.setRequestProperty("Accept", "application/json");
			/*
			 * setting accept to json
			 */
			int responseCode = httpConnection.getResponseCode();
			System.out.println("get stops Http connection Response " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream()));
				StringBuffer response = new StringBuffer();
				String inputLine;
				while ((inputLine = bufferedReader.readLine()) != null) {
					response.append(inputLine);
				}

				// using json library to parse the data.
				Gson gsonParser = new Gson();
				/*
				 * get stops as array, class structure is same for both stops
				 * and direction.So using direction class.
				 */
				Directions[] stopsArray = gsonParser.fromJson(response.toString(), Directions[].class);

				stops = new ArrayList<>(Arrays.asList(stopsArray));
				/*
				 * convert stops array to arraylist.
				 */
			} else {
				return stops;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stops;
	}

}
