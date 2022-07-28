package br.com.quatty.backend.business.entity.enums;

public enum RoleName {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private final String code;

    RoleName(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public RoleName getRole(String code){
        for (RoleName role: RoleName.values()) {
            if (role.getCode().equalsIgnoreCase(code)){
                return role;
            }
        }throw new IllegalArgumentException("Invalid role code");
    }
}
