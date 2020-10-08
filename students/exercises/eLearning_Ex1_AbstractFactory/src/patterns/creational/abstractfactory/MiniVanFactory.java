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
import patterns.creational.carparts.MiniVanHood;
import patterns.creational.carparts.Wheel;

/**
 * A factory for creating MiniVan objects.
 */
public class MiniVanFactory extends AbstractCarFactory {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * patterns.creational.abstractfactory.AbstractCarFactory#createHood(java.lang.
	 * String)
	 */
	@Override
	public Hood createHood(String color) {
		Hood hood = new MiniVanHood(color);
		hood.setId(getNextId());
		return hood;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.creational.abstractfactory.AbstractCarFactory#createWheel()
	 */
	@Override
	public Wheel createWheel() {
		/*
		 * TODO: 3. Implement this method exactly like the 'createHood' method, only to
		 * return a the relevant MiniVanWheel instance with it's id property correctly
		 * set
		 */
		throw new UnsupportedOperationException("Method MiniVanFactory.createWheel() is not implemented yet!");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see patterns.creational.abstractfactory.AbstractCarFactory#createEngine()
	 */
	@Override
	public Engine createEngine() {
		/*
		 * TODO: 4. Implement this method exactly like the 'createHood' method, only to
		 * return a the relevant MiniVanEngine instance with it's id property correctly
		 * set
		 */
		throw new UnsupportedOperationException("Method MiniVanFactory.createEngine() is not implemented yet!");
	}
}