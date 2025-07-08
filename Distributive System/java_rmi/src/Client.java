// Client.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099); // replace 'localhost' with server IP if remote
            RemoteInterface stub = (RemoteInterface) registry.lookup("HelloService");
            String response = stub.sayHello("Friend");
            System.out.println("Response from server: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
