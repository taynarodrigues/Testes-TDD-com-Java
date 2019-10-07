package br.com.caelum.leilao.servico;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private double media = 0;
	//atributo da classe
	private List<Lance> maiores;

	public void avalia(Leilao leilao) {
		
		double total = 0;
		for(Lance lance : leilao.getLances()) {
			if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
			if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
			total += lance.getValor();
		}
		if(total == 0) {
			media = 0;
			return;
		}
		media = total / leilao.getLances().size();
		
		maiores = new ArrayList<Lance>(leilao.getLances());
		//ordenar o array
		Collections.sort(maiores, new Comparator<Lance>() {

			public int compare(Lance o1, Lance o2) {
				//De forma descrescente
				if(o1.getValor() < o2.getValor()) return 1;
				if(o1.getValor() > o2.getValor()) return -1;
				return 0;
			}
		});
		//pegar os três primeiros lances
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());	//checar o valor da lista
	}
	
	//criar um gettter para a Lista
	public List<Lance> getTresMaiores() {
		return maiores;
	}
		
	public double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public double getMenorLance() {
		return menorDeTodos;
	}
	public double getMedia() {
		return media;
	}


}
