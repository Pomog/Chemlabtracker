package core.database;

public class Database {

    private final UserDatabase userDatabase;
    private final ItemDatabase itemDatabase;
    private final CartDatabase cartDatabase;
    private final CartItemDatabase cartItemDatabase;

    public Database() {
        this.userDatabase = new InMemoryUserDatabaseImpl();
        this.itemDatabase = new InMemoryItemDatabaseImpl();
        this.cartDatabase = new InMemoryCartDatabaseImpl();
        this.cartItemDatabase = new InMemoryCartItemDatabaseImpl();
    }

    public UserDatabase accessUserDatabase() {
        return userDatabase;
    }

    public ItemDatabase accessItemDatabase() {
        return itemDatabase;
    }

    public CartDatabase accessCartDatabase() {
        return cartDatabase;
    }

    public CartItemDatabase accessCartItemDatabase() {
        return cartItemDatabase;
    }

}
