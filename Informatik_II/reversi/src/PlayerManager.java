/*    */ package reversi;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ class PlayerManager implements Runnable
/*    */ {
/*    */   private GameBoard gameBoard;
/*    */   private ReversiPlayer reversiPlayer;
/*    */   Throwable moveDone;
/*    */   Coordinates coord;
/*    */   
/*    */   PlayerManager(GameBoard g, ReversiPlayer r) {
/* 13 */     this.gameBoard = g;
/* 14 */     this.reversiPlayer = r;
/* 15 */     this.coord = null;
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/* 20 */     this.moveDone = null;
/*    */     
/*    */     try
/*    */     {
/* 24 */       this.coord = this.reversiPlayer.nextMove(this.gameBoard);
/*    */     }
/*    */     catch (Throwable e)
/*    */     {
/* 28 */       System.err.println("player thread exited with exception: " + e.getMessage());
/* 29 */       e.printStackTrace();
/* 30 */       this.moveDone = e;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/PlayerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */