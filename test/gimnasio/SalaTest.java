package gimnasio;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class SalaTest {

	@Test
	void testTieneEquipamiento_TipoNoDisponible() {
		
		//Arrange
		Sala sala = new Sala("Sala Arcoiris", 20);
		Equipamiento equipamiento = mock(Equipamiento.class); 
		
		when(equipamiento.getNombre()).thenReturn("Arco");
		when(equipamiento.necesitaMantenimiento()).thenReturn(false);
		
		sala.agregarEquipamiento(equipamiento);
		//Act
		
		boolean resultado = sala.tieneEquipamientoNecesario("Tapete");
		
		//Assert
		assertFalse(resultado);
		
	}

}
