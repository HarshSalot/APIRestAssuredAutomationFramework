package pojo;

import java.util.List;

public class ContactPayload {

	public int ownerId;
	public String ownerType;
	public String countryName;
	public List<String> salesReps;
	public String officeNumber;
	public Integer cityId;
	public Integer provinceId;
	public boolean setAsDefaultEmail;
	public Integer countryId;
	public String cityName;
	public String firstName;
	public String provinceName;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public boolean isSetAsDefaultEmail() {
		return setAsDefaultEmail;
	}

	public void setSetAsDefaultEmail(boolean setAsDefaultEmail) {
		this.setAsDefaultEmail = setAsDefaultEmail;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

}
