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
package patterns.structural.bridge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The Class HashDataMapJUnitTest is a sanity JUnit test case to test the
 * functionality of the default {@link DataMap} implementation (the class
 * {@link HashDataMap} )
 */
public class HashDataMapJUnitTest {

    private DataMap<Integer, String> intMap = null;
    private DataMap<String, String> strMap = null;

    @BeforeEach
    public void beforeEach(TestInfo info) {
	System.out.printf("entering %s%n", info.getDisplayName());
	intMap = new HashDataMap<Integer, String>();
	intMap.put(1, "one");
	intMap.put(2, "two");
	intMap.put(3, "three");
	strMap = new HashDataMap<String, String>();
	strMap.put("1", "one");
	strMap.put("2", "two");
	strMap.put("3", "three");
    }

    @Test
    @DisplayName("test create data map")
    public void testHashDataMap() {
	assertNotNull(intMap);
	assertNotNull(strMap);
    }

    @Test
    @DisplayName("test int map get")
    public void testGetIntMapValue() {
	String expected = "one";
	String actual = intMap.get(1);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test string map get")
    public void testGetStringMapValue() {
	String expected = "one";
	String actual = strMap.get("1");
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test key exists")
    public void testKeyExists() {
	Integer intKey = 1;
	String strKey = "1";
	assertTrue(intMap.keyExists(intKey));
	assertTrue(strMap.keyExists(strKey));
    }

    @Test
    @DisplayName("test key does not exist")
    public void testNoneExistingKeyNotExists() {
	Integer intKey = 10;
	String strKey = "10";
	assertFalse(intMap.keyExists(intKey));
	assertFalse(strMap.keyExists(strKey));
    }

    @Test
    @DisplayName("test put")
    public void testPut() {
	Integer intKey = 11;
	String strKey = "11";
	String value = "eleven";
	intMap.put(intKey, value);
	strMap.put(strKey, value);
	assertTrue(intMap.keyExists(intKey));
	assertTrue(strMap.keyExists(strKey));
	assertNotNull(intMap.get(intKey));
	assertNotNull(strMap.get(strKey));
	assertEquals(value, intMap.get(intKey));
	assertEquals(value, strMap.get(strKey));
    }
}
