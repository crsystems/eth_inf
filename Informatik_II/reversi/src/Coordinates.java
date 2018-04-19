/*    */ package reversi;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Coordinates
/*    */ {
/*    */   private int row;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   private int col;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Coordinates(int row, int col)
/*    */   {
/* 22 */     this.row = row;
/* 23 */     this.col = col;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getRow()
/*    */   {
/* 31 */     return this.row;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int getCol()
/*    */   {
/* 39 */     return this.col;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 44 */     return "Coordinates( " + this.row + ", " + this.col + " )";
/*    */   }
/*    */   
/*    */   public boolean equals(Object o)
/*    */   {
/* 49 */     if ((o != null) && ((o instanceof Coordinates)))
/*    */     {
/* 51 */       Coordinates c = (Coordinates)o;
/* 52 */       return (this.row == c.row) && (this.col == c.col);
/*    */     }
/*    */     
/*    */ 
/* 56 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String toMoveString()
/*    */   {
/* 64 */     char ccol = (char)(96 + this.col);
/* 65 */     String move = ccol + this.row;
/*    */     
/* 67 */     return move;
/*    */   }
/*    */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/Coordinates.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */