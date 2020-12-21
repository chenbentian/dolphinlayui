package com.bt.dolphin.common.data;

import java.io.Serializable;

public class IpInfo implements Serializable {

    private int code;
    private Info data;

    public static class Info {
        private String ip;
        private String country;
        private String area;
        private String region;
        private String city;
        private String county;
        private String isp;
        private String countryId;
        private String areaId;
        private String regionId;
        private String cityId;
        private String countyId;
        private String ispId;
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getCounty() {
			return county;
		}
		public void setCounty(String county) {
			this.county = county;
		}
		public String getIsp() {
			return isp;
		}
		public void setIsp(String isp) {
			this.isp = isp;
		}
		public String getCountryId() {
			return countryId;
		}
		public void setCountryId(String countryId) {
			this.countryId = countryId;
		}
		public String getAreaId() {
			return areaId;
		}
		public void setAreaId(String areaId) {
			this.areaId = areaId;
		}
		public String getRegionId() {
			return regionId;
		}
		public void setRegionId(String regionId) {
			this.regionId = regionId;
		}
		public String getCityId() {
			return cityId;
		}
		public void setCityId(String cityId) {
			this.cityId = cityId;
		}
		public String getCountyId() {
			return countyId;
		}
		public void setCountyId(String countyId) {
			this.countyId = countyId;
		}
		public String getIspId() {
			return ispId;
		}
		public void setIspId(String ispId) {
			this.ispId = ispId;
		}
        
        
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Info getData() {
		return data;
	}

	public void setData(Info data) {
		this.data = data;
	}
    
    
    
}
