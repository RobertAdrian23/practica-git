
interface Vehiculo {

    double obtenerPrecio();

    String obtenerDescripcion();
}

class VehiculoBasico implements Vehiculo {

    @Override
    public double obtenerPrecio() {

        return 85000;
    }

    @Override
    public String obtenerDescripcion() {

        return "Toyota Corolla 2024";
    }
}

abstract class ServicioAdicional implements Vehiculo {

    protected Vehiculo vehiculo;

    public ServicioAdicional(Vehiculo vehiculo) {

        this.vehiculo = vehiculo;
    }
}

class Seguro extends ServicioAdicional {

    public Seguro(Vehiculo vehiculo) {

        super(vehiculo);
    }

    @Override
    public double obtenerPrecio() {

        return vehiculo.obtenerPrecio() + 2000;
    }

    @Override
    public String obtenerDescripcion() {

        return vehiculo.obtenerDescripcion() + " + Seguro vehicular";
    }
}

class GPS extends ServicioAdicional {

    public GPS(Vehiculo vehiculo) {

        super(vehiculo);
    }

    @Override
    public double obtenerPrecio() {

        return vehiculo.obtenerPrecio() + 800;
    }

    @Override
    public String obtenerDescripcion() {

        return vehiculo.obtenerDescripcion() + " + GPS";
    }
}

class GarantiaExtendida extends ServicioAdicional {

    public GarantiaExtendida(Vehiculo vehiculo) {

        super(vehiculo);
    }

    @Override
    public double obtenerPrecio() {

        return vehiculo.obtenerPrecio() + 1500;
    }

    @Override
    public String obtenerDescripcion() {

        return vehiculo.obtenerDescripcion() + " + Garantia extendida";
    }
}

public class Decorator {

    public static void main(String[] args) {

        System.out.println("=== SISTEMA AUTOVENTA ===");

        Vehiculo vehiculo = new VehiculoBasico();

        System.out.println("\nVEHICULO ORIGINAL:");
        System.out.println(vehiculo.obtenerDescripcion());
        System.out.println("Precio: Bs. " + vehiculo.obtenerPrecio());

        vehiculo = new Seguro(vehiculo);

        vehiculo = new GPS(vehiculo);

        vehiculo = new GarantiaExtendida(vehiculo);

        System.out.println("\nVEHICULO CON SERVICIOS ADICIONALES:");
        System.out.println(vehiculo.obtenerDescripcion());
        System.out.println("Precio final: Bs. " + vehiculo.obtenerPrecio());
    }
}
