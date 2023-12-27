//                            Slip=1
//1=Write a Java Program to implement I/O Decorator for converting uppercase letters to lower case letters
import java.io.*;
class LowerCaseInputStream extends FilterInputStream {
    public LowerCaseInputStream(InputStream in) {
        super(in);
    }
//@Override
    public int read() throws IOException {
        int c = super.read();
        return (c == -1) ? c : Character.toLowerCase((char) c);
    }
// @Override
    public int read(byte[] b, int offset, int len) throws IOException {
        int result = super.read(b, offset, len);
        for (int i = offset; i < offset + result; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}
public class example2 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = new LowerCaseInputStream(new
                    FileInputStream("input.txt"));
            int data;
            while ((data = inputStream.read()) != -1) {
                System.out.print((char) data);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// 2. Write a Java Program to implement Singleton pattern for multithreading
class Singleton {
    private static volatile Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
public class example4 {
    public static void main(String[] args) {
        Runnable task = () -> {
            Singleton singleton = Singleton.getInstance();
            singleton.showMessage();
        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
    }
}
// 3. Write a JAVA Program to implement built-in support (java.util.Observable) Weather station
// with members temperature, humidity, pressure and methods mesurmentsChanged(),
// setMesurment(), getTemperature(), getHumidity(), getPressure()
import java.util.Observable;
import java.util.Observer;

class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

// Display class that implements Observer
class Display implements Observer {
    private Observable observable;

    public Display(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            float temperature = weatherData.getTemperature();
            float humidity = weatherData.getHumidity();
            float pressure = weatherData.getPressure();

            displayData(temperature, humidity, pressure);
        }
    }

    public void displayData(float temperature, float humidity, float pressure) {
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Pressure: " + pressure + " hPa");
    }
}

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        Display display = new Display(weatherData);

        weatherData.setMeasurements(25.0f, 60.0f, 1013.25f);
    }
}

// 4. Write a Java Program to implement Factory method for Pizza Store with createPizza(),
// orederPizza(), prepare(), Bake(), cut(), box(). Use this to create variety of pizza’s
// like NyStyleCheesePizza, ChicagoStyleCheesePizza etc.
interface Pizza {
    void prepare();
    void bake();
    void cut();
    void box();
}

// Concrete pizza classes
class NyStyleCheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing New York style cheese pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking New York style cheese pizza");
    }

    @Override
    public void cut() {
        System.out.println("Cutting New York style cheese pizza");
    }

    @Override
    public void box() {
        System.out.println("Boxing New York style cheese pizza");
    }
}

class ChicagoStyleCheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing Chicago style cheese pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Chicago style cheese pizza");
    }

    @Override
    public void cut() {
        System.out.println("Cutting Chicago style cheese pizza");
    }

    @Override
    public void box() {
        System.out.println("Boxing Chicago style cheese pizza");
    }
}

// Pizza Store interface
interface PizzaStore {
    Pizza createPizza(String type);
    void orderPizza(String type);
}

// Concrete pizza store classes
class NyPizzaStore implements PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NyStyleCheesePizza();
        }
        return null;
    }

    @Override
    public void orderPizza(String type) {
        Pizza pizza = createPizza(type);
        if (pizza != null) {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } else {
            System.out.println("Sorry, we don't have that type of pizza.");
        }
    }
}

class ChicagoPizzaStore implements PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        }
        return null;
    }

    @Override
    public void orderPizza(String type) {
        Pizza pizza = createPizza(type);
        if (pizza != null) {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } else {
            System.out.println("Sorry, we don't have that type of pizza.");
        }
    }
}

public class PizzaStores {
    public static void main(String[] args) {
        PizzaStore nyPizzaStore = new NyPizzaStore();
        nyPizzaStore.orderPizza("cheese");

        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
        chicagoPizzaStore.orderPizza("cheese");
    }
}

// 5. Write a Java Program to implement Adapter pattern for Enumeration iterator

import java.util.Enumeration;
import java.util.Iterator;

// Adapter class that adapts Enumeration to Iterator
class EnumerationAdapter<T> implements Iterator<T> {
    private Enumeration<T> enumeration;

