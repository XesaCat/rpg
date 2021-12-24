package xyz.xesacat.rpg.util;

public class CmdLineParser {
    private static String[] args;

    public CmdLineParser(String[] a) {
        args = a;
    }

    public String[] parseArgument(String argumentName, int argumentValueCount) throws MalformedArgumentException {
        for (int i = 0; i < args.length; i++) {
            String argument = args[i];

            if (!argument.startsWith("-")) {
                if (i == 0)
                    throw new MalformedArgumentException("Unexpected argument \"" + argument + "\"");
                continue;
            }

            argument = argument.split("-")[1];

            if (!argument.equals(argumentName))
                continue;

            String[] parsedArgumentValues = new String[argumentValueCount];
            for (int v = 1; v < argumentValueCount + 1; v++) {
                if (argument.equals(argumentName)) {
                    if (i == args.length - 1)
                        throw new MalformedArgumentException("Value for '" + argument + "' is Missing");

                    if (args[i + argumentValueCount].startsWith("-"))
                        throw new MalformedArgumentException("Value for '" + argument + "' is Missing");

                    parsedArgumentValues[v - 1] = args[i + v];
                }
            }
            i += argumentValueCount;

            return parsedArgumentValues;
        }
        throw new MalformedArgumentException("Argument '" + argumentName + "' is Missing");
    }
}
