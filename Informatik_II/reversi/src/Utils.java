/*    */ package reversi;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Utils
/*    */ {
/*    */   public static int other(int player)
/*    */   {
/* 15 */     assert ((player == 2) || (player == 1));
/* 16 */     if (player == 2) return 1;
/* 17 */     return 2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static String toString(int player)
/*    */   {
/* 28 */     assert ((player == 2) || (player == 1));
/* 29 */     if (player == 2) return "GREEN";
/* 30 */     return "RED";
/*    */   }
/*    */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */