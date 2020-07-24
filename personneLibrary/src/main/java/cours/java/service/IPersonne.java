package cours.java.service;



import cours.java.model.Personne;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IPersonne extends Remote {

    public void add(Personne s) throws RemoteException;
    public List<Personne> getAllPersonne() throws RemoteException;
    public void update(Personne p) throws RemoteException;
    public void findAll() throws RemoteException;
    public void deletePersonne(long id) throws RemoteException;
}
