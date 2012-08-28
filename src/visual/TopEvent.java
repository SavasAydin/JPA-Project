package visual;

import homeronfire.Locations;

/*
 * TopEvent is to send location
 * wherever to be listened (TopListener)
 */

import java.util.EventObject;

public class TopEvent extends EventObject {

	private static final long serialVersionUID = -7447147412191304035L;

	private Locations loc;

	public TopEvent(Object source, Locations loc) {
		super(source);

		this.loc = loc;
	}

	public Locations getLoc() {
		return loc;
	}

}
