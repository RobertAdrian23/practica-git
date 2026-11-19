
class Inventario {

    public boolean verificarDisponibilidad(String vehiculo) {

        System.out.println("Verificando disponibilidad del vehiculo...");

        return true;
    }
}

class ProcesadorPago {

    public void procesarPago(double monto) {

        System.out.println("Procesando pago de Bs. " + monto);
    }
}

class RegistroVenta {

    public void registrar(String cliente, String vehiculo) {

        System.out.println("Registrando venta...");
        System.out.println("Cliente: " + cliente);
        System.out.println("Vehiculo: " + vehiculo);
    }
}

class Facturacion {

    public void generarFactura(String cliente, String vehiculo, double monto) {

        System.out.println("Generando factura...");
        System.out.println("Factura emitida correctamente.");
        System.out.println("Total: Bs. " + monto);
    }
}

class VentaFacade {

    private Inventario inventario;
    private ProcesadorPago pago;
    private RegistroVenta registro;
    private Facturacion facturacion;

    public VentaFacade() {

        inventario = new Inventario();
        pago = new ProcesadorPago();
        registro = new RegistroVenta();
        facturacion = new Facturacion();
    }

    public void realizarVenta(String cliente, String vehiculo, double monto) {

        System.out.println("\n=== INICIANDO VENTA ===");

        boolean disponible = inventario.verificarDisponibilidad(vehiculo);

        if (disponible) {

            pago.procesarPago(monto);

            registro.registrar(cliente, vehiculo);

            facturacion.generarFactura(cliente, vehiculo, monto);

            System.out.println("\nVENTA COMPLETADA EXITOSAMENTE.");

        } else {

            System.out.println("El vehiculo no esta disponible.");
        }
    }
}

public class Facade {

    public static void main(String[] args) {

        System.out.println("=== SISTEMA AUTOVENTA ===");

        VentaFacade venta = new VentaFacade();

        venta.realizarVenta(
            "Carlos Perez",
            "Toyota Corolla 2024",
            85000
        );
    }
}
