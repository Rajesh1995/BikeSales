package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import model.Argument;
import model.Book;
import model.Sale;

public class BookSalesEngine {

	private List<Book> bookList = new ArrayList<>();
	private List<Sale> saleList = new ArrayList<>();
	private Map<String, Integer> topBooks = new LinkedHashMap<>();
	private Map<String, Integer> topCustomers = new LinkedHashMap<>();

	public void execute(Argument argument) {
		if (!argument.validateArgs()) {
			System.out.println("Incorrect values to args");
			return;
		}
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(argument.getBooks()));
			while ((line = br.readLine()) != null) {
				String[] books = line.split(csvSplitBy);
				Book book = new Book();
				book.setId(books[0]);
				book.setTitle(books[1]);
				book.setAuthor(books[2]);
				book.setPrice(Double.parseDouble(books[3]));
				bookList.add(book);
			}
		} catch (IOException e) {

		}
		try {
			br = new BufferedReader(new FileReader(argument.getSales()));
			while ((line = br.readLine()) != null) {
				String[] sales = line.split(csvSplitBy);
				Sale sale = new Sale();
				DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
				Date date;
				date = format.parse(sales[0]);
				sale.setDate(date);
				sale.setEmail(sales[1]);
				sale.setPaymentMethod(sales[2]);
				int count = Integer.parseInt(sales[3]);
				sale.setCount(count);
				Map<String, Integer> id = new HashMap<String, Integer>();
				for (int i = 0; i < count; i++) {
					String temp = sales[4 + i];
					String[] temparr = temp.split(";");
					id.put(temparr[0], Integer.parseInt(temparr[1]));
				}
				sale.setId(id);
				saleList.add(sale);
			}

		} catch (NumberFormatException | IOException | ParseException e) {
			System.out.println("some issue" + e);

		}

		if (argument.getTopSellingCount() != null) {
			for (Sale sale : saleList) {
				Iterator<Entry<String, Integer>> map = sale.getId().entrySet().iterator();
				while (map.hasNext()) {
					Entry<String, Integer> item = map.next();
					if (bookList.stream().filter(book -> book.getId().equals(item.getKey())).count() > 0) {
						if (topBooks.containsKey(item.getKey())) {
							topBooks.put(item.getKey(), topBooks.get(item.getKey()) + item.getValue());
						} else {
							topBooks.put(item.getKey(), item.getValue());
						}
					}
				}
			}
			System.out.println("Top Selling Books:");
			Stream<Map.Entry<String, Integer>> st = topBooks.entrySet().stream();
			st.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(argument.getTopSellingCount())
					.forEach(entry -> {
						System.out.println(entry.getKey());
					});
		}

		if (argument.getTopCustomers() != null) {
			for (Sale sale : saleList) {
				if (topCustomers.containsKey(sale.getEmail())) {
					topCustomers.put(sale.getEmail(), sale.getCount() + topCustomers.get(sale.getEmail()));
				} else {
					topCustomers.put(sale.getEmail(), sale.getCount());
				}
			}
			System.out.println("Top Customers:");
			Stream<Entry<String, Integer>> st = topCustomers.entrySet().stream();
			st.sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(argument.getTopCustomers())
					.forEach(entry -> {
						System.out.println(entry.getKey());
					});
		}

		if (argument.getSalesOnDate() != null) {
			double total = 0;
			for (Sale sale : saleList) {
				if (sale.getDate().equals(argument.getSalesOnDate())) {
					Iterator<Entry<String, Integer>> it = sale.getId().entrySet().iterator();
					while (it.hasNext()) {
						Entry<String, Integer> item = it.next();
						double temp = bookList.stream().filter(book -> book.getId().equals(item.getKey())).findFirst()
								.get().getPrice();
						temp *= item.getValue();
						total += temp;
					}
				}
			}
			System.out.println("Sales on date:");
			System.out.println(argument.getSalesOnDate() + " " + total);
		}

	}

}
