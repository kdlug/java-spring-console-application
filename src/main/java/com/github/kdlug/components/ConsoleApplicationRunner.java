package com.github.kdlug.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ConsoleApplicationRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(ConsoleApplicationRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));

        log.info("# NonOptionArgs: " + args.getNonOptionArgs().size());
        log.info("NonOptionArgs: {}", args.getNonOptionArgs());
        log.info("OptionNames: {}", args.getOptionNames());

        log.info("# OptionArgs: " + args.getOptionNames().size());

        for (String name : args.getOptionNames()){
            log.info("arg-" + name + "=" + args.getOptionValues(name));
        }

        boolean containsOption = args.containsOption("customArgument");
        log.info("Contains customArgument: " + containsOption);

        // check if option --customArgument is used
        if (containsOption) {
            log.info("customArgument values: " + args.getOptionValues("customArgument"));
        }
    }
}

