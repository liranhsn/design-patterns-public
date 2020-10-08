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

/**
 * The class BinaryTree is the default implementation of
 * {@link AbstractBinaryTree}
 *
 * @param <T> the type of node key
 */
public class BinaryTree<T extends Comparable<T>> extends AbstractBinaryTree<T> {

	public BinaryTree(Class<T> clazz, T key) {
		super(clazz, key);
	}

	public BinaryTree(Class<T> clazz) {
		super(clazz);
	}

	protected int balance(TreeNode<T> node) {
		return node == null ? 0 : height(node.left) - height(node.right);
	}

	public static void main(String[] args) {
		ITree<Integer> tree = new BinaryTree<>(Integer.class, 1);
		tree.root().setLeft(new TreeNode<>(2));
		tree.root().setRight(new TreeNode<>(3));
		tree.root().left().setLeft(new TreeNode<>(4));
		tree.root().left().setRight(new TreeNode<>(5));
		tree.root().right().setLeft(new TreeNode<>(6));
		tree.root().right().setRight(new TreeNode<>(7));
		tree.root().right().left().setRight(new TreeNode<>(8));
		tree.print();
		System.out.printf("Level 4: %s, Level 5: %s, distance(4, 5) = %s%n", tree.level(tree.root(), 4, 0), tree.level(tree.root(), 5, 0),
				tree.distance(tree.root(), 4, 5));
		System.out.println("distance(4, 6) = " + tree.distance(tree.root(), 4, 6));
		System.out.println("distance(3, 4) = " + tree.distance(tree.root(), 3, 4));
		System.out.println("distance(2, 4) = " + tree.distance(tree.root(), 2, 4));
		System.out.println("distance(8, 5) = " + tree.distance(tree.root(), 8, 5));
	}
}
