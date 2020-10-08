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
import java.math.MathContext;
import java.util.function.BiFunction;

/**
 * The interface ICalculator represents a calculator that uses a memento to
 * restore it's state of a previous calculation.
 *
 * It is the Originator's interface
 */
public interface ICalculator<T extends Number> {

	// create memento (save)
	public IMemento createMemento();

	// set memento (restore)
	public void setMemento(IMemento memento);
	// actual services provided by the originator

	public BigDecimal getResult();

	public void setOperand1(T operand1);

	public void setOperand2(T operand2);

	public void setOperation(BiFunction<T, T, BigDecimal> operation);

	public T getOperand1();

	public T getOperand2();

	// some default operations

	default BiFunction<T, T, BigDecimal> addition() {
		return (a, b) -> new BigDecimal(a.toString()).add(new BigDecimal(b.toString()));
	}

	default BiFunction<T, T, BigDecimal> subtraction() {
		return (a, b) -> new BigDecimal(a.toString()).subtract(new BigDecimal(b.toString()));
	}

	default BiFunction<T, T, BigDecimal> division() {
		return (a, b) -> new BigDecimal(a.toString()).divide(new BigDecimal(b.toString()), MathContext.DECIMAL32);
	}

	default BiFunction<T, T, BigDecimal> multiplication() {
		return (a, b) -> new BigDecimal(a.toString()).multiply(new BigDecimal(b.toString()));
	}

}
