package gimnasio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SocioTest {

	@Test
	void testPuedeTomarClase_NivelInsuficiente() {
		 
		//Arrange
		Socio socio = new Socio("S-01", "Valeria","Rojas", "989808266","BASIC",3);
		
		//Act
		boolean resultado = socio.puedeTomarClase("AVANZADO");
		
		//Assert
		assertFalse(resultado);
		
	}

	
	@Test
	void testIncrementarPenalizacion() {

	    // Arrange
	    Socio socio = new Socio("S-02", "Oscar", "Vara", "989808266", "VIP", 3);
	    int valorEsperado = 1;

	    // Act
	    socio.incrementarPenalizacion();
	    int valorResultante = socio.getContadorPenalizaciones();

	    // Assert
	    assertEquals(valorEsperado, valorResultante);
	}
}
