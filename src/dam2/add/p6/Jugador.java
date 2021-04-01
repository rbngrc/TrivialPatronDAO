package dam2.add.p6;

public class Jugador {
	String nombre;
	String aciertos;
	String fallos;
	String puntuacion;

	public Jugador(String nombre, String aciertos, String fallos, String puntuacion) {
		super();
		this.nombre = nombre;
		this.aciertos = aciertos;
		this.fallos = fallos;
		this.puntuacion = puntuacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAciertos() {
		return aciertos;
	}

	public void setAciertos(String aciertos) {
		this.aciertos = aciertos;
	}

	public String getFallos() {
		return fallos;
	}

	public void setFallos(String fallos) {
		this.fallos = fallos;
	}

	public String getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}
}
