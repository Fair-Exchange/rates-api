package org.safecoin.dto;

import java.math.BigDecimal;

public class Rate {
    public static class Builder {
        private String code;
        private String name;
        private BigDecimal rate;

        public Builder(String code){
            this.code = code;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder atRate(BigDecimal rate){
            this.rate = rate;
            return this;
        }

        public Rate build(){
            Rate rate = new Rate();
            rate.code = this.code;
            rate.name = this.name;
            rate.rate = this.rate;
            return rate;
        }
    }

    private String code;
    private String name;
    private BigDecimal rate;

    private Rate(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}