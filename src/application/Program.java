package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrasilTaxService;
import model.services.RentalServices;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		System.out.println("Enter rental Data");
		System.out.print("Car Model: ");
		String model = sc.nextLine();
		System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Return (dd/MM/yyyy hh:mm): ");
		Date finish = sdf.parse(sc.nextLine());

		CarRental car = new CarRental(start, finish, new Vehicle(model));

		System.out.print("Enter price per Hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter price per Day: ");
		double pricePerDay = sc.nextDouble();

		RentalServices rentalServices = new RentalServices(pricePerHour, pricePerDay, new BrasilTaxService());

		rentalServices.processingInvoice(car);

		System.out.println();
		System.out.println("INVOICE: ");
		System.out.println("Basic Payment: " + String.format("%.2f", car.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", car.getInvoice().getTax()));
		System.out.println("Total Payment: " + String.format("%.2f", car.getInvoice().totalPayment()));

		sc.close();
	}

}
