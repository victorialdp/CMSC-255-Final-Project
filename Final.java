public class Final {
  //main method
  public static void main(String[] args) {
    //main method
  }

  //generateSign
    public static String generateSign(String month, int day) {
      if (month.isEqual("January") && day >= 20 || month.isEqual("February") && day <= 18) {
        return "Aquarius";
      } else if (month.isEqual("February") || month.isEqual("March") && day <= 20) {
        return "Pisces";
      } else if (month.isEqual("March") || month.isEqual("April") && day <= 19) {
        return "Aries";
      } else if (month.isEqual("April") || month.isEqual("May") && day <= 20) {
        return "Taurus";
      } else if (month.isEqual("May") || month.isEqual("June") && day <= 20) {
        return "Gemini";
      } else if (month.isEqual("June") || month.isEqual("July") && day <= 22) {
        return "Cancer";
      } else if (month.isEqual("July") || month.isEqual("August") && day <= 22) {
        return "Leo";
      } else if (month.isEqual("August") || month.isEqual("September") && day <= 22) {
        return "Virgo";
      } else if (month.isEqual("September") || month.isEqual("October") && day <= 22) {
        return "Libra";
      } else if (month.isEqual("October") || month.isEqual("November") && day <= 21) {
        return "Scorpio";
      } else if (month.isEqual("November") || month.isEqual("December") && day <= 21) {
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
