package cours.java.service;

import cours.java.model.Ville;
import cours.java.utils.HibernateUtil;
import org.hibernate.Session;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class VilleDao  extends UnicastRemoteObject implements IVille {
    private Session session;
    public VilleDao() throws RemoteException {
        session = HibernateUtil.getSession();
    }

    @Override
    public List<Ville> findAll() throws Exception {
       // return session.createQuery("SELECT v FROM ville v").list();
        return session.createQuery("SELECT s FROM Ville s", Ville.class).list();
    }
}
