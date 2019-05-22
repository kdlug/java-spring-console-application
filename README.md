# Command Line Application

## How to run

```bash
/gradlew bootRun -Pargs=--spring.main.banner-mode=off,--customArgument=yrdyii,--dateFrom=2018-07-01,--dateTo=2018-08-02
```

Spring Boot provides two interfaces, CommandLineRunner and ApplicationRunner to run some logic after the application context is loaded.

## CommandLineRunner

Provides access to arguments as string array. 

```java
@Component
public class AppCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(AppCommandLineRunner.class);
    
    @Override
    public void run(String...args) throws Exception {
        log.info("Application started with arguments: {}", Arrays.toString(args));
    }
}
```

## ApplicationRunner

ApplicationRunner wraps the raw application arguments and exposes the ApplicationArguments interface, which has many convinent methods to get arguments.

```java
@Component
public class AppRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application started with option names : {}", args.getOptionNames());
    }
}
```

## Passing Command Line arguments

Configure bootRun in order to run command-line arguments as follows:

```gradle
bootRun {
	if (project.hasProperty('args')) {
		args project.args.split(',')
	}
}
```

Now you can pass arguments like this

```gradle
./gradlew bootRun -Pargs=--spring.main.banner-mode=off,--customArgument=custom
```

## Overriding system properties

F.ex. application.properties

```bash
server.port=8081
```

We can pass system 
```conslole
  ./gradlew bootRun -Dspring-boot.run.arguments=--server.port=8888
```

Spring Boot converts command-line arguments to properties and adds them as environment variables.
