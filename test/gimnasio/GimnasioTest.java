package gimnasio;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class GimnasioTest {

	@Test
	void testInstructorPuedeImpartirClase() {
		//Arrange
		Gimnasio gimnasio = new Gimnasio ("SmartFit", "Lima");
		Instructor instructor = mock(Instructor.class);
		
		gimnasio.agregarInstructor(instructor);
		when(instructor.puedeImpartir("Yoga")).thenReturn(true);
		
		//Act 
		boolean resultado = gimnasio.puedeImpartirClase(instructor, "Yoga");
		
		//Assert
		assertTrue(resultado);
	}

}
