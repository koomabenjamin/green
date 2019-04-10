package com.shapeworks.mivule.Entities;

import java.util.Date;

/**
 * Created by koomabenjamin on 12/13/17.
 */

public class Payments {

    private String email;
    private String paymentAmount;
    private Date paymentDate;
    private String paymentId;
    private String paymentStatus;
    private String transId;
    private String userId;

    public Payments() {
    }

    public Payments(String email, String paymentAmount, Date paymentDate, String paymentId, String paymentStatus, String transId, String userId) {
        this.email = email;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.paymentId = paymentId;
        this.paymentStatus = paymentStatus;
        this.transId = transId;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getTransId() {
        return transId;
    }

    public String getUserId() {
        return userId;
    }
}
