package self.edu.clicamel;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import self.edu.clicamel.routes.MyRoute;

import java.util.List;
import java.util.concurrent.Callable;

@Component
@Command(name = "mycommand", mixinStandardHelpOptions = true, subcommands = MyCommand.Sub.class)
public class MyCommand implements Callable<Integer> {

    private final MyRoute myRoute;

    public MyCommand(MyRoute myRoute) {
        this.myRoute = myRoute;
    }

    @Option(names = "-x", description = "optional option")
    private String x;

    @Parameters(description = "positional params")
    private List<String> positionals;

    @Override
    public Integer call() throws Exception {
        this.myRoute.getContext().start();
        Thread.sleep(10000);
        System.out.printf("mycommand was called with -x=%s and positionals: %s%n", x, positionals);
        return 23;
    }

    @Component
    @Command(name = "sub", mixinStandardHelpOptions = true, subcommands = MyCommand.SubSub.class,
            exitCodeOnExecutionException = 34)
    static class Sub implements Callable<Integer> {
        @Option(names = "-y", description = "optional option")
        private String y;

        @Parameters(description = "positional params")
        private List<String> positionals;

        @Override
        public Integer call() {
            System.out.printf("mycommand sub was called with -y=%s and positionals: %s%n", y, positionals);
            return 33;
        }
    }

    @Component
    @Command(name = "subsub", mixinStandardHelpOptions = true,
            exitCodeOnExecutionException = 44)
    static class SubSub implements Callable<Integer> {
        @Option(names = "-z", description = "optional option")
        private String z;

        @Override
        public Integer call() {
            System.out.printf("mycommand sub subsub was called with -z=%s. Service says: '%s'%n", z, "Hello");
            return 43;
        }
    }
}
