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
        System.out.println("Bike :  program1008.java:108  Untitled1:108  program1009.java:108  Untitled1:108  Untitled2:108 - program1010.java:108"+getVehicleNumber());
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
        System.out.println("Car :  program1008.java:126  Untitled1:126  program1009.java:126  Untitled1:126  Untitled2:126 - program1010.java:126"+getVehicleNumber());
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
        System.out.println("Truck :  program1008.java:144  Untitled1:144  program1009.java:144  Untitled1:144  Untitled2:144 - program1010.java:144"+getVehicleNumber());
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

/////////////////////////////////////////////////////////
// Step 4 : Create ParkingSpot Hierarchy
// It is used to create hierarchy of Parking Spots
// Concepts : Encapsulation, Abstraction, Inheritance, Polymorphism
/////////////////////////////////////////////////////////

abstract class ParkingSpot
{
    // Unique number for parking spot (Primary Key)
    private int spotNumber;

    // Type of parking spot
    private SpotType spotType;

    // Indaicates wheteher spot is currently occupied of not
    private boolean occupied;

    // Stores information about the vechicle
    private Vehicle vehicle;

    // Parametrised constructor
    public ParkingSpot(int spotNumber, SpotType spotType)
    {
        this.spotNumber = spotNumber;
        this.spotType = spotType;

        // Initialsed with default values
        this.occupied = false;
        this.vehicle = null;
    }

    public int getSpotNumber()
    {
        return this.spotNumber;
    }

    public SpotType getSpotType()
    {
        return this.spotType;
    }

    public boolean isOccupied()
    {
        return this.occupied;
    }

    public Vehicle getVehicle()
    {
        return this.vehicle;
    }

    // It is used to park the vechicle
    public void parkVehicle(Vehicle vehicle)
    {
        if(this.occupied == true)
        {
            throw new RuntimeException("Parking spot is already occupied");
        }
        else
        {
            this.vehicle = vehicle;
            this.occupied = true;
        }
    }

    public Vehicle removeVehicle()
    {
        if(this.occupied == true)
        {
            Vehicle temp = vehicle;

            this.vehicle = null;
            this.occupied = false;

            return temp;
        }
        else
        {
            throw new RuntimeException("Parking spot is already empty");
        }
    }

    // This method decides whether we can park it in the spot or not
    public abstract boolean canFitVehicle(Vehicle vehicle);

    public void display()
    {
        System.out.println("Spot :  program1008.java:264  Untitled1:264  program1009.java:264  Untitled1:264  Untitled2:264 - program1010.java:264"+spotNumber+ "["+ spotType+"]");
    
        if(this.occupied == true)
        {
            System.out.println("Occupied by :  program1008.java:268  Untitled1:268  program1009.java:268  Untitled1:268  Untitled2:268 - program1010.java:268"+vehicle.getVehicleNumber());
        }
        else
        {
            System.out.println("Spot is available  program1008.java:272  Untitled1:272  program1009.java:272  Untitled1:272  Untitled2:272 - program1010.java:272");
        }
    }
} // End of ParkingSpot class

