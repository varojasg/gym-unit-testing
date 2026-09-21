package gimnasio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Gimnasio {

    private String nombre;
    private String direccion;
    private List<Sala> salas;
    private List<Instructor> instructores;
    private PoliticaCancelacion politicaCancelacion;
    private List<Socio> socios;

    public Gimnasio(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.salas = new ArrayList<>();
        this.instructores = new ArrayList<>();
        this.socios = new ArrayList<>();
        this.politicaCancelacion = new PoliticaCancelacion(12, 3);
    }

    public void agregarSala(Sala sala) {
        if (!salas.contains(sala)) {
            salas.add(sala);
            sala.setGimnasio(this);
        }
    }

    public void agregarInstructor(Instructor instructor) {
        if (!instructores.contains(instructor)) {
            instructores.add(instructor);
            instructor.setGimnasio(this);
        }
    }

    public void registrarSocio(Socio socio) {
        if (!socios.contains(socio)) {
            socios.add(socio);
        }
    }

    public boolean puedeImpartirClase(Instructor instructor, String tipoClase) {
        return instructores.contains(instructor)
                && instructor.puedeImpartir(tipoClase);
    }
 // Getters y métodos adicionales
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	public List<Instructor> getInstructores() {
		return instructores;
	}

	public void setInstructores(List<Instructor> instructores) {
		this.instructores = instructores;
	}

	public PoliticaCancelacion getPoliticaCancelacion() {
		return politicaCancelacion;
	}

	public void setPoliticaCancelacion(PoliticaCancelacion politicaCancelacion) {
		this.politicaCancelacion = politicaCancelacion;
	}

	public List<Socio> getSocios() {
		return socios;
	}

	public void setSocios(List<Socio> socios) {
		this.socios = socios;
	}

    
}

class Sala {

    private String nombre;
    private int capacidad;
    private List<Equipamiento> equipamientos;
    private Gimnasio gimnasio;
    private List<ClaseGrupal> clases;

    public Sala(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.equipamientos = new ArrayList<>();
        this.clases = new ArrayList<>();
    }

    public void agregarEquipamiento(Equipamiento equipamiento) {
        if (!equipamientos.contains(equipamiento)) {
            equipamientos.add(equipamiento);
            equipamiento.setSala(this);
        }
    }

    public void agregarClase(ClaseGrupal clase) {
        if (!clases.contains(clase)) {
            clases.add(clase);
            clase.setSala(this);
        }
    }

    public boolean tieneEquipamientoNecesario(String tipoEquipamiento) {
        return equipamientos.stream()
                .anyMatch(e -> e.getNombre().equals(tipoEquipamiento)
                        && !e.necesitaMantenimiento());
    }
    // Setters y getters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public List<Equipamiento> getEquipamientos() {
		return equipamientos;
	}

	public void setEquipamientos(List<Equipamiento> equipamientos) {
		this.equipamientos = equipamientos;
	}

	public Gimnasio getGimnasio() {
		return gimnasio;
	}

	public void setGimnasio(Gimnasio gimnasio) {
		this.gimnasio = gimnasio;
	}

	public List<ClaseGrupal> getClases() {
		return clases;
	}

	public void setClases(List<ClaseGrupal> clases) {
		this.clases = clases;
	}

   
    
    
}

class Equipamiento {

    private String nombre;
    private String tipo;
    private LocalDate ultimoMantenimiento;
    private Sala sala;

    public Equipamiento(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public boolean necesitaMantenimiento() {
        return ultimoMantenimiento == null
                || ultimoMantenimiento.isBefore(LocalDate.now().minusMonths(3));
    }

    public void realizarMantenimiento() {
        this.ultimoMantenimiento = LocalDate.now();
    }
 // Setters y getters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDate getUltimoMantenimiento() {
		return ultimoMantenimiento;
	}

	public void setUltimoMantenimiento(LocalDate ultimoMantenimiento) {
		this.ultimoMantenimiento = ultimoMantenimiento;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

    
}

class Instructor {

    private String nombre;
    private String especialidad;
    private List<Certificacion> certificaciones;
    private Gimnasio gimnasio;

    public Instructor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.certificaciones = new ArrayList<>();
    }

    public boolean puedeImpartir(String tipoClase) {
        return certificaciones.stream()
                .anyMatch(c -> c.getTipoClase().equals(tipoClase)
                        && c.isVigente());
    }

