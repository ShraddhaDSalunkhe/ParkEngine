/*
    ParkingLot Autoamation System

    Step 1 : Create required enums
    Step 2 : Vehicle Hierarchy creation
    Step 3 : VehicleFactory creation (Factory Pattern)
    Step 4 : ParkingSpot Hirrarchy
    Step 5 : ParkingObserver class
    Step 6 : ParkingFloor class 
    Step 7 : ParkingDispalyBoard (Observer Pattern)
    Step 8 : ParkingStrategy Class (Strategy Pattern)
    Step 9 : PricingStrategy Class (Strategy Pattern)
    Step 10 : PaymentStrategy Class
    Step 11 : ParkingTicket Class
    Step 12 : EntryGate Class
    Step 13 : ExitGate Class
    Step 14 : ParkingLot Class (Singleton Pattern)
    Step 15 : Main class (Controller)

*/

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/////////////////////////////////////////////////////////
// Step 1 : Create Enums
// It is used to create fixed constants which are required
// throughout the project
/////////////////////////////////////////////////////////

// Represents the diffrent types of vehicles supported by the project
enum VehicleType
{
    BIKE,
    CAR,
    TRUCK
}

// Represents diffrent types of parking spots
enum SpotType
{
    BIKE,
    CAR,
    TRUCK
}

// Represents the current state of parking ticket
enum TicketStatus
{
    ACTIVE,
    CLOSED
}

/////////////////////////////////////////////////////////
// Step 2 : Create Vehicle Class hierarchy
// It is used to create multiple types of classes which r
// represnets the types of vehicles
// Concepts : Abstraction , Inheritance, Polymorphism, Encapsulation
/////////////////////////////////////////////////////////

// Class which represnts a generic vehicle type
abstract class Vehicle
{
    // Abstracted (Hidden) characteristics of class

    private String vehicleNumber;

    private VehicleType vehicleType;

    // Parametrised constructor
    public Vehicle(String vehicleNumber, VehicleType vehicleType)
    {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    // Concrete getter method
    public VehicleType getVehicleType()
    {
        return this.vehicleType;
    }

    // Concrete getter method
    public String getVehicleNumber()
    {
        return this.vehicleNumber;
    }

    // Every concrete class will provide its own defination
    public abstract void display();
} 

// Class which represnets the Vechile type as Bike
class Bike extends Vehicle
{
    // Parametrised constructor
    public Bike(String vehicleNumber)
    {
        // Calls Vechile class constructor
        super(vehicleNumber, VehicleType.BIKE);
    }

    // Method overriding
    @Override 
    public void display()
    {
        System.out.println("Bike : "+getVehicleNumber());
    }
}

// Class which represnets the Vechile type as Car
class Car extends Vehicle
{
    // Parametrised constructor
    public Car(String vehicleNumber)
    {
        // Calls Vechile class constructor
        super(vehicleNumber, VehicleType.CAR);
    }

    // Method overriding
    @Override 
    public void display()
    {
        System.out.println("Car : "+getVehicleNumber());
    }
}

// Class which represnets the Vechile type as Truck
class Truck extends Vehicle
{
    // Parametrised constructor
    public Truck(String vehicleNumber)
    {
        // Calls Vechile class constructor
        super(vehicleNumber, VehicleType.TRUCK);
    }

    // Method overriding
    @Override 
    public void display()
    {
        System.out.println("Truck : "+getVehicleNumber());
    }
}
/////////////////////////////////////////////////////////
// Step 3 : Create VehicleFActory Class
// It is used to centralsied the creation of vechile objects
// Concepts : Factory Design Pattern
/////////////////////////////////////////////////////////

class VehicleFactory
{
    // Creates and return the desired class object

    public static Vehicle creatVehicle(VehicleType type, String number)
    {
        switch(type)
        {
            case BIKE:
                return new Bike(number);

            case CAR:
                return new Car(number);

            case TRUCK:
                return new Truck(number);

            default:
                throw new IllegalArgumentException("Invalid Vehicle type");
        }
    }
}


