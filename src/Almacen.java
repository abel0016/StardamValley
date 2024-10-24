import java.util.HashMap;
import java.util.Map;

public class Almacen {

    HashMap<Semilla, Integer> almacen=new HashMap<>();

    public void mostrarAlmacen() {
        if (almacen.isEmpty()) {
            System.out.println("El almacén está vacío");
        } else {
            for (Semilla semilla : almacen.keySet()) {
                System.out.println(semilla.getNombre() + ": " + almacen.get(semilla) + " frutos");
            }
        }
    }
    public void añadirCosecha(Map<Semilla, Integer> nuevaCosecha) {
        for (Map.Entry<Semilla, Integer> entrada : nuevaCosecha.entrySet()) {
            Semilla semilla = entrada.getKey();
            int cantidad = entrada.getValue();
            // Añadir la cantidad de frutos cosechados al almacén
            almacen.put(semilla, almacen.getOrDefault(semilla, 0) + cantidad);
            System.out.println("Se han añadido " + cantidad + " frutos de " + semilla.getNombre() + " al almacén");
        }
    }
    public int venderFrutos() {
        int gananciasTotales = 0;
        //Recorremos el almacen con KeySet que me devuelve las semillas almacenadas
        for (Semilla semilla : almacen.keySet()) {
            int cantidad = almacen.get(semilla);
            int gananciaSemilla = cantidad*semilla.getPrecioVentaFruto();
            System.out.println("Se han vendido " + cantidad + " unidades de " + semilla.getNombre() + " por " + gananciaSemilla + "€");
            gananciasTotales += gananciaSemilla;
        }
        //Vaciamos el almacen después de vender
        almacen.clear();
        System.out.println("Ganancias totales: " + gananciasTotales + "€");
        return gananciasTotales;
    }

}
