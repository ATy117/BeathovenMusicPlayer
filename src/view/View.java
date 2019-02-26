package view;

import controller.Controller;

public abstract class View {

	private Controller controller;

	protected View() { }

	protected View(Controller controller)
	{
		this.controller = controller;
	}
	public abstract void Update();
}
