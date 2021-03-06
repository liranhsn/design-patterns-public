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
 * The Class SingletonEasy is the easiest type of singleton to implement. It
 * does not use lazy initialization so none of the synchronization issues
 * regarding the initialization of the instance apply here. It's very simple
 */
public final class SingletonEasy {

    // TODO: 7. declare the private INSTANCE static member and initialize it

    /**
     * Gets the single instance of SingletonEasy.
     *
     * @return single instance of SingletonEasy
     */
    public static SingletonEasy getInstance() {
        /*
         *  TODO: 8. return the singleton instance
         */
        throw new UnsupportedOperationException("method SingletonEasy.getInstance() is not implemented yet!");
    }

    /**
     * private constructor instantiates a new singleton easy.
     */
    private SingletonEasy() {
        super();
        System.out.println("SingletonEasy.<init> -> Initialized SingletonEasy");
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
				SingletonEasy singletonEasy = SingletonEasy.getInstance();
				if (singletonEasy == null) {
					System.err.println("Null Instance!!!");
				}
			});
			threads.add(thread);
		}
		return threads;
	}

}
