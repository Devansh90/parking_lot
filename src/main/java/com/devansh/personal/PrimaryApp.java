package com.devansh.personal;

import com.devansh.personal.constants.Constants;
import com.devansh.personal.exception.ParkingLotException;
import com.devansh.personal.model.ParkingLot;

import java.util.Scanner;

public class PrimaryApp {

    ParkingLot[] parkingLotList;

    public static void main(String args[]) {
        //Scanner
        Scanner scanner = new Scanner(System.in).useDelimiter("\\s");
        PrimaryApp app = new PrimaryApp();

        //TODO
        //Check if args is there & is of type file

        //Commands
        String command = scanner.next();
        while (!Constants.Commands.exit.equalsIgnoreCase(command)) {
            try {
                //Execute Command
                app.executeCommand(command, scanner);
            } catch (ParkingLotException e) {
                //Display Exception if any
                System.out.println(e.getMessage());
            }
            //Get next command
            command = scanner.next();
        }
        //Treat Message
        System.out.println("Thanks for using the system!");
    }

    //To Execute every type of command
    private void executeCommand(final String command, final Scanner inputScanner) {
        switch (command) {
            case Constants.Commands.create_parking_lot:
                createParkingLot(inputScanner);
                break;

            case Constants.Commands.park_car:
                parkCar(inputScanner);
                break;

            case Constants.Commands.car_departure:
                carDeparture(inputScanner);
                break;

            case Constants.Commands.lot_status:
                lotStatus();
                break;

            case Constants.Commands.search_slot_by_car_number:
                parkCar(inputScanner);
                break;

            case Constants.Commands.search_car_by_colour:
                carDeparture(inputScanner);
                break;

            case Constants.Commands.search_slot_by_colour:
                lotStatus();
                break;

            default:
                return;
        }
    }

    //Create Car Parking
    private void parkCar(final Scanner inputScanner) {
        return;
    }

    //Create new parking lot, throws designed exceptions
    private void createParkingLot(final Scanner inputScanner) {

        Integer slotCount = 0;
        //Read size of slots to be created
        try {
            slotCount = Integer.parseInt(inputScanner.next());
        } catch (NumberFormatException e) {
            throw new ParkingLotException("Parking lot size not defined / unrecognizable");
        }

        if (parkingLotList != null) throw new ParkingLotException("Parking lot is already initialized!");

        parkingLotList = new ParkingLot[slotCount];
        System.out.println("Created a parking slot with " + slotCount + " slots");
    }
}

