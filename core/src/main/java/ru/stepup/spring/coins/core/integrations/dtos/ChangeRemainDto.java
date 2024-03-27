package ru.stepup.spring.coins.core.integrations.dtos;

public class ChangeRemainDto {
    private String code;

    public ChangeRemainDto(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ChangeRemainDto() {}
}