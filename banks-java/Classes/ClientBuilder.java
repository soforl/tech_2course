package Classes;

public class ClientBuilder {
    private String clientFirstName;
    private String clientLastName;
    private String clientAddress = null;
    private String clientPassport = null;

    public ClientBuilder buildFirstName(String firstName) {
        clientFirstName = firstName;
        return this;
    }

    public ClientBuilder buildLastName(String lastName) {
        clientLastName = lastName;
        return this;
    }

    public ClientBuilder buildAddress(String address) {
        if (!address.trim().isEmpty()) {
            clientAddress = address;
        }

        return this;
    }

    public ClientBuilder buildPassport(String passport) {
        if (!passport.trim().isEmpty()) {
            clientPassport = passport;
        }

        return this;
    }

    public Client build() {
        Client client = new Client(clientFirstName, clientLastName, clientAddress, clientPassport);
        return client;
    }
}
