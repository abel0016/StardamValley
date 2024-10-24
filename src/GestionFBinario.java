import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestionFBinario {

    //Ruta del archivo binario
    public static final String RUTA_FICHERO_BINARIO = "./resources/stardam_valley.bin";

    //Crear el archivo binario
    public static void crearFicheroBinario() {
        try {
            Path pathBinario = Paths.get(RUTA_FICHERO_BINARIO);
            Files.createFile(pathBinario);
        } catch (IOException e) {
            System.out.println("No se pudo crear el fichero binario");
            e.printStackTrace();
        }
    }

    // Eliminar el fichero binario de la partida guardada
    public static void eliminarPartidaGuardada() {
        try {
            Path pathBinario = Paths.get(RUTA_FICHERO_BINARIO);
            if (existeFicheroBinario()) {
                Files.delete(pathBinario);
                System.out.println("Archivo de partida guardada eliminadoz");
            }
        } catch (IOException e) {
            System.out.println("No se pudo eliminar el archivo de partida guardada");
            e.printStackTrace();
        }
    }

    // Comprobar si existe el archivo binario
    public static boolean existeFicheroBinario() {
        return Files.exists(Paths.get(RUTA_FICHERO_BINARIO));
    }

    // Cargar la partida guardada
    public static Granja cargarPartidaGuardada() {
        Granja granja = null;
        try (FileInputStream fis = new FileInputStream(RUTA_FICHERO_BINARIO);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            granja = (Granja) ois.readObject();
            System.out.println("Partida guardada cargada");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar la partida");
            e.printStackTrace();
        }
        return granja;
    }

    // Guardar la partida
    public static void guardarPartida(Granja granja) {
        try (FileOutputStream fos = new FileOutputStream(RUTA_FICHERO_BINARIO);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(granja);
            System.out.println("Partida guardada");

        } catch (IOException e) {
            System.out.println("Error al guardar la partida");
            e.printStackTrace();
        }
    }
}