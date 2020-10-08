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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * The Class ReadOnlyDBTableJUnitTest is a sanity JUnit test case to test the
 * functionality of the {@link ReadOnlyDBTable} class.
 */
public class ReadOnlyDBTableJUnitTest {

    private DataMap<Integer, String> dataMap = null;
    private AbstractDBTable<Integer, String> table = null;

    @BeforeEach
    public void beforeEach(TestInfo info) {
	System.out.printf("entering %s%n", info.getDisplayName());
	dataMap = new HashDataMap<Integer, String>();
	dataMap.put(1, "one");
	dataMap.put(2, "two");
	dataMap.put(3, "three");
	table = new ReadOnlyDBTable<Integer, String>(dataMap);
    }

    @Test
    @DisplayName("test create read only DB table")
    public void testNotNullReadOnlyDBTable() {
	assertNotNull(dataMap);
	assertNotNull(table);
    }

    @Test
    @DisplayName("test get implementation")
    public void testGetImpl() {
	DataMap<Integer, String> actual = table.getImpl();
	assertNotNull(actual);
	assertEquals(dataMap, actual);
	assertSame(dataMap, actual);
    }

    @Test
    @DisplayName("test select existing key")
    public void testSelectExistingKey() {
	Integer id = 1;
	String expected = "one";
	String actual = table.select(id);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test select none existing key")
    public void testSelectNoneExistingKey() {
	Integer id = 4;
	String expected = null;
	String actual = table.select(id);
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test insert into read obly table and throw")
    public void testInsert() {
	assertThrows(UnsupportedOperationException.class, () -> table.insert(10, "test"));
    }

    @Test
    @DisplayName("test update read obly table and throw")
    public void testUpdate() {
	assertThrows(UnsupportedOperationException.class, () -> table.update(1, "test"));
    }
}