    public EnumerationAdapter(Enumeration<T> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public T next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported.");
    }
}

// Test class
public class AdapterPatternEnumrationIterator {
    public static void main(String[] args) {
        // Create an Enumeration of integers
        Enumeration<Integer> enumeration = new java.util.Vector<>(java.util.Arrays.asList(1,2,3,4,5,6,7)).elements();

        // Adapt the Enumeration to an Iterator
        Iterator<Integer> iterator = new EnumerationAdapter<>(enumeration);

        // Use the Iterator to traverse the elements
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

//6. Write a Java Program to implement command pattern to test Remote Control

interface Command {
    void execute();
}

// Concrete command classes
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Receiver class
class Light {
    void turnOn() {
        System.out.println("Light is ON");
    }

    void turnOff() {
        System.out.println("Light is OFF");
    }
}

// Invoker class
class RemoteControl {
    private Command command;

    void setCommand(Command command) {
        this.command = command;
    }

    void pressButton() {
        command.execute();
    }
}

public class ImplementCommandRemoreControl {
    public static void main(String[] args) {
        Light light = new Light();

        // Create the command objects
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);

        // Create the invoker
        RemoteControl remoteControl = new RemoteControl();

        // Set the commands
        remoteControl.setCommand(lightOnCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(lightOffCommand);
        remoteControl.pressButton();
    }
}

// Q 7. Write a Java Program to implement undo command to test Ceiling fan.
import java.util.Stack;

// Ceiling Fan class
class CeilingFan {
    private String state;

    public CeilingFan() {
        state = "OFF";
    }

    public void turnOn() {
        state = "ON";
        System.out.println("Ceiling Fan is ON.");
    }

    public void turnOff() {
        state = "OFF";
        System.out.println("Ceiling Fan is OFF.");
    }

    public String getState() {
        return state;
    }
}

// Command interface
interface Command {
    void execute();
    void undo();
}

// Concrete command for turning on the fan
class TurnOnCommand implements Command {
    private CeilingFan fan;

    public TurnOnCommand(CeilingFan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOn();
    }

    @Override
    public void undo() {
        fan.turnOff();
    }
}

// Concrete command for turning off the fan
class TurnOffCommand implements Command {
    private CeilingFan fan;

    public TurnOffCommand(CeilingFan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.turnOff();
    }

    @Override
    public void undo() {
        fan.turnOn();
    }
}

// Remote control to invoke commands and handle undo
class RemoteControl {
    private Command command;
    private Stack<Command> history = new Stack<>();

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
        history.push(command);
    }

    public void pressUndo() {
        if (!history.isEmpty()) {
            Command previousCommand = history.pop();
            previousCommand.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }
}

// Main class
public class UndoCmdtotestCellingFan {
    public static void main(String[] args) {
        CeilingFan fan = new CeilingFan();
        TurnOnCommand turnOn = new TurnOnCommand(fan);
        TurnOffCommand turnOff = new TurnOffCommand(fan);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(turnOn);
        remote.pressButton();
        remote.pressUndo();

        remote.setCommand(turnOff);
        remote.pressButton();
        remote.pressUndo();
        remote.pressUndo();
    }
}

// Q8.Write a Java Program to implement State Pattern for Gumball Machine.
// Create instance variable that holds current state from there, we just need to handle all
// actions, behaviors and state transition that can happen

class GumballMachine {
    final static int SOLD_OUT = 0;
    final static int NO_QUARTER = 1;
    final static int HAS_QUARTER = 2;
    final static int SOLD = 3;
    private static final boolean Oops = false;
    int state = SOLD_OUT;
    int count = 0;
    public GumballMachine(int count) {
        this.count = count;
        if (count > 0) {
            state = NO_QUARTER;
        }
    }
    public void insertQuarter() {
        if (state == HAS_QUARTER) {
            System.out.println("You cant insert another quarter");
        } else if (state == NO_QUARTER) {
            state = HAS_QUARTER;
            System.out.println("You inserted a quarter");
        } else if (state == SOLD_OUT) {
            System.out.println("You cant insert a quarter, the machine is sold out");
        } else if (state == SOLD) {
            System.out.println("Please wait, were already giving you a gumball");
        }
    }
    public void ejectQuarter() {
        if (state == HAS_QUARTER) {
            System.out.println("Quarter returned");
            state = NO_QUARTER;
        } else if (state == NO_QUARTER) {
            System.out.println("You havent inserted a quarter");
        } else if (state == SOLD) {
            System.out.println("Sorry, you already turned the crank");
        } else if (state == SOLD_OUT) {
            System.out.println("You cant eject, you havent inserted a quarter yet");
        }
    }
    public void turnCrank() {
        if (state == SOLD) {
            System.out.println("Turning twice doesnt get you another gumball!");
        } else if (state == NO_QUARTER) {
            System.out.println("You turned but theres no quarter");
        } else if (state == SOLD_OUT) {
            System.out.println("You turned, but there are no gumballs");
        } else if (state == HAS_QUARTER) {
            System.out.println("You turned...");
            state = SOLD;
            dispense();
        }
    }
    public void dispense() {
        if (state == SOLD) {
            System.out.println("A gumball comes rolling out the slot");
            count = count - 1;
            if (count == 0) {
                System.out.println("Oops, out of gumballs!");
                state = SOLD_OUT;
            } else {
                state = NO_QUARTER;
            }
        } else if (state == NO_QUARTER) {
            System.out.println("You need to pay first");
        } else if (state == SOLD_OUT) {
            System.out.println("No gumball dispensed");
        } else if (state == HAS_QUARTER) {
            System.out.println("No gumball dispensed");
        }
    }
// other methods here like toString() and refill()
}
public class Implementgumbalmachine {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(5);
        System.out.println(gumballMachine);
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);
        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.ejectQuarter();
        System.out.println(gumballMachine);
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);
    }
}
// Q9.Write a java program to implement Adapter pattern to design Heart Model to Beat
// Model
class HeartModel {
    private boolean isBeating;
    public void start() {
        isBeating = true;
        System.out.println("Heart is beating...");
    }
    public void stop() {
        isBeating = false;
        System.out.println("Heart has stopped beating.");
    }
    public boolean isBeating() {
        return isBeating;
    }
}
// The BeatModel represents the target interface that the client code expects.
interface BeatModel {
    void startBeat();
    void stopBeat();
    boolean isBeatOn();
}
// The HeartAdapter adapts the HeartModel to the BeatModel interface.
class HeartAdapter implements BeatModel {
    private HeartModel heartModel;
    public HeartAdapter(HeartModel heartModel) {
        this.heartModel = heartModel;
    }
    @Override
    public void startBeat() {
        heartModel.start();
    }
    @Override
    public void stopBeat() {
        heartModel.stop();
    }
    @Override
    public boolean isBeatOn() {
        return heartModel.isBeating();
    }
}
// Client code that uses the BeatModel interface
public class AdapterPatternExample {
    public static void main(String[] args) {
// Create a HeartModel
        HeartModel heartModel = new HeartModel();
// Create a HeartAdapter to adapt the HeartModel to the BeatModel interface
        BeatModel beatModel = new HeartAdapter(heartModel);
// Use the BeatModel interface to start and stop the beat
        beatModel.startBeat();
        System.out.println("Is beat on? " + beatModel.isBeatOn());
        beatModel.stopBeat();
        System.out.println("Is beat on? " + beatModel.isBeatOn());
    }
}
// Q10.Write a Java Program to implement Decorator Pattern for interface Car to define the
// assemble() method and then decorate it to Sports car and Luxury Car

