package gimnasio;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class IntegracionTest {

	@Test
	void testCumpleRequisitosClaseGrupal() {
		
		//Arrange
		Socio socio = new Socio("S-03","Valeria", "Rojas","989808266","PREMIUM",3);
		Instructor instructor = new Instructor("Carlos","Yoga");
		Sala sala = new Sala("Sala Zen", 20);
		Equipamiento equipamiento = new Equipamiento("Mat", "Yoga");
		
		equipamiento.realizarMantenimiento();
		sala.agregarEquipamiento(equipamiento);
		
		ClaseGrupal clase = new ClaseGrupal("C-01", "Yoga", instructor, 20, "INTERMEDIO", LocalDateTime.now().plusDays(1),60);
		
		sala.agregarClase(clase);
		
		//Act 
		boolean resultado = clase.cumpleRequisitos(socio);
		
		//Assert
		assertTrue(resultado);
		
	}
	
	@Test
    void testCrearReservaIntegraSocioYClase() {

        // Arrange
        Socio socio = new Socio(
                "S-04",
                "Briguitte",
                "Carhuaz",
                "989808267",
                "PREMIUM",
                3
        );

        Instructor instructor = new Instructor(
                "Andrea",
                "Yoga"
        );

        Sala sala = new Sala(
                "Sala Principal",
                10
        );

        Equipamiento equipamiento = new Equipamiento(
                "Mat",
                "Yoga"
        );

        equipamiento.realizarMantenimiento();
        sala.agregarEquipamiento(equipamiento);

        ClaseGrupal clase = new ClaseGrupal(
                "C-02",
                "Yoga",
                instructor,
                10,
                "INTERMEDIO",
                LocalDateTime.now().plusDays(1),
                60
        );

        sala.agregarClase(clase);

        // Act
        Reserva reserva = new Reserva(socio, clase);

        // Assert
        assertEquals("ACTIVA", reserva.getEstado());
        assertEquals(1, socio.getReservasActivas().size());
        assertEquals(1, clase.getReservasActivas().size());
    }
	
}
