public class Token
{
   private String lexeme;
   private int code;

   public static Token createToken(int code)
   {
      return new Token(Constants.getLexeme(code), code);
   }

   public static Token createIdToken(String lexeme)
   {
      return new Token(lexeme, Constants.TK_ID);
   }

   public static Token createNumToken(String lexeme)
   {
      return new Token(lexeme, Constants.TK_NUM);
   }

   private Token(String lexeme, int code)
   {
      this.lexeme = lexeme;
      this.code = code;
   }

   public String toString()
   {
      return lexeme;
   }

   public int getCode()
   {
      return code;
   }
}