interface Car {
    void assemble();
}
// Concrete implementation of Car
class BasicCar implements Car {
    @Override
    public void assemble() {
        System.out.print("Basic Car");
    }
}
// Decorator class
abstract class CarDecorator implements Car {
    protected Car car;
    public CarDecorator(Car car) {
        this.car = car;
    }
    @Override
    public void assemble() {
        car.assemble();
    }
}
// Concrete decorators
class SportsCar extends CarDecorator {
    public SportsCar(Car car) {
        super(car);
    }
    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" with Sports Features");
    }
}
class LuxuryCar extends CarDecorator {
    public LuxuryCar(Car car) {
        super(car);
    }
    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" with Luxury Features");
    }
}
public class carr {
    public static void main(String[] args) {
// Create a basic car
        Car basicCar = new BasicCar();
        System.out.print("Basic Car: ");
        basicCar.assemble();
        System.out.println();
// Decorate a basic car with sports features
        Car sportsCar = new SportsCar(basicCar);
        System.out.print("Sports Car: ");
        sportsCar.assemble();
        System.out.println();
// Decorate a basic car with luxury features
        Car luxuryCar = new LuxuryCar(basicCar);
        System.out.print("Luxury Car: ");
        luxuryCar.assemble();
        System.out.println();
// Decorate a basic car with both sports and luxury features
        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(basicCar));
        System.out.print("Sports Luxury Car: ");
        sportsLuxuryCar.assemble();
        System.out.println();
    }
}
// Q11. Write a Java Program to implement Facade Design Pattern for HomeTheater
class Amplifier {
    void on() {
        System.out.println("Amplifier is on");
    }
    void off() {
        System.out.println("Amplifier is off");
    }
}
class DVDPlayer {
    void playMovie(String movie) {
        System.out.println("Playing movie: " + movie);
    }
    void stopMovie() {
        System.out.println("Stopping movie");
    }
}
class Projector {
    void on() {
        System.out.println("Projector is on");
    }
    void off() {
        System.out.println("Projector is off");
    }
}
class Lights {
    void dim(int level) {
        System.out.println("Dimming lights to level " + level);
    }
    void bright() {
        System.out.println("Lights are bright");
    }
}
// Home Theater Facade
class HomeTheaterFacade {
    private Amplifier amplifier;
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private Lights lights;
    public HomeTheaterFacade() {
        amplifier = new Amplifier();
        dvdPlayer = new DVDPlayer();
        projector = new Projector();
        lights = new Lights();
    }
    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim(10);
        projector.on();
        amplifier.on();
        dvdPlayer.playMovie(movie);
    }
    public void endMovie() {
        System.out.println("Shutting down the movie...");
        dvdPlayer.stopMovie();
        projector.off();
        amplifier.off();
        lights.bright();
    }
}
public class Hometheater {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();
        homeTheater.watchMovie("Inception");
        System.out.println("\nTime to end the movie...\n");
        homeTheater.endMovie();
    }
}
// Q12. Write a Java Program to implement Abstract Factory Pattern for Shape interface.
// Shape interface
interface Shape {
    void draw();
}

