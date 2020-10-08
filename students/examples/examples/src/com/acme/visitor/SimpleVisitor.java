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
package com.acme.visitor;

/**
 * The class SimpleVisitor is the default implementation of a {@link Visitor}
 */
public class SimpleVisitor implements Visitor {

	@Override
	public void visit(Chapter chapter) {
		System.out.printf("Visiting the Chapter '%s' with %d sentences", chapter.title, chapter.sentences.size());
	}

	@Override
	public void visit(Sentence sentence) {
		System.out.printf("%n%nVisiting a sentence begining with '%s' with %d words:%n", sentence.words.get(0), sentence.words.size());
	}

	@Override
	public void visit(Word word) {
		System.out.printf("%s ", word);
	}
}
