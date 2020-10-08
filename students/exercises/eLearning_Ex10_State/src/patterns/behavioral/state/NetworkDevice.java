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
 * The Class NetworkDevice plays the role of the context in the state design
 * pattern. It holds an AbstractDeviceState {@code currentState} to which it
 * delegates the inherited operations of {@code INetworkDevice}
 */
public class NetworkDevice implements INetworkDevice {

	protected AbstractDeviceState currentState; // The current state (protected only for testing)
	public final AbstractDeviceState DISABLED_STATE = new DisabledState(this);
	public final AbstractDeviceState ENABLED_STATE = new EnabledState(this);
	public final AbstractDeviceState SUSPENDED_STATE = new SuspendedState(this);

	public NetworkDevice() {
		super();
		currentState = DISABLED_STATE;
	}

	public void setState(AbstractDeviceState newState) {
		currentState = newState;
		System.out.println("State changed to: " + newState.getClass().getSimpleName());
	}

	@Override
	public void enable() {
		/*
		 * TODO: 3. use the current state to enable it
		 */
		throw new UnsupportedOperationException(getClass().getSimpleName() + ".enable() -> Not implemented yet!");
	}

	@Override
	public void disable() {
		/*
		 * TODO: 4. disable the current state
		 */
		throw new UnsupportedOperationException(getClass().getSimpleName() + ".disable() -> Not implemented yet!");
	}

	@Override
	public void transmit() {
		/*
		 * TODO: 5. transmit from the current state
		 */
		throw new UnsupportedOperationException(getClass().getSimpleName() + ".transmit() -> Not implemented yet!");
	}

	@Override
	public void receive() {
		/*
		 * TODO: 6. receive from the current state
		 */
		throw new UnsupportedOperationException(getClass().getSimpleName() + ".receive() -> Not implemented yet!");
	}

	@Override
	public void suspend() {
		/*
		 * TODO: 7. suspend the current state
		 */
		throw new UnsupportedOperationException(getClass().getSimpleName() + ".suspend() -> Not implemented yet!");
	}

	@Override
	public void resume() {
		/*
		 * TODO: 8. resume the current state
		 */
		throw new UnsupportedOperationException(getClass().getSimpleName() + ".resume() -> Not implemented yet!");
	}

	public static void main(String[] args) {
		try {
			NetworkDevice device = new NetworkDevice();
			device.enable();
			device.transmit();
			device.suspend();
			device.receive(); // Is this right? What do you get here???
			device.resume();
			device.disable();
			System.out.println("Done");
		} catch (Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			ex.printStackTrace(System.err);
		}
	}
}
