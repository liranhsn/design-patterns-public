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
 * The Class AbstractDeviceState represents an abstraction of the state in the
 * state design pattern. It defines the default behavior of a network device but
 * leaves concrete implementations to sub-classes
 */
public abstract class AbstractDeviceState implements INetworkDevice {

	protected NetworkDevice networkDevice;

	public AbstractDeviceState(NetworkDevice networkDevice) {
		this.networkDevice = networkDevice;
	}

	public void enable() {
		/*
		 * TODO: 1. create a default implementation of the INetworkDevice operation
		 * enable. For example, throw an unsupported operation exception telling the
		 * client this is not a supported operation for the sub-class
		 */
		System.out.printf(
				"%s.enable() -> Hi. I'm a default operation. I don't do anything! I'm here to remind you to implement me in the relevant sub-class!%n",
				getClass().getName());
	}

	/*
	 * TODO: 2. create default implementations of the other INetworkDevice
	 * operations. They should'nt really do anything except throw an exception
	 * indicate to the client that it's useless to use the default methods
	 *
	 * Note the current implementations are not bad. You can also just leave the
	 * methods blank, but in this case clients will not get indications about what
	 * happened when an irrelevant method was called on the state
	 */

	@Override
	public void disable() {
		System.out.printf(
				"%s.disable() -> Hi. I'm a default operation. I don't do anything! I'm here to remind you to implement me in the relevant sub-class!%n",
				getClass().getName());
	}

	@Override
	public void transmit() {
		System.out.printf(
				"%s.transmit() -> Hi. I'm a default operation. I don't do anything! I'm here to remind you to implement me in the relevant sub-class!%n",
				getClass().getName());
	}

	@Override
	public void receive() {
		System.out.printf(
				"%s.receive() -> Hi. I'm a default operation. I don't do anything! I'm here to remind you to implement me in the relevant sub-class!%n",
				getClass().getName());
	}

	@Override
	public void suspend() {
		System.out.printf(
				"%s.suspend() -> Hi. I'm a default operation. I don't do anything! I'm here to remind you to implement me in the relevant sub-class!%n",
				getClass().getName());
	}

	@Override
	public void resume() {
		System.out.printf(
				"%s.resume() -> Hi. I'm a default operation. I don't do anything! I'm here to remind you to implement me in the relevant sub-class!%n",
				getClass().getName());
	}
}
