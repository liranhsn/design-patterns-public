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

import com.acme.composite.tree.api.ITree;
import com.acme.composite.tree.api.ITreeNode;

/**
 * The class AVLTree is an implementation of AVL tree (named after inventors
 * Adelson-Velsky and Landis) and is a self-balancing binary search tree.
 *
 * In an AVL tree, the heights of the two child subtrees of any node differ by
 * at most one.
 *
 * if at any time they differ by more than one, rebalancing is done to restore
 * this property.
 *
 * Lookup, insertion, and deletion all take O(log n) time in both the average
 * and worst cases, where n is the number of nodes in the tree prior to the
 * operation.
 *
 * Insertions and deletions may require the tree to be rebalanced by one or more
 * tree rotations.
 *
 * @param <T> the type of key
 */
public class AVLTree<T extends Comparable<T>> extends BinaryTree<T> {

	public AVLTree(Class<T> clazz, T key) {
		super(clazz, key);
	}

	public AVLTree(Class<T> clazz) {
		super(clazz);
	}

	private ITreeNode<T> leftRotate(ITreeNode<T> x) {
		ITreeNode<T> y = x.right();
		ITreeNode<T> T2 = y.left();
		y.setLeft(x);
		x.setRight(T2);
		return y;
	}

	private ITreeNode<T> rightRotate(ITreeNode<T> y) {
		ITreeNode<T> x = y.left();
		ITreeNode<T> T2 = x.right();
		x.setRight(y);
		y.setLeft(T2);
		return x;
	}

	protected int balance(ITreeNode<T> node) {
		return node == null ? 0 : height(node.left()) - height(node.right());
	}

	@Override
	protected ITreeNode<T> insert(ITreeNode<T> root, T key) {
		// perform the normal BST insertion
		root = super.insert(root, key);
		// get the balance factor of this node
		int balance = balance(root);
		// If this node becomes unbalanced, then there are 4 cases
		if (balance > 1 && key.compareTo(root.left().key()) < 0) // Left Left Case
			return rightRotate(root);
		if (balance < -1 && key.compareTo(root.right().key()) > 0) // Right Right Case
			return leftRotate(root);
		if (balance > 1 && key.compareTo(root.left().key()) > 0) { // Left Right Case
			root.setLeft(leftRotate(root.left()));
			return rightRotate(root);
		}
		if (balance < -1 && key.compareTo(root.right().key()) < 0) { // Right Left Case
			root.setRight(rightRotate(root.right()));
			return leftRotate(root);
		}
		return root;
	}

	@Override
	protected ITreeNode<T> delete(ITreeNode<T> root, T key) {
		root = super.delete(root, key);
		// If this node becomes unbalanced, then there are 4 cases
		int balance = balance(root); // Left Left Case
		if (balance > 1 && balance(root.left()) >= 0)
			return rightRotate(root);
		if (balance > 1 && balance(root.left()) < 0) { // Left Right Case
			root.setLeft(leftRotate(root.left()));
			return rightRotate(root);
		}
		if (balance < -1 && balance(root.right()) <= 0) // Right Right Case
			return leftRotate(root);
		if (balance < -1 && balance(root.right()) > 0) { // Right Left Case
			root.setRight(rightRotate(root.right()));
			return leftRotate(root);
		}
		return root;
	}

	@Override
	public ITree<T> delete(T key) {
		root = delete(root, key);
		return this;
	}

	@Override
	public ITreeNode<T> lowestCommonAncestor(ITreeNode<T> root, T key1, T key2) {
		if (root == null)
			return root;
		if (root.key().compareTo(key1) == 0 || root.key().compareTo(key2) == 0)
			return root;
		ITreeNode<T> left = lowestCommonAncestor(root.left(), key1, key2);
		ITreeNode<T> right = lowestCommonAncestor(root.right(), key1, key2);
		if (left != null && right != null)
			return root;
		if (left != null)
			return lowestCommonAncestor(root.left(), key1, key2);
		else
			return lowestCommonAncestor(root.right(), key1, key2);
	}

	public static void main(String[] args) {
		AVLTree<Integer> avl = new AVLTree<>(Integer.class);
		int size = 10;
		for (int i = 1; i <= size; i++)
			avl.insert(i * 2);
		avl.print();
		System.out.println();
		for (int i = size; i >= 1; i--) {
			System.out.printf("deleting %s...%n", i * 2);
			avl.delete(i * 2);
			avl.print();
			System.out.println();
		}
	}
}
