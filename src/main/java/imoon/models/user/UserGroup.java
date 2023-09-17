package imoon.models.user;

public enum UserGroup {
    COMMON_CAN_AUTH("Пользователь может авторизоваться в системе"),

    PANEL_SEE_USER_CONTROLLER("Видеть пользователей системы");

    private String label;

    UserGroup(String label) {
        this.label = label;
    }

}
