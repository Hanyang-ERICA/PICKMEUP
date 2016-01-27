package info.androidhive.customlistviewvolley.model;

import java.util.ArrayList;

public class Movie {
	private String catchphrase, imgurl, name, nameurl, company, age, height, weight, period, hobby, talent, comment;

	public Movie() {
	}

	public Movie(String catchphrase, String imgurl, String name, String nameurl, String company, String age,
				 String height, String weight, String period, String hobby, String talent, String comment) {
		this.catchphrase = catchphrase;
		this.imgurl = imgurl;
		this.name = name;
		this.nameurl = nameurl;
		this.company = company;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.period = period;
		this.hobby = hobby;
		this.talent = talent;
		this.comment = comment;
	}

	public String getCatchphrase() {
		return catchphrase;
	}

	public void setCatchphrase(String catchphrase) {
		this.catchphrase = catchphrase;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameurl() {
		return nameurl;
	}

	public void setNameurl(String nameurl) {
		this.nameurl = nameurl;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getTalent() {
		return talent;
	}

	public void setTalent(String talent) {
		this.talent = talent;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
