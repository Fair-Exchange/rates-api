package org.safecoin.config;

public enum Coins {
    SAFE("SafeCoin", RateDataSource.SAFE_TRADE),
    RITO("Ritocoin", RateDataSource.SAFE_TRADE),
    BTCZ("BitcoinZ", RateDataSource.COINLIB),
    BTG("Bitcoin Golg", RateDataSource.COINLIB),
    ANON("Anonymous Bitcoin", RateDataSource.SAFE_TRADE),
    ZEC("Zcash", RateDataSource.COINLIB),
    YEC("Ycash", RateDataSource.SAFE_TRADE),
    ZEL("ZelCash", RateDataSource.COINLIB),
    ZEN("HoriZen", RateDataSource.COINLIB),
    RVN("Ravencoin", RateDataSource.COINLIB),
    ZCL("Zclassic", RateDataSource.COINLIB),
    LTC("Litecoin", RateDataSource.COINLIB),
    BCH("Bitcoin Cash", RateDataSource.COINLIB);

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
