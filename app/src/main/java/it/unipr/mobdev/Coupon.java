package it.unipr.mobdev;

public class Coupon {

    private final String company;
    private final String code;
    private String expiration;

    public Coupon(String company, String code) {
        this.company = company;
        this.code = code;
        this.expiration = null;
    }

    public Coupon(String company, String code, String expiration) {
        this.company = company;
        this.code = code;
        this.expiration = expiration;
    }

    public String getCompany() {
        return company;
    }

    public String getCode() {
        return code;
    }

    public String getExpiration() {
        return expiration;
    }

}
