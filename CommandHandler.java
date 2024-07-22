package WCTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CommandHandler {

    /**
     * Counts the number of bytes in the specified file and prints the result.
     * 
     * @param path The path to the file.
     */
    public void countBytes(String path) {
        File file = new File(path);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            System.out.print(fileInputStream.available() + " ");
        } catch (FileNotFoundException e) {
            printErrorMessage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counts the number of lines in the specified file and prints the result.
     * 
     * @param path The path to the file.
     */
    public void countLines(String path) {
        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int count = 0;
            while ((br.readLine()) != null) {
                count++;
            }
            System.out.print(count + " ");
        } catch (FileNotFoundException e) {
            printErrorMessage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counts the number of words in the specified file and prints the result.
     * 
     * @param path The path to the file.
     */
    public void countWords(String path) {
        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int wordCount = 0;
            String line;
            while ((line = br.readLine()) != null) {
                // Regex to split on whitespaces
                String[] wordsArr = line.split("\\s");
                // Only count non-empty words
                for (String word : wordsArr) {
                    if (!word.isEmpty()) {
                        wordCount++;
                    }
                }
            }
            System.out.print(wordCount + " ");
        } catch (FileNotFoundException e) {
            printErrorMessage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counts the number of characaters in the specified file and prints the result.
     * 
     * @param path The path to the file.
     */
    public void countChars(String path) {
        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int charCount = 0;
            while ((br.read()) != -1) {
                charCount++;
            }
            System.out.print(charCount + " ");
        } catch (FileNotFoundException e) {
            printErrorMessage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints an error message indicating that the specified file was not found and
     * exits the program.
     * 
     * @param path The path to the file.
     */
    public void printErrorMessage(String path) {
        System.out.println("ccwc: " + path + ": No such file or directory");
        System.exit(1);
    }

    /**
     * Verifies if the given flag is valid.
     * 
     * @param flag The flag to verify.
     * @return true if the flag is valid, false otherwise.
     */
    public boolean verifyFlag(String flag) {
        String[] flags = { "-c", "-l", "-w", "-m" };
        for (String f : flags) {
            if (f.equals(flag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints an error message if the flag is invalid and exits the program.
     * 
     * @param flag The invalid flag.
     */
    public void invalidFlagMessage(String flag) {
        if (!verifyFlag(flag)) {
            String option = flag.substring(1);
            System.out.println("ccwc: invalid option -- '" + option + "'");
            System.exit(1);
        }
    }

    /**
     * Processes the command based on the provided flag and file path.
     * 
     * @param flag The flag indicating the type of operation.
     * @param path The path to the file.
     */
    public void processCommand(String flag, String path) {
        switch (flag) {
            case "-c":
                countBytes(path);
                break;
            case "-l":
                countLines(path);
                break;
            case "-w":
                countWords(path);
                break;
            case "-m":
                countChars(path);
                break;
            default:
                // -l -w -c
                countLines(path);
                countWords(path);
                countBytes(path);
                break;
        }
        System.out.print(path);
    }
}
