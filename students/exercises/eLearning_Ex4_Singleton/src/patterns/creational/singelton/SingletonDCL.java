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
package patterns.creational.singelton;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Class SingletonDCL is the singleton pattern that uses the 'volatile'
 * reserved word to declare a private static singleton instance that can be used
 * in conjunction with the DCL (double checked locking) mechanism to synchronize
 * the instance while it's being initialized
 */
public class SingletonDCL {

	/** The instance. */
	// TODO: 6. remove this annotation after implementation
	@SuppressWarnings("unused")
	private static volatile SingletonDCL INSTANCE;

	/**
	 * private constructor
	 */
	private SingletonDCL() {
		super();
	}

	/**
	 * Gets the single instance of SingletonDCL.
	 *
	 * @return single instance of SingletonDCL
	 */
	public static SingletonDCL getInstance() {
		/*
		 * TODO: 1. Ask the first question in the DCL pattern: is this instance null?
		 */
		if (true) { // FIXME
			/*
			 * TODO: 2. if the answer is true, it's time to synchronize on the class
			 * synchronized block begin
			 */
			synchronized ("".getClass()) { // FIXME
				/*
				 * TODO: 3. according to the DCL pattern, you now ask the question again
				 */
				if (true ) { // FIXME
					/*
					 * TODO: 4. if the answer is true - initialize the instance
					 */
					System.out.printf("SingletonDCL.getInstance() -> Initializing SingletonDCL");
					INSTANCE = null; // FIXME
				}

			}
		}
		/*
		 * TODO: 5. return the instance
		 */
		throw new UnsupportedOperationException("method SingletonDCL.getInstance() is not implmented yet!");
	}

	/**
	 * If everything is implemented fine, this program will exhaust the thread list
	 * and exit normally w/o visible errors
	 */
	public static void main(String[] args) {
		getThreadList(100).forEach(Thread::start);
	}


	/**
	 * Gets the thread list.
	 *
	 * @param numOfThreads the num of threads
	 * @return the thread list
	 */
	private static List<Thread> getThreadList(int numOfThreads) {
		List<Thread> threads = new LinkedList<Thread>();
		for (int i = 0; i < numOfThreads; i++) {
			Thread thread = new Thread(() -> {
				try {
					Thread.sleep(ThreadLocalRandom.current().nextInt(1000) + 1);
				} catch (InterruptedException iex) {
					System.err.println("Error: " + iex.getMessage());
				}
				System.out.printf("%s running...%n", Thread.currentThread().getName());
				SingletonDCL singletonDCL = SingletonDCL.getInstance();
				if (singletonDCL == null) {
					System.err.println("Null Instance!!!");
				}
			});
			threads.add(thread);
		}
		return threads;
	}
}
