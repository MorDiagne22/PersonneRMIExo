package cours.java.service;

import cours.java.model.Personne;
import cours.java.model.Rue;
import cours.java.model.Ville;
import cours.java.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PersonneDao extends UnicastRemoteObject implements IPersonne {
    private Session session;

    public PersonneDao() throws RemoteException {
        session = HibernateUtil.getSession();
    }

    @Override
    public void add(Personne personne) throws RemoteException {
        Transaction t = session.getTransaction();
        try {
            t.begin();
            session.save(personne);
            t.commit();
        }catch (Exception e){
            t.rollback();
            e.printStackTrace();
        }
    }


    @Override
    public List<Personne> getAllPersonne() throws RemoteException {
        return session.createQuery("SELECT p FROM Personne p",Personne.class).list();
    }

    @Override
    public void update(Personne personne) throws RemoteException {

        Transaction t = session.getTransaction();
        try {
            t.begin();
            session.merge(personne);
            t.commit();
        }catch (Exception e){
            t.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void findAll() throws RemoteException {

    }

    @Override
    public void deletePersonne(long i) throws RemoteException {
        Personne p = session.find(Personne.class, i);
        System.out.println("Nom: "+p.getNom());
        Transaction t = session.getTransaction();
        try {
            t.begin();
            session.remove(p);
            t.commit();
        }catch (Exception e){
            t.rollback();
            e.printStackTrace();
        }

    }

}