class BikeSpot extends ParkingSpot
{
    public BikeSpot(int spotNumber)
    {
        super(spotNumber, SpotType.BIKE);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.BIKE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

class CarSpot extends ParkingSpot
{
    public CarSpot(int spotNumber)
    {
        super(spotNumber, SpotType.CAR);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.CAR)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

class TruckSpot extends ParkingSpot
{
    public TruckSpot(int spotNumber)
    {
        super(spotNumber, SpotType.TRUCK);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.TRUCK)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

/////////////////////////////////////////////////////////
// Step 5 : ParkingObserver class
// It is used to automatically update display board when
// the parking availablity changes
// Concepts : Observer 
/////////////////////////////////////////////////////////

interface ParkingObserver
{
    void update();
}

/////////////////////////////////////////////////////////
// Step 6 : ParkingFloor class
// It is used to manage parking floor
// Concepts : Composition, ArrayList, Object Management
/////////////////////////////////////////////////////////

class ParkingFloor
{
    // Unique floor number
    private int floorNumber;

    // Collection of all parking spots
    private List<ParkingSpot> parkingSpots;

    // Collection of observers registered for the floor
    private List<ParkingObserver> observers;

    public ParkingFloor(int floorNumber)
    {
        this.floorNumber = floorNumber;

        this.parkingSpots = new ArrayList<>();

        this.observers = new ArrayList<>();
    }

    public int getFloorNumber()
    {
        return this.floorNumber;
    }

    public void addParkingSpot(ParkingSpot spot)
    {
        parkingSpots.add(spot);
    }

    public void addObserver(ParkingObserver observer)
    {
        observers.add(observer);
    }

    private void notifyObservers()
    {
        for(ParkingObserver observer : observers)
        {
            observer.update();
        }
    }

    // Method is going to search parking spot for speific type of vechile
    public ParkingSpot findAvailabSpot(Vehicle vehicle)
    {
        for(ParkingSpot spot : parkingSpots)
        {
            if(!spot.isOccupied() && spot.canFitVehicle(vehicle))
            {
                return spot;
            }
        } 

        return null;
    }

    // Called when new vechile gets parked
    public void occupySpot(ParkingSpot spot, Vehicle vehicle)
    {
        // allocate sopt for the vechile
        spot.parkVehicle(vehicle);

        // Notify all observers about the availablity of spots
        notifyObservers();
    }

    public void releaseSpot(ParkingSpot spot)
    {
        // relase the already allocated spot
        spot.removeVehicle();

        // Notify all observers about the availablity of spots
        notifyObservers();
    }

    public int getAvailableCount(SpotType type)
    {
        int count = 0;

        for(ParkingSpot spot : parkingSpots)
        {
            if(spot.getSpotType() == type && !spot.isOccupied())
            {
                count++;
            }
        }

        return count;
    }

    // Display all parking spots on specific floor
    public void displayFloor()
    {
        System.out.println();

        System.out.println("Floor :  program1008.java:454  Untitled1:454  program1009.java:454  Untitled1:454  Untitled2:454 - program1010.java:454"+floorNumber);

        for(ParkingSpot spot : parkingSpots)
        {
            spot.display();
        }
    }
}

/////////////////////////////////////////////////////////
// Step 7 : Create ParkingDispalyBoard Class
// It is used to create a class which displays the parking
// status

// Subject  ->  ParkingFloor 
// Observer ->  ParkingDispalyBoard

// Note : Any observer is going to observe the subject
// There will be multiple observers for one subject

// Concepts : Observer Design pattern
/////////////////////////////////////////////////////////

class ParkingDispalyBoard implements ParkingObserver
{
    // Floor whose availablity is displayed by this board
    private ParkingFloor floor;

    // Constructor
    public ParkingDispalyBoard(ParkingFloor floor)
    {
        this.floor = floor;
    }

    // Automatically called whenever floor availablity changes
    @Override 
    public void update()
    {
        System.out.println();
        System.out.println("Display Board  program1008.java:493  Untitled1:493  program1009.java:493  Untitled1:493  Untitled2:493 - program1010.java:493");
        
        System.out.println("Floor :  program1008.java:495  Untitled1:495  program1009.java:495  Untitled1:495  Untitled2:495 - program1010.java:495"+floor.getFloorNumber());

        System.out.println("Available Bike spots :  program1008.java:497  Untitled1:497  program1009.java:497  Untitled1:497  Untitled2:497 - program1010.java:497"+floor.getAvailableCount(SpotType.BIKE));
       
        System.out.println("Available Car spots :  program1008.java:499  Untitled1:499  program1009.java:499  Untitled1:499  Untitled2:499 - program1010.java:499"+floor.getAvailableCount(SpotType.CAR));
        
        System.out.println("Available Truck spots :  program1008.java:501  Untitled1:501  program1009.java:501  Untitled1:501  Untitled2:501 - program1010.java:501"+floor.getAvailableCount(SpotType.TRUCK));
        
        System.out.println("");
        System.out.println();
    }
}

// We can create new observers for the same subject
/*
    class ParkingWebsite implements ParkingObserver
    {
        public void update()
        {   
        
        }
    }
*/

/////////////////////////////////////////////////////////
// Step 8 : Create ParkingStrategy Class

// It is used to create a class ParkingStrategy which is
// responsible to decide the parking spot selection

// Concepts : Strategy Design pattern
/////////////////////////////////////////////////////////

// Defines a common concepts for parking spot selection algorithm
interface ParkingStrategy
{
    ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}

// Selects the first available parking spot 
class FirstAvialableParkingStrategy implements ParkingStrategy
{
    @Override 
    public ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle)
    {
        // Iterate over all available floors
        for(ParkingFloor floor : floors)
        {
            ParkingSpot spot = floor.findAvailabSpot(vehicle);

            if(spot != null)
            {
                return spot;
            }
        }

        return null;
    }
}

/*
    class NearestAvialableParkingStrategy implements ParkingStrategy
    {

    }
*/

/////////////////////////////////////////////////////////
// Step 9 : Create PricingStrategy Class

// It is used to create a class PricingStrategy
// It keeps the pricing algorithm idependent of exit logic

// Concepts : Strategy Design pattern
/////////////////////////////////////////////////////////

interface PricingStrategy
{
    double calculatePrice(Vehicle vehicle, long hours);
} 

class NormalPricingStrategy implements PricingStrategy
{
    @Override 
    public double calculatePrice(Vehicle vehicle, long hours)
    {
        if(hours <= 0)
        {
            hours = 1;
        }

        switch(vehicle.getVehicleType())
        {
            case BIKE:
                return hours * 20;

            case CAR:
                return hours * 50;

            case TRUCK:
                return hours * 100;

            default:
                return 0;
        }
    }
}

class WeekendPricingStrategy implements PricingStrategy
{
    @Override 
    public double calculatePrice(Vehicle vehicle, long hours)
    {
        if(hours <= 0)
        {
            hours = 1;
        }

        switch(vehicle.getVehicleType())
        {
            case BIKE:
                return hours * 40;

            case CAR:
                return hours * 100;

            case TRUCK:
                return hours * 200;

            default:
                return 0;
        }
    }
}

/////////////////////////////////////////////////////////
// Step 10 : Create PaymentStrategy Class

// It is used to create a class PaymentStrategy
// It supports diffrent types of payment methods

// Concepts : Strategy Design pattern
/////////////////////////////////////////////////////////

// Common contract for all payment methods
interface PaymentStrategy
{
    void pay(double amount);
}

class UPIPayment implements PaymentStrategy
{
    @Override 
    public void pay(double amount)
    {
        System.out.println("UPI payment succesful : Rs.  program1008.java:650  Untitled1:650  program1009.java:650  Untitled1:650  Untitled2:650 - program1010.java:650"+amount);
    }
}

class CardPayment implements PaymentStrategy
{
    @Override 
    public void pay(double amount)
    {
        System.out.println("Card payment succesful : Rs.  program1008.java:659  Untitled1:659  program1009.java:659  Untitled1:659  Untitled2:659 - program1010.java:659"+amount);
    }
}

class CashPayment implements PaymentStrategy
{
    @Override 
    public void pay(double amount)
    {
        System.out.println("Cash payment succesful : Rs.  program1008.java:668  Untitled1:668  program1009.java:668  Untitled1:668  Untitled2:668 - program1010.java:668"+amount);
    }
}

/////////////////////////////////////////////////////////
// Step 11 : Create ParkingTicket Class

// It is used to represent one complete parking transaction

/////////////////////////////////////////////////////////

class ParkingTicket
{
    // Used for generating unique tickets
    private static int counter = 1000;

    // Ticket number for unique tcket
    private int ticketNumber;

    // Vechile associated with that ticket
    private Vehicle vehicle;

    // Floor on which the vechile is parked
    private ParkingFloor floor;

    // Actual spot on which the vechile is parked
    private ParkingSpot spot;

    // Time at which vechile arrives
    private LocalDateTime entryTime;

    // Time at which vechile exited from parking floor
    private LocalDateTime exitTime;

    // It maintains the status of the ticket
    private TicketStatus status;

    // Paramerised constructor
    public ParkingTicket(
                            Vehicle vehicle,
                            ParkingFloor floor,
                            ParkingSpot spot
                        )
    {
        this.ticketNumber = ++counter;
        this.vehicle = vehicle;
        this.floor = floor;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
        this.status = TicketStatus.ACTIVE;
    }

    // Getter method for ticket number
    public int getTicketNumber()
    {
        return this.ticketNumber;
    }

    // Getter method for vechile
    public Vehicle getVehicle()
    {
        return this.vehicle;
    }

    // Getter method for floor
    public ParkingFloor getFloor()
    {
        return this.floor;
    }

    // Getter method for spot
    public ParkingSpot getSpot()
    {
        return this.spot;
    }

    // Getter method for entrytime
    public LocalDateTime getEntryTime()
    {
        return this.entryTime;
    }

    // Getter method for exittime
    public LocalDateTime getExitTime()
    {
        return this.exitTime;
    }

    // Getter method for status
    public TicketStatus getStatus()
    {
        return this.status;
    }

    // Method gets called when vechile is going out
    public void closeTicket()
    {
        this.exitTime = LocalDateTime.now();

        this.status = TicketStatus.CLOSED;
    }

    // Calculate the total number of hours the vechile is parked
    public long calculateHours()
    {
        LocalDateTime endtime;

        if(exitTime == null)
        {
            endtime = LocalDateTime.now();
        }
        else
        {
            endtime = exitTime;
        }

        // Calculate the actual time
        long minutes = Duration.between(entryTime, endtime).toMinutes();
        
        // Converts minutes to hours
        long hours = minutes / 60;

        if(minutes % 60 != 0)
        {
            hours++;
        }
        
        if(hours == 0)
        {
            hours = 1;
        }

        return hours;
    }

    // It will display complete ticket on screen
    public void displayTicket()
    {
        System.out.println();

        System.out.println("");
        System.out.println("Parking Ticket  program1008.java:809  Untitled1:809  program1009.java:809  Untitled1:809  Untitled2:809 - program1010.java:809");
        System.out.println("");

        System.out.println("Ticket Number :  program1008.java:812  Untitled1:812  program1009.java:812  Untitled1:812  Untitled2:812 - program1010.java:812"+this.ticketNumber);

        System.out.println("Vechile Number :  program1008.java:814  Untitled1:814  program1009.java:814  Untitled1:814  Untitled2:814 - program1010.java:814"+this.vehicle.getVehicleNumber());
        
        System.out.println("Vechile Type :  program1008.java:816  Untitled1:816  program1009.java:816  Untitled1:816  Untitled2:816 - program1010.java:816"+this.vehicle.getVehicleType());

        System.out.println("Floor Number :  program1008.java:818  Untitled1:818  program1009.java:818  Untitled1:818  Untitled2:818 - program1010.java:818"+this.floor.getFloorNumber());

        System.out.println("Spot Number :  program1008.java:820  Untitled1:820  program1009.java:820  Untitled1:820  Untitled2:820 - program1010.java:820"+this.spot.getSpotNumber());

        System.out.println("Entry Time :  program1008.java:822  Untitled1:822  program1009.java:822  Untitled1:822  Untitled2:822 - program1010.java:822"+this.entryTime);

        System.out.println("Ticket Status :  program1008.java:824  Untitled1:824  program1009.java:824  Untitled1:824  Untitled2:824 - program1010.java:824"+this.status);

        System.out.println("");

        System.out.println();
    }
}

/////////////////////////////////////////////////////////
// Step 12 : Create EntryGate Class
// It is used to create a class EntryGate
// It handles the entry of vehicles into the parking lot
// Concepts : Singleton Design pattern
/////////////////////////////////////////////////////////

class EntryGate
{
    // Unique gate number
    private int gateNumber;

    // Constructor
    public EntryGate(int gateNumber)
    {
        this.gateNumber = gateNumber;
    }

    public int getGateNumber()
    {
        return this.gateNumber;
    }
    
    // it generates new parking ticket when vechile enters into the parking lot
    public ParkingTicket generateTicket(Vehicle vehicle, ParkingFloor floor, ParkingSpot spot)
    {
        System.out.println("Vehicle entered through Gate :  program1009.java:858  Untitled1:858  Untitled2:858 - program1010.java:858" + this.gateNumber);
        // new ticket is generated for the vechile
        return new ParkingTicket(vehicle, floor, spot);
    }
}

/////////////////////////////////////////////////////////
// Step 13 : Create ExitGate Class
// It is used to handle billing and payment when vechile exits from the parking lot
// Concepts : Singleton Design pattern
/////////////////////////////////////////////////////////

class ExitGate
{
    // Unique gate number
    private int gateNumber;

    // Constructor
    public ExitGate(int gateNumber)
    {
        this.gateNumber = gateNumber;
    }

    public int getGateNumber()
    {
        return this.gateNumber;
    }

    public void processExit(
                             ParkingTicket ticket,
                             PricingStrategy pricingStrategy,
                             PaymentStrategy paymentStrategy
                        )
    {
        System.out.println("Vehicle exited through Gate :  program1009.java:888  Untitled1:892  Untitled2:892 - program1010.java:892" + this.gateNumber);

        // Close the ticket
        ticket.closeTicket();

        //calculate the total hours the vechile is parked
        long hours = ticket.calculateHours();

        // Calculate the total bill for the vechile
        double amount = pricingStrategy.calculatePrice(ticket.getVehicle(), hours);

        System.out.println();
        
        System.out.println("Vehicle exited through Gate :  program1009.java:894  Untitled1:905  Untitled2:905 - program1010.java:905" + this.gateNumber);
        System.out.println("Total hours :  program1009.java:898  Untitled1:906  Untitled2:906 - program1010.java:906" + hours);
        System.out.println("Total amount :  program1009.java:900  Untitled1:907  Untitled2:907 - program1010.java:907" + amount);

        // Process the payment
        paymentStrategy.pay(amount);
    }

   
}

class program1010
{
    public static void main(String A[])
    {

    }
}