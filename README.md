# Farm Manager

Farm Manager helps manage user's farm. It gives ability to store operations on farmlands. Creating reports about operations, costs. And so on.

## Features description

### Farmlands

Each User can have unlimited farmlands. Farmlands have to be linked with `Season`. 
Because User can change farmland's area, seeded crops in next season. 

More information about this feature is available [here](https://github.com/Mistral/farm-manager/wiki/Feature-Farmland)

### Operations

Operations (`Operation`) are all activities planned, done, cancelled on a farmland. 
Operation can use resources (`OperationResource`) like spraying products, seeds and so on. Some of resources can have 
variants. Each operations can use many resources. Operation has own unit cost (expressed in cost per hectare), in 
addition operation which uses any resource should calculate used material's cost.
It is possible to set dose (`Dose`) in OperationResource. For example we can use spraying product with fixed dose.

More information about this feature is available [here](https://github.com/Mistral/farm-manager/wiki/Feature-Operation)

### Reports

User can generate and see reports on each farmland. It can be personalized. List all operations done at this farmland
 with included cost of operation and material's cost. 
 
 
### Manuring calculation
User can use manuring calculation to choose right manuring strategy. 

### Warehouses
User can have warehouses where are stored crops.

## Prerequisites

- Java 8

### Installing

```
mvn spring-boot:run
```

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Authors


## License

This project is licensed under the GNU GPL v3 License - see the [LICENSE.md](LICENSE.md) file for details
