package com.example.entity;

import org.w3c.dom.Element;

import com.example.xml.XMLRelated;

public class CampbellsKitchenInfoEntity implements RecipeInfoEntity {
	
	String recipeId;
	String title;
	String description;
	String webUrl;
	String imageUrl;
	Float rate;

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public String getComeFrom() {
		// TODO Auto-generated method stub
		return "CampbellsKitchen";
	}

	public CampbellsKitchenInfoEntity(Element element) {
		recipeId = XMLRelated.getValueSafely(element, "recipeid");
		title = XMLRelated.getValueSafely(element, "name");
		webUrl = XMLRelated.getValueSafely(element, "thumbimg");
		description = XMLRelated.getValueSafely(element, "description");
		imageUrl = XMLRelated.getValueSafely(element, "recipelink");
		try {
			rate = Float.parseFloat(XMLRelated.getValueSafely(element, "rating"));
		} catch (Exception e) {
			rate = null;
		}
	}
}
