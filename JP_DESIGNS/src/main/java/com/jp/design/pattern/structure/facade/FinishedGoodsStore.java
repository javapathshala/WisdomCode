/**
 * 
 */
package com.jp.design.pattern.structure.facade;

/**
 * @author dimit.chadha
 * 
 */
public class FinishedGoodsStore implements Store {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jp.application.pattern.facade.Store#getGoods()
	 */
	
	public Goods getGoods() {
		FinishedGoods finishedGoods = new FinishedGoods();
		return finishedGoods;

	}

}
