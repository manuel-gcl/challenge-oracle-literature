
# Literature App

This project was developed for **Java backend specialization** from **Alura's Oracle One** program.
The Literature App consist in a command-line Java application that allows users to search books from [Gutendex Library API](https://gutendex.com/) and store them in a local database.
The main goal of the project was to deepen knowledge of Java programming, learn about databases, Hibernate, and Spring Boot.

## Installation and Setup
To set up and use the Literature App, follow these steps:

1. **Clone the Repository:**
   Clone the repository to your local machine using Git or download the ZIP file.
```console
git clone https://github.com/manuel-gcl/challenge-oracle-literature.git
```
2. **Setting Up the Database**
   You will need to create a PostgreSQL database to use with this application.
   Follow these steps to set it up:

- Install PostgreSQL if you haven't already.
- Enter to PostgreSQL terminal:
```console
sudo -u postgres psql
```
- Create database and user
```console
CREATE DATABASE literature_DB;
CREATE USER youruser WITH ENCRYPTED PASSWORD 'yourpass';
GRANT ALL PRIVILEGES ON DATABASE yourdbname TO youruser;
```
- Configure the database connection:
  After creating the database, you will need to configure the connection in the application.
  Update the database connection details in the 'application.properties' file with your information or set enviroment variables.
********************************
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
********************************
3. **Configure dependencies with Maven:**
   This project uses Maven to manage dependencies and build the application.
   All dependencies are already set in pom.xml file.
- jackson
- postgresql
- spring-boot-starter-data-jpa
- spring-boot-maven-plugin

To make sure all dependencies are installed, navigate to the project root and run:
```console
mvn clean install
```

4. **Build and Run the Application with Maven:**
   Build and run the application using Maven.
```console
mvn package
```
5. **Run the application:**
```console
mvn exec:java -Dexec.mainClass="com.alura.literature.LiteratureApplication"
```

## Usage:
```
********************************
Please select an option:
1. Search book
2. Search author
3. List available books
4. List available authors by name
5. List available authors by birth year
6. Filter books by language
7. Filter books by genre
8. Filter books before author birth year
9. Exit
********************************
```

1. **Search book**
   Enter the book name to search in the database. If the book is not found, the search will be extended to an external API. Any results found, including the book and author details, will be saved to the database for future searches. The matching books will be displayed if any are found.
2. **Search author**
   Enter the author’s name to search in the database. If no exact match is found, partial matches with the first name will be searched. The matching authors will be displayed if any are found.
3. **List available books**
   Display all books stored in the database along with their details.
4. **List available authors by name**
   Retrieve and display a list of all authors in the database, sorted by their names in alphabetical order.
5.  **List available authors by birth year**
    Retrieve and display a list of all authors, sorted by their birth year.
6. **Filter books by language**
   Display all books that match a specific language, sorted alphabetically by their names. Language codes are represented by 2 or 3-letter abbreviations, such as {en} for English or {it} for Italian
7. **Filter books by genre**
   Filter and display all books belonging to a specific genre sorted by their names in alphabetical order.
8. **Filter books before author birth year**
   Filter and display books published before a specific author's birth year sorted by their birth year.
9. **Exit**

The app will remain active until you choose to exit. To perform another action, simply select the corresponding option again.
