package com.cognizant.dto;

import com.cognizant.entities.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDto {

    @Id
    @Size(min=6,max=6)
    private String payment_id;

    @NotNull
    private String upiid;

    private String phone;

    private int amount;

    private LocalDate date;

    private String custid;

    @Override
    public String toString() {
        return "PaymentDto{" +
                "payment_id='" + payment_id + '\'' +
                ", upiid='" + upiid + '\'' +
                ", phone=" + phone +
                ", amount=" + amount +
                ", date=" + date +
                ", custId=" + custid +
                '}';
    }
}
