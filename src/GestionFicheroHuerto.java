import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class GestionFicheroHuerto {

    private static final String RUTA_FICHERO="./resources/huerto.dat";
    private static final int TAMANIO_ID_SEMILLA=Integer.BYTES;
    private static final int TAMANIO_REGADA=1;
    private static final int TAMANIO_NUM_DIAS_CRECIMIENTO=Integer.BYTES;
    private static final int TAMANIO_REGISTRO=TAMANIO_ID_SEMILLA+TAMANIO_REGADA+TAMANIO_NUM_DIAS_CRECIMIENTO;
    private static final int VALOR_DEFECTO_ENTERO=-1;
    private static final Boolean VALOR_DEFECTO_BOOLEAN=false;
    GestionFPropiedades conf=GestionFPropiedades.getInstancia();


    public void actualizarHuertoNuevoDia(){
        //aquí queremos que si la semilla fue regada el dia anterior aumentar el valor de numdias
        // (que son los dias que lleva regado) y cambiar el boolean a false.
        //tenemos que leer y escribir simultaneamente en este metodo
        try {
            RandomAccessFile raf=new RandomAccessFile(new File(RUTA_FICHERO),"rw");
            int idsemilla;
            boolean regado;
            int numdias;
            //importante mover el puntero antes de empezar
            raf.seek(0);
            while(raf.getFilePointer()<raf.length()){
                idsemilla=raf.readInt();
                regado=raf.readBoolean();
                //con este condicional, si la semilla estaba regada la cambiamos a su valor por defecto
                //y aumentamos en 1 los dias que lleva regada
                if(regado==true){
                    raf.seek(raf.getFilePointer()-TAMANIO_REGADA);
                    raf.writeBoolean(VALOR_DEFECTO_BOOLEAN);
                    numdias=raf.readInt()+1;
                    raf.seek(raf.getFilePointer()-TAMANIO_NUM_DIAS_CRECIMIENTO);
                    raf.writeInt(numdias);
                }else
                    numdias=raf.readInt();

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void mostrarHuerto(){
        //primero movemos el puntero a la posicion 0 del fichero.
        try {
            RandomAccessFile raf=new RandomAccessFile(new File(RUTA_FICHERO),"r");
            raf.seek(0);
            int filasHuerto = Integer.parseInt(conf.getPropiedad("filasHuerto"));
            int columnasHuerto = Integer.parseInt(conf.getPropiedad("columnasHuerto"));
            int[][] huerto=new int[filasHuerto][columnasHuerto];

            //recorremos el fichero hasta que no haya mas registros
            //con los 2 bucles for recorremos la matriz del huerto para escribir el id, el boolean y el otro id
            //en cada celda
            while(raf.getFilePointer()<raf.length()){
                int idsemilla=raf.readInt();
                boolean regado=raf.readBoolean();
                int numdias=raf.readInt();
                for (int i=0;i<huerto.length;i++){
                    for (int j=0;j<huerto[i].length;j++){
                        System.out.print("["+idsemilla+"-"+regado+"-"+numdias+"]");
                    }
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void crearFicheroHuerto(){
        //comprobamos que existe el fichero properties antes de crear el huerto
        Path path_defecto= Paths.get("./resources/default_config.properties");
        Path path_personalizado=Paths.get("./resources/personalized_config.properties");
        try {
            RandomAccessFile raf=new RandomAccessFile(new File(RUTA_FICHERO),"rw");
            if(Files.exists(path_defecto)||Files.exists(path_personalizado)){
                int filasHuerto = Integer.parseInt(conf.getPropiedad("filasHuerto"));
                int columnasHuerto = Integer.parseInt(conf.getPropiedad("columnasHuerto"));
                //esta variable equivaldría a la cantidad de celdas de nuestro huerto o matriz, es decir
                //si hay 4 filas y 3 columnas habrá 12 registros o celdas en la que cada una contendra 1 int 1 boolean y 1 int
                //lo escribimos de base con los valores por defecto
                int tamanio_huerto=filasHuerto*columnasHuerto;
                for (int i=0;i<tamanio_huerto;i++){
                    raf.writeInt(VALOR_DEFECTO_ENTERO);
                    raf.writeBoolean(VALOR_DEFECTO_BOOLEAN);
                    raf.writeInt(VALOR_DEFECTO_ENTERO);
                }

            }else{
                System.out.println("No hay fichero properties, por lo tanto no sabemos el tamaño del huerto!");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void eliminarFicheroHuerto() {
        File fichero = new File(RUTA_FICHERO);
        if (fichero.exists()) {
            fichero.delete();
            System.out.println("El fichero ha sido eliminado");
            }
        else {
            System.out.println("El fichero no existe");
        }
    }
}
