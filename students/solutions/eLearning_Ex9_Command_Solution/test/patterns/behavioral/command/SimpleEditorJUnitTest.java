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
package patterns.behavioral.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class SimpleEditorJUnitTest {

    private SimpleEditor editor;

    @BeforeEach
    public void beforeEach(TestInfo info) throws Exception {
	System.out.printf("%s%n", info.getDisplayName());
	editor = new SimpleEditor();
	assertNotNull(editor.getBuffer());
    }

    @Test
    @DisplayName("test append command")
    public void testPerformAppendCommand() {
	String expected = "123";
	editor.perform(editor.new AppendCommand(expected));
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test append commands")
    public void testPerformAppendCommands() {
	String expected = "123456";
	editor.perform(editor.new AppendCommand("123"));
	editor.perform(editor.new AppendCommand("456"));
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test undo append command")
    public void testUndoAppendCommand() {
	String expected = "";
	editor.perform(editor.new AppendCommand("123"));
	editor.undo();
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test undo append commands")
    public void testUndoAppendCommands() {
	String expected = "123";
	editor.perform(editor.new AppendCommand("123"));
	editor.perform(editor.new AppendCommand("456"));
	editor.undo();
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test undo all append commands")
    public void testUndoAllAppendCommands() {
	String expected = "";
	editor.perform(editor.new AppendCommand("123"));
	editor.perform(editor.new AppendCommand("456"));
	editor.perform(editor.new AppendCommand("789"));
	editor.undo();
	editor.undo();
	editor.undo();
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test redo append command")
    public void testRedoAppendCommand() {
	String expected = "123";
	editor.perform(editor.new AppendCommand(expected));
	editor.undo();
	editor.redo();
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test delete command")
    public void testPerformDelCommand() {
	String expected = "";
	editor.perform(editor.new AppendCommand("123"));
	int startIdx = 0;
	int endIdx = editor.getBuffer().length();
	editor.perform(editor.new DeleteCommand(startIdx, endIdx));
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test delete commands")
    public void testPerformDelCommands() {
	String expected = "123123";
	editor.perform(editor.new AppendCommand("123456789123"));
	int startIdx = 3;
	int endIdx = 6;
	editor.perform(editor.new DeleteCommand(startIdx, endIdx));
	editor.perform(editor.new DeleteCommand(startIdx, endIdx));
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test undo delete command")
    public void testUndoDelCommand() {
	String expected = "123";
	editor.perform(editor.new AppendCommand("123"));
	int startIdx = 0;
	int endIdx = editor.getBuffer().length();
	editor.perform(editor.new DeleteCommand(startIdx, endIdx));
	editor.undo();
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test delete commands")
    public void testUndoDelCommands() {
	String expected = "123456789123";
	editor.perform(editor.new AppendCommand(expected));
	int startIdx = 3;
	int endIdx = 6;
	editor.perform(editor.new DeleteCommand(startIdx, endIdx));
	editor.perform(editor.new DeleteCommand(startIdx, endIdx));
	editor.undo();
	editor.undo();
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test undo all commands")
    public void testUndoAllCommands() {
	String expected = "";
	editor.perform(editor.new AppendCommand("123456789123"));
	int startIdx = 3;
	int endIdx = 6;
	editor.perform(editor.new DeleteCommand(startIdx, endIdx));
	editor.perform(editor.new DeleteCommand(startIdx, endIdx));
	editor.undo();
	editor.undo();
	editor.undo();
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test redo delete command")
    public void testRedoDelCommand() {
	String expected = "123789";
	editor.perform(editor.new AppendCommand("123456789"));
	int startIdx = 3;
	int endIdx = 6;
	editor.perform(editor.new DeleteCommand(startIdx, endIdx));
	editor.undo();
	editor.redo();
	String actual = editor.getBuffer().toString();
	assertEquals(expected, actual);
    }
}
