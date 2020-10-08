package patterns.creational.prototype.gui;

import java.util.List;

import patterns.creational.prototype.ComboBox;

/**
 * The Class ComboBoxImpl is a {@link ComboBox} implementation. It has an 'id'
 * and 'label' properties and a list of 'IndexedValue'. The {@link IndexedValue}
 * class is just a label/value wrapper that any combo box uses.
 */
public class ComboBoxImpl implements ComboBox, Cloneable {

	private String id;
	private String label;
	private List<IndexedValue> values;
	private IndexedValue selected;

	public ComboBoxImpl() {
		super();
	}

	/**
	 * Instantiates a new combo box impl.
	 *
	 * @param id     the id
	 * @param label  the label
	 * @param values the values
	 */
	public ComboBoxImpl(String id, String label, List<IndexedValue> values) {
		super();
		this.id = id;
		this.label = label;
		this.values = values;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#clone()
	 */
	// TODO: 11. Remove this annotation once implemented
	@SuppressWarnings({ "null", "unused" })
	@Override
	public ComboBoxImpl clone() {
		/*
		 * TODO: 3. This ComboBoxImpl is cloneable. You must therefore implement the
		 * clone method. The easiest way is to return a clone obtained via a call to
		 * super.clone(). [e.g. return (ComboBoxImpl) super.clone();]. This will not
		 * take care of the 'selected' property and the list of IndexedValues cloning.
		 * We need to take care of this manually, Of course, if this, for any reason,
		 * throws an exception, we must handle it and return some sort of an
		 * alternative...
		 */
		try {
			/*
			 * TODO: 4. You'll need to call the super.clone() method to get a reference to a
			 * ComboBoxImpl copy
			 */
			ComboBoxImpl copy = null;
			/*
			 * TODO: 5. You'll need to call the clone() method of this.selected to clone it
			 */
			copy.selected = null;
			/*
			 * TODO: 6. ...and clone every property
			 */
			copy.id = null;
			copy.label = null;
			/*
			 * TODO: 7. You'll need to instantiate the the copy.values properties with
			 * something like a new LinkedList<IndexedValue>();
			 */
			copy.values = null;
			for (IndexedValue indexedValue : values) {
				/*
				 * Then, you'll need to add a clone of each indexed value in the original list
				 * TODO: 8. add a cloned indexedValue to the list
				 */
			}
			// TODO: 9. return a result
		} catch (Exception ex) {
			// TODO: 10. handle a clone related exception and return something the factory can use and return it
		}
		throw new UnsupportedOperationException("method ComboBoxImpl.clone() is not implemented yet!");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ComboBoxImpl [id=" + id + ", label=" + label + ", selected=" + selected);
		if (values != null) {
			sb.append(", values=\n");
			for (IndexedValue value : values) {
				sb.append("\t" + value + "\n");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	// getters and setters etc...

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<IndexedValue> getValues() {
		return values;
	}

	public void setValues(List<IndexedValue> values) {
		this.values = values;
	}

	public IndexedValue getSelected() {
		return selected;
	}

	public void setSelected(IndexedValue selected) {
		this.selected = selected;
	}

	/**
	 * The Class IndexedValue is just a wrapper around an index (key) and value
	 * properties. Any combo box uses an similar object for keeping reference to a
	 * list of identifiable values
	 */
	class IndexedValue implements Cloneable {

		private String index;
		private String value;

		public IndexedValue(String index, String value) {
			super();
			this.index = index;
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Object#clone()
		 */
		@Override
		public IndexedValue clone() throws CloneNotSupportedException {
			try {
				return (IndexedValue) super.clone();
			} catch (CloneNotSupportedException cnsex) {
				System.err.println("Error: " + cnsex.getMessage());
				cnsex.printStackTrace(System.err);
				return new IndexedValue(index, value);
			}
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((index == null) ? 0 : index.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			IndexedValue other = (IndexedValue) obj;
			if (index == null) {
				if (other.index != null)
					return false;
			} else if (!index.equals(other.index))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "IndexedValue [index=" + index + ", value=" + value + "]";
		}

		// getters setters etc...

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}
}
