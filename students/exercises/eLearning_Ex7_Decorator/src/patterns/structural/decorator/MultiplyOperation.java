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
package patterns.structural.decorator;

/**
 * The Class MultiplyOperation implements the {@code MathOperation} interface
 * and overrides the mandatory {@code eval} method by multiplying all the
 * operands represented by the {@code operands} variable arguments array.
 */
public class MultiplyOperation implements MathOperation {

	@Override
	public Double eval(Double... operands) {
		/**
		 * TODO: 1. Implement the multiplication operation.
		 *
		 * The easiest way is to review the AddOperation implementation as an example
		 * and implement the same here, taking into account the multiplication operation
		 * instead of addition.
		 *
		 * or:
		 *
		 * <pre>
		 * Double result = 1D;
		 * for (Double operand : operands)
		 * 	result *= operand;
		 * return result;
		 * </pre>
		 *
		 * or - using stream features (reduce):
		 * <pre>
		 * return operands == null || operands.length < 1 ? 0D : Arrays.stream(operands).reduce((a, b) -> a * b).orElse(operands[0]);
		 * </pre>
		 *
		 */
		throw new UnsupportedOperationException("method MultiplyOperation.eval(Double... operands) is not implemented yet!");
	}
}