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
package patterns.behavioral.state;

/**
 * The Class EnabledState represents a network device in the enabled mode. It's
 * a concrete state in the state design pattern. It can be disabled, suspended
 * transmitted from, and used for receiving
 */
public class EnabledState extends AbstractDeviceState {

	/**
	 * Instantiates a new enabled state.
	 *
	 * @param networkDevice the network device
	 */
	public EnabledState(NetworkDevice networkDevice) {
		super(networkDevice);
	}

	public void disable() {
		/*
		 * TODO: 11. You'll want to implement disable by setting the networkDevice to
		 * the disabled state. Such utility state exists as a member in the
		 * NetworkDevice class, so basically, what you need is to do something like
		 * networkDevice.setState(networkDevice.DISABLED_STATE)
		 */
		//throw new UnsupportedOperationException(getClass().getSimpleName() + ".disable() -> not implemented yet!");
	}

	public void suspend() {
		/*
		 * TODO: 12. You'll want to implement suspend by setting the networkDevice to
		 * the suspended state. Such utility state exists as a member in the
		 * NetworkDevice class, so basically, what you need is to do something like
		 * networkDevice.setState(networkDevice.SUSPENDED_STATE)
		 */
		//throw new UnsupportedOperationException(getClass().getSimpleName() + ".suspend() -> not implemented yet!");
	}

	public void transmit() {
		/*
		 * TODO: 13. You'll want to implement transmit by transmitting some data... e.g.
		 * by printing some data to the console for in this exercise:
		 *
		 * System.out.printf("%s transmitting... ... ...%n",getClass().getName());
		 */
		throw new UnsupportedOperationException(getClass().getSimpleName() + ".transmit() -> not implemented yet!");

	}

	public void receive() {
		/*
		 * TODO: 14. You'll want to implement receive by receiving some data... e.g. by
		 * printing some data to the console for in this exercise:
		 *
		 * System.out.printf("%s receiving...%n",getClass().getName());
		 */
		throw new UnsupportedOperationException(getClass().getSimpleName() + ".receive() -> not implemented yet!");
	}
}
