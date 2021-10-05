public class BaseConverter {
  private int fromBase;
  private int toBase;

  private String fromChars;
  private String toChars;
  
  BaseConverter(int from, int to) throws IncorrectBaseException{
    setFromBase(from);
    setToBase(to);
  }

  public void reverse(){
    int tempFromBase = fromBase;
    String tempFromChars = fromChars;

    fromBase = toBase;
    fromChars = toChars;

    toBase = tempFromBase;
    toChars = tempFromChars;
  }

  public String convert(String val) throws IncorrectBaseException{
    if(!checkValidBase(val, fromChars))
      throw new IncorrectBaseException("Value given contains values that are not in the current base.");

    int numVal = baseToInt(val.toLowerCase());
    return intToBase(numVal);
  }


  void setFromBase(int from) throws IncorrectBaseException{
    fromBase = from;
    fromChars = genDefaultChars(from);
  }

  int getFromBase(){
    return fromBase;
  }

  void setToBase(int to) throws IncorrectBaseException{
    toBase = to;
    toChars = genDefaultChars(to);
  }

  int setToBase(){
    return toBase;
  }

  static String genDefaultChars(int base) throws IncorrectBaseException{
    if(base <= 0)
      throw new IncorrectBaseException("Base cant be less than 1.");

    String str = "";

    for(int i = 0; i < base; i++){
      if(i < 10){
        str += i;
      } else {
        int a = (int)('a');
        str += (char)(a - 10 + i);
      }
    }

    return str;
  }

  private static boolean checkValidBase(String val, String baseChars){
    for(int i = 0; i < val.length(); i++){
      if(baseChars.indexOf(val.charAt(i)) == -1)
        return false;
    }

    return true;
  }

  private int baseToInt(String val){
    int returnVal = 0;

    for(int i = 0; i < val.length(); i++){
      int column = (int)Math.pow(fromBase, i);
      char columnChar = val.charAt(val.length() - i - 1);
      int columnVal = fromChars.indexOf(columnChar);
      returnVal += column * columnVal;
    }

    return returnVal;
  }

  private String intToBase(int val){
    String str = "";

    while(val > 0){
      str = toChars.charAt(val % toBase) + str;
      val /= toBase;
    }

    return str;
  }
}

