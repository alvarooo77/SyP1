import java.util.concurrent.locks.ReentrantLock;

public class App {
    
    public static void main(String[] args) throws Exception {

        Producto uno = new Producto(1);
        Producto dos = new Producto(2);
        Producto tres = new Producto(3);
        uno.start();
        dos.start();
        tres.start();

        uno.setName("Alvaro");
        dos.setName("Janhin");
        tres.setName("Adrián");

        uno.join();
        dos.join();
        tres.join();

        Producto.mostrarProducto();

    }
}
