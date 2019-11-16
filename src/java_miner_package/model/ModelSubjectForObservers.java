package java_miner_package.model;

interface ModelSubjectForObservers {
    void registerObserver(ModelObserver observer);
    void deleteObserver(ModelObserver observer);
    void notifyObservers();
}
