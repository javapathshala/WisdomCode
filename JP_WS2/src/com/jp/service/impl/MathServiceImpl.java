/**
 * MathServiceImpl.java This file was auto-generated from WSDL by the Apache
 * Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.jp.service.impl;

import com.jp.service.MathService;
import com.jp.service.client.AddServiceAdapter;
import com.jp.service.request.MathRequest;
import com.jp.service.response.MathResponse;

public class MathServiceImpl implements MathService {

	@Override
	public MathResponse doAdd(MathRequest mathRequest) {

		AddServiceAdapter addServiceAdapter = new AddServiceAdapter();
		MathResponse mathResponse = addServiceAdapter.adapterAdd(mathRequest);
		return mathResponse;
	}
}