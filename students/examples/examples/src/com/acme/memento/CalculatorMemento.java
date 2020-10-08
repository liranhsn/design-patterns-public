/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the 
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.acme.memento;

import java.math.BigDecimal;
import java.util.function.BiFunction;

/**
 * The class CalculatorMemento is the default calculator state and memento
 * implementation. It stores the operands for the calculation.
 */
public class CalculatorMemento<T extends Number> implements IMemento, ICalculatorState<T> {

	private T operand1;
	private T operand2;
	private BiFunction<T, T, BigDecimal> operation;

	public CalculatorMemento(T operand1, T operand2, BiFunction<T, T, BigDecimal> operation) {
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operation = operation;
	}

	@Override
	public T getOperand1() {
		return operand1;
	}

	@Override
	public T getOperand2() {
		return operand2;
	}

	@Override
	public BiFunction<T, T, BigDecimal> getOperation() {
		return operation;
	}
}
