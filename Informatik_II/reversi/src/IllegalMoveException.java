/*    */ package reversi;
/*    */ 
/*    */ class IllegalMoveException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public Coordinates coord;
/*    */   
/*    */   public IllegalMoveException(String msg)
/*    */   {
/* 10 */     super(msg);
/*    */   }
/*    */   
/*    */   public IllegalMoveException(String msg, Coordinates c)
/*    */   {
/* 15 */     super(msg);
/* 16 */     this.coord = c;
/*    */   }
/*    */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/IllegalMoveException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */