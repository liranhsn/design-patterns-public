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

/**
 * The Class PerformanceDecorator records the amount of time in milliseconds the
 * operation takes to execute and prints that to the standard output device.
 */
public class PerformanceDecorator extends MathOperationDecorator {

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
    @Override
    public Double eval(Double... operands) {
	long startTime = System.nanoTime(); // Capture the start time of the execution for example:
	Double result = operation.eval(operands); // Execute the operation
	long endTime = System.nanoTime(); // Measure the execution time in ms, for example:
	System.out.printf("PerformanceDecorator,eval() -> executed %s in %.2fms%n", operation.getClass().getSimpleName(),
		(endTime - startTime) / 1000D); // Print the result. for example:
	return result;
    }
}