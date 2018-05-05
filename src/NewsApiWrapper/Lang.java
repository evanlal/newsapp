package NewsApiWrapper;

public enum Lang {
    DE ("de"),
    EN ("en"),
    FR ("fr"),
    IT ("it"),
    RU ("ru");

    private final String code;

    private Lang(String value) {
        this.code = value;
    }

    @Override
    public String toString() {
        return code;
    }
}