// Concrete Circle class
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

// Concrete Square class
class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }
}

// Concrete Triangle class
class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Triangle");
    }
}

// Abstract Factory interface
interface ShapeFactory {
    Shape createShape();
}

// Concrete Circle Factory
class CircleFactory implements ShapeFactory {
    @Override
    public Shape createShape() {
        return new Circle();
    }
}

// Concrete Square Factory
class SquareFactory implements ShapeFactory {
    @Override
    public Shape createShape() {
        return new Square();
    }
}

// Concrete Triangle Factory
class TriangleFactory implements ShapeFactory {
    @Override
    public Shape createShape() {
        return new Triangle();
    }
}

// Client code that uses the abstract factory pattern
public class ShapeInterFace {
    public static void main(String[] args) {
        // Creating a family of shapes using abstract factory pattern
        ShapeFactory circleFactory = new CircleFactory();
        ShapeFactory squareFactory = new SquareFactory();
        ShapeFactory triangleFactory = new TriangleFactory();

        // Creating shape objects without specifying their concrete classes
        Shape circle = circleFactory.createShape();
        Shape square = squareFactory.createShape();
        Shape triangle = triangleFactory.createShape();

        // Drawing the shapes
        circle.draw();
        square.draw();
        triangle.draw();
    }
}

// Q13.Write a JAVA Program to implement built-in support (java.util.Observable) Weather
// station with members temperature, humidity, pressure and methods
// mesurmentsChanged(), setMesurment(), getTemperature(), getHumidity(),
// getPressure()

