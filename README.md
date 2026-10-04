# ParkEngine
A scalable Java-based parking lot management system demonstrating LLD, OOP, SOLID principles, and design patterns.
ParkEngine -- Scalable Parking Allocation System

A Java-based Low-Level Design (LLD) project that simulates a
real-world parking lot using Object-Oriented Programming and multiple
Design Patterns.

Overview

ParkEngine is a console-based parking lot management system
developed in Java. It models the complete parking lifecycle, including
vehicle entry, parking spot allocation, ticket generation, vehicle
search, fee calculation, payment processing, and vehicle exit.

The project is primarily focused on demonstrating Object-Oriented
Design, Low-Level Design (LLD), SOLID-oriented architecture, and Design
Patterns through a practical real-world problem.

The current implementation is contained in a single Java source file and
uses Java Collections and the java.time API for managing parking data
and ticket duration.

Features

Supports Bike, Car, and Truck

 Vehicle-specific parking spots

Automatic parking ticket generation

Search an actively parked vehicle by vehicle number

Prevents the same vehicle from being parked twice

Parking availability display using the Observer Pattern

Configurable parking allocation strategy

Configurable pricing strategy

Supports Cash, UPI, and Card payments

* Calculates parking duration using LocalDateTime and Duration

Entry and exit gate management

Supports multiple parking floors

Singleton-based central ParkingLot controller

️ Handles invalid operations such as full parking, duplicate
vehicles, invalid tickets, and occupied/empty spots

️ System Architecture

The system is organized around the following major components:

                    ┌───────────────────┐
                    │    ParkingLot     │
                    │    Singleton      │
                    └─────────┬─────────┘
                              │
              ┌───────────────┼────────────────┐
              │               │                │
              ▼               ▼                ▼
       ParkingFloor      EntryGate         ExitGate
              │                                │
       ┌──────┴──────┐                         │
       │             │                         ▼
       ▼             ▼                  PricingStrategy
 ParkingSpot     DisplayBoard                 │
       │                                      ▼
       ▼                              PaymentStrategy
     Vehicle

System Flow

Vehicle Entry

Select Vehicle Type
        ↓
Create Vehicle using VehicleFactory
        ↓
Check for Duplicate Vehicle
        ↓
Find Available Parking Spot
        ↓
Occupy Parking Spot
        ↓
Generate Parking Ticket
        ↓
Store Active Ticket

Vehicle Exit

Enter Ticket Number
        ↓
Find Active Ticket
        ↓
Close Ticket & Record Exit Time
        ↓
Calculate Parking Duration
        ↓
Calculate Parking Charges
        ↓
Process Payment
        ↓
Release Parking Spot
        ↓
Remove Active Ticket

Design Patterns Used

1. Factory Pattern

VehicleFactory centralizes the creation of Bike, Car, and Truck
objects.

VehicleFactory
      ├── Bike
      ├── Car
      └── Truck

This keeps vehicle creation separate from the main application flow.

2. Singleton Pattern

ParkingLot follows the Singleton approach so the application works
with one central parking-lot controller.

ParkingLot parkingLot = ParkingLot.getInstance();

3. Observer Pattern

ParkingFloor acts as the subject and ParkingDispalyBoard acts as an
observer.

Whenever a vehicle is parked or removed, the display board is notified
and the available spot counts are updated.

ParkingFloor
     │
     └── notifies
             ↓
     ParkingDispalyBoard

4. Strategy Pattern -- Parking Allocation

ParkingStrategy separates the parking-spot selection algorithm from
the parking-lot logic.

The current implementation provides:

FirstAvialableParkingStrategy

The architecture can be extended with strategies such as nearest-spot
selection.

5. Strategy Pattern -- Pricing

PricingStrategy keeps parking fee calculation independent from the
exit process.

Current implementations:

NormalPricingStrategy

WeekendPricingStrategy

6. Strategy Pattern -- Payment

PaymentStrategy allows different payment methods to be selected
without changing the exit logic.

Current implementations:

CashPayment

UPIPayment

CardPayment

Core Classes

Component               Responsibility

