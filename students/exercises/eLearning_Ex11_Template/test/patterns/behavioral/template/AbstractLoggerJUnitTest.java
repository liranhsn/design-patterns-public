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

import static org.junit.Assert.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;


/**
 * The Class AbstractLoggerJUnitTest is a sanity JUnit test case to test the
 * functionality of the {@link AbstractLogger} concrete sub-classes
 */
public class AbstractLoggerJUnitTest {

    private static final String TEST_LOG_FILE_NAME = "test-log.txt";
    private AbstractLogger console;
    private AbstractLogger logger;

    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @BeforeEach
    public void beforeEach(TestInfo info) throws Exception {
    	System.out.printf("entering %s%n",info.getDisplayName());
        console = new ConsoleLogger();
        logger = new FileLogger(TEST_LOG_FILE_NAME);
    }

    @AfterEach
    public void afterEach() {
        File logFile = new File(TEST_LOG_FILE_NAME);
        if (logFile.exists())
            assertTrue(logFile.delete());
    }

    @Test
    @DisplayName("test log file exists after logging")
    public void testLogFileExists() {
        File logFile = new File(TEST_LOG_FILE_NAME);
        String header = "test-header";
        String body = "test-body";
        logger.log(header, body);
        assertTrue(logFile.delete());
    }

    @Test
    @DisplayName("test console logging")
    public void testConsoleLogString() {
        String header = "test-header";
        String body = "test-body";
        console.log(header, body);
        String message = console.getMessage();
        assertTrue(message.contains(header));
        assertTrue(message.contains(body));
        assertTrue(message.contains(header) && message.contains(body));
    }

    @Test
    @DisplayName("test logging expected output")
    public void testFileLogString() {
        String header = "test-header";
        String body = "test-body";
        logger.log(header, body);
        String message = logger.getMessage();
        assertTrue(message.contains(header));
        assertTrue(message.contains(body));
        assertTrue(message.contains(header) && message.contains(body));
    }
}