import java.util.Observable;
import java.util.Observer;

// WeatherData class that extends Observable
class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

// Display class that implements Observer
class Display implements Observer {
    private Observable observable;

    public Display(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            float temperature = weatherData.getTemperature();
            float humidity = weatherData.getHumidity();
            float pressure = weatherData.getPressure();

            displayData(temperature, humidity, pressure);
        }
    }

    public void displayData(float temperature, float humidity, float pressure) {
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Pressure: " + pressure + " hPa");
    }
}

public class WheatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        Display display = new Display(weatherData);

        weatherData.setMeasurements(25.0f, 60.0f, 1013.25f);
    }
}

// Q14. Write a Java Program to implement command pattern to test Remote Control
interface Command {
    void execute();
}
// Concrete command classes
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }
    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Receiver class
class Light {
    void turnOn() {
        System.out.println("Light is ON");
    }

    void turnOff() {
        System.out.println("Light is OFF");
    }
}

// Invoker class
class RemoteControl {
    private Command command;

    void setCommand(Command command) {
        this.command = command;
    }

    void pressButton() {
        command.execute();
    }
}

public class ImplementCommandRemoreControl {
    public static void main(String[] args) {
        Light light = new Light();

        // Create the command objects
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);

        // Create the invoker
        RemoteControl remoteControl = new RemoteControl();

        // Set the commands
        remoteControl.setCommand(lightOnCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(lightOffCommand);
        remoteControl.pressButton();
    }
}

// Q15. Write a Java Program to implement Iterator Pattern for Designing Menu like Breakfast,
// Lunch or Dinner Menu
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Define the MenuItem class
class MenuItem {
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - " + description + ", $" + price;
    }
}

// Define the Menu interface and its Iterator
interface Menu {
    Iterator<MenuItem> createIterator();
}

// Concrete implementation of a Breakfast Menu
class BreakfastMenu implements Menu {
    private List<MenuItem> items;

    public BreakfastMenu() {
        items = new ArrayList<>();
        addItem("Scrambled Eggs", "Two eggs with toast", 5.99);
        addItem("Pancakes", "Pancakes with maple syrup", 6.49);
    }

    public void addItem(String name, String description, double price) {
        MenuItem menuItem = new MenuItem(name, description, price);
        items.add(menuItem);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return items.iterator();
    }
}

// Concrete implementation of a Lunch Menu
class LunchMenu implements Menu {
    private MenuItem[] items;
    private int numberOfItems = 0;

    public LunchMenu() {
        items = new MenuItem[5];
        addItem("Chicken Sandwich", "Grilled chicken sandwich", 7.99);
        addItem("Caesar Salad", "Fresh Caesar salad", 6.99);
    }

    public void addItem(String name, String description, double price) {
        MenuItem menuItem = new MenuItem(name, description, price);
        if (numberOfItems < items.length) {
            items[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return new LunchMenuIterator(items);
    }

    private class LunchMenuIterator implements Iterator<MenuItem> {
        private MenuItem[] items;
        private int position = 0;

        public LunchMenuIterator(MenuItem[] items) {
            this.items = items;
        }

        @Override
        public boolean hasNext() {
            return position < items.length && items[position] != null;
        }

        @Override
        public MenuItem next() {
            MenuItem menuItem = items[position];
            position++;
            return menuItem;
        }
    }
}

// Client code to test the Iterator Pattern
public class BreakFast {
    public static void main(String[] args) {
        Menu breakfastMenu = new BreakfastMenu();
        Menu lunchMenu = new LunchMenu();

        System.out.println("Breakfast Menu:");
        printMenu(breakfastMenu.createIterator());

        System.out.println("\nLunch Menu:");
        printMenu(lunchMenu.createIterator());
    }

    private static void printMenu(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            System.out.println(menuItem.toString());
        }
    }
}

// Q16. Write a Java Program to implement Singleton pattern for multithreading
class Singleton {
    private static volatile Singleton instance;

