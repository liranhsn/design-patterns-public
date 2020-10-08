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
package com.acme.composite.linkedlist;

/**
 * The class LinkedList is a simple composite of recursive Visitable elements
 */
public class LinkedList {

	private Node head; // head of linked list

	public LinkedList() {
		super();
	}

	public LinkedList(int data) {
		this();
		this.head = Node.create(data);
	}

	public LinkedList(Node node) {
		this();
		this.head = node;
	}

	/*
	 * Inserts a new Visitable at front of the list.
	 */
	public LinkedList push(int data) {
		Node node = Node.create(data); // 1 create node
		node.next = head; // 2. assign head to next of new Visitable
		head = node; // 3. assign node as head
		return this;
	}

	/*
	 * append a node to the end of the list
	 */
	public LinkedList append(int data) {
		if (head != null) {
			Node node = head;
			while (node != null && node.next != null)
				node = node.next;
			node.next = Node.create(data);
		} else
			head = Node.create(data);
		return this;
	}

	/*
	 * To delete a node from linked list, we need to do following steps.
	 *
	 * 1) find previous node of the node to be deleted. 2) change the next of
	 * previous node. 3) free memory for the node to be deleted.
	 */
	public void remove(int data) {
		Node temp = head, prev = null; // store head node
		if (temp != null && temp.data == data) { // if head node itself is to be deleted
			head = temp.next; // changed head
			return;
		}
		while (temp != null && temp.data != data) { // search for the data to be deleted, keep track of previous node as we need to
			// change temp.next
			prev = temp;
			temp = temp.next; // advance the pointer
		}
		if (temp == null) // if data was not present
			return;
		prev.next = temp.next; // unlink the node
		temp = null;
	}

	/*
	 * get the middle of the linked list by advancing a fast pointer and a slow
	 * pointer at half the speed
	 */
	public Node getMiddle() {
		Node fast = head, slow = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/*
	 * This is the simple way where two loops are used. Outer loop is used to pick
	 * the elements one by one and inner loop compares the picked element with rest
	 * of the elements.
	 */
	private void removedDuplicates() {
		Node outer = head;
		while (outer != null && outer.next != null) {
			Node inner = outer;
			while (inner != null && inner.next != null) {
				if (inner.next.data == outer.data) {
					System.out.printf("removing duplicate %d%n", inner.next.data);
					Node dup = inner.next; // remove
					inner.next = dup.next;
					dup = null;
				}
				inner = inner.next;
			}
			outer = outer.next;
		}
	}

	/*
	 * reverse a linked list
	 */
	public LinkedList reverse() {
		Node prev = null, current = head, next = null;
		while (current != null) {
			next = current.next; // next is one step ahead
			current.next = prev; // point current to prev (initially null)
			prev = current; // advance prev and point it to current
			current = next; // advance current to next
		}
		head = prev; // set head to prev (prev is now tail, next and current null)
		return this;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("LinkedList [");
		Node node = head;
		while (node != null) {
			buffer.append(node.data).append(" -> ");
			node = node.next;
		}
		if (head != null) {
			buffer.delete(buffer.length() - " -> ".length(), buffer.length());
		}
		buffer.append("]");
		return buffer.toString();
	}

	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		for (int i = 0; i < 5; i++)
			list.append(i + 1);
		System.out.printf("initialized: %s%n", list);
		list.append(6);
		System.out.printf("appended 6: %s%n", list);
		list.push(0).push(-1);
		System.out.printf("pushed 0 and -1: %s%n", list);
		list.remove(4);
		System.out.printf("removed 4: %s%n", list);
		System.out.printf("middle node: %d%n", list.getMiddle().data);
		list.remove(6);
		System.out.printf("removed 6: %s%n", list);
		System.out.printf("middle node: %d%n", list.getMiddle().data);
		list.append(0).append(2).append(3).append(4).append(5);
		System.out.printf("appended 0,2,3,4 and 5: %s%n", list);
		list.push(-1);
		System.out.printf("pushed -1: %s%n", list);
		list.removedDuplicates();
		System.out.printf("removed duplicates: %s%n", list);
		list.reverse();
		System.out.printf("reversed list: %s%n", list);
	}
}
