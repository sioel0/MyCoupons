package it.unipr.mobdev;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// singleton class to make sure that there is only one coupon list in all the application
public class CouponList {

    private final List<Coupon> list;
    private static CouponList couponList;

    public static CouponList getInstance() {
        if(couponList == null)
            couponList = new CouponList();
        return couponList;
    }

    private CouponList() {
        this.list = new ArrayList<>();
        this.addData();
    }

    private void addData() {
        addElement(new Coupon("McDonalds", "abcd"));
        addElement(new Coupon("Rosso Pomodoro", "1234"));
        addElement(new Coupon("Camion", "12ab"));
        addElement(new Coupon("AliExpress", "23cd"));
        addElement(new Coupon("Burger King", "1bc4"));
    }

    public void addElement(Coupon c) {
        if(list.contains(c))
            throw new IllegalArgumentException("coupon already in list");
        if(list.isEmpty()) {
            list.add(c);
            return;
        }
        // keep the list ordered by company name
        for(Coupon e : list) {
            if(e.getIdentifier().compareTo(c.getIdentifier()) < 0)
                continue;
            list.add(list.indexOf(e), c);
            return;
        }
        list.add(c);
    }

    public void removeElement(int index) {
        list.remove(index);
    }

    public Coupon couponAtIndex(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean containsCouponNamed(String name) {
        return !list.stream()
                .filter(c -> c.getIdentifier().equals(name))
                .collect(Collectors.toSet()).isEmpty();
    }

}
