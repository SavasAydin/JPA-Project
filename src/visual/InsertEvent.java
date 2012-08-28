package visual;

import java.util.EventObject;

/*
 * InsertEvent to send (String) 
 *  message wherever to be listened (InsertListener)
 */
public class InsertEvent extends EventObject {

	private static final long serialVersionUID = -8241277289870095147L;

	private String text;

	public InsertEvent(Object source, String text) {
		super(source);

		this.text = text;
	}

	public String getText() {
		return text;
	}

}
