package br.com.caelum.leilao.matematica;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatematicaMalucaTest {
	
	@Test
	public void deveMultiplicarNumeroMaioresQue30() {
		MatematicaMaluca matematica = new MatematicaMaluca();
		assertEquals(50*4, matematica.contaMaluca(50));
	}
	
	@Test
	public void deveMultiplicarNumeroMaioresQue10EmenoresQue30() {
		MatematicaMaluca matematica = new MatematicaMaluca();
		assertEquals(20*3, matematica.contaMaluca(20));
	}
	
	@Test
	public void deveMultiplicarNumeroMenoresQue10() {
		MatematicaMaluca matematica = new MatematicaMaluca();
		assertEquals(5*2, matematica.contaMaluca(5));
	}

}
