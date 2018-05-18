package org.intracode.heliot;

/**
 * Created by August on 2018-04-30.
 */

public class UserInformation_FB {

    public String account_comperer;
    public String order_number;
    public String plant;
    public String water_amount;
    public String time_hour;
    public String time_min;

    public UserInformation_FB() {
    }

    public UserInformation_FB(String account_comperer, String order_number, String plant, String water_amount, String time_hour, String time_min) {
        this.account_comperer = account_comperer;
        this.order_number = order_number;
        this.plant = plant;
        this.water_amount = water_amount;
        this.time_hour = time_hour;
        this.time_min = time_min;
    }
    public String getAccount_comperer(){ return  account_comperer; }

    public void setAccount_comperer(String account_comperer) { this.account_comperer = account_comperer; }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getWater_amount() {
        return water_amount;
    }

    public void setWater_amount(String water_amount) {
        this.water_amount = water_amount;
    }

    public String getTime_hour() {
        return time_hour;
    }

    public void setTime_hour(String time_hour) {
        this.time_hour = time_hour;
    }

    public String getTime_min() {
        return time_min;
    }

    public void setTime_min(String time_min) {
        this.time_min = time_min;
    }
}