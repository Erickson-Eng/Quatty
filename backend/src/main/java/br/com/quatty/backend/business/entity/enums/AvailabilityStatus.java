package br.com.quatty.backend.business.entity.enums;

public enum AvailabilityStatus {
    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE");

    private final String code;

    AvailabilityStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public AvailabilityStatus getAvailableStatus(String code){
        for (AvailabilityStatus value : AvailabilityStatus.values()) {
            if (value.getCode().equalsIgnoreCase(code))
                return value;
        }
        throw new IllegalArgumentException("Invalid code");
    }

}
