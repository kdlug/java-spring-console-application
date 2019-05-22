package com.github.kdlug.components;

import com.github.kdlug.ConsoleArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Set;

@Component
public class ConsoleApplicationRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(ConsoleApplicationRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));

        log.info("# NonOptionArgs: " + args.getNonOptionArgs().size());
        log.info("NonOptionArgs: {}", args.getNonOptionArgs());

        log.info("# OptionArgs: " + args.getOptionNames().size());
        log.info("OptionNames: {}", args.getOptionNames());

        for (String name : args.getOptionNames()){
            log.info("arg-" + name + "=" + args.getOptionValues(name));
        }

        if (!args.containsOption("customArgument") || !args.containsOption("dateFrom") || !args.containsOption("dateTo")) {
            log.error("Required arguments: customArgument, dateFrom, dateTo");
            throw new IllegalArgumentException("Too few arguments");
        }

        String type = args.getOptionValues("customArgument").get(0);
        String dateFrom = args.getOptionValues("dateFrom").get(0);
        String dateTo = args.getOptionValues("dateTo").get(0);

        ConsoleArgs consoleArgs = new ConsoleArgs();
        consoleArgs.setType(type);
        consoleArgs.setDateFrom(new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom));
        consoleArgs.setDateTo(new SimpleDateFormat("yyyy-MM-dd").parse(dateTo));

        log.info(consoleArgs.toString());

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<ConsoleArgs>> constrains = validator.validate(consoleArgs);
        for (ConstraintViolation constrain : constrains) {
            log.error(
                    "[" + constrain.getPropertyPath() + "][" + constrain.getMessage() + "]"
            );
        }
    }
}

