package br.com.caelum.leilao.anoBissexto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AnoBissextoTest {
	
	@Test
	public void deveRetornarAnoBissexto() {
		AnoBissexto anoBissexto = new AnoBissexto();
		
		assertEquals(true, anoBissexto.isAnoBissexto(2016));
		assertEquals(true, anoBissexto.isAnoBissexto(2012));
	}
	
	@Test
	public void naoDeveRetornarAnoBissexto() {
		AnoBissexto anoBissexto = new AnoBissexto();
		assertEquals(false, anoBissexto.isAnoBissexto(2019));
		assertEquals(false, anoBissexto.isAnoBissexto(2011));
	}

}
