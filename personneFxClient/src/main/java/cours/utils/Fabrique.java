package cours.utils;


import cours.java.service.IPersonne;
import cours.java.service.IRue;
import cours.java.service.IVille;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Fabrique {
    private static IPersonne iPersonne;
    private static IVille iVille;
    private static IRue iRue;

    private static void init() throws Exception{
        try {
            Registry registry = LocateRegistry.getRegistry(5003);
            iPersonne = (IPersonne) registry.lookup("personneRemote");
            iVille = (IVille) registry.lookup("villeRemote");
            iRue = (IRue) registry.lookup("rueRemote");
        }
        catch(Exception e){
            throw e;
        }
    }

    public static IPersonne getiPersonne() throws  Exception{

        try {
            if(iPersonne == null) {
                init();
            }
            return iPersonne;
        }
        catch(Exception e){
            throw e;
        }

    }

    public static IVille getiVille() throws  Exception{

        try {
            if(iVille == null) {
                init();
            }
            return iVille;
        }
        catch(Exception e){
            throw e;
        }

    }

    public static IRue getiRue() throws  Exception{

        try {
            if(iRue == null) {
                init();
            }
            return iRue;
        }
        catch(Exception e){
            throw e;
        }

    }
}
