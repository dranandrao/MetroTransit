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
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.models.Departure;

/**
 * @author dranandrao
 *
 */
public class TimePointDepartureUtil {
	
	
	public ArrayList<Departure> getTimePointDeparture(int route, int direction, String stop) {
		ArrayList<Departure> departures = null;
		String departure_get_url = new StringBuffer("http://svc.metrotransit.org/NexTrip/").append(route + "/")
				.append(direction + "/").append(stop).toString();

		try {
			java.net.URL url = new java.net.URL(departure_get_url);
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
			System.out.println("get timepoint departure Http connection Response " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream()));
				StringBuffer response = new StringBuffer();
				String inputLine;
				while ((inputLine = bufferedReader.readLine()) != null) {
					response.append(inputLine);
				}

				// using json library to parse the data.
				GsonBuilder gsonBuilder = new GsonBuilder();
				gsonBuilder.registerTypeAdapter(Date.class, new DotNetDateDeserializer());
				Gson gsonParser = gsonBuilder.create();
				/*
				 * get departure as array, class structure is same for both
				 * stops and direction.So using direction class.
				 */
				Departure[] departureArray = gsonParser.fromJson(response.toString(), Departure[].class);

				departures = new ArrayList<>(Arrays.asList(departureArray));
				/*
				 * convert stops array to arraylist.
				 */
			} else {
				return departures;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return departures;
	}
}
