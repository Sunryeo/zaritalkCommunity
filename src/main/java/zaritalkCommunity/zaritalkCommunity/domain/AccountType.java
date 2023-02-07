package zaritalkCommunity.zaritalkCommunity.domain;

public enum AccountType {
    LESSOR("임대인"),
    REALTOR("공인 중개사"),
    LESSEE("임차인");

    public final String value;

    private AccountType(String value) {
        this.value = value;
    }
}
