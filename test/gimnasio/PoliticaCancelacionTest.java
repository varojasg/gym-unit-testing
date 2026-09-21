package gimnasio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class PoliticaCancelacionTest {

	@Test
	void testCalcularTarifaDeCancelacion_CancelacionTardia() {
		
		//Arrange
		PoliticaCancelacion politica = new PoliticaCancelacion(8, 20);
		LocalDateTime fechaReserva = LocalDateTime.now().plusHours(5);
		
		double resEsperado = 10.0;
		//Act
		
		double resultado = politica.calcularTarifaCancelacion(fechaReserva);
		
		//Assert
		assertEquals(resEsperado,resultado);
		
	}
	
	
	@Test
	void testCalcularTarifaDeCancelacion_CancelacionAnticipada() {
		
		//Arrange
		PoliticaCancelacion politica = new PoliticaCancelacion(9, 20);
		LocalDateTime fechaReserva = LocalDateTime.now().plusHours(10);
		
		double resEsperado = 0.0;
		//Act
		
		double resultado = politica.calcularTarifaCancelacion(fechaReserva);
		
		//Assert
		assertEquals(resEsperado,resultado);
		
	}
	
	@Test
	void testPuedeCancelar_EnLimiteDePenalizaciones() {
		//Arrange
		PoliticaCancelacion politica = new PoliticaCancelacion(8, 3);
		Socio socio = mock(Socio.class);
		
		when(socio.getContadorPenalizaciones()).thenReturn(3);
		
		//Act
		boolean resultado = politica.puedeCancelar(socio);
		
		//Assert
		assertFalse(resultado);
	}
}
