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
 * The Class SuspendedState represents a network device in the suspended mode.
 * It's a concrete state in the state design pattern.F It can only be resumed,
 * or disabled!
 */
public class SuspendedState extends AbstractDeviceState {

	public SuspendedState(NetworkDevice device) {
		super(device);
	}

	@Override
	public void disable() {
		/*
		 * TODO: 9. You'll want to implement disable by setting the networkDevice to the
		 * disabled state. Such utility state exists as a member in the NetworkDevice
		 * class, so basically, what you need is to do something like
		 * networkDevice.setState(networkDevice.DISABLED_STATE)
		 */
		throw new UnsupportedOperationException(getClass().getSimpleName() + ".disable() -> not implemented yet!");
	}

	@Override
	public void resume() {
		/*
		 * TODO: 10. You'll want to implement resume by setting the networkDevice to the
		 * enabled state. Such utility state exists as a member in the NetworkDevice
		 * class, so basically, what you need is to do something like
		 * networkDevice.setState(networkDevice.ENABLED_STATE)
		 */
		throw new UnsupportedOperationException(getClass().getSimpleName() + ".resume() -> not implemented yet!");
	}
}
