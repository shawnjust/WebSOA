package com.example.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

import com.example.entity.BigOvenRecipeInfoEntity;
import com.example.entity.CampbellsKitchenInfoEntity;
import com.example.entity.RecipeInfoEntity;
import com.example.entity.RecipepuppyRecipeInfoEntity;

public class DisplayData {

	public List<RecipeInfoEntity> getDataBySearchKeyWord(String keyWord) {
		String httpUrl = "http://api.bigoven.com/recipes?api_key=dvxwX664hy67JXWMljn75t27DDJ9dFG9";
		httpUrl += "&any_kw=" + keyWord;
		httpUrl += "&pg=" + 1;
		httpUrl += "&rpp=" + 20;
		Document document = null;
		List<RecipeInfoEntity> entityList = new ArrayList<RecipeInfoEntity>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			document = documentBuilder.parse(httpUrl);
			NodeList list = document.getElementsByTagName("RecipeInfo");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				BigOvenRecipeInfoEntity entity = new BigOvenRecipeInfoEntity(element);
				entityList.add(entity);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		httpUrl = "http://www.recipepuppy.com/api/?i=&format=xml";
		httpUrl += "&q=" + keyWord;
		httpUrl += "&p=" + 1;
//		Log.e("data", retrieveDataFromNet(httpUrl));
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			document = documentBuilder.parse(httpUrl);
			NodeList list = document.getElementsByTagName("recipe");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				RecipepuppyRecipeInfoEntity entity = new RecipepuppyRecipeInfoEntity(element);
				entityList.add(entity);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		httpUrl = "http://api.campbellskitchen.com/brandservice.svc/api/search?format=xml&app_id=a3f68079&app_key=fdf3a0728b43df9cdb358a48a5c24aef";
		httpUrl += "&keywords=" + keyWord;
//		Log.e("data", retrieveDataFromNet(httpUrl));
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			document = documentBuilder.parse(httpUrl);
			NodeList list = document.getElementsByTagName("recipe");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				CampbellsKitchenInfoEntity entity = new CampbellsKitchenInfoEntity(element);
				entityList.add(entity);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entityList;
	}

	private String retrieveDataFromNet(String httpUrl) {
		HttpGet httpRequest = new HttpGet(httpUrl);
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return EntityUtils.toString(httpResponse.getEntity());
			} else {
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
