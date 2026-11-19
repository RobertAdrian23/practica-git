
abstract class ProcesoVenta {

    public final void realizarVenta() {

        verificarCliente();

        verificarVehiculo();

        procesarPago();

        confirmarVenta();
    }

    private void verificarCliente() {

        System.out.println("Verificando datos del cliente...");
    }

    private void verificarVehiculo() {

        System.out.println("Verificando disponibilidad del vehiculo...");
    }

    protected abstract void procesarPago();

    private void confirmarVenta() {

        System.out.println("Venta confirmada correctamente.");
    }
}

class VentaContado extends ProcesoVenta {

    @Override
    protected void procesarPago() {

        System.out.println("Procesando pago completo en efectivo.");
    }
}

class VentaCredito extends ProcesoVenta {

    @Override
    protected void procesarPago() {

        System.out.println("Evaluando solicitud de credito...");
        System.out.println("Aprobando financiamiento del vehiculo...");
    }
}

public class Template_Method {

    public static void main(String[] args) {

        System.out.println("=== SISTEMA AUTOVENTA ===");

        System.out.println("\nVENTA AL CONTADO:");

        ProcesoVenta contado = new VentaContado();

        contado.realizarVenta();

        System.out.println("\nVENTA A CREDITO:");

        ProcesoVenta credito = new VentaCredito();

        credito.realizarVenta();
    }
}
