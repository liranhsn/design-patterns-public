/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the 
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
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
