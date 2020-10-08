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
package patterns.behavioral.template;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Class AbstractLogger defines the methods for logging. It defines two
 * hooks, {@link #preLogHook()} and {@link #postLogHook()} an abstract template
 * method {@link #log(String)} to be implemented by sub-classes
 */
public abstract class AbstractLogger {

	private StringBuilder buffer = new StringBuilder();
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSSSSS");

	public AbstractLogger() {
		super();
	}

	/**
	 * Gets the message.
	 *
	 * @return the message for testing purpose
	 */
	protected String getMessage() {
		return buffer.toString();
	}

	/**
	 * Any sub class that want to perform some actions BEFORE the actual logging,
	 * can override this method.
	 */
	protected void preLogHook() {
	}

	/**
	 * Any sub class that want to perform some actions AFTER the actual logging, can
	 * override this method.
	 */
	protected void postLogHook() {
	}

	/**
	 * Log a message containing a possible {@code header} and a message
	 * {@code body}. Both should be preceded with a date. The actual logging is done
	 * by the implementing sub-class
	 *
	 * @param header the header of the message or null if none exists
	 * @param body   the body of the message
	 */
	public void log(String header, String body) {
		/*
		 * TODO: 1. define the algorithm: in this exercise, we format a date and a
		 * header line before the body of the message. After appending the message to
		 * the buffer, define the general algorithm for logging a message:
		 *
		 * first, by calling the preLogHook() method,
		 *
		 * then, by calling the log(message) method,
		 *
		 * and finally, by calling the postLogHook() method.
		 */
		buffer.setLength(0); // for testing: reset the buffer
		String message = String.format("%s: [%2$s]%n%3$s", LocalDateTime.now().format(formatter), header, body);
		buffer.append(message); // for testing: append to the buffer
		throw new UnsupportedOperationException(this.getClass().getName() + ".logMessage() -> Not implemented yet!");
	}

	/**
	 * the template method
	 *
	 * @param message the message
	 */
	protected abstract void log(String message);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			AbstractLogger logger = new ConsoleLogger();
			// AbstractLogger logger = new FileLogger("log.txt");
			logger.log("alert", "memory is low");
			logger.log("info", "logger is active");
			logger.log("alert", "memory is low");
			logger.log("info", "logger is active");
			logger.log("alert", "memory is low");
			logger.log("info", "logger is active");
			logger.log("alert", "memory is low");
			logger.log("info", "logger is active");
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
