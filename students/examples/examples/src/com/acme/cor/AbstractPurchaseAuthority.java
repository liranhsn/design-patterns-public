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
package com.acme.cor;

/**
 * The class AbstractPurchaseAuthority is base purchasing authority and defines
 * the {@link #approve(PurchaseRequest)} method to handle the purchase request
 */
public abstract class AbstractPurchaseAuthority {

	protected final double base_authority_limit = 100;
	protected AbstractPurchaseAuthority successor;

	/**
	 * A method that defines the maximum price an authority can handle
	 *
	 * @return
	 */
	abstract double getAuthorityLimit();

	public AbstractPurchaseAuthority(AbstractPurchaseAuthority successor) {
		super();
		this.successor = successor;
	}

	public void setSuccessor(AbstractPurchaseAuthority successor) {
		this.successor = successor;
	}

	/**
	 * A template method that defines the algorithm for approving a purchase request
	 *
	 * @param request the purchase request
	 */
	public void approve(PurchaseRequest request) {
		try {
			processRequest(request);
		} catch (AboveMyPaygradeException ex) {
			System.out.printf("Next in chain: %s%n", (successor == null ? "No body!" : successor.getClass().getSimpleName()));
			if (successor != null)
				successor.approve(request);
			else
				System.out.printf("Your request in the sum of $%.2f requires a board meeting!%n", request.getTotal());
		}
	}

	/**
	 * template method to define a purchase request algorithm request. An authority
	 * that can't handle, should throw {@link AboveMyPaygradeException}
	 *
	 * @param request the purchase request
	 * @throws AboveMyPaygradeException
	 */
	void processRequest(PurchaseRequest request) throws AboveMyPaygradeException {
		if (request.getTotal() <= getAuthorityLimit()) {
			System.out.printf("%s approves %d X unit/s in the sum of $%.2f to purchase because: '%s'\n", getClass().getSimpleName(),
					request.getQuantity(), request.getTotal(), request.getReason());
		} else
			throw new AboveMyPaygradeException();
	}
}