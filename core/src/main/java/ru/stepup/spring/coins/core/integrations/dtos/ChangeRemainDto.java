package ru.stepup.spring.coins.core.integrations.dtos;




public class ChangeRemainDto {
    private ChangeRemainCode code;

    public ChangeRemainDto(ChangeRemainCode code) {
        this.code = code;
    }

    public ChangeRemainCode getCode() {
        return code;
    }

    public void setCode(ChangeRemainCode code) {
        this.code = code;
    }

    public ChangeRemainDto() {}

    public enum ChangeRemainCode {
        CODE_OK, CODE_NO
    }
}