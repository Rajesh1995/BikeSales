package application;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import model.Argument;

public class ArgumentOptions {
	private static final String BOOKS = "--books=";
	private static final String SALES = "--sales=";
	private static final String TOPSELLINGBOOKS = "--top_selling_books=";
	private static final String TOPCUSTOMERS = "--top_customers=";
	private static final String SALESONDATE = "--sales_on_date=";

	public Argument scanOptions(String[] args) {

		if (!(args != null && args.length > 0)) {
			return null;
		}
		int index = 0;
		String arg;
		int mandatory = 0;
		int num;
		Argument argument = new Argument();
		while (index < args.length) {
			arg = args[index++];
			if (arg.startsWith(BOOKS)) {
				mandatory++;
				argument.setBooks(arg.substring(BOOKS.length()));
			} else if (arg.startsWith(SALES)) {
				mandatory++;
				argument.setSales(arg.substring(SALES.length()));
			} else if (arg.startsWith(TOPSELLINGBOOKS)) {
				try {
					num = Integer.parseInt(arg.substring(TOPSELLINGBOOKS.length()));
				} catch (NumberFormatException e) {
					System.out.println("Incorrect number");
					return null;
				}
				argument.setTopSellingCount(num);
			} else if (arg.startsWith(TOPCUSTOMERS)) {
				try {
					num = Integer.parseInt(arg.substring(TOPCUSTOMERS.length()));
				} catch (NumberFormatException e) {
					System.out.println("Incorrect number");
					return null;
				}
				argument.setTopCustomers(num);
			} else if (arg.startsWith(SALESONDATE)) {
				DateFormat format = new SimpleDateFormat("yyyy mm dd", Locale.ENGLISH);
				Date date;
				try {
					date = format.parse(arg.substring(SALESONDATE.length()));
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}
				argument.setSalesOnDate(date);
			} else {
				System.out.println("invalid args");
				return null;
			}
		}
		if (mandatory == 2) {
			return argument;
		} else {
			System.out.println("All mandatory args are not specified");
			return null;
		}

	}

}
