package patterns.creational.prototype.gui;

import patterns.creational.prototype.Button;

/**
 * The Class ButtonImpl.
 */
public class ButtonImpl implements Button, Cloneable {

	private String id;
	private String label;
	private String action;

	public ButtonImpl() {
		super();
	}

	/**
	 * Instantiates a new button impl.
	 *
	 * @param id     the id
	 * @param label  the label
	 * @param action the action
	 */
	public ButtonImpl(String id, String label, String action) {
		super();
		this.id = id;
		this.label = label;
		this.action = action;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Button clone() {
		/*
		 * TODO: 16. this ButtonImpl is cloneable. You must therefore implement the
		 * clone method. The easiest way is to return a clone obtained via a call to
		 * super.clone(). [e.g. return (Button) super.clone();]. However, if this, for
		 * any reason, throws an exception, we must handle it and return some sort of an
		 * alternative...
		 */
		throw new UnsupportedOperationException("method ButtonImpl.clone() is not implemented yet!");
	}

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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		ButtonImpl other = (ButtonImpl) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
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
		return "ButtonImpl [id=" + id + ", label=" + label + ", action=" + action + "]";
	}
}
