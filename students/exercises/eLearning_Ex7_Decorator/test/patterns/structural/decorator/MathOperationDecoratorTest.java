/*
 * Copyright (C) 2009 - 2020 T.N.Silverman, All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation  and/or other materials provided with the distribution.
 * 3. Neither the names of the copyright holders nor the names of any
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
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
