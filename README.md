# Farm Manager

Farm Manager helps manage user's farm. It gives ability to store operations on farmlands. Creating reports about operations, costs. And so on.

## Features description

### Farmlands

Each User can have unlimited farmlands. Farmlands have to be linked with `Season`. 
Because User can change farmland's area, seeded crops in next season. 

### Operations

Operations (`Operation`) are all activities planned, done, cancelled on farmland. 
Operation can use resources (`OperationResource`) like spraying products, seeds and so on. Some of resources can have 
variants. Each operations can use many resources. Operation has own unit cost (expressed in cost per hectare), in 
addition operation which uses any resource should calculate used material's cost.
It is possible to set dose (`Dose`) in OperationResource. For example we can use spraying product with fixed dose.

### Reports

User can generate and see reports on each farmland. It can be personalized. List all operations done at this farmland
 with included cost of operation and material's cost. 
 
 
### Manuring calculation
User can use manuring calculation to choose right manuring strategy. 

### Warehouses
User can have warehouses where are stored crops.


### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
