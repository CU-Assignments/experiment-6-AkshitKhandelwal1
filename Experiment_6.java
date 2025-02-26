// Question 1: Write a program to sort a list of Employee objects (name, age, salary) using lambda expressions.

import java.util.ArrayList;
import java.util.List;

class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}

class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 30, 50000));
        employees.add(new Employee("Alice", 25, 60000));
        employees.add(new Employee("Bob", 35, 45000));
        employees.add(new Employee("Charlie", 28, 55000));

        // Sort by name
        employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        System.out.println("Sorted by name:");
        employees.forEach(System.out::println);

        // Sort by age
        employees.sort((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println("\nSorted by age:");
        employees.forEach(System.out::println);

        // Sort by salary
        employees.sort((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println("\nSorted by salary:");
        employees.forEach(System.out::println);
    }
}

// Question 2: Create a program to use lambda expressions and stream operations
// to filter students scoring above 75%, sort them by marks, and display their
// names.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Student {
    private String name;
    private double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}

class Main {
    public static void main(String[] args) {
        // Create a list of students
        List<Student> students = Arrays.asList(
                new Student("Alice", 82.5),
                new Student("Bob", 65.0),
                new Student("Charlie", 90.0),
                new Student("David", 78.0),
                new Student("Eva", 88.0));

        // Use streams to filter, sort, and display names of students scoring above 75%
        List<String> result = students.stream()
                .filter(student -> student.getMarks() > 75) // Filter students with marks > 75%
                .sorted((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks())) // Sort by marks in descending order
                .map(Student::getName) // Extract student names
                .collect(Collectors.toList()); // Collect the names into a list

        // Display the names of students
        System.out.println("Students scoring above 75% (sorted by marks in descending order):");
        result.forEach(System.out::println);
    }
}

// Question 3: Write a Java program to process a large dataset of products using
// streams.
// Perform operations such as grouping products by category, finding the most
// expensive product in each category,
// and calculating the average price of all products.

import java.util.*;
import java.util.stream.Collectors;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}

class Main {
    public static void main(String[] args) {
        // Create a large dataset of products
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Smartphone", "Electronics", 800.0),
                new Product("Tablet", "Electronics", 500.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Shirt", "Clothing", 30.0),
                new Product("Jeans", "Clothing", 50.0),
                new Product("Shoes", "Clothing", 80.0),
                new Product("Watch", "Accessories", 200.0),
                new Product("Sunglasses", "Accessories", 100.0));

        // 1. Group products by category
        Map<String, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("Products grouped by category:");
        productsByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });

        // 2. Find the most expensive product in each category
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.maxBy(Comparator.comparing(Product::getPrice))));

        System.out.println("\nMost expensive product in each category:");
        mostExpensiveByCategory.forEach((category, product) -> {
            product.ifPresent(p -> System.out.println(category + ": " + p.getName() + " - $" + p.getPrice()));
        });

        // 3. Calculate the average price of all products
        double averagePrice = products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);

        System.out.println("\nAverage price of all products: $" + averagePrice);
    }
}