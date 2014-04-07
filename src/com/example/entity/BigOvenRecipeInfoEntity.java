package com.example.entity;

import org.w3c.dom.Element;

import com.example.xml.XMLRelated;

public class BigOvenRecipeInfoEntity implements RecipeInfoEntity  {
	String recipeId;
	public String getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Float getStarRating() {
		return starRating;
	}

	public void setStarRating(Float starRating) {
		this.starRating = starRating;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl120() {
		return imageUrl120;
	}

	public void setImageUrl120(String imageUrl120) {
		this.imageUrl120 = imageUrl120;
	}
	
	public String getComeFrom() {
		return "BigOven";
	}

	String title;
	String category;
	Float starRating;
	String webUrl;
	String imageUrl;
	String imageUrl120;

	public BigOvenRecipeInfoEntity(Element element) {
		recipeId = XMLRelated.getValueSafely(element, "RecipeID");
		title = XMLRelated.getValueSafely(element, "Title");
		category = XMLRelated.getValueSafely(element, "Category");
		try {
			starRating = Float.parseFloat(XMLRelated.getValueSafely(element,
					"StarRating"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			starRating = null;
		}
		webUrl = XMLRelated.getValueSafely(element, "WebURL");
		imageUrl = XMLRelated.getValueSafely(element, "ImageURL");
		imageUrl120 = XMLRelated.getValueSafely(element, "ImageURL120");

	}

	@Override
	public String toString() {
		return "recipeId=" + recipeId + " title=" + title + " imageUrl120="
				+ imageUrl120;
	}
}
