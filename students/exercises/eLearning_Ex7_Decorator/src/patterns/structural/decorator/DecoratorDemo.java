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
 * The Class {@code DecoratorDemo} is a test application that shows the two math
 * operations running without decoration, decorated by the
 * {@code LoggingDecorator} , decorated by the {@code PerformanceDecorator}, and
 * decorated by both.
 */
public class DecoratorDemo {

	/**
	 * The main method.
	 *
	 * @param args the arguments (not used)
	 */
	public static void main(String[] args) {
		MathOperation add = new AddOperation();
		MathOperation mult = new MultiplyOperation();
		System.out.println("No Decorators");
		System.out.println("=============");
		System.out.printf("%s %.2f\n", "100 + 100 =", add.eval(100D, 100D));
		System.out.printf("%s %.2f\n", "100 * 100 =", mult.eval(100D, 100D));
		System.out.println("\nLogging Decorator");
		System.out.println("=================");
		MathOperation loggingAdd = new LoggingDecorator(add);
		MathOperation loggingMult = new LoggingDecorator(mult);
		System.out.printf("%s %.2f\n", "100 + 100 =", loggingAdd.eval(100D, 100D));
		System.out.printf("%s %.2f\n", "100 * 100 =", loggingMult.eval(100D, 100D));
		System.out.println("\nPerformance Decorator");
		System.out.println("=====================");
		MathOperation perfAdd = new PerformanceDecorator(add);
		MathOperation perfMult = new PerformanceDecorator(mult);
		System.out.printf("%s %.2f\n", "100 + 100 =", perfAdd.eval(100D, 100D));
		System.out.printf("%s %.2f\n", "100 * 100 =", perfMult.eval(100D, 100D));
		System.out.println("\nPerformance and Logging Decorator");
		System.out.println("=================================");
		MathOperation perfLoggingAdd = new LoggingDecorator(new PerformanceDecorator(add));
		MathOperation perfLoggingMult = new LoggingDecorator(new PerformanceDecorator(mult));
		System.out.printf("%s %.2f\n", "100 + 100 =", perfLoggingAdd.eval(100D, 100D));
		System.out.printf("%s %.2f\n", "100 * 100 =", perfLoggingMult.eval(100D, 100D));
	}
}