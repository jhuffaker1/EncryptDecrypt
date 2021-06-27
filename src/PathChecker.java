public class PathChecker {

    public PathChecker() {

    }

    public boolean checkPath(String directoryPath) {
        if (directoryPath != "") {
            return true;
        } else {
            System.out.println("The path has not yet been supplied for the desired directory"
                    + " try using option 1 first. ");
            return false;
        }
    }
}
