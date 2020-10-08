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
 * The Class LoggingDecorator prints the name of the MathOperation class, its
 * arguments, and its results to the standard output device
 */
public class LoggingDecorator extends MathOperationDecorator {

	/**
	 * Instantiates a new logging decorator.
	 *
	 * @param operation the operation
	 */
	public LoggingDecorator(MathOperation operation) {
		super(operation);
	}

	@Override
	public Double eval(Double... operands) {
		Double result = 0D;

	    Class<?> clazz = operation.getClass();
	    while (!Object.class.isAssignableFrom(clazz))
	    	clazz = operation.getClass().getSuperclass();


		/*
		 *  TODO: 7. Before the evaluation: Log (print) the operation name and arguments
		 */
		result = operation.eval(operands);
		/*
		 *  TODO: 8. After the evaluation: Log the result
		 */
		return result;
	}
}