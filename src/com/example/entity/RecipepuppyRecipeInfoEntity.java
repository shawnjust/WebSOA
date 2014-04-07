package com.example.entity;

import org.w3c.dom.Element;

import com.example.xml.XMLRelated;

public class RecipepuppyRecipeInfoEntity implements RecipeInfoEntity {
	
	String title;
	String webUrl;
	String ingreients;

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public String getComeFrom() {
		// TODO Auto-generated method stub
		return "RecipePuppy";
	}
	
	public RecipepuppyRecipeInfoEntity(Element element) {
		title = XMLRelated.getValueSafely(element, "title");
		webUrl = XMLRelated.getValueSafely(element, "href");
		ingreients = XMLRelated.getValueSafely(element, "ingredients");
	}

}
