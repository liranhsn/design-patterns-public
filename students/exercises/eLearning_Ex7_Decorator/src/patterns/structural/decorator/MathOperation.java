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

/**
 * The MathOperation interface exposes a single method: {@code eval}, that
 * accepts a variable number or arguments.
 *
 * From the MathOperation there are two derived implementations:
 * {@code AddOperation} and {@code MultiplyOperation}. Then there is the
 * {@code MathOperationDecorator} abstract class that serves as the base class
 * for all decorators, and two decorator implementation classes:
 * {@code LoggingDecorator} and {@code PerformanceDecorator}.
 *
 * The LoggingDecorator prints the name of the {@code MathOperation} class, its
 * arguments, and its results to the standard output device and the
 * {@code PerformanceDecorator} records the amount of time the operation takes
 * to execute and prints that to the standard output device.
 *
 * Finally there is a test application that shows the two operations running
 * without decoration, decorated by the {@code LoggingDecorator}, decorated by
 * the {@code PerformanceDecorator}, and decorated by both.
 */
@FunctionalInterface
public interface MathOperation {

	/**
	 * Evaluate an arbitrary operation using a variable number of arguments
	 *
	 * @param operands the operands to evaluate
	 * @return the double result of the operation on the operands
	 */
	public Double eval(Double... operands);
}