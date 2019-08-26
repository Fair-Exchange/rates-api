package org.safecoin.config;

public enum Coins {
    SAFE("SafeCoin", RateDataSource.SAFE_TRADE),
    ANON("Anonymous Bitcoin", RateDataSource.SAFE_TRADE),
    BCH("Bitcoin Cash", RateDataSource.COINLIB),
    BC("Bitcoin Confidential", RateDataSource.SAFE_TRADE),
    BTCZ("BitcoinZ", RateDataSource.COINLIB),
    BTG("Bitcoin Gold", RateDataSource.COINLIB),
    DOGE("Dogecoin", RateDataSource.COINLIB),
    ZEN("HoriZen", RateDataSource.COINLIB),
    LTC("Litecoin", RateDataSource.COINLIB),
    RVN("Ravencoin", RateDataSource.COINLIB),
    RITO("Ritocoin", RateDataSource.SAFE_TRADE),
    XSG("Snowgem", RateDataSource.COINLIB),
    YEC("Ycash", RateDataSource.SAFE_TRADE),
    ZEC("Zcash", RateDataSource.COINLIB),
    ZCL("Zclassic", RateDataSource.COINLIB),
    ZEL("ZelCash", RateDataSource.COINLIB);

    private String name;

    private RateDataSource rateDataSource;

    Coins(String name, RateDataSource rateDataSource){
        this.name = name;
        this.rateDataSource = rateDataSource;
    }

    public String getName(){
        return name;
    }

    public RateDataSource getRateDataSource(){
        return rateDataSource;
    }
}
