package application;

import engine.BookSalesEngine;
import model.Argument;

public class Application {
	public static void main(String[] args) {
		ArgumentOptions options = new ArgumentOptions();
		Argument argument = options.scanOptions(args);
		if (argument == null) {
			Runtime.getRuntime().exit(0);
		}
		BookSalesEngine engine = new BookSalesEngine();
		engine.execute(argument);

	}
}
