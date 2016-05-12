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
*
 */
public class DataProviderImpl implements DataProvider {

	private static final Logger LOG = Logger.getLogger(DataProviderImpl.class);

	private Collection<BookVO> persons = new ArrayList<>();

	public DataProviderImpl() {

//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				StringBuffer response = new StringBuffer();
//				// Do post (read, from link below)
//
//				String url = "http://localhost:8080/webstore/rbooks/";
//
//				URL obj;
//				try {
//					obj = new URL(url);
//					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//					// optional default is GET
//					con.setRequestMethod("GET");
//
//					// add request header
//					// con.setRequestProperty("User-Agent", USER_AGENT);
//
//					int responseCode = con.getResponseCode();
//					System.out.println("\nSending 'GET' request to URL : " + url);
//					System.out.println("Response Code : " + responseCode);
//
//					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//					String inputLine;
//
//					while ((inputLine = in.readLine()) != null) {
//						response.append(inputLine);
//					}
//					in.close();
//
//					// print result
//					System.out.println(response.toString());
//				} catch (MalformedURLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				JSONArray json = new JSONArray();
//				try {
//					json = (JSONArray) new JSONParser().parse(response.toString());
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				for (int i = 0; i < json.size(); i++) {
//					JSONObject j = (JSONObject) json.get(i);
//					BookVO q = new BookVO(j.get("title").toString(), j.get("authors").toString(),
//							j.get("status").toString(), j.get("genre").toString(), j.get("year").toString());
//					persons.add(q);
//				}
//			}
//		}).start();
	}

	@Override
	public Collection<BookVO> findBooks(String title, String authors) {
		LOG.debug("Entering findPersons()");

		Collection<BookVO> result = new ArrayList<>();

//		if (authors.trim().isEmpty())
//			result = persons.stream()
//					.filter(p -> ((title == null || title.isEmpty())
//							|| (title != null && !title.isEmpty() && p.getTitle().contains(title))))
//					.collect(Collectors.toList());
//		else if (title.trim().isEmpty()) {
//			result = persons.stream()
//					.filter(p -> ((authors == null || authors.isEmpty())
//							|| (authors != null && !authors.isEmpty() && p.getAuthors().contains(authors))))
//					.collect(Collectors.toList());
//		} else {
//			result = persons.stream()
//					.filter(p -> ((title == null || title.isEmpty() || authors == null || authors.isEmpty())
//							|| (title != null && !title.isEmpty() && p.getTitle().contains(title) && authors != null
//									&& !authors.isEmpty() && p.getAuthors().contains(authors))))
//					.collect(Collectors.toList());
//		}
//		LOG.debug("Leaving findPersons()");
		return result;
	}

	@Override
	public Collection<BookVO> getBooks() {
		Collection<BookVO> result = persons;
		LOG.debug("Leaving findPersons()");
		return result;
	}
}
