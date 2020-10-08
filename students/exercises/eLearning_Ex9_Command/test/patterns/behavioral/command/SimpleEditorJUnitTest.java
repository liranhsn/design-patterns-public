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
