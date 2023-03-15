package jpabook.jpashop.domain;


import lombok.Getter;

import javax.persistence.Embeddable;


/**
 * 값타입은 setter 를 만들지 않는게 좋다. 불변 상태로 만들어야함.
 */
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
