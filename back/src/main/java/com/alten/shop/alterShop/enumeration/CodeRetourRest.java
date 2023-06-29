package com.alten.shop.alterShop.enumeration;

public enum CodeRetourRest {
	PRODUCT_CREATED(1, "Product created with success"),
	PRODUCT_UPDATED(2, "Product updated"),
	PRODUCT_DELETED(3, "Product deleted"),
	PRODUCT_NOT_FOUND(4, "Product not found"),
	OK_AVEC_RESULTAT(20,"appel reussi avec resultat"),
	OK_SANS_RESULTAT(21, "appel reussi sans resultat");

	int code;
	String description;

	CodeRetourRest(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}