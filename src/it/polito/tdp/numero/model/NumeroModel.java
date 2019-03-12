package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {
	
	private final int NMAX = 100;
	private final int TMAX = 8;

	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	public NumeroModel() {
		inGioco = false;
	}

	/**
	 * Avvia nuova partita
	 */
	public void newGame() {
		inGioco = true;
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti = 0;
	}
	
	/**
	 * Metodo per effettuare un tentativo
	 * 
	 * @param t il tentativo
	 * @returnse 1 se il tentativoe' troppo alto, -1 se il tentativo e' troppo basso, 0 se e' giusto
	 */

	public int tentativo(int t) {
		
		// controllo se la partita e' in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita e' terminata");
		}
		// controllo se input e' nel range corretto
		if(!tentativoValido(t)) {
			throw new InvalidParameterException(String.format("Devi inserire un numero tra %d e %d", 1, NMAX));
		}
		//gestisci tentativo
		this.tentativiFatti++;
		if(this.tentativiFatti == this.TMAX) {
			//partita terminata
			this.inGioco = false;
		}
		
		if(t == this.segreto) {
			//ho indovinato il numero
			this.inGioco = false;
			return 0;
		}
		if(t > this.segreto)
			return 1;
		
		//altrimenti
		return -1;
	}
	
	public boolean tentativoValido(int t) {
		if(t<1 || t>NMAX) {
			return false;
		} else
			return true;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getTMAX() {
		return TMAX;
	}
	
}
