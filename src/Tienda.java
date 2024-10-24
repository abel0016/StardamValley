import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Tienda {

    private static final int NUM_SEMILLAS_TIENDA = 3;
    //almacén de las semillas del día
    private HashSet<Semilla> tiendaSemillas = new HashSet<>();

    public void generarNuevaTienda(List<Semilla> semillasDisponibles) {
        Random random = new Random();
        //vaciamos lo que hay en la tienda anterior
        tiendaSemillas.clear();

        while (tiendaSemillas.size() < NUM_SEMILLAS_TIENDA && semillasDisponibles.size() > 0) {
            int indiceAleatorio = random.nextInt(semillasDisponibles.size());
            Semilla semillaAleatoria = semillasDisponibles.get(indiceAleatorio);
            tiendaSemillas.add(semillaAleatoria);
        }

        System.out.println("Semillas generadas para la tienda de hoy:");
        for (Semilla semilla : tiendaSemillas) {
            System.out.println(semilla.getNombre());
        }
    }

    public Semilla venderSemillas(int dinero, int numSemillasNecesarias) {
        for (Semilla semilla : tiendaSemillas) {
            int precioSemilla = semilla.getPrecioCompraSemilla();
            // Verificar si el jugador tiene suficiente dinero
            if (dinero >= precioSemilla * numSemillasNecesarias) {
                System.out.println("Has comprado " + numSemillasNecesarias + " semillas de " + semilla.getNombre());
                dinero-=(precioSemilla*numSemillasNecesarias);
                return semilla;
            }
        }
        System.out.println("No tienes suficiente dinero para comprar las semillas");
        return null;
    }
}