    public void agregarCertificacion(Certificacion certificacion) {
        if (!certificaciones.contains(certificacion)) {
            certificaciones.add(certificacion);
            certificacion.setInstructor(this);
        }
    }

    public boolean estaDisponible(LocalDateTime fechaHora) {
        // Implementar lógica de disponibilidad
        return true;
    }
    
    
    // Setters y getters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public List<Certificacion> getCertificaciones() {
		return certificaciones;
	}

	public void setCertificaciones(List<Certificacion> certificaciones) {
		this.certificaciones = certificaciones;
	}

	public Gimnasio getGimnasio() {
		return gimnasio;
	}

	public void setGimnasio(Gimnasio gimnasio) {
		this.gimnasio = gimnasio;
	}

   
    
    
}

class Certificacion {

    private String tipoClase;
    private LocalDate fechaExpedicion;
    private LocalDate fechaExpiracion;
    private Instructor instructor;

    public Certificacion(String tipoClase, int añosValidez) {
        this.tipoClase = tipoClase;
        this.fechaExpedicion = LocalDate.now();
        this.fechaExpiracion = fechaExpedicion.plusYears(añosValidez);
    }
    // Setters y getters
    public boolean isVigente() {
        return LocalDate.now().isBefore(fechaExpiracion);
    }

	public String getTipoClase() {
		return tipoClase;
	}

	public void setTipoClase(String tipoClase) {
		this.tipoClase = tipoClase;
	}

	public LocalDate getFechaExpedicion() {
		return fechaExpedicion;
	}

