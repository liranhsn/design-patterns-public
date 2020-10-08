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

	private StringBuilder buffer = new StringBuilder(); // The buffer containing the editor's text
	private List<EditorCommand> commands = new ArrayList<EditorCommand>(); // the list of commands
	private int index = 0; // index pointing at the current command in the list.

	/*
	 * a program to demo the editor
	 */
	public static void main(String[] args) {
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
		editor.perform(editor.new AppendCommand("J,K,L"));
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
		System.out.printf("performing %s, index %d...%n", command.getClass().getSimpleName(), index);
		commands = commands.subList(0, index);
		commands.add(command);
		index = commands.size();
		command.execute();
		System.out.printf("performed %s, index %d%n", command.getClass().getSimpleName(), index);
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
		System.out.println("redoing command at index " + index);
		if (index == commands.size())
			return;
		EditorCommand command = commands.get(index);
		command.execute();
		++index;
		System.out.printf("redone %s at index %d%n", command.getClass().getSimpleName(), index);
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
		System.out.printf("undone %s at index %d%n", command.getClass().getSimpleName(), index);
		System.out.printf("%n\t%s%n%n", buffer.toString());
	}

	/**
	 * The Class AppendCommand adds a given text to the buffer.
	 */
	public class AppendCommand implements EditorCommand {

		private String text;

		public AppendCommand(String text) {
			this.text = text;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see patterns.behavioral.command.EditorCommand#execute()
		 */
		@Override
		public void execute() {
			System.out.printf("AppendCommand.execute() -> appending '%s'%n", text);
			/*
			 * TODO: 1. append the given text to the editor's buffer using the append()
			 * method
			 */
			throw new UnsupportedOperationException("method AppendCommand.execute() is not implemented yet!");
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see patterns.behavioral.command.EditorCommand#unexecute()
		 */
		@Override
		public void undo() {
			System.out.printf("AppendCommand.undo() -> un-appending '%s'%n", text);
			/*
			 * TODO: 2. change the length of the character sequence in the buffer to not
			 * include the given text that was previously appended. This is done via the the
			 * buffer's setLength(length of buffer - length of text) method.
			 */
			throw new UnsupportedOperationException("method AppendCommand.unexecute() is not implemented yet!");
		}
	}

	/**
	 * The Class DeleteCommand deletes a given text from the buffer.
	 */
	public class DeleteCommand implements EditorCommand {

		private int start; // The start index
		private int end; // The end index
		private String text; // The deleted text (found between the two indices)

		public DeleteCommand(int startIdx, int endIdx) {
			text = "";
			this.start = startIdx;
			this.end = endIdx;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see patterns.behavioral.command.EditorCommand#execute()
		 */
		@Override
		public void execute() {
			System.out.printf("DeleteCommand.execute() -> deleting '%s' from position %d to position %d%n", text, start, end);
			/*
			 * TODO: 3. keep a reference to the deleted text that's found between the two
			 * given indices in the buffer. You'll need that text to later implement the
			 * 'un-execute' method. You can do this be using the buffer's subString(int
			 * start,int end) method.
			 */
			text = null; // FIXME
			System.out.printf("<<< DeleteCommand.execute() -> deleted '%s' from position %d to position %d%n", text, start, end);
			/*
			 * TODO: 4. once we have a reference to the 'text' being deleted, delete it by
			 * calling the buffer's delete(start, end) method.
			 */
			throw new UnsupportedOperationException("method DeleteCommand.execute() is not implemented yet!");
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see patterns.behavioral.command.EditorCommand#unexecute()
		 */
		@Override
		public void undo() {
			System.out.printf("DeleteCommand.undo() -> un-deleting '%s' starting at position %d%n", text, start);
			/*
			 * TODO: 5. you'll want to undo the previous deletion by inserting the deleted
			 * text in the former index (prior to deletion) in the buffer. This can be done
			 * by using the buffer's insert(start, text) method.
			 */
			throw new UnsupportedOperationException("method DeleteCommand.unexecute() is not implemented yet!");
			//System.out.println(">>> DeleteCommand.undo() -> re-inserted '" + text + "' at position " + start);
		}
	}

	/**
	 * Gets the buffer (for testing only)
	 *
	 * @return the buffer
	 */
	public StringBuilder getBuffer() {
		return buffer;
	}

	/**
	 * Gets the index (for testing only)
	 *
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
}