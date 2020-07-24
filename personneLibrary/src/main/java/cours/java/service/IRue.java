package cours.java.service;

import cours.java.model.Rue;

import java.rmi.Remote;
import java.util.List;

public interface IRue extends Remote {
    public List<Rue> findByVilleId(int id) throws Exception;
}
