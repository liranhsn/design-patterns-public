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
package com.acme.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The class UppercaseProxy demonstrates how to use Java's own dynamic proxy
 * mechanism via {@link Proxy} and {@link InvocationHandler} to make sure a list
 * contains only upper case strings
 */
public class UppercaseProxy implements InvocationHandler {

	private Object obj;

	public UppercaseProxy(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.printf("UppercaseProxy.invoke() -> invoking method '%s'%n", method.getName());
		if (args != null && method.getName().equals("add")) {
			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof String) {
					String mixedCase = (String) args[i];
					args[i] = mixedCase.toUpperCase();
				}
			}
		}
		if (args != null && method.getName().equals("addAll")) {
			Collection<?> c = (Collection<?>) args[0];
			List<String> strings = new ArrayList<>();
			c.forEach(e -> {
				if (e instanceof String)
					strings.add(((String) e).toUpperCase());
			});
			args[0] = strings;
		}
		return method.invoke(obj, args);
	}

	public static void main(String[] args) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<String> proxy = (List<String>) Proxy.newProxyInstance(java.util.List.class.getClassLoader(), // class loader
				new Class[] { java.util.List.class, java.util.Collection.class }, // interfaces to implement
				new UppercaseProxy(list)); // invocation handler
		System.out.println("UppercaseProxy.main() -> proxy class: " + proxy.getClass().getName());
		Arrays.stream(proxy.getClass().getInterfaces())
				.forEach(c -> System.out.printf("UppercaseProxy.main() -> proxy implements %s%n", c.getName()));
		proxy.add("AlL");
		proxy.add("String");
		proxy.add("Are");
		proxy.addAll(List.of("uPpEr", "CaSe"));
		proxy.remove("IN");
		System.out.printf("UppercaseProxy.main() -> proxy: %s%n", proxy);
	}
}