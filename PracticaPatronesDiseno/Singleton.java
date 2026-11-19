
class ConfiguracionConcesionaria {

    
    private static ConfiguracionConcesionaria instancia;

    private String nombreEmpresa;
    private String moneda;
    private double impuesto;

    
    private ConfiguracionConcesionaria() {

        nombreEmpresa = "AutoVenta";
        moneda = "Bolivianos";
        impuesto = 13.0;

        System.out.println("Configuracion creada.");
    }

    
    public static ConfiguracionConcesionaria getInstancia() {

        if (instancia == null) {

            instancia = new ConfiguracionConcesionaria();
        }

        return instancia;
    }

    
    public void mostrarConfiguracion() {

        System.out.println("Empresa: " + nombreEmpresa);
        System.out.println("Moneda: " + moneda);
        System.out.println("Impuesto: " + impuesto + "%");
    }
}

public class Singleton {

    public static void main(String[] args) {

        System.out.println("=== SISTEMA AUTOVENTA ===");

        
        ConfiguracionConcesionaria config1 =
                ConfiguracionConcesionaria.getInstancia();

        
        ConfiguracionConcesionaria config2 =
                ConfiguracionConcesionaria.getInstancia();

        
        ConfiguracionConcesionaria config3 =
                ConfiguracionConcesionaria.getInstancia();

        System.out.println("\nCONFIGURACION GENERAL:");

        config1.mostrarConfiguracion();

        
        System.out.println("\nCOMPROBACION:");

        System.out.println("config1 == config2: " + (config1 == config2));

        System.out.println("config2 == config3: " + (config2 == config3));
    }
}
