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
 * The Class PerformanceDecorator records the amount of time in milliseconds the
 * operation takes to execute and prints that to the standard output device.
 */
public class PerformanceDecorator extends MathOperationDecorator {

	/**
	 * Instantiates a new performance decorator.
	 *
	 * @param operation the operation
	 */
	public PerformanceDecorator(MathOperation operation) {
		super(operation);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.structural.decorator.MathOperation#eval(java.lang.Double[])
	 *
	 * Basic profiling. Prints the operation execution time in milliseconds to the
	 * standard out
	 */
	// TODO: 6. remove this annotation when done
	@SuppressWarnings("unused")
	@Override
	public Double eval(Double... operands) {
		/*
		 * TODO: 2. Capture the start time of the execution for example: long startTime = System.nanoTime();
		 */
		System.out.println("I was supposed to take the time before the operation execution...");
		// Execute the operation
		Double result = operation.eval(operands);
		/*
		 * TODO: 3. Measure the execution time in ms, for example: long endTime = System.nanoTime();
		 */
		System.out.println("I was supposed to take the time after the operation execution...");
		/*
		 * TODO: 4. Print the result. for example:
		 * System.out.printf("Execution for operation %s took %.2fms to execute\n",operation.getClass().getName(), (endTime - startTime) / 1000D);
		 */
		System.out.println("This line was suppose to show the profiling information...");
		/*
		 * TODO: 5. Return the result
		 */
		throw new UnsupportedOperationException("method PerformanceDecorator.eval(Double... operands) is not implemented yet!");
	}
}