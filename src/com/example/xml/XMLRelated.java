package com.example.xml;

import org.w3c.dom.Element;

public class XMLRelated {
	public static String getValueSafely(Element element, String key, int index) {
		try {
			String result = element.getElementsByTagName(key).item(0)
					.getFirstChild().getNodeValue();
			// Log.e(key, result);
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public static String getValueSafely(Element element, String key) {
		return getValueSafely(element, key, 0);
	}
}
