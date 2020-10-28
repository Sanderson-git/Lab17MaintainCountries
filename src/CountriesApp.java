import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CountriesApp {
	
	private static Path filePath = Paths.get("countries.txt");
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		
		while (true) {
			System.out.print("Enter a command (list, add, quit): ");
			String command = scnr.nextLine();
			if (command.equals("quit")) {
				break;
			} else if (command.equals("list")) {
				List<Country> countryList = readFile();
				int i = 1; 
				for (Country thing : countryList) {
					System.out.println(i++ + ". " + thing);
				}
			} else if (command.equals("add")) {
				Country newCountry = getCountryInfo(scnr);
				System.out.println("Adding " + newCountry);
				appendLine(newCountry);
			} else {
				System.out.println("Invalid command.");
			}
		}
		System.out.println("Goodbye.");
		scnr.close();
	}
	
	private static Country getCountryInfo(Scanner scnr) {
		String name = Validator.getString(scnr, "Enter country name: ");
		int population = Validator.getInt(scnr, "Enter country population: ");
		return new Country(name, population);
	}

	public static List<Country> readFile() {
		try {
			List<String> lines = Files.readAllLines(filePath);
			List<Country> things = new ArrayList<>();
			
			for(String line : lines) {
				String[] parts = line.split("_");
				String name = parts[0];
				int population = Integer.parseInt(parts[1]);
				things.add(new Country(name, population));
			}
			return things;
			
		} catch (IOException e) {
			System.out.println("Unable to read file.");
			return new ArrayList<>();
		}
	}

	public static void appendLine(Country thing) {
		String line = thing.getName() + "_" + thing.getPopulation();
		List<String> lines = Collections.singletonList(line);
		try {
			Files.write(filePath, lines, StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Unable to write to file.");
		}
	}
}


