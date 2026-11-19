
import java.util.Scanner;


interface Notificacion {

    void enviar(String mensaje);
}


class NotificacionCorreo implements Notificacion {

    @Override
    public void enviar(String mensaje) {

        System.out.println("Enviando correo electronico...");
        System.out.println("Mensaje: " + mensaje);
    }
}

class NotificacionSMS implements Notificacion {

    @Override
    public void enviar(String mensaje) {

        System.out.println("Enviando mensaje SMS...");
        System.out.println("Mensaje: " + mensaje);
    }
}

class NotificacionPush implements Notificacion {

    @Override
    public void enviar(String mensaje) {

        System.out.println("Enviando notificacion Push...");
        System.out.println("Mensaje: " + mensaje);
    }
}

abstract class CreadorNotificacion {

    public abstract Notificacion crearNotificacion();

    public void notificarCliente(String mensaje) {

        Notificacion notificacion = crearNotificacion();

        notificacion.enviar(mensaje);
    }
}

class CreadorCorreo extends CreadorNotificacion {

    @Override
    public Notificacion crearNotificacion() {

        return new NotificacionCorreo();
    }
}

class CreadorSMS extends CreadorNotificacion {

    @Override
    public Notificacion crearNotificacion() {

        return new NotificacionSMS();
    }
}

class CreadorPush extends CreadorNotificacion {

    @Override
    public Notificacion crearNotificacion() {

        return new NotificacionPush();
    }
}

public class Factory_Method {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        CreadorNotificacion creador = null;

        System.out.println("=== SISTEMA AUTOVENTA ===");
        System.out.println("ENVIO DE NOTIFICACIONES");
        System.out.println("1. Correo electronico");
        System.out.println("2. Mensaje SMS");
        System.out.println("3. Notificacion Push");

        System.out.print("Seleccione una opcion: ");

        int opcion = entrada.nextInt();

        switch (opcion) {

            case 1:
                creador = new CreadorCorreo();
                break;

            case 2:
                creador = new CreadorSMS();
                break;

            case 3:
                creador = new CreadorPush();
                break;

            default:
                System.out.println("Opcion no valida.");
                entrada.close();
                return;
        }

        entrada.nextLine();

        System.out.print("Ingrese el mensaje para el cliente: ");
        String mensaje = entrada.nextLine();

        System.out.println("\n=== RESULTADO ===");

        creador.notificarCliente(mensaje);

        entrada.close();
    }
}

