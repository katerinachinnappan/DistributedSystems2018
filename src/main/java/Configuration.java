public class Configuration {
    public static final int NUMBER_OF_ERRORS_TO_CORRECT = 2;
    public static final int NUMBER_OF_HEADNODES = NUMBER_OF_ERRORS_TO_CORRECT+1;
    public static final int NUMBER_OF_WORKERS_IN_POOL = NUMBER_OF_ERRORS_TO_CORRECT*2+1;//byzantian errors
}
