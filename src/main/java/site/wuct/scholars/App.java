package site.wuct.scholars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "run.sh", mixinStandardHelpOptions = true, version = "0.0.1", subcommands = {})
@SpringBootApplication
public class App extends SpringApplication implements Callable<Integer> {

    @Option(names = { "-v", "--verbose" }, description = "Enable verbose mode")
    private boolean verbose;

    @Option(names = { "-h", "--help" }, description = "Display a help message")
    private boolean helpRequested;

    @Override
    @SneakyThrows
    public Integer call() {
        return 0;
    }

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new App());
        int exitCode = cmd.execute(args);
        if (exitCode != CommandLine.ExitCode.OK) {
            System.exit(exitCode);
        }
        App app = cmd.getCommand();
        if (app.verbose) {
            ((ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger("root"))
                    .setLevel(ch.qos.logback.classic.Level.DEBUG);
        }
        if (app.helpRequested) {
            cmd.usage(System.out);
            return;
        }
        SpringApplication.run(App.class, args);
    }
}