	public void setFechaExpedicion(LocalDate fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public LocalDate getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(LocalDate fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

   
}

class Socio {

    private String id;
    private String nombre;
    private String apellido;
    private String telefono;
    private LocalDateTime fechaInscripcion;
    private String tipoMembresia;
    private int nivel; // 1-5 según experiencia
    private int contadorPenalizaciones;
    private List<Reserva> reservas;
    private Gimnasio gimnasio;

    public Socio(
            String id,
            String nombre,
            String apellido,
            String telefono,
            String tipoMembresia,
            int nivel) {

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.tipoMembresia = tipoMembresia;
        this.nivel = nivel;
        this.fechaInscripcion = LocalDateTime.now();
        this.reservas = new ArrayList<>();
        this.contadorPenalizaciones = 0;
    }

    public boolean puedeReservar() {
        return getReservasActivas().size() < getLimiteReservas()
                && contadorPenalizaciones < 3;
    }

    public boolean puedeTomarClase(String dificultad) {
        return switch (dificultad) {
            case "PRINCIPIANTE" -> true;
            case "INTERMEDIO" -> nivel >= 2;
            case "AVANZADO" -> nivel >= 4;
            default -> false;
        };
}

    public List<Reserva> getReservasActivas() {
        return reservas.stream()
                .filter(r -> r.getEstado().equals("ACTIVA"))
                .toList();
    }

    private int getLimiteReservas() {
        return switch (tipoMembresia) {
            case "BASIC" -> 3;
            case "PREMIUM" -> 5;
            case "VIP" -> 10;
            default -> 0;
        };
    }

    public int incrementarPenalizacion() {
        return contadorPenalizaciones++;
    }
    
    // Método para agregar una reserva al socio
    public void agregarReserva(Reserva reserva) {
        if (!reservas.contains(reserva)) { // Evitar duplicados
            reservas.add(reserva);
            System.out.println("Reserva agregada al socio: " + nombre);
        }
    }
 // Resto de métodos
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDateTime getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public String getTipoMembresia() {
		return tipoMembresia;
	}

	public void setTipoMembresia(String tipoMembresia) {
		this.tipoMembresia = tipoMembresia;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getContadorPenalizaciones() {
		return contadorPenalizaciones;
	}

	public void setContadorPenalizaciones(int contadorPenalizaciones) {
		this.contadorPenalizaciones = contadorPenalizaciones;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Gimnasio getGimnasio() {
		return gimnasio;
	}

	public void setGimnasio(Gimnasio gimnasio) {
		this.gimnasio = gimnasio;
	}

    
}

class ClaseGrupal {

    private String id;
    private String nombre;
    private Instructor instructor;
    private int capacidad;
    private String dificultad;
    private LocalDateTime fechaHora;
    private int duracion; // en minutos
    private Sala sala;
    private List<Reserva> reservas;

    public ClaseGrupal(
            String id,
            String nombre,
            Instructor instructor,
            int capacidad,
            String dificultad,
            LocalDateTime fechaHora,
            int duracion) {

        this.id = id;
        this.nombre = nombre;
        this.instructor = instructor;
        this.capacidad = capacidad;
        this.dificultad = dificultad;
        this.fechaHora = fechaHora;
        this.duracion = duracion;
        this.reservas = new ArrayList<>();
    }

    public boolean hayCupoDisponible() {
        return getReservasActivas().size() < capacidad;
    }

    public boolean cumpleRequisitos(Socio socio) {
        return socio.puedeTomarClase(dificultad)
                && instructor.estaDisponible(fechaHora)
                && (sala != null
                && sala.tieneEquipamientoNecesario(
                        getTipoEquipamientoRequerido()));
    }

    private String getTipoEquipamientoRequerido() {
        return switch (nombre) {
            case "Spinning" -> "Bicicleta Spinning";
            case "Yoga" -> "Mat";
            case "Crossfit" -> "Pesas";
            case "Crossfit Intenso" -> "Pesas";
            default -> "General";
        };
    }

    // Método para agregar una reserva a la clase
    public void agregarReserva(Reserva reserva) {
        if (!reservas.contains(reserva)) { // Evitar duplicados
            reservas.add(reserva);
            System.out.println("Reserva agregada a la clase: " + nombre);
        }
    }

    // Método que filtra y devuelve solo reservas ACTIVAS
    public List<Reserva> getReservasActivas() {
        return reservas.stream()
                .filter(reserva -> reserva.getEstado().equals("ACTIVA"))
                .collect(Collectors.toList());
    }
 // Resto de métodos
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

    
}

class Reserva {

    private String id;
    private Socio socio;
    private ClaseGrupal clase;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCancelacion;
    private String estado;
    private boolean asistio;

    public Reserva(Socio socio, ClaseGrupal clase) {

        if (!socio.puedeReservar()) {
            throw new IllegalStateException(
                    "El socio no puede realizar más reservas");
        }

        if (!clase.hayCupoDisponible()) {
            throw new IllegalStateException(
                    "No hay cupo disponible");
        }

        if (!clase.cumpleRequisitos(socio)) {
            throw new IllegalStateException(
                    "El socio no cumple los requisitos para esta clase");
        }

        this.id = generarId();
        this.socio = socio;
        this.clase = clase;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = "ACTIVA";
        this.asistio = false;

        socio.agregarReserva(this);
        clase.agregarReserva(this);
    }

    public void cancelar() {

        if (estado.equals("ACTIVA")) {

            long horasRestantes = ChronoUnit.HOURS.between(
                    LocalDateTime.now(),
                    clase.getFechaHora());

            if (horasRestantes >= 12) {
                estado = "CANCELADA";
                fechaCancelacion = LocalDateTime.now();
            } else {
                socio.incrementarPenalizacion();
                throw new IllegalStateException(
                        "Cancelación tardía - penalización aplicada");
            }
        }
    }
 // Resto de métodos
    private String generarId() {
        return "RES-" + System.currentTimeMillis();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public ClaseGrupal getClase() {
		return clase;
	}

	public void setClase(ClaseGrupal clase) {
		this.clase = clase;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(LocalDateTime fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isAsistio() {
		return asistio;
	}

	public void setAsistio(boolean asistio) {
		this.asistio = asistio;
	}

    
}

class PoliticaCancelacion {

    private int horasMinimas;
    private int maxPenalizaciones;
    private double tarifaCancelacionTardia;

    public PoliticaCancelacion(
            int horasMinimas,
            int maxPenalizaciones) {

        this.horasMinimas = horasMinimas;
        this.maxPenalizaciones = maxPenalizaciones;
        this.tarifaCancelacionTardia = 10.0; // USD
    }

    public boolean puedeCancelar(Socio socio) {
        return socio.getContadorPenalizaciones() < maxPenalizaciones;
    }

    public double calcularTarifaCancelacion(
            LocalDateTime fechaReserva) {

        long horas = ChronoUnit.HOURS.between(
                LocalDateTime.now(),
                fechaReserva);

        return horas < horasMinimas
                ? tarifaCancelacionTardia
                : 0.0;
    }
}