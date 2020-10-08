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
 * The Class LoggingDecorator prints the name of the MathOperation class, its
 * arguments, and its results to the standard output device
 */
public class LoggingDecorator extends MathOperationDecorator {

    public LoggingDecorator(MathOperation operation) {
	super(operation);
    }

    @Override
    public Double eval(Double... operands) {
	Double result = 0D;
	// Before the evaluation: Log the operation name and arguments
	StringBuilder sb = new StringBuilder(operands.length);
	sb.append("[");
	for (Double operand : operands)
	    sb.append(operand).append(",");
	sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, "]");
	System.out.printf("LoggingDecorator.eval() -> evaluating %s with operands %s%n", operation.getClass().getSimpleName(), sb.toString());
	result = operation.eval(operands);
	// After the evaluation: Log the result
	System.out.printf("LoggingDecorator.eval() -> %s %s = %s%n", sb.toString(), operation.getClass().getSimpleName(), result);
	return result;
    }
}