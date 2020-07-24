package cours.java.service;

import cours.java.model.Ville;

import java.rmi.Remote;
import java.util.List;

public interface IVille extends Remote {
    public List<Ville> findAll() throws Exception;
}
