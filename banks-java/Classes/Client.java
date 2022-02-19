package Classes;

import java.util.ArrayList;

public class Client {
    private String firstName;
    private String lastName;
    private String address;
    private String passport;
    private ArrayList<ClientData> clientAccounts;

    public Client(String firstName, String lastName, String address, String passport) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.passport = passport;
        clientAccounts = new ArrayList<ClientData>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void addAddress(String address) {
        this.address = address;
    }

    public void addPassport(String passport) {
        this.passport = passport;
    }

    public ClientBuilder toBuild(ClientBuilder client) {
        client.buildFirstName(firstName)
                .buildLastName(lastName)
                .buildAddress(address)
                .buildPassport(passport);
        return client;
    }

    public boolean checkRegistration() {
        if (address != null && passport != null)
        {
            return true;
        }

        return false;
    }

    public ArrayList<ClientData> addBankAccount(Bank bank, BankAccount bankAccount) {
        clientAccounts.add(new ClientData(bank, bankAccount));
        return clientAccounts;
    }
}
