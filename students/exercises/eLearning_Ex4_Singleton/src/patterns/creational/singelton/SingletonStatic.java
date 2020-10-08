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
 * The Class SingletonStatic is a vanilla singleton pattern. It works on all JDK
 * version and is the oldest way to generate a singleton. The principle is to
 * create a static inner class holding a reference to the singleton INSTANCE.
 * The INSTANCE is initialized the first time it's accessed or the first time
 * the wrapper class is accessed.
 */
public class SingletonStatic {

	/**
	 * Gets the single instance of SingletonStatic.
	 *
	 * @return single instance of SingletonStatic
	 */
	public static SingletonStatic getInstance() {
		/*
		 * TODO: 10. return the INSTANCE by accessing the
		 * SingletonClassicHolder.INSTANCE static member
		 */
		throw new UnsupportedOperationException("method SingletonStatic.getInstance() is not implemented yet!");
	}

	/**
	 * Instantiates a new singleton classic.
	 */
	private SingletonStatic() {
		super();
	}

	/**
	 * The Class SingletonClassicHolder.
	 */
	static class SingletonClassicHolder {

		// TODO: 11. remove this annotation when implemented
		@SuppressWarnings("unused")
		private static SingletonStatic INSTANCE;
		/*
		 * TODO: 9. create a static block to initialize the INSTANCE (e.g. static
		 * {INSTANCE = new SingletonStatic();})
		 */
		static {
			// FIXME
		}
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
				SingletonStatic singletonStatic = SingletonStatic.getInstance();
				if (singletonStatic == null) {
					System.err.println("Null Instance!!!");
				}
			});
			threads.add(thread);
		}
		return threads;
	}
}
