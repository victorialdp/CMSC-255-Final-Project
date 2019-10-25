import java.util.Scanner;

// This import statement allows us to read from a text file
import java.io.File;

// This import statement allows us to respond to an error if the file is not found
import java.io.FileNotFoundException;

public class Final {
  public static void main(String[] args) {
    
    boolean repeat;
    Scanner input = new Scanner(System.in);

    do {
      System.out.println("Enter a birth month: ");
      String month = input.nextLine();
      System.out.println("Enter a birth day: ");
      int day = input.nextInt();

      String sign = generateSign(month, day);
      String horoscope = generateHoroscope();

      System.out.println(sign + ": " + horoscope);
      System.out.println("Do you want me to read another horoscope? (y/n)");
      String userEntry = input.next();
      repeat = userEntry.equalsIgnoreCase("yes") || Character.toUpperCase(userEntry.charAt(0)) == 'Y';
      
    } while (repeat);
    
  }

  //generateSign
  public static String generateSign(String month, int day) {
    if (month.equals("January") && day >= 20 || month.equals("February") && day <= 18) {
      return "Aquarius";
    } else if (month.equals("February") || month.equals("March") && day <= 20) {
      return "Pisces";
    } else if (month.equals("March") || month.equals("April") && day <= 19) {
      return "Aries";
    } else if (month.equals("April") || month.equals("May") && day <= 20) {
      return "Taurus";
    } else if (month.equals("May") || month.equals("June") && day <= 20) {
      return "Gemini";
    } else if (month.equals("June") || month.equals("July") && day <= 22) {
      return "Cancer";
    } else if (month.equals("July") || month.equals("August") && day <= 22) {
      return "Leo";
    } else if (month.equals("August") || month.equals("September") && day <= 22) {
      return "Virgo";
    } else if (month.equals("September") || month.equals("October") && day <= 22) {
      return "Libra";
    } else if (month.equals("October") || month.equals("November") && day <= 21) {
      return "Scorpio";
    } else if (month.equals("November") || month.equals("December") && day <= 21) {
      return "Sagittarius";
    } else {
      return "Capricorn";
    }
        
  }

  // Returns a random String to use as a horoscope
  public static String generateHoroscope() {
    
    // Horoscopes are actually constructed from fortune cookie fortunes 
    // taken from https://joshmadison.com/2008/04/20/fortune-cookie-fortunes/
    
    // The try block is here in case the file cannot be found
    try {
      // Creates a File object from a .txt file "Horoscopes.txt"
      // Horoscopes.txt must be located in the same directory as the program
      File horoscopeFile = new File("Horoscopes.txt");
      
      // We create two Scanners, the first to read the number of lines,
      // the second to read the content of each line.
      // Two Scanners are required as you cannot rewind a Scanner
      // The Scanners read from the file rather than from System.in (command line)
      Scanner readLines = new Scanner(horoscopeFile);
      Scanner readText = new Scanner(horoscopeFile);
      
      // Counts the number of lines, then creates a String array of that length
      int lineCount = 0;
      while (readLines.hasNextLine()) {
        readLines.nextLine();
        lineCount++;
      }
      String[] horoscopes = new String[lineCount];
    
      // Fills the array with the various fortunes in the file
      for (int i = 0 ; i < lineCount ; i++) {
        horoscopes[i] = readText.nextLine();
      }
      
      // Generates two random integers between 0 and lineCount - 1
      // then returns a two-sentence horoscope 
      // constructed from the two fortunes found at those indices
      int index1 = (int)(Math.random() * lineCount);
      int index2 = (int)(Math.random() * lineCount);
      return horoscopes[index1] + " " + horoscopes[index2];
      
    } 
    // Error message in the event the file is not found
    catch (FileNotFoundException error) {
      return "I'm sorry, we seem to have lost our star charts. Try again later.";
    }
    
  }
  
}
