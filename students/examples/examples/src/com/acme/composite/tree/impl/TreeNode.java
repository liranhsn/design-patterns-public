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
package com.acme.composite.tree.impl;

import com.acme.composite.tree.api.ITreeNode;

/**
 * The class TreeNode is a default implementation of {@link ITreeNode}
 *
 * @param <T> the type of key
 */
public class TreeNode<T extends Comparable<T>> implements ITreeNode<T> {

	protected T key;
	protected ITreeNode<T> left, right;

	public TreeNode(T key) {
		super();
		this.key = key;
	}

	@Override
	public T key() {
		return this.key;
	}

	@Override
	public ITreeNode<T> right() {
		return this.right;
	}

	@Override
	public ITreeNode<T> left() {
		return this.left;
	}

	@Override
	public void setKey(T key) {
		this.key = key;
	}

	@Override
	public void setRight(ITreeNode<T> right) {
		this.right = right;
	}

	@Override
	public void setLeft(ITreeNode<T> left) {
		this.left = left;
	}

	private int height(ITreeNode<T> root) {
		if (root == null)
			return 0;
		int lheight = height(root.left());
		int rheight = height(root.right());
		return Math.max(lheight, rheight) + 1; // return larger + 1 level for root
	}

	private int size(ITreeNode<T> root) {
		if (root == null)
			return 0;
		return size(root.left()) + size(root.right()) + 1;
	}

	public int height() {
		return height(this);
	}

	public int size() {
		return size(this);
	}

	public void print() {
		if (right != null)
			((TreeNode<T>) right).printTree(true, "");
		System.out.printf("%s:%s%n", (key == null ? "null" : key.toString()), height());
		if (left != null)
			((TreeNode<T>) left).printTree(false, "");
	}

	private void printTree(boolean isRight, String indent) {
		if (right != null) {
			((TreeNode<T>) right).printTree(true, indent + (isRight ? "        " : " |      "));
		}
		System.out.print(indent);
		if (isRight) {
			System.out.print(" /");
		} else {
			System.out.print(" \\");
		}
		System.out.print("----- ");
		System.out.printf("%s:%s%n", (key == null ? "null" : key.toString()), height());
		if (left != null) {
			((TreeNode<T>) left).printTree(false, indent + (isRight ? " |      " : "        "));
		}
	}

	@Override
	public ITreeNode<T> inOrderSuccessor() {
		ITreeNode<T> current = this;
		/* loop down to find the leftmost leaf */
		while (current != null)
			current = current.left();
		return current;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(key);
		return builder.toString();
	}
}
