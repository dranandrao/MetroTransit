/**
 * This class handles the direction logics.
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
public class DirectionUtil {
	public ArrayList<Directions> getDirections(int route) {
		ArrayList<Directions> directions = null;
		String direction_get_url = new StringBuffer("http://svc.metrotransit.org/NexTrip/Directions/").append(route)
				.toString();
		try {
			java.net.URL url = new java.net.URL(direction_get_url);
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
			System.out.println("get Direction funcation Http connection Response " + responseCode);
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
				 * get routes as array.
				 */
				Directions[] directionArray = gsonParser.fromJson(response.toString(), Directions[].class);

				directions = new ArrayList<>(Arrays.asList(directionArray));
				/*
				 * convert route array to arraylist.
				 */
			} else {
				return directions;
			}
		} catch (

		MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return directions;
	}
}
