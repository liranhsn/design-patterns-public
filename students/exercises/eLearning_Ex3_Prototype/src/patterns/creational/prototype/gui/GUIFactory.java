package patterns.creational.prototype.gui;

import java.util.LinkedList;
import java.util.List;

import patterns.creational.prototype.Button;
import patterns.creational.prototype.ComboBox;
import patterns.creational.prototype.gui.ComboBoxImpl.IndexedValue;

public class GUIFactory {

	// TODO: 14. remove this when implemented
	@SuppressWarnings("unused")
	private Button button;
	// TODO: 15. remove this when implemented
	@SuppressWarnings("unused")
	private ComboBox combo;

	public GUIFactory(Button button, ComboBox combo) {
		this.button = button;
		this.combo = combo;
	}

	/**
	 * implement this method to return a button clone. Is the button cloneable?
	 *
	 * We can do this, for example, using reflection:
	 *
	 * <pre>
	 * Button copy = null;
	 * try {
	 * 	 Method method = button.getClass().getDeclaredMethod(&quot;clone&quot;, new Class&lt;?&gt;[] {});
	 * 	 copy = (Button) method.invoke(this.button, new Object[] {});
	 * } catch (Exception ex) {
	 * 	 ex.printStackTrace();
	 * }
	 * return copy;
	 * </pre>
	 */
	public Button createButton() {
		/*
		 * TODO: 12. replace this with an implementation. The easiest implementation is
		 * casting and cloning: ((ButtonImpl) button).clone();
		 *
		 * BUT we want this to support all implementations of Button, so see TODO #1
		 */
		throw new UnsupportedOperationException("method GUIFactory.createButton() is not implemented yet!");
	}

	/**
	 * Implement this method to return a combo box copy. Is the ComboBox Cloneable?
	 *
	 * We can do this, for example, using reflection:
	 *
	 * <pre>
	 * ComboBox copy = null;
	 * try {
	 * 	Method method = this.combo.getClass().getDeclaredMethod(&quot;clone&quot;, new Class&lt;?&gt;[] {});
	 * 	copy = (ComboBox) method.invoke(this.combo, new Object[] {});
	 * } catch (Exception ex) {
	 * 	ex.printStackTrace();
	 * }
	 * return copy;
	 * </pre>
	 */
	public ComboBox createComboBox() {
		/*
		 * TODO: 13. replace this with an implementation. The easiest implementation is
		 * casting and cloning: ((ComboBoxImpl) combo).clone()
		 *
		 * BUT we want this to support all implementations of ComboBox, so see TODO #2
		 */
		throw new UnsupportedOperationException("method GUIFactory.createComboBox() is not implemented yet!");
	}

	public static void main(String[] args) throws Exception {
		Button button = new ButtonImpl("Button", "My Button", "My Action");
		List<IndexedValue> values = new LinkedList<IndexedValue>();
		ComboBoxImpl comboBox = new ComboBoxImpl("Combo Box", "My Combo Box", values);
		IndexedValue iv1 = comboBox.new IndexedValue("1", "value 1");
		IndexedValue iv2 = comboBox.new IndexedValue("2", "value 2");
		values.add(iv1);
		values.add(iv2);
		comboBox.setSelected(iv2);
		GUIFactory factory = new GUIFactory(button, comboBox);
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				System.out.println(factory.createButton());
			} else {
				System.out.println(factory.createComboBox());
			}
		}
	}
}
