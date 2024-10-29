import java.util.concurrent.locks.ReentrantLock;

public class Producto extends Thread{
   
    static PrecioProducto producto[];
    static ReentrantLock cerrojo;
    int num;

    public Producto(int num){
        cerrojo = new ReentrantLock();
        producto = new PrecioProducto[10];
        this.num = num;
    }
    public static void mostrarProducto(){
        entrada_seccion_critica();
        for (int i = 0; i < producto.length; i++) {
            if (producto[i]!=null) 
            System.out.println(producto[i].getProveedor()+" precio: "+producto[i].getPrecio());
        }
        
        salida_seccion_critica() ;
    }

    public void insertarProducto(PrecioProducto precioProducto, int posicion){
        entrada_seccion_critica();
        if (posicion<10){
            producto[posicion]=precioProducto;
        }
        salida_seccion_critica();
    }

    public void run(){
        for (int i = 0; i < 10; i++) {
            if (num==1){
            producto[i]= new PrecioProducto(getName()+i, i*10+1);
            } else if (num==2) {
                producto[i]= new PrecioProducto(getName()+i, i*10+100);
            } else {
                producto[i]= new PrecioProducto(getName()+i, i*10+1000);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void entrada_seccion_critica(){
        cerrojo.lock();
        }

    public static void salida_seccion_critica(){
        cerrojo.unlock();
    }
        

}

class PrecioProducto {
    private String proveedor;
    private float precio;
    /* Getters y Setters precios */
    public PrecioProducto(String proveedor, float precio) {
        this.proveedor = proveedor;
        this.precio = precio;
    }
    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
}