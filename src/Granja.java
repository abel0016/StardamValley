import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Granja implements Serializable {

    private int dia_juego;
    private int diaPorEstacion;
    private Estacion estacion;
    private double presupuesto;
    private Tienda tienda;
    private Almacen almacen;
    private List <Semilla> semillasDisponibles;
    private Map semillasPorEstacion;
    private Map semillasPorId;


    GestionFPropiedades conf=GestionFPropiedades.getInstancia();

    private void cambiarEstacion() {
        List<Estacion> estaciones = List.of(Estacion.PRIMAVERA,Estacion.VERANO,Estacion.OTONIO,Estacion.INVIERNO);
        int duracionEstacion=Integer.parseInt(conf.getPropiedad("diasPorEstacion"));
        if (diaPorEstacion>duracionEstacion){


        }

    }

    public void iniciar_nuevo_dia(){
        dia_juego++;
        diaPorEstacion++;
        if(dia_juego)
    }

    public void ciudarHuerto(){

    }
    public void plantarCultivosPorColumna(int columna){

    }
    public void venderFrutos(){

    }
    public void mostrarGranjaInfo(){

    }

    public int getDia_juego() {
        return dia_juego;
    }

    public void setDia_juego(int dia_juego) {
        this.dia_juego = dia_juego;
    }

    public int getDiaPorEstacion() {
        return diaPorEstacion;
    }

    public void setDiaPorEstacion(int diaPorEstacion) {
        this.diaPorEstacion = diaPorEstacion;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public List<Semilla> getSemillasDisponibles() {
        return semillasDisponibles;
    }

    public void setSemillasDisponibles(List<Semilla> semillasDisponibles) {
        this.semillasDisponibles = semillasDisponibles;
    }

    public Map getSemillasPorEstacion() {
        return semillasPorEstacion;
    }

    public void setSemillasPorEstacion(Map semillasPorEstacion) {
        this.semillasPorEstacion = semillasPorEstacion;
    }

    public Map getSemillasPorId() {
        return semillasPorId;
    }

    public void setSemillasPorId(Map semillasPorId) {
        this.semillasPorId = semillasPorId;
    }
}
