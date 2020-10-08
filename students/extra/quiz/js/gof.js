var intents = new Array(
		// Abstract Factory
		"Provide an interface for creating families of related or dependent\nobjects without specifying their concrete classes.",
		// Builder
		"Separate the construction of a complex object from its representation\nso that the same construction process can create different representations.",
		// Factory Method
		"Define an interface for creating an object, but let subclasses decide\nwhich class to instantiate.  Lets a class defer instantiation to subclasses.",
		// Prototype
		"Specify the kinds of objects to create using a cloneable instance\nand create new objects by copying this instance.",
		// Singleton
		"Ensure a class only has one instance, and provide a global point of access to it.",
		// Adapter
		"Convert the interface of a class into another interface clients expect.\nLets classes work together that couldn't otherwise because of incompatible interfaces.",
		// Bridge
		"Decouple an abstraction from its implementation so that the two can vary independently.",
		// Composite
		"Arrange objects into tree structures to represent part-whole hierarchies.\nLets clients treat individual objects and collections of objects uniformly.",
		// Decorator
		"Attach additional responsibilities to an object dynamically.  Provide\na flexible alternative to subclassing for extending functionality.",
		// Facade
		"Provide a unified interface to a set of interfaces in a subsystem.\nDefines a higher-level interface that makes the subsystem easier to use.",
		// Flyweight
		"Use sharing to support large numbers of fine-grained objects efficiently.",
		// Proxy
		"Provide a surrogate or placeholder for another object to control access to it.",
		// Chain of Responsibility
		"Avoid coupling the sender of a request to its receiver by giving more\nthan one object a chance to handle the request.  Link the receiving\nobjects and pass the request along the list until an object handles it.",
		// Command
		"Encapsulate a request as an object, thereby letting you parameterize\nclients with different requests, queue or log requests, and support\nundoable operations.",
		// Interpreter
		"Given a language, define a represention for its grammar along with a\nprocessor that uses the representation to parse sentences in the language.",
		// Iterator
		"Provide a way to access the elements of an aggregate object sequentially\nwithout exposing its underlying representation.",
		// Mediator
		"Define an object that encapsulates how a set of objects interact.\nPromotes loose coupling by keeping objects from referring to each\nother explicitly, and lets you vary their interaction independently.",
		// Memento
		"Without violating encapsulation, capture and externalize an object's\ninternal state so that the object can be restored to this state later.",
		// Observer
		"Define a one-to-many dependency between objects so that when one object\nchanges state, all its dependents are notified and updated automatically.",
		// State
		"Allow an object to alter its behavior when its internal state changes.\nThe object will appear to change its class.",
		// Strategy
		"Define a family of algorithms, encapsulate each one, and make them\ninterchangeable.  Lets the algorithm vary independently from clients\nthat use it.",
		// Template Method
		"Define the skeleton of an algorithm in an operation, deferring some\nsteps to subclasses.  Lets subclasses redefine certain steps of an\nalgorithm without changing the algorithm's structure.",
		// Visitor
		"Represent an operation to be performed on the elements of an object structure.\nLets you define a new operation without changing the classes of the elements on\nwhich it operates.");

