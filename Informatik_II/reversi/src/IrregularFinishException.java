/*    */ package reversi;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IrregularFinishException
/*    */   extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public int exception_id;
/*    */   
/* 12 */   public boolean timeOut = false;
/* 13 */   public boolean playerThreadDied = false;
/*    */   
/* 15 */   public boolean illegalMove = false;
/* 16 */   public boolean moveOutOfBounds = false;
/* 17 */   public boolean didNotPass = false;
/*    */   
/* 19 */   public boolean internetAccess = false;
/* 20 */   public boolean systemManipulation = false;
/* 21 */   public boolean memoryLimitExceeded = false;
/*    */   
/* 23 */   public boolean playerInit = false;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public IrregularFinishException(int exception_id)
/*    */   {
/* 47 */     this.exception_id = exception_id;
/* 48 */     switch (exception_id) {
/*    */     case 1: 
/* 50 */       this.timeOut = true;
/* 51 */       break;
/*    */     case 2: 
/* 53 */       this.playerThreadDied = true;
/* 54 */       break;
/*    */     case 3: 
/* 56 */       this.illegalMove = true;
/* 57 */       break;
/*    */     case 4: 
/* 59 */       this.moveOutOfBounds = true;
/* 60 */       break;
/*    */     case 5: 
/* 62 */       this.didNotPass = true;
/* 63 */       break;
/*    */     case 6: 
/* 65 */       this.internetAccess = true;
/* 66 */       break;
/*    */     case 7: 
/* 68 */       this.systemManipulation = true;
/* 69 */       break;
/*    */     case 8: 
/* 71 */       this.memoryLimitExceeded = true;
/* 72 */       break;
/*    */     
/*    */     case 9: 
/* 75 */       this.playerInit = true;
/* 76 */       break;
/*    */     
/*    */     default: 
/* 79 */       this.exception_id = 10;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/IrregularFinishException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */