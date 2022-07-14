package it.unipr.mobdev;

public class Coupon {

    private final String identifier;
    private final String code;
    private final String expiration;

    public Coupon(String company, String code) {
        this.identifier = company;
        this.code = code;
        this.expiration = null;
    }

    public Coupon(String company, String code, String expiration) {
        this.identifier = company;
        this.code = code;
        this.expiration = expiration;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getCode() {
        return code;
    }

    public String getExpiration() {
        return expiration;
    }

}
