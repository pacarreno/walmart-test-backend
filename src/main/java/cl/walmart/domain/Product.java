package cl.walmart.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
public class Product {

	@Id
	private String _id;
	private Long id;
	private String brand;
	private String description;
	private String image;
	private String price;
	
	transient private boolean palindrome;
	transient private String orginalPrice;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public boolean isPalindrome() {
		return palindrome;
	}
	public void setPalindrome(boolean palindrome) {
		this.palindrome = palindrome;
	}
	public String getOrginalPrice() {
		return orginalPrice;
	}
	public void setOrginalPrice(String orginalPrice) {
		this.orginalPrice = orginalPrice;
	}
	@Override
	public String toString() {
		return "Product [_id=" + _id + ", id=" + id + ", brand=" + brand + ", description=" + description + ", image="
				+ image + ", price=" + price + "]";
	}

}
