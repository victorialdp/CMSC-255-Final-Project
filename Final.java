public class Final {
  //main method
  public static void main(String[] args) {
    //main method
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

  // generateHoroscope
  public static String generateHoroscope() {
      // generateHoroscope
  }
          
  }
  
}
