/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
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
package patterns.creational.abstractfactory;

import patterns.creational.carparts.Engine;
import patterns.creational.carparts.Hood;
import patterns.creational.carparts.Wheel;

/**
 * A base factory for creating car parts. This class sets the contract for every
 * other car parts factory.
 */
public abstract class AbstractCarFactory {

	// Nothing to do here - just understand the role of each method

	/** A global counter indicating the amount of parts created by the factory */
	private static int partsCounter = 0;

	/**
	 * This method is used in every sub-class method that creates a car part. It is
	 * the implementor's role to call this method to generate a car part id
	 *
	 * @return the next id for a car part and increments the global counter
	 */
	public static int getNextId() {
		return partsCounter++;
	}

	/**
	 * Creates a new Hood object. This method is to be implemented in every factory
	 * sub-class to generate a hierarchy dependent car part
	 *
	 * @param color the color of the hood
	 * @return the hood
	 */
	public abstract Hood createHood(String color);

	/**
	 * Creates a new Wheel object. This method is to be implemented in every factory
	 * sub-class to generate a hierarchy dependent car part
	 *
	 * @return the wheel
	 */
	public abstract Wheel createWheel();

	/**
	 * Creates a new Engine object. This method is to be implemented in every
	 * factory sub-class to generate a hierarchy dependent car part
	 *
	 * @return the engine
	 */
	public abstract Engine createEngine();
}