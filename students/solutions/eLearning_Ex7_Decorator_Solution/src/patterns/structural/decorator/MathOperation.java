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