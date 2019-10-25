
java.util.Scanner

public class Final {
  public static void main(String[] args) {
  Scanner input = new Scanner(System.in);

    System.out.println("Enter a birth month: ");
    String month = input.nextLine();
    System.out.println("Enter a a birth day: ");
    int day = input.nextInt();
    
    String sign = generaterSign(month, day);
    String horoscope = generateHoroscope();
    
    System.out.println(sign + " " + horoscope);
    
    

  }
}