var highlights = new Array(
		// Abstract Factory
		"- a level of indirection that provides creation services\n- supports a \"family\" of products\n- supprts many creation strategies: choice of derived class, reusing cached objects,\n  distributed creation, choice of platform or address space\n- the \"new\" statement considered harmful",
		// Builder
		"- one common input, many possible outputs\n- wrapper/delegate structure\n  - wrapper directs the algorithm of creation/composition\n  - each delegate encapsulates a target output",
		// Factory Method
		"- indirect creation through inheritance\n- virtual constructor\n- the \"new\" statement considered harmful",
		// Prototype
		"- indirect creation through delegation\n- \"clone\"\n- the \"new\" statement considered harmful",
		// Singleton
		"- enforces a fixed number of instances of a class\n- lazy initialization\n- global access",
		// Adapter
		"- wrap an existing class with a new interface\n- impedance match an old component with a new system\n- wrapper/delegate structure",
		// Bridge
		"- allows the implementation to change while the interface remains stable\n- wrapper/delegate structure\n  - wrapper is a hierarchy that publishes the interface\n  - delegate is a hierarchy that hides implementation baggage\n- insulation: handle/body, envelope/letter",
		// Composite
		"- recursive composition\n- 1-to-many \"has a\" up the \"is a\" hierarchy\n- examples:\n  - file system hierarchy\n  - GUI (menus, layout managers)",
		// Decorator
		"- recursive composition\n- 1-to-1 \"has a\" up the \"is a\" hierarchy\n- a single core object wrapped by possibly many optional objects\n- user configuration of optional features to an existing class",
		// Facade
		"- wrap an existing system with a new interface\n- a simple entry point to a large sub-system\n- a layer of indirection that hides legacy complexity",
		// Flyweight
		"- how to design dozens of small objects that incur minimal overhead\n- instance-independent state stays in the class\n- instance-dependent state is supplied by the customer\n- a factory facilitates object reuse",
		// Proxy
		"- an extra level of indirection that provides additional functionality:\n  - distributed communication\n  - auditing, logging\n  - smart pointer\n- wrapper/delegate structure",
		// Chain of Responsibility
		"- recursive composition\n- 1-to-1 \"has a\" at the top of the \"is a\" hierarchy\n- object-oriented linked list",
		// Command
		"- object-oriented callback\n- a magic cookie that encapsulates a \"method invocation\"\n- \"execute\"",
		// Interpreter
		"- recursive composition\n- 1-to-many \"has a\" up the \"is a\" hierarchy\n- process a grammar",
		// Iterator
		"- polymorphic traversal\n- promote to full object status the traversal of a collection",
		// Mediator
		"- an extra level of indirection that encapsulates the many-to-many relationships between other components\n- wrapper/delegate structure\n  - wrapper is a \"mapping\" object\n  - delegates are a network of collaborating objects\n- a politically-correct manager (or God) object",
		// Memento
		"- undo, rollback\n- a magic cookie that encapsulates a \"check point\" capability",
		// Observer
		"- wrapper/delegate structure\n  - wrapper encapsulates the core business logic\n  - each delegate provides user-configurable, optional functionality\n- example:\n  - a data presentation application with graph, bar chart, pie chart, and table views",
		// State
		"- wrapper/delegate structure\n- wrapper passes its \"this\" pointer\n- delegate collaborates with wrapper",
		// Strategy
		"- configure choice of algorithm\n- wrapper/delegate structure\n  - the client is the wrapper\n  - the algorithm object is the delegate",
		// Template Method
		"- configure steps of an algorithm\n- placeholders specified in base class, implemented in derived classes",
		// Visitor
		"- double dispatch\n- do the right thing based on the type of two objects\n- add operations to an existing hierarchy");

var images;
var orderOfModels;
var currentModel, numHints, numWrong;
var beginIdx = 0;
var pass = 72;

function init(start, end) {
	beginIdx = start - 1;
	images = new Array(end - beginIdx);
	orderOfModels = new Array(end - beginIdx);
	// alert("orderOfModels.length: " + orderOfModels.length);
	for ( var i = 0; i < orderOfModels.length; i++)
		orderOfModels[i] = i + (start);
	for ( var j = 0; j < images.length; j++) {
		images[j] = new Image();
		images[j].src = "../images/gof" + (j + (start)) + ".jpg";
	}
	reset();
}

function reset() {
	for ( var i = 0, j, temp; i < orderOfModels.length; i++) {
		j = Math.floor(Math.random() * 1017 % orderOfModels.length);
		temp = orderOfModels[i];
		// alert("random: " + j + ", orderOfModels[j]: " + orderOfModels[j] + ",
		// orderOfModels[i]: " + orderOfModels[i]);
		orderOfModels[i] = orderOfModels[j];
		orderOfModels[j] = temp;
	}
	currentModel = -1;
	numHints = 0;
	numWrong = 0;
	nextModel();
}

function nextModel() {
	currentModel += 1;
	if (currentModel == orderOfModels.length) {
		var total = orderOfModels.length;
		var numRight = total - numWrong;
		var grade = numRight > 0 ? (numRight / total) * 100 : 0;
		alert("Number of hints requested - "
				+ numHints
				+ "\nNumber of incorrect choices - "
				+ numWrong
				+ "\nYour mark: "
				+ grade
				+ "%"
				+ (grade >= pass ? "\nPass!" : "\nFail! (passing grade: "
						+ pass + "%)") + "\nShuffling and starting over.");
		reset();
	} else
		eval("document.imgForm.model.src=\""
				+ images[(orderOfModels[currentModel] - (beginIdx) - 1)].src
				+ "\"");
}

function pick(number) {
	if (number != (orderOfModels[currentModel] - beginIdx - 1)) {
		numWrong += 1;
		alert("Try again.  Consider these highlights:\n\n"
				+ highlights[orderOfModels[currentModel] - 1]);
	} else {
		alert("CORRECT.  Highlights are:\n\n"
				+ highlights[orderOfModels[currentModel] - 1]);
		nextModel();
	}
}

function intent() {
	numHints += 1;
	alert(intents[orderOfModels[currentModel] - 1]);
}
