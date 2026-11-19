
import java.util.ArrayList;
import java.util.List;

interface Observador {

    void actualizar(String mensaje);
}

class Cliente implements Observador {

    private String nombre;

    public Cliente(String nombre) {

        this.nombre = nombre;
    }

    @Override
    public void actualizar(String mensaje) {

        System.out.println(
            "Notificacion para " + nombre + ": " + mensaje
        );
    }
}

class Concesionaria {

    private List<Observador> clientes = new ArrayList<>();

    public void agregarCliente(Observador cliente) {

        clientes.add(cliente);

        System.out.println("Cliente registrado correctamente.");
    }

    public void eliminarCliente(Observador cliente) {

        clientes.remove(cliente);

        System.out.println("Cliente eliminado de las notificaciones.");
    }

    public void notificarClientes(String mensaje) {

        for (Observador cliente : clientes) {

            cliente.actualizar(mensaje);
        }
    }

    public void publicarVehiculo(String vehiculo) {

        System.out.println("\n=== NUEVO VEHICULO DISPONIBLE ===");

        System.out.println("Vehiculo: " + vehiculo);

        notificarClientes(
            "Ha llegado un nuevo vehiculo: " + vehiculo
        );
    }
}

public class Observer {

    public static void main(String[] args) {

        System.out.println("=== SISTEMA AUTOVENTA ===");

        Concesionaria concesionaria = new Concesionaria();

        Cliente cliente1 = new Cliente("Carlos");
        Cliente cliente2 = new Cliente("Maria");
        Cliente cliente3 = new Cliente("Luis");

        System.out.println("\nREGISTRO DE CLIENTES:");

        concesionaria.agregarCliente(cliente1);
        concesionaria.agregarCliente(cliente2);
        concesionaria.agregarCliente(cliente3);

        concesionaria.publicarVehiculo(
            "Toyota Corolla 2024"
        );

        System.out.println("\nELIMINANDO UN CLIENTE:");

        concesionaria.eliminarCliente(cliente2);

        concesionaria.publicarVehiculo(
            "Suzuki Swift 2025"
        );
    }
}
