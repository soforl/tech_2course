package Classes;

public interface IObservable {
    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyObservers(BankAccount bankAccount, double operation);
}
