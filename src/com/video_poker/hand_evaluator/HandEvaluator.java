package com.video_poker.hand_evaluator;

import java.util.List;

/*
 * De seguida surgem os m�todos a ser utilizados ao implementar esta classe.
 * � de notar que nem todos os m�todos funcionam de igual forma, podendo ser especificados
 * de forma diferente, dado que por exemplo no high card evaluator utilizou-se um histograma.
 */


import com.video_poker.CardPos;

public interface HandEvaluator {
	
	
	/**
	 * Consoante o tipo de implementa��o (para as diferentes combina��es), adiciona as cartas que s�o relevantes 
	 * para a verifica��o da jogada
	 * @param card_pos - carta a ser analisada
	 */
	public void addCard(CardPos card_pos);
	
	
	/**
	 * Analisa o vector de cartas guardadas e retorna as jogadas aconselh�veis
	 * @return
	 */
	public AdviceRank getAdviceRank();
	
	/*
	 * devolve o conselho 
	 */
	public List<CardPos> getAdviceHoldVector();
	
	
	/*
	 * Devolve a jogada que est� presente na m�o
	 */
	public HandRank getHandRank();
}