Vehicle               Abstract base class for vehicles
Bike                  Represents a bike
Car                   Represents a car
Truck                 Represents a truck
VehicleFactory        Creates vehicle objects
ParkingSpot           Abstract representation of a parking spot
BikeSpot              Parking spot for bikes
CarSpot               Parking spot for cars
TruckSpot             Parking spot for trucks
ParkingFloor          Manages parking spots and observers
ParkingDispalyBoard   Displays current parking availability
ParkingStrategy       Defines spot-selection behavior
PricingStrategy       Defines parking-price calculation
PaymentStrategy       Defines payment behavior
ParkingTicket         Stores a parking transaction
EntryGate             Generates parking tickets
ExitGate              Handles billing and payment
ParkingLot            Central controller for the parking system
program1017           Console application entry point

️ Tech Stack

Language: Java

Core Concepts: OOP, Abstraction, Encapsulation, Inheritance,
Polymorphism

Design: Low-Level Design (LLD)

Design Patterns: Factory, Singleton, Observer, Strategy

Collections: ArrayList, HashMap, List, Map

Date & Time: LocalDateTime, Duration

Application Type: Console-based application

Project Structure

The current project is implemented in a single Java file:

ParkEngine/
│
├── program1017.java
└── README.md

The Java file contains the complete model, strategy, gate, parking-lot,
and controller implementations.

️ How to Run

Prerequisites

Java JDK installed

Command Prompt / Terminal / IDE such as IntelliJ IDEA or VS Code

Compile

javac program1017.java

Run

java program1017

The public entry point is the program1017 class, so the source file
is currently named program1017.java.

️ Console Menu

When the application starts, it provides the following operations:

1 : Park Vehicle
2 : Exit Vehicle
3 : Search Vehicle
4 : Display Parking Lot
5 : Exit

Parking a Vehicle

The user selects:

1 : Bike
2 : Car
3 : Truck

and enters the vehicle number. The system then creates the vehicle,
finds a suitable available spot, parks the vehicle, and generates a
ticket.

Exiting a Vehicle

The user enters the ticket number and selects:

1 : Cash
2 : UPI
3 : Card

The system calculates the parking duration and charges, processes the
selected payment method, releases the parking spot, and removes the
active parking records.

Current Pricing

The project includes two pricing strategies.

Normal Pricing

Vehicle          Rate

Bike         ₹20/hour
Car          ₹50/hour
Truck       ₹100/hour

Weekend Pricing

Vehicle          Rate

Bike         ₹40/hour
Car         ₹100/hour
Truck       ₹200/hour

The minimum billable duration is 1 hour.

🅿️ Current Parking Configuration

The sample application creates 2 floors, with each floor containing:

2 Bike spots

2 Car spots

2 Truck spots

Floor 1
├── Bike: 101, 102
├── Car:  103, 104
└── Truck: 105, 106

Floor 2
├── Bike: 201, 202
├── Car:  203, 204
└── Truck: 205, 206

OOP Concepts Demonstrated

Abstraction

Abstract classes such as Vehicle and ParkingSpot define common
behavior while allowing specialized implementations.

Encapsulation

Vehicle, ticket, parking spot, floor, and parking-lot data are
maintained through private fields and public methods.

Inheritance

Bike, Car, and Truck inherit from Vehicle. Similarly, specific
parking spots inherit from ParkingSpot.

Polymorphism

Common interfaces such as ParkingStrategy, PricingStrategy, and
PaymentStrategy allow different implementations to be used
interchangeably.

Possible Future Enhancements

The current implementation can be extended with:

️ Database persistence

REST API / Spring Boot backend

Web or mobile UI

User authentication

Real payment gateway integration

Nearest parking spot strategy

Concurrent vehicle entry/exit handling

Parking analytics and reports

️ Reservation and pre-booking

EV charging spot support

Digital receipts

Multiple entry and exit gates

Learning Outcomes

This project provides hands-on practice with:

Real-world problem modelling

Low-Level System Design

Object-Oriented Programming

Design Patterns

Java Collections Framework

Strategy-based extensibility

Observer-based notifications

Separation of responsibilities

Console application development
