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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.acme.composite.tree.api.ITree;
import com.acme.composite.tree.api.ITreeNode;

/**
 * The class AbstractBinaryTree is the base class for binary tree
 * implementations
 *
 * @param <T> the type of node key
 */
public abstract class AbstractBinaryTree<T extends Comparable<T>> implements ITree<T> {

	protected ITreeNode<T> root;
	private Class<T> clazz;

	public AbstractBinaryTree(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	public AbstractBinaryTree(Class<T> clazz, T key) {
		super();
		this.clazz = clazz;
		if (key != null)
			this.root = new TreeNode<T>(key);
	}

	/*
	 * this is a BST insertion algorithm
	 */
	protected ITreeNode<T> insert(ITreeNode<T> root, T key) {
		if (root == null) {
			root = new TreeNode<>(key);
			return root;
		}
		if (key.compareTo(root.key()) < 0)
			root.setLeft(insert(root.left(), key));
		else if (key.compareTo(root.key()) > 0)
			root.setRight(insert(root.right(), key));
		return root;
	}

	@Override
	public ITree<T> insert(T key) {
		root = insert(root, key);
		return this;
	}

	@Override
	public ITreeNode<T> inOrderSuccessor(ITreeNode<T> root) {
		// step 1 of the above algorithm
		if (root != null && root.right() != null)
			return root.right().inOrderSuccessor();
		// Start from root and search for successor down the tree
		ITreeNode<T> node = root;
		while (node != null) {
			if (node.key().compareTo(root.key()) < 0)
				node = node.left();
			else if (node.key().compareTo(root.key()) > 0)
				node = node.right();
			else
				break;
		}
		return node;
	}

	/**
	 * delete a node from it by making sure that tree shrinks from the bottom (i.e.
	 * the deleted node is replaced by bottom most and rightmost node). This
	 * different from BST deletion. Here we do not have any order among elements, so
	 * we replace with last element.
	 *
	 * 1. Starting at root, find the deepest and rightmost node in binary tree and
	 * node which we want to delete.
	 *
	 * 2. Replace the deepest rightmost node’s data with node to be deleted.
	 *
	 * 3. Then delete the deepest rightmost last node.
	 */
	protected ITreeNode<T> delete(ITreeNode<T> root, T key) {
		if (root == null) // base case: if the tree is empty
			return root;
		if (key.compareTo(root.key()) < 0) // otherwise, recurse down the tree
			root.setLeft(delete(root.left(), key));
		else if (key.compareTo(root.key()) > 0)
			root.setRight(delete(root.right(), key));
		else { // if key is same as root's key, then this is the node to be deleted
			if (root.left() == null) // node with only one child or no child
				return root.right();
			else if (root.right() == null)
				return root.left();
			// else node with two children:
			ITreeNode<T> temp = inOrderSuccessor(root.right()); // get the inorder successor (smallest in the right subtree)
			System.out.printf("deleting %s - in order successor: %s%n", key, temp);
			root.setKey(temp.key()); // copy the inorder successor's data to this node
			root.setRight(delete(root.right(), temp.key())); // delete the inorder successor
		}
		return root;
	}

	@Override
	public ITree<T> delete(T key) {
		root = delete(root, key);
		return this;
	}

	private List<T> inOrder(ITreeNode<T> root, List<T> arr) {
		if (root == null)
			return arr;
		arr = inOrder(root.left(), arr);
		arr.add(root.key());
		arr = inOrder(root.right(), arr);
		return arr;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] inOrder() {
		List<T> list = inOrder(root, new ArrayList<>());
		return (T[]) list.toArray((T[]) Array.newInstance(clazz, list.size()));
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] inOrder(ITreeNode<T> node) {
		List<T> list = inOrder(node, new ArrayList<>());
		return (T[]) list.toArray((T[]) Array.newInstance(clazz, list.size()));
	}

	private List<T> preOrder(ITreeNode<T> root, List<T> arr) {
		if (root == null)
			return arr;
		arr.add(root.key());
		arr = preOrder(root.left(), arr);
		arr = preOrder(root.right(), arr);
		return arr;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] preOrder() {
		List<T> list = preOrder(root, new ArrayList<>());
		return (T[]) list.toArray((T[]) Array.newInstance(clazz, list.size()));
	}

	private List<T> postOrder(ITreeNode<T> root, List<T> arr) {
		if (root == null)
			return arr;
		arr = postOrder(root.left(), arr);
		arr = postOrder(root.right(), arr);
		arr.add(root.key());
		return arr;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] postOrder() {
		List<T> list = postOrder(root, new ArrayList<>());
		return (T[]) list.toArray((T[]) Array.newInstance(clazz, list.size()));
	}

	protected List<T> levelOrder(ITreeNode<T> root, int level, List<T> arr) {
		if (root == null)
			return arr;
		if (level <= 1)
			arr.add(root.key());
		else {
			levelOrder(root.left(), level - 1, arr);
			levelOrder(root.right(), level - 1, arr);
		}
		return arr;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] levelOrder() {
		int height = height();
		List<T> arr = new ArrayList<>();
		for (int level = 1; level <= height; level++)
			arr = levelOrder(root, level, arr);
		return (T[]) arr.toArray((T[]) Array.newInstance(clazz, arr.size()));
	}

	protected T min(ITreeNode<T> root) {
		if (root == null)
			return null;
		T min = root.key();
		T lmin = min(root.left());
		T rmin = min(root.right());
		if (lmin != null && lmin.compareTo(min) < 0)
			min = lmin;
		if (rmin != null && rmin.compareTo(min) < 0)
			min = rmin;
		return min;
	}

	@Override
	public T min() {
		return min(root);
	}

	protected T max(ITreeNode<T> root) {
		if (root == null)
			return null;
		T max = root.key();
		T rmax = max(root.right());
		T lmax = max(root.left());
		if (rmax != null && rmax.compareTo(max) > 0)
			max = rmax;
		if (lmax != null && lmax.compareTo(max) > 0)
			max = lmax;
		return max;
	}

	@Override
	public T max() {
		return max(root);
	}

	protected int height(ITreeNode<T> root) {
		return root == null ? 0 : Math.max(height(root.left()), height(root.right())) + 1;
	}

	@Override
	public int height() {
		return height(root);
	}

	protected int size(ITreeNode<T> root) {
		return root == null ? 0 : size(root.left()) + size(root.right()) + 1;
	}

	@Override
	public int size() {
		return size(root);
	}

	/**
	 * General search algorithm for any tree
	 *
	 * @param root the root node
	 * @param key  the key to look for
	 * @return the first node in the tree with the same key
	 */
	protected ITreeNode<T> search(ITreeNode<T> root, T key) {
		if (root == null || key == null)
			return null;
		if (key.compareTo(root.key()) == 0)
			return root;
		else {
			ITreeNode<T> found = null;
			if (key.compareTo(root.key()) < 0)
				found = search(root.left(), key);
			else if (key.compareTo(root.key()) > 0)
				found = search(root.right(), key);
			return found;
		}
	}

	@Override
	public ITreeNode<T> search(T key) {
		return search(root, key);
	}

	@Override
	public ITreeNode<T> root() {
		return this.root;
	}

	public void print() {
		System.out.printf("%n");
		if (root != null && root.right() != null)
			printTree(root.right(), true, "");
		System.out.printf("%s:%s%n", (root == null ? "null" : root.key().toString()), height(root));
		if (root != null && root.left() != null)
			printTree(root.left(), false, "");
		System.out.printf("%n");
	}

	private void printTree(ITreeNode<T> node, boolean isRight, String indent) {
		if (node.right() != null) {
			printTree(node.right(), true, indent + (isRight ? "      " : " |    "));
		}
		System.out.print(indent);
		if (isRight) {
			System.out.print(" /");
		} else {
			System.out.print(" \\");
		}
		System.out.print("-- ");
		System.out.printf("%s:%s%n", (node == null ? "null" : node.key().toString()), height(node));
		if (node != null && node.left() != null) {
			printTree(node.left(), false, indent + (isRight ? " |    " : "      "));
		}
	}

	protected ITreeNode<T> last(ITreeNode<T> root) {
		if (root == null)
			return null;
		if (root.right() == null)
			return root;
		return last(root.right());
	}

	@Override
	public TreeNode<T> last() {
		T[] arr = levelOrder();
		return new TreeNode<T>(arr[arr.length - 1]);
	}

	private boolean isFullTree(ITreeNode<T> node) {
		if (node == null) // if empty tree
			return true;
		if (node.left() == null && node.right() == null) // if leaf node
			return true;
		if (node.left() != null && node.right() != null) // if both left and right subtrees are not null the are full
			return isFullTree(node.left()) && isFullTree(node.right());
		return false; // if none work
	}

	@Override
	public boolean isFull() {
		return isFullTree(root);
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/*
	 * This function returns pointer to LCA of two given values key1 and key2. This
	 * function assumes that key1 and key2 are present in the Binary Tree
	 */
	@Override
	public ITreeNode<T> lowestCommonAncestor(ITreeNode<T> root, T key1, T key2) {
		// Base case
		if (root == null)
			return null;
		/*
		 * If either key1 or key2 matches with root's key, report the presence by
		 * returning root (Note that if a key is ancestor of other, then the ancestor
		 * key becomes LCA
		 */
		if (root.key().compareTo(key1) == 0 || root.key().compareTo(key2) == 0)
			return root;
		// Look for keys in left and right subtrees
		ITreeNode<T> lcaLeft = lowestCommonAncestor(root.left(), key1, key2);
		ITreeNode<T> lcaRight = lowestCommonAncestor(root.right(), key1, key2);
		/*
		 * If both of the above calls return Non-NULL, then one key is present in once
		 * subtree and other is present in other, So this node is the LCA
		 */
		if (lcaLeft != null && lcaRight != null)
			return root;
		// otherwise check if left subtree or right subtree is LCA
		return lcaLeft != null ? lcaLeft : lcaRight;
	}

	@Override
	public int level(ITreeNode<T> root, T key, int level) {
		if (root == null)
			return -1;
		if (root.key().compareTo(key) == 0)
			return level;
		int left = level(root.left(), key, level + 1);
		if (left == -1)
			return level(root.right(), key, level + 1);
		return left;
	}

	/*
	 * returns the distance between two nodes represented by key1 and key2. First
	 * find LCA of two nodes. Then we find distance from LCA to two nodes.
	 */
	@Override
	public int distance(ITreeNode<T> root, T key1, T key2) {
		ITreeNode<T> lca = lowestCommonAncestor(root, key1, key2);
		return level(lca, key1, 0) + level(lca, key2, 0);
	}
}
