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
package patterns.structural.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The Class MathOperationDecoratorTest is a sanity JUnit test for the various
 * components of the {@code MathOperation} that are related to the
 * implementations of the decorator pattern exercise.
 */
public class MathOperationDecoratorTest {

    private MathOperation addOperation = null;
    private MathOperation multOperation = null;
    private MathOperation logAddOperation = null;
    private MathOperation logMultOperation = null;
    private MathOperation profileAddOperation = null;
    private MathOperation profileMultOperation = null;
    private MathOperation profileLogAddOperation = null;
    private MathOperation profileLogMultOperation = null;

    @BeforeEach
    public void beforeEach(TestInfo info) throws Exception {
	System.out.printf("entering %s%n", info.getDisplayName());
	addOperation = new AddOperation();
	multOperation = new MultiplyOperation();
	logAddOperation = new LoggingDecorator(addOperation);
	logMultOperation = new LoggingDecorator(multOperation);
	profileAddOperation = new PerformanceDecorator(addOperation);
	profileMultOperation = new PerformanceDecorator(multOperation);
	profileLogAddOperation = new PerformanceDecorator(new LoggingDecorator(addOperation));
	profileLogMultOperation = new PerformanceDecorator(new LoggingDecorator(multOperation));
    }

    @Test
    @DisplayName("test add operation")
    public void testAddOperation() {
	Double operand1 = 10D;
	Double operand2 = 5D;
	Double expected = 15D;
	Double actual = addOperation.eval(operand1, operand2);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test multiply operation")
    public void testMultOperation() {
	Double operand1 = 10D;
	Double operand2 = 5D;
	Double expected = 50D;
	Double actual = multOperation.eval(operand1, operand2);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test log add operation")
    public void testLogAddOperation() {
	Double operand1 = 10D;
	Double operand2 = 5D;
	Double expected = 15D;
	Double actual = logAddOperation.eval(operand1, operand2);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test log multiply operation")
    public void testLogMultOperation() {
	Double operand1 = 10D;
	Double operand2 = 5D;
	Double expected = 50D;
	Double actual = logMultOperation.eval(operand1, operand2);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test profile add operation")
    public void testProfileAddOperation() {
	Double operand1 = 10D;
	Double operand2 = 5D;
	Double expected = 15D;
	Double actual = profileAddOperation.eval(operand1, operand2);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test profile multiply operation")
    public void testProfileMultOperation() {
	Double operand1 = 10D;
	Double operand2 = 5D;
	Double expected = 50D;
	Double actual = profileMultOperation.eval(operand1, operand2);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test profile log add operation")
    public void testProfileLogAddOperation() {
	Double operand1 = 10D;
	Double operand2 = 5D;
	Double expected = 15D;
	Double actual = profileLogAddOperation.eval(operand1, operand2);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test profile log multiply operation")
    public void testProfileLogMultOperation() {
	Double operand1 = 10D;
	Double operand2 = 5D;
	Double expected = 50D;
	Double actual = profileLogMultOperation.eval(operand1, operand2);
	assertEquals(expected, actual);
    }
}
