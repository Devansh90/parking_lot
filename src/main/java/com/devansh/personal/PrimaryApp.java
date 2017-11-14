package com.devansh.personal;

import com.devansh.personal.constants.Constants;
import com.devansh.personal.exception.ParkingLotException;
import com.devansh.personal.model.ParkingSlot;
import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PrimaryApp {

    ParkingSlot[] parkingLot;
    Integer currentSize;

    public static void main(String args[]) {
        //Scanner
        Scanner scanner = new Scanner(System.in).useDelimiter("\\s");
        PrimaryApp app = new PrimaryApp();

        //Check if args is there & is of type file
        if (args.length > 0) {
            fileReaderMode(args[0],app);
        } else {
            commandMode(scanner, app);
        }

        //Treat Message
        //System.out.println("Thanks for using the system!");
    }

    private static void commandMode(final Scanner scanner, final PrimaryApp app) {
        //Command Mode
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
    }

    private static void fileReaderMode(final String fileName, final PrimaryApp app) {
        //File reader mode
        BufferedReader br = null;
        FileReader fr = null;
        try {
            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String sCurrentLine;
            Scanner scanner;
            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                scanner = new Scanner(sCurrentLine).useDelimiter("\\s");
                String command = scanner.next();
                app.executeCommand(command, scanner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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
                slotByCarNumber(inputScanner.next());
                break;

            case Constants.Commands.search_car_by_colour:
                carByColour(inputScanner.next());
                break;

            case Constants.Commands.search_slot_by_colour:
                slotByColour(inputScanner.next());
                break;

            default:
                System.out.println("Command not recognized!");
        }
    }

    private void slotByColour(final String carColour) {
        if(parkingLot == null)
            throw new ParkingLotException("Parking lot is not defined");

        ArrayList<String> finalString = new ArrayList<>();
        for (ParkingSlot parkingSlot : parkingLot) {
            if (parkingSlot != null) {
                if (carColour.equalsIgnoreCase(parkingSlot.getCar().getCarColour())) {
                    finalString.add(String.valueOf(parkingSlot.getLotId()));
                }
            }
        }

        System.out.println(StringUtils.join(finalString, ","));
    }

    private void carByColour(final String carColour) {
        if(parkingLot == null)
            throw new ParkingLotException("Parking lot is not defined");

        ArrayList<String> finalString = new ArrayList<>();
        for (ParkingSlot parkingSlot : parkingLot) {
            if (parkingSlot != null) {
                if (carColour.equalsIgnoreCase(parkingSlot.getCar().getCarColour())) {
                    finalString.add(parkingSlot.getCar().getCarLicenceNumber());
                }
            }
        }

        System.out.println(StringUtils.join(finalString, ","));
    }

    private void slotByCarNumber(final String carNumber) {
        if(parkingLot == null)
            throw new ParkingLotException("Parking lot is not defined");

        for (ParkingSlot parkingSlot : parkingLot) {
            if (parkingSlot != null) {
                if (carNumber.equalsIgnoreCase(parkingSlot.getCar().getCarLicenceNumber())) {
                    System.out.println(parkingSlot.getLotId());
                    return;
                }
            }
        }
        System.out.println("Not found");
    }

    private void lotStatus() {
        if(parkingLot == null)
            throw new ParkingLotException("Parking lot is not defined");

        System.out.println("Slot No. \tRegistration No \tColour");
        for (ParkingSlot parkingSlot : parkingLot) {
            if (parkingSlot != null) {
                System.out.println(parkingSlot.getLotId() + "\t\t\t"
                                           + parkingSlot.getCar().getCarLicenceNumber() + "\t\t"
                                           + parkingSlot.getCar().getCarColour());
            }
        }
    }

    private void carDeparture(final Scanner inputScanner) {
        if(parkingLot == null)
            throw new ParkingLotException("Parking lot is not defined");

        Integer exitSlotNumber = inputScanner.nextInt();
        if (exitSlotNumber > parkingLot.length) throw new ParkingLotException("Cannot exit slot:" + exitSlotNumber + " as no such exist!");

        if (parkingLot[exitSlotNumber] == null) throw new ParkingLotException("Cannot exit slot:" + exitSlotNumber + " as no such exist!");

        parkingLot[exitSlotNumber] = null;
        System.out.println("Slot number " + exitSlotNumber + " is free");
        currentSize = currentSize - 1;
    }

    //Create Car Parking
    private void parkCar(final Scanner inputScanner) {
        if(parkingLot == null)
            throw new ParkingLotException("Parking lot is not defined");

        ParkingSlot.Car car = new ParkingSlot.Car(inputScanner.next(), inputScanner.next());
        if (currentSize < parkingLot.length) {
            for (int i = 0; i < parkingLot.length; i++) {
                if (parkingLot[i] == null) {
                    parkingLot[i] = new ParkingSlot(i + 1, car);
                    System.out.println("Allocated slot number:" + (i + 1));
                    break;
                }
            }
            currentSize = currentSize + 1;
        }
        else {
            System.out.println("Sorry, parking lot is full");
        }
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

        if (parkingLot != null) throw new ParkingLotException("Parking lot is already initialized!");

        parkingLot = new ParkingSlot[slotCount];
        currentSize = 0;
        System.out.println("Created a parking slot with " + slotCount + " slots");
    }
}

