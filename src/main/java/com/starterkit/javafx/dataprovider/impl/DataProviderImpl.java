package com.starterkit.javafx.dataprovider.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.starterkit.javafx.dataprovider.DataProvider;
import com.starterkit.javafx.dataprovider.data.BookVO;

/**
 * Provides data. Data is stored locally in this object. Additionally a call
 * delay is simulated.
 *
 * @author Leszek
 */
public class DataProviderImpl implements DataProvider {

	private static final Logger LOG = Logger.getLogger(DataProviderImpl.class);

	/**
	 * Delay (in ms) for method calls.
	 */
	// private static final long CALL_DELAY = 3000;

	private Collection<BookVO> persons = new ArrayList<>();

	public DataProviderImpl() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				StringBuffer response = new StringBuffer();
				// Do post (read, from link below)

				String url = "http://localhost:8080/webstore/rbooks/";

				URL obj;
				try {
					obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();

					// optional default is GET
					con.setRequestMethod("GET");

					// add request header
					// con.setRequestProperty("User-Agent", USER_AGENT);

					int responseCode = con.getResponseCode();
					System.out.println("\nSending 'GET' request to URL : " + url);
					System.out.println("Response Code : " + responseCode);

					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();

					// print result
					System.out.println(response.toString());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONArray json = new JSONArray();
				try {
					json = (JSONArray) new JSONParser().parse(response.toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (int i = 0; i < json.size(); i++) {
					JSONObject j = (JSONObject) json.get(i);
					BookVO q = new BookVO(j.get("title").toString(), j.get("authors").toString(),
							j.get("status").toString(), j.get("genre").toString(), j.get("year").toString());
					persons.add(q);
				}
			}
		}).start();
	}

	@Override
	public Collection<BookVO> findBooks(String name) {
		LOG.debug("Entering findPersons()");

		/*
		 * Simulate a call delay.
		 */
		// try {
		// Thread.sleep(CALL_DELAY);
		// } catch (InterruptedException e) {
		// throw new RuntimeException("Thread interrupted", e);
		// }

		// Collection<BookVO> result = persons.stream().filter(p -> //
		// ((name == null || name.isEmpty()) || (name != null && !name.isEmpty()
		// && p.getTitle().contains(name))) //
		//
		// ).collect(Collectors.toList());

		Collection<BookVO> result = persons;
		System.out.println("RESULTS: " + result);

		LOG.debug("Leaving findPersons()");
		return result;
	}
}
