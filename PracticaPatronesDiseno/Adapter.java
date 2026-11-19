
interface SistemaPago {

    void realizarPago(double monto);
}

class PlataformaExterna {

    public void ejecutarTransaccion(double cantidad) {

        System.out.println("Procesando pago mediante plataforma externa...");
        System.out.println("Cantidad procesada: Bs. " + cantidad);
    }
}

class AdaptadorPago implements SistemaPago {

    private PlataformaExterna plataforma;

    public AdaptadorPago(PlataformaExterna plataforma) {

        this.plataforma = plataforma;
    }

    @Override
    public void realizarPago(double monto) {

        plataforma.ejecutarTransaccion(monto);
    }
}

class Venta {

    private SistemaPago sistemaPago;

    public Venta(SistemaPago sistemaPago) {

        this.sistemaPago = sistemaPago;
    }

    public void comprarVehiculo(String vehiculo, double precio) {

        System.out.println("\n=== PROCESANDO COMPRA ===");
        System.out.println("Vehiculo: " + vehiculo);
        System.out.println("Precio: Bs. " + precio);

        sistemaPago.realizarPago(precio);

        System.out.println("Compra realizada correctamente.");
    }
}

public class Adapter {

    public static void main(String[] args) {

        System.out.println("=== SISTEMA AUTOVENTA ===");

        PlataformaExterna plataforma = new PlataformaExterna();

        SistemaPago adaptador = new AdaptadorPago(plataforma);

        Venta venta = new Venta(adaptador);

        venta.comprarVehiculo("Toyota Corolla 2024", 85000);
    }
}
