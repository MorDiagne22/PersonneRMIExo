package cours.java.service;

import cours.java.model.Rue;
import cours.java.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RueDao extends UnicastRemoteObject implements IRue {
    private Session session;
    public RueDao() throws RemoteException {
        session = HibernateUtil.getSession();
    }

    @Override
    public List<Rue> findByVilleId(int id) throws Exception {
        String sql = "from Rue where ville_id = '"+id+"'";
        Query query = session.createQuery(sql);
        return query.list();
    }
}
