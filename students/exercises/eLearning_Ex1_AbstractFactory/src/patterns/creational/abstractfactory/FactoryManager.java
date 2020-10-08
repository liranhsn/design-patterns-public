/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
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
package patterns.creational.abstractfactory;

import java.util.ResourceBundle;

import patterns.creational.carparts.Engine;
import patterns.creational.carparts.Hood;
import patterns.creational.carparts.Wheel;

/**
 * The Class FactoryManager manages the creation of a the concrete
 * {@link AbstractCarFactory} that is returned by it's factory method
 * {@link FactoryManager#getFactory()}. The concrete factory class name is
 * configured in a properties file read by this manager. The manger then
 * instantiates the factory by using standard reflection API.
 */
public class FactoryManager {

	static final String PROP_KEY = "cars.factory.name";
	static final String DEFAULT_FACTORY = "patterns.creational.abstractfactory.MiniVanFactory";
	static final String RESOURCE_BUNDLE_NAME = "patterns.creational.abstractfactory.abstractfactory";
	private AbstractCarFactory factory;
	private String factoryClassName;
	public static volatile FactoryManager INSTANCE;

	/**
	 * Instantiates a new factory manager.
	 */
	// TODO: 10. remove this annotation once the constructor is implemented
	@SuppressWarnings("unused")
	private FactoryManager() {
		super();
		/*
		 * TODO: 5. Read the properties file using a resource bundle. This is typically
		 * done by code like this: ResourceBundle rb =
		 * ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME)
		 */
		ResourceBundle rb = null;
		/*
		 * TODO: 6. Initialize the factoryClassName property. It's value is the outcome
		 * of reading the 'PROP_KEY' property from the resource bundle [e.g.
		 * rb.getString(PROP_KEY)];
		 */
		factoryClassName = null;
		if (factoryClassName == null) {
			/*
			 * TODO: 7. Make sure if the property does not exist, to initialize
			 * the'factoryClassName' with a default value. [e.g. if (factoryClassName ==
			 * null) factoryClassName = DEFAULT_FACTORY]
			 */
		}
		/**
		 * TODO: 8. Now that you got a 'factoryClassName' value, use reflection to
		 * instantiate the factory:
		 *
		 * Example:
		 *
		 * <pre>
		 * factory = (AbstractCarFactory) Class.forName(factoryClassName).getDeclaredConstructor(new Class<?>[] {}).newInstance(new Object[] {});
		 * </pre>
		 *
		 * Note such code can throw multiple types of exceptions that can be handled or
		 * thrown
		 */
		factory = null;
	}

	/**
	 * Gets the single instance of FactoryManager.
	 *
	 * @return single instance of FactoryManager
	 */
	public synchronized static FactoryManager getInstance() {
		if (INSTANCE == null) {
			synchronized (FactoryManager.class) {
				if (INSTANCE == null) {
					INSTANCE = new FactoryManager();
				}
			}
		}
		return INSTANCE;
	}

	/**
	 * Gets the factory.
	 *
	 * @return the factory
	 */
	public synchronized AbstractCarFactory getFactory() {
		assert factory != null : "member 'factory' is null";
		/*
		 * TODO: 9. Once the constructor is implemented and all fields are initialized,
		 * return the factory
		 */
		throw new UnsupportedOperationException("The method FactoryManager.getFactory() is not implemented yet!");
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		FactoryManager factoryManager = FactoryManager.getInstance();
		AbstractCarFactory factory = factoryManager.getFactory();
		System.out.printf("Factory of type: %s%n", factory.getClass().getSimpleName());
		Hood hood = factory.createHood("red");
		System.out.println(hood);
		Wheel wheel = factory.createWheel();
		System.out.println(wheel);
		Engine engine = factory.createEngine();
		System.out.println(engine);
	}
}