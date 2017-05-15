package com.video_poker.hand_evaluator;

import java.util.List;

/*
 * De seguida surgem os métodos a ser utilizados ao implementar esta classe.
 * É de notar que nem todos os métodos funcionam de igual forma, podendo ser especificados
 * de forma diferente, dado que por exemplo no high card evaluator utilizou-se um histograma.
 */


import com.video_poker.CardPos;

public interface HandEvaluator {
	
	
	/**
	 * Consoante o tipo de implementação (para as diferentes combinações), adiciona as cartas que são relevantes 
	 * para a verificação da jogada
	 * @param card_pos - carta a ser analisada
	 */
	public void addCard(CardPos card_pos);
	
	
	/**
	 * Analisa o vector de cartas guardadas e retorna as jogadas aconselháveis
	 * @return
	 */
	public AdviceRank getAdviceRank();
	
	/*
	 * devolve o conselho 
	 */
	public List<CardPos> getAdviceHoldVector();
	
	
	/*
	 * Devolve a jogada que está presente na mão
	 */
	public HandRank getHandRank();
}
