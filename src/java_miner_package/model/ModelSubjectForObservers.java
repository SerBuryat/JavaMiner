package java_miner_package.model;

public interface ModelSubjectForObservers {
    void registerObserver(ModelObserver observer);
    void deleteObserver(ModelObserver observer);
    void notifyObservers();
}
