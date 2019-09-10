package com.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "PERSON", schema = "STARWARS")
public class Person implements java.io.Serializable {
	// Fields
 
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String gender;
	private String skincolor;
	private String haircolor;
	private String eyecolor;
	private String birthyear;
	private Integer height;
	private Integer mass;
 
	// Constructors
 
	/** default constructor */
	public Person() {
	}
 
	/** minimal constructor */
	public Person(Integer id) {
		this.id = id;
	}
 
	/** full constructor */
	public Person(Integer id, String name,
			String gender, String skincolor, String haircolor,
			String eyecolor, String birthyear,
			Integer height, Integer mass) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.skincolor = skincolor;
		this.haircolor = haircolor;
		this.eyecolor = eyecolor;
		this.birthyear = birthyear;
		this.height = height;
		this.mass = mass;
	}
 
	// Property accessors
	@Id
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}
 
	public void setId(Integer id) {
		this.id = id;
	}
 
	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	@Column(name = "GENDER", length = 20)
	public String getGender() {
		return this.gender;
	}
 
	public void setGender(String gender) {
		this.gender = gender;
	}
 
	@Column(name = "SKINCOLOR", length = 30)
	public String getSkincolor() {
		return this.skincolor;
	}
 
	public void setSkincolor(String skincolor) {
		this.skincolor = skincolor;
	}
 
	@Column(name = "HAIRCOLOR", length = 30)
	public String getHaircolor() {
		return this.haircolor;
	}
 
	public void setHaircolor(String haircolor) {
		this.haircolor = haircolor;
	}
 
	@Column(name = "EYECOLOR", length = 30)
	public String getEyecolor() {
		return this.eyecolor;
	}
 
	public void setEyecolor(String eyecolor) {
		this.eyecolor = eyecolor;
	}
 
	@Column(name = "BIRTHYEAR", length = 20)
	public String getBirthyear() {
		return this.birthyear;
	}
 
	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}
 
	@Column(name = "HEIGHT")
	public Integer getHeight() {
		return this.height;
	}
 
	public void setHeight(Integer height) {
		this.height = height;
	}
 
	@Column(name = "MASS")
	public Integer getMass() {
		return this.mass;
	}
 
	public void setMass(Integer mass) {
		this.mass = mass;
	}
 
}
