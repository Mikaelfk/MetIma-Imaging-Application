# MetIma

The project is an application designed to allow a user to import, 
browse, organize with tags, export and read the metadata of images.  
of an image. 

## Prerequisites

The application is written for Java 11, but might compile and run fine with 
never versions of Java. Compiling and running is done with Maven.

For obvious reasons, the database password is not pushed to Git. For the 
application to run the application must be configures with a  
database before compilation.

## Dependencies

* [Maven](https://maven.apache.org/) - Dependency Management
* [Java Development Kit](https://www.oracle.com/java/technologies/javase-downloads.html) - Compilation of Java code
* [Java Runtime Enviroment](https://www.java.com/en/download/manual.jsp) - Running compiled Java code

## Compiling and running

Before compiling, the application must be configured with a database. To do this,
add a persistance unit in 
[persistence.xml](/src/main/resources/META-INF/persistence.xml) according to
the specifications 
[found here.](https://www.objectdb.com/java/jpa/entity/persistence-unit)


Afterwards, with Maven installed, it is as simple as executing a single Maven goal in the project root:

```bash 
mvn javafx:run
```

## Authors

* **Mikael Falkenberg Krog** - *Project Leader*
* **Magnus Gluppe** - *Oranization Responsible*
* **Jakob Frantzvåg Karlsmoen** - *Documentation Responsible*
* **Mikkel Aas** - *Head of Design*
