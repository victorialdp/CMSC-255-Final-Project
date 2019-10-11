public class Final {
  //main method
  public static void main(String[] args) {
    //main method
  }

  //generateSign
    public static String generateSign(String month, int day) {
      if (month.isequal("January") && day >= 20 || month.isequal("February") && day <= 18) {
        return "Aquarius";
      } else if (month.isequal("February") || month.isequal("March") && day <= 20) {
        return "Pisces";
      } else if (month.isequal("March") || month.isequal("April") && day <= 19) {
        return "Aries";
      } else if (month.isequal("April") || month.isequal("May") && day <= 20) {
        return "Taurus";
      } else if (month.isequal("May") || month.isequal("June") && day <= 20) {
        return "Gemini";
      } else if (month.isequal("June") || month.isequal("July") && day <= 22) {
        return "Cancer";
      } else if (month.isequal("July") || month.isequal("August") && day <= 22) {
        return "Leo";
      } else if (month.isequal("August") || month.isequal("September") && day <= 22) {
        return "Virgo";
      } else if (month.isequal("September") || month.isequal("October") && day <= 22) {
        return "Libra";
      } else if (month.isequal("October") || month.isequal("November") && day <= 21) {
        return "Scorpio";
      } else if (month.isequal("November") || month.isequal("December") && day <= 21) {
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
