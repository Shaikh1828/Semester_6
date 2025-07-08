// Server.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            RemoteImplementation obj = new RemoteImplementation();
            Registry registry = LocateRegistry.createRegistry(1099); // default RMI port
            registry.rebind("HelloService", obj);
            System.out.println("Server is running and waiting for clients...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
