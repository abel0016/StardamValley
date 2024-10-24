import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class GestionFPropiedades {
    //Rutas de los archivos de configuración
    public static final String RUTA_FICHERO_CONF = "./resources/default_config.properties";
    public static final String RUTA_FICHERO_CONF_PERS = "./resources/personalized_config.properties";
    private static Properties propiedades = new Properties();

    //Instancia única del Singleton
    private static GestionFPropiedades conf;

    //Constructor privado para evitar la creación de nuevas instancias
    private GestionFPropiedades() {}

    //Metodo para obtener la única instancia de la clase
    public static GestionFPropiedades getInstancia() {
        if (conf == null) {
            conf = new GestionFPropiedades();
        }
        return conf;
    }

    //Crear Fichero por defecto
    public void crearFicheroPropiedades() {
        propiedades.setProperty("filasHuerto", "4");
        propiedades.setProperty("columnasHuerto", "4");
        propiedades.setProperty("presupuestoInicial", "1000");
        propiedades.setProperty("estacionInicial", "PRIMAVERA");
        propiedades.setProperty("diasPorEstacion", "30");

        try (FileOutputStream fos = new FileOutputStream(RUTA_FICHERO_CONF)) {
            propiedades.store(fos,"Configuracion por defecto");
        } catch (IOException e) {
            System.out.println("No se ha podido guardar la configuracion por defecto");
            e.printStackTrace();
        }
    }

    //Establecer o actualizar una propiedad en el archivo de configuración personalizado
    public void setPropiedad(String clave, String valor) {
        propiedades.setProperty(clave, valor);
    }

    //Guardar los cambios realizados en el archivo de configuración personalizado
    public void guardarCambios() {
        try (FileOutputStream fos = new FileOutputStream(RUTA_FICHERO_CONF_PERS)) {
            propiedades.store(fos,"Configuracion personalizada");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Obtener una propiedad del archivo de configuración
    public String getPropiedad(String clave) {
        Path pathPersonalizado = Paths.get(RUTA_FICHERO_CONF_PERS);
        Path pathPorDefecto = Paths.get(RUTA_FICHERO_CONF);
        if (Files.exists(pathPorDefecto)){
            try (FileInputStream fis = new FileInputStream(RUTA_FICHERO_CONF)) {
                propiedades.load(fis);
                return propiedades.getProperty(clave);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else if (Files.exists(pathPersonalizado)) {
            try (FileInputStream fis = new FileInputStream(RUTA_FICHERO_CONF_PERS)) {
                propiedades.load(fis);
                return propiedades.getProperty(clave);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return "";
    }

    //Eliminar el archivo de configuración personalizado o por defecto
    public void eliminarFicheroPropiedades() {
        try {
            Path pathPersonalizado = Paths.get(RUTA_FICHERO_CONF_PERS);
            Path pathPorDefecto = Paths.get(RUTA_FICHERO_CONF);
            if (Files.exists(pathPersonalizado)) {
                Files.delete(pathPersonalizado);
                System.out.println("Archivo de configuración personalizado eliminado");
            } else if (Files.exists(pathPorDefecto)) {
                Files.delete(pathPorDefecto);
                System.out.println("Archivo de configuración por defecto eliminado");
            } else {
                System.out.println("No existe ningun archivo de configuración guardado");
            }

        } catch (IOException e) {
            System.out.println("Error en la eliminación de los archivos .properties");
            e.printStackTrace();
        }
    }
}
