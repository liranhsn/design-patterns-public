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

import java.util.ArrayList;
import java.util.List;

/**
 * The Class SimpleEditor uses the command pattern to manage test in a
 * {@link StringBuilder}. It uses a list of implementation of the
 * {@link EditorCommand} (implemented as inner classes) to operate upon the
 * buffer.
 *
 * The inner classes have access to the buffer and are a convenient programming
 * structure to use in such cases. In this exercise, the inner command classes
 * simply add or remove text to, and from, the buffer.
 *
 * The SimpleEditor class, simply manages the current index that points to the
 * current command to execute in the list of commands.
 *
 * The point is, the SimpleEditor is unaware of the inner classes
 * implementation. It only issues API calls (e.g. execute and unexecute of the
 * {@link EditorCommand} interface).
 */
public class SimpleEditor {

	private StringBuilder buffer = new StringBuilder(); // The buffer containing the editor's text.
	private List<EditorCommand> commands = new ArrayList<EditorCommand>(); // The list of commands
	private int index = 0; // The index pointing at the current command in the list.

	public static void main(String[] args) throws Exception {
		SimpleEditor editor = new SimpleEditor();
		editor.perform(editor.new AppendCommand("A,B,C,"));
		editor.perform(editor.new AppendCommand("D,E,F,"));
		editor.perform(editor.new AppendCommand("G,H,I,"));
		// delete 'D,E,F,'
		editor.perform(editor.new DeleteCommand(6, 12));
		editor.undo();
		editor.undo();
		editor.undo();
		editor.redo();
		// append 'J,K,L'
		editor.perform(editor.new AppendCommand("J,K,L"));
		editor.undo();
		editor.redo();
	}

	/**
	 * Perform the given command. This method adds the given command to the list of
	 * commands and executes it by invoking the 'execute' method.
	 *
	 * Once a command is executed, it can be redone or undone. To makes sure a new
	 * command does not disrupt the order of un-done, or re-done commands, we always
	 * sub-list the commands list to the current index before adding the new command
	 * to the list. Basically, any command that's been redone or undone, is removed
	 * from the list when a new one is executed.
	 *
	 * When the first command is executed, it is the first in the list of commands,
	 * the current index is pointing at '1' and the command is ready to be redone or
	 * undone, or the list could be added another command.
	 *
	 * @param command the command to execute
	 */
	public void perform(EditorCommand command) {
		commands = commands.subList(0, index);
		commands.add(command);
		index = commands.size();
		command.execute();
		System.out.printf("performed %s - index is now %d%n", command.getClass().getSimpleName(), index);
		System.out.printf("%n\t%s%n%n", buffer.toString());
	}

	/**
	 * Redo the last command in the list, if one exists. A command does not exists
	 * in the list if the index is equal to the list size. In this case, we do
	 * nothing.
	 *
	 * If a command exists in the list, we execute it and increment the index to the
	 * next command (or to the end of the list if no other commands exist)
	 */
	public void redo() {
		if (index == commands.size()) {
			return;
		}
		EditorCommand command = commands.get(index);
		command.execute();
		++index;
		System.out.printf("redone %s - index is now %d%n", command.getClass().getSimpleName(), index);
		System.out.printf("%n\t%s%n%n", buffer.toString());
	}

	/**
	 * Undo the commands in the list. If our index is '0' there are no command to
	 * undo. Otherwise, we decrease the index and un-execute the command. We simply
	 * start at the last command in the list and work our way down, calling on the
	 * un-execute method.
	 */
	public void undo() {
		System.out.printf("undoing command at index %d%n", index);
		if (index == 0)
			return;
		--index;
		EditorCommand command = commands.get(index);
		command.undo();
		System.out.printf("undone %s - index is now %d%n", command.getClass().getSimpleName(), index);
		System.out.printf("%n\t%s%n%n", buffer.toString());
	}

	/**
	 * The Class AppendCommand adds a given text to the buffer.
	 */
	class AppendCommand implements EditorCommand {

		private String text;

		public AppendCommand(String text) {
			this.text = text;
		}

		@Override
		public void execute() {
			System.out.printf(">>> AppendCommand.execute() -> appending '%s'%n", text);
			buffer.append(text);
		}

		@Override
		public void undo() {
			System.out.printf("<<< AppendCommand.undo() -> un-appending '%s'%n", text);
			/*
			 * change the length of the character sequence in the buffer to not include the
			 * given text that was previously appended. This is done via the the buffer's
			 * setLenght(int length) method.
			 */
			buffer.setLength(buffer.length() - text.length());
		}
	}

	/**
	 * The Class DeleteCommand deletes a given text from the buffer.
	 */
	class DeleteCommand implements EditorCommand {

		private int start; // The start index
		private int end; // The end index
		private String text; // The deleted text (found between the two indices)

		public DeleteCommand(int from, int to) {
			text = "";
			this.start = from;
			this.end = to;
		}

		@Override
		public void execute() {
			/*
			 * keep a reference to the deleted text that's found between the two given
			 * indices in the buffer. You'll need that text to later implement the
			 * 'un-execute' method. You can do this be using the buffer's subString(int
			 * fromIdx,int toIdx) method.
			 */
			text = buffer.substring(start, end);
			System.out.printf("<<< DeleteCommand.execute() -> deleted '%s' from position %d to position %d%n", text, start, end);
			/*
			 * once we have a reference to the 'text' being deleted, delete it by calling
			 * the buffer's delete(int fromIdx,int toIdx) method.
			 */
			buffer.delete(start, end);
		}

		@Override
		public void undo() {
			/*
			 * you'll want to undo the previous deletion by inserting the deleted text in
			 * the former index (prior to deletion) in the buffer. This can be done by using
			 * the buffer's insert(start, text) method.
			 */
			buffer.insert(start, text);
			System.out.println(">>> DeleteCommand.undo() -> re-inserted '" + text + "' at position " + start);
		}
	}

	public StringBuilder getBuffer() {
		return buffer;
	}

	public int getIndex() {
		return index;
	}
}