    // Private constructor to prevent instantiation from outside
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}

public class SingleToneMultiThreding {
    public static void main(String[] args) {
        Runnable task = () -> {
            Singleton singleton = Singleton.getInstance();
            singleton.showMessage();
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }
}
// Q17. Write a Java Program to implement Strategy Pattern for Duck Behavior. Create
// instance variable that holds current state of Duck from there, we just need to handle all
// Flying Behaviors and Quack Behavior.

interface QuackBehavior {
    void quack();
}

// Concrete quack behavior classes
class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}

class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}

class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<<Silence>>");
    }
}

// Define the FlyBehavior interface
interface FlyBehavior {
    void fly();
}

// Concrete fly behavior classes
class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with wings");
    }
}

class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}

// The Duck class
abstract class Duck {
    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;

    public Duck(QuackBehavior quackBehavior, FlyBehavior flyBehavior) {
        this.quackBehavior = quackBehavior;
        this.flyBehavior = flyBehavior;
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void performFly() {
        flyBehavior.fly();
    }

    public abstract void display();

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
}

// Concrete duck subclasses
class MallardDuck extends Duck {
    public MallardDuck() {
        super(new Quack(), new FlyWithWings());
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}

class RubberDuck extends Duck {
    public RubberDuck() {
        super(new Squeak(), new FlyNoWay());
    }

    @Override
    public void display() {
        System.out.println("I'm a rubber duck");
    }
}

public class Duck_Behavior {
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        Duck rubberDuck = new RubberDuck();

        System.out.println("Mallard Duck:");
        mallardDuck.display();
        mallardDuck.performQuack();
        mallardDuck.performFly();

        System.out.println("\nRubber Duck:");
        rubberDuck.display();
        rubberDuck.performQuack();
        rubberDuck.performFly();

        // Change the behavior at runtime
        rubberDuck.setQuackBehavior(new Quack());
        rubberDuck.setFlyBehavior(new FlyWithWings());

        System.out.println("\nRubber Duck (after behavior change):");
        rubberDuck.performQuack();
        rubberDuck.performFly();
    }
}

// Q18. Write a Java Program to implement Factory method for Pizza Store with createPizza(),
// orederPizza(), prepare(), Bake(), cut(), box(). Use this to create variety of pizza’s
// like NyStyleCheesePizza, ChicagoStyleCheesePizza etc.
interface Pizza {
    void prepare();
    void bake();
    void cut();
    void box();
}

// Concrete pizza classes
class NyStyleCheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing New York style cheese pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking New York style cheese pizza");
    }

    @Override
    public void cut() {
        System.out.println("Cutting New York style cheese pizza");
    }

    @Override
    public void box() {
        System.out.println("Boxing New York style cheese pizza");
    }
}

class ChicagoStyleCheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing Chicago style cheese pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Chicago style cheese pizza");
    }

    @Override
    public void cut() {
        System.out.println("Cutting Chicago style cheese pizza");
    }

    @Override
    public void box() {
        System.out.println("Boxing Chicago style cheese pizza");
    }
}

// Pizza Store interface
interface PizzaStore {
    Pizza createPizza(String type);
    void orderPizza(String type);
}

// Concrete pizza store classes
class NyPizzaStore implements PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NyStyleCheesePizza();
        }
        return null;
    }

    @Override
    public void orderPizza(String type) {
        Pizza pizza = createPizza(type);
        if (pizza != null) {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } else {
            System.out.println("Sorry, we don't have that type of pizza.");
        }
    }
}

class ChicagoPizzaStore implements PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        }
        return null;
    }

    @Override
    public void orderPizza(String type) {
        Pizza pizza = createPizza(type);
        if (pizza != null) {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } else {
            System.out.println("Sorry, we don't have that type of pizza.");
        }
    }
}

public class PizzaStores {
    public static void main(String[] args) {
        PizzaStore nyPizzaStore = new NyPizzaStore();
        nyPizzaStore.orderPizza("cheese");

        PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
        chicagoPizzaStore.orderPizza("cheese");
    }
}
