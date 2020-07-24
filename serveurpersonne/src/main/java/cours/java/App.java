package cours.java;

import cours.java.service.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            System.setSecurityManager(new SecurityManager());
            Registry registry = LocateRegistry.createRegistry(5003);

            IPersonne iPersonne = new PersonneDao();
            registry.bind("personneRemote", iPersonne);

            IVille iVille = new VilleDao();
            registry.bind("villeRemote", iVille);

            IRue iRue = new RueDao();
            registry.bind("rueRemote", iRue);


            System.out.println("Serveur lance sur le port 5003");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
