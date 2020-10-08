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
 * The Class {@code DecoratorDemo} is a test application that shows
 * the two math operations running without decoration, decorated by the
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