// RemoteImplementation.java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteImplementation extends UnicastRemoteObject implements RemoteInterface {
    protected RemoteImplementation() throws RemoteException {
        super();
    }

    public String sayHello(String name) throws RemoteException {
        return "Hello, " + name + "! This is from the remote server. \n" +
                "Shaikh";
    }
}
