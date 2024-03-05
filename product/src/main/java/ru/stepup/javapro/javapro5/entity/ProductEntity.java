package ru.stepup.javapro.javapro5.entity;

import java.math.BigDecimal;

public class ProductEntity {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private Integer type;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ProductEntity(Long id, String accountNumber, BigDecimal balance, Integer type, Long userId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public ProductEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}