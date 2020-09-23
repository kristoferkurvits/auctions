package com.Kurvits.bacchusback.User;


import com.Kurvits.bacchusback.Auction.Auction;
import org.hibernate.annotations.Type;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.UUID;

@Entity
public class User {

    @Id
    @Type(type="uuid-char")
    private UUID userId = UUID.randomUUID();

    @Type(type="uuid-char")
    private UUID productId;

    @NotEmpty(message = "Name may not be empty")
    private String userName;
    private String productName;

    @NotNull
    @Max(Long.MAX_VALUE)
    @Min(Long.MIN_VALUE)
    private int bid;

    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    private ZonedDateTime dateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC"));

    @Transient
    private boolean validBid;

    User(){

    }

    public User(UUID productId, String userName, String productName, int bid) throws IOException, ParseException {
        this.userId = UUID.randomUUID();
        this.productId = productId;
        this.userName = userName;
        this.productName = productName;
        this.bid = bid;
        this.dateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC"));
        this.validBid = false;

    }

    public void checkDateValidity(UUID productId) throws IOException, ParseException {


        try{
            JSONArray jsonArray = Auction.fetchAuctions();


            Iterator<JSONObject> it = jsonArray.listIterator();
            while (it.hasNext()) {
                JSONObject jobj =(JSONObject) it.next();

                if (jobj.get("productId").equals(productId.toString())){
                    ZonedDateTime bidDate = LocalDateTime.parse(jobj.get("biddingEndDate").toString(), DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.of("UTC"));
                    if (bidDate.isAfter(this.dateTime)){

                        this.validBid = true;
                        break;
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }


    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public boolean isValidBid() {
        return validBid;
    }

    public void setValidBid(boolean validBid) {
        this.validBid = validBid;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", userName='" + userName + '\'' +
                ", productName='" + productName + '\'' +
                ", bid=" + bid +
                ", localDateTime=" + dateTime +
                ", validBid=" + validBid +
                '}';
    }
}
