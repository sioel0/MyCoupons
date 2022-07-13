package it.unipr.mobdev;

public class Coupon {
    private final String company;
    private final String code;
    // 1 qr code -- 2 bar code
    private final int format;
    private Expiration expiration;

    public Coupon(String company, String code, int format) {
        this.company = company;
        this.code = code;
        this.format = format;
        this.expiration = null;
    }

    public Coupon(String company, String code, int format, Expiration expiration) {
        this.company = company;
        this.code = code;
        this.format = format;
        this.expiration = expiration;
    }

    public String getCompany() {
        return company;
    }

    public String getCode() {
        return code;
    }

    public int getFormat() {
        return format;
    }

    public Expiration getExpiration() {
        return expiration;
    }

}
