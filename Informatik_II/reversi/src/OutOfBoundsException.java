/*    */ package reversi;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OutOfBoundsException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */ 
/*    */ 
/*    */   public Coordinates coord;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public OutOfBoundsException(String msg, Coordinates c)
/*    */   {
/* 20 */     super(msg);
/* 21 */     this.coord = new Coordinates(c.getRow(), c.getCol());
/*    */   }
/*    */   
/*    */   public OutOfBoundsException(String msg, int row, int col)
/*    */   {
/* 26 */     super(msg);
/* 27 */     this.coord = new Coordinates(row, col);
/*    */   }
/*    */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/OutOfBoundsException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */