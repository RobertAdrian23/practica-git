
class Vehiculo {

    private String marca;
    private String modelo;
    private int anio;
    private String color;
    private String motor;
    private String combustible;
    private String transmision;

    private Vehiculo(Builder builder) {

        this.marca = builder.marca;
        this.modelo = builder.modelo;
        this.anio = builder.anio;
        this.color = builder.color;
        this.motor = builder.motor;
        this.combustible = builder.combustible;
        this.transmision = builder.transmision;
    }

    public void mostrarInformacion() {

        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Anio: " + anio);
        System.out.println("Color: " + color);
        System.out.println("Motor: " + motor);
        System.out.println("Combustible: " + combustible);
        System.out.println("Transmision: " + transmision);
    }

    public static class Builder {

        private String marca;
        private String modelo;
        private int anio;
        private String color;
        private String motor;
        private String combustible;
        private String transmision;

        public Builder(String marca, String modelo) {

            this.marca = marca;
            this.modelo = modelo;
        }

        public Builder anio(int anio) {

            this.anio = anio;
            return this;
        }

        public Builder color(String color) {

            this.color = color;
            return this;
        }

        public Builder motor(String motor) {

            this.motor = motor;
            return this;
        }

        public Builder combustible(String combustible) {

            this.combustible = combustible;
            return this;
        }

        public Builder transmision(String transmision) {

            this.transmision = transmision;
            return this;
        }

        public Vehiculo build() {

            return new Vehiculo(this);
        }
    }
}

public class Builder {

    public static void main(String[] args) {

        System.out.println("=== SISTEMA AUTOVENTA ===");

        Vehiculo vehiculo1 = new Vehiculo.Builder("Toyota", "Corolla")
                .anio(2024)
                .color("Blanco")
                .motor("1.8L")
                .combustible("Gasolina")
                .transmision("Automatica")
                .build();

        System.out.println("\nVEHICULO 1:");
        vehiculo1.mostrarInformacion();

        Vehiculo vehiculo2 = new Vehiculo.Builder("Suzuki", "Swift")
                .anio(2025)
                .color("Rojo")
                .motor("1.2L")
                .combustible("Gasolina")
                .transmision("Manual")
                .build();

        System.out.println("\nVEHICULO 2:");
        vehiculo2.mostrarInformacion();
    }
}
