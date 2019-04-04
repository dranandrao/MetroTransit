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
import com.models.Routes;

/**
 * @author dranandrao
 *
 */
public class RoutesUtil {

	// Gets routes available all over the place
	public ArrayList<Routes> getRoutes() {
		ArrayList<Routes> routes = null;
		String routes_get_url = "http://svc.metrotransit.org/NexTrip/Routes";
		try {
			java.net.URL url = new java.net.URL(routes_get_url);
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
			System.out.println("get Routes Http connection Response " + responseCode);
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
				Routes[] routeArray = gsonParser.fromJson(response.toString(), Routes[].class);

				routes = new ArrayList<>(Arrays.asList(routeArray));
				/*
				 * convert route array to arraylist.
				 */
			} else {
				return routes;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return routes;
	}
}
