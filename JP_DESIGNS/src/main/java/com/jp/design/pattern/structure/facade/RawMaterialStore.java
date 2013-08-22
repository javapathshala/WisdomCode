package com.jp.design.pattern.structure.facade;

public class RawMaterialStore {

	public RawMaterialGoods getGoods() {
		
		return new RawMaterialGoods();
	}

}
