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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The Class FileLogger is a concrete logger implementation that writes logging
 * lines to a file via the inherited method {@link FileLogger#log(String)}. It
 * can optionally override any or none of the super class hooks.
 */
public class FileLogger extends AbstractLogger {

	private File logFile;
	private String message;

	public FileLogger(String fileName) {
		super();
		logFile = new File(fileName);
	}

	/**
	 * A utility method to write text to a file.
	 *
	 * @param message the message line to write to a file
	 * @param append  if true, text will be appended to the file. Otherwise the file
	 *                will be re-written each time
	 */
	// TODO: 6. remove this annotation when done
	@SuppressWarnings("unused")
	private void writeToFile(String message, boolean append) {
		try (FileWriter out = new FileWriter(logFile, append); PrintWriter writer = new PrintWriter(out);) {
			writer.println(message);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	protected void postLogHook() {
		/*
		 * TODO: 4. Implement this so the current message line that was written to the
		 * file, will be shown to the user on the console
		 */
		message = message.replace("\n", " ");
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".postLogHook() -> Not implemented yet!");
	}

	@Override
	protected void log(String message) {
		/*
		 * TODO: 5. implement this method possibly by using the provided writeToFile
		 * utility method
		 */
		this.message = message;
		throw new UnsupportedOperationException(this.getClass().getSimpleName() + ".log() -> Not implemented yet!");
	}
}
