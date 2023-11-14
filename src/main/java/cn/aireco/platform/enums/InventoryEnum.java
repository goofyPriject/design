package cn.aireco.platform.enums;

public enum InventoryEnum {

    INVENTORY1("", "");

    private final String code;

    private final String value;

    InventoryEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
