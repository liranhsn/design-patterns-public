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
 * The class Calculator is the originator Implementation.
 */
public class Calculator<T extends Number> implements ICalculator<T> {

	private T operand1;
	private T operand2;
	private BiFunction<T, T, BigDecimal> operation;

	/*
	 * create a memento object used for restoring the two operands
	 */
	@Override
	public IMemento createMemento() {
		return new CalculatorMemento<>(operand1, operand2, operation);
	}

	@Override
	public void setOperation(BiFunction<T, T, BigDecimal> operation) {
		this.operation = operation;
	}

	/*
	 * for simplicity, result of a calculation is just adding two numbers
	 */
	@Override
	public BigDecimal getResult() {
		if (operation == null) {
			System.err.printf("Calculator Warning: no operation specified! Defaultiong to addition%n");
			operation = addition();
		}
		return operation.apply(operand1, operand2);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setMemento(IMemento memento) {
		if (!ICalculatorState.class.isAssignableFrom(memento.getClass()))
			throw new IllegalArgumentException("memento must be ICalculatorState");
		this.operand1 = (T) ((ICalculatorState) memento).getOperand1();
		this.operand2 = (T) ((ICalculatorState) memento).getOperand2();
		this.operation = ((ICalculatorState) memento).getOperation();
	}

	@Override
	public void setOperand1(T operand1) {
		this.operand1 = operand1;
	}

	@Override
	public void setOperand2(T operand2) {
		this.operand2 = operand2;
	}

	@Override
	public T getOperand1() {
		return operand1;
	}

	@Override
	public T getOperand2() {
		return operand2;
	}
}
