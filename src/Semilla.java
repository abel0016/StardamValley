import java.util.List;

public class Semilla {
    private int id;
    private String nombre;
    private List<Estacion> estaciones;
    private int diasCrecimiento;
    private int precioCompraSemilla;
    private int precioVentaFruto;
    private int maxFrutos;

    public Semilla(int id, String nombre, List<Estacion> estaciones, int diasCrecimiento, int precioCompraSemilla, int precioVentaFruto, int maxFrutos) {
        this.id = id;
        this.nombre = nombre;
        this.estaciones = estaciones;
        this.diasCrecimiento = diasCrecimiento;
        this.precioCompraSemilla = precioCompraSemilla;
        this.precioVentaFruto = precioVentaFruto;
        this.maxFrutos = maxFrutos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List estaciones) {
        this.estaciones = estaciones;
    }

    public int getDiasCrecimiento() {
        return diasCrecimiento;
    }

    public void setDiasCrecimiento(int diasCrecimiento) {
        this.diasCrecimiento = diasCrecimiento;
    }

    public int getPrecioCompraSemilla() {
        return precioCompraSemilla;
    }

    public void setPrecioCompraSemilla(int precioCompraSemilla) {
        this.precioCompraSemilla = precioCompraSemilla;
    }

    public int getPrecioVentaFruto() {
        return precioVentaFruto;
    }

    public void setPrecioVentaFruto(int precioVentaFruto) {
        this.precioVentaFruto = precioVentaFruto;
    }

    public int getMaxFrutos() {
        return maxFrutos;
    }

    public void setMaxFrutos(int maxFrutos) {
        this.maxFrutos = maxFrutos;
    }
}
