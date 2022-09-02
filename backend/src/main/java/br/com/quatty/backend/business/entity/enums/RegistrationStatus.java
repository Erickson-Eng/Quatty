package br.com.quatty.backend.business.entity.enums;

public enum RegistrationStatus {
    WAITING_FOR_APPROVAL("WAITING_FOR_APPROVAL"),
    APPROVED("APPROVED"),
    CANCELED("CANCELED"),
    DISAPPROVED("DISAPPROVED");

    private final String code;

    RegistrationStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    public static RegistrationStatus getRegistrationStatus(String code){
        for (RegistrationStatus value: RegistrationStatus.values()){
            if (value.getCode().equalsIgnoreCase(code))
                return value;
        }
        throw new IllegalArgumentException("Invalid code");
    }
}
