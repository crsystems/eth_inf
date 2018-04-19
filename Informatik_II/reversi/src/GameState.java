/*     */ package reversi;
/*     */ 
/*     */ 
/*     */ class GameState
/*     */ {
/*     */   public static final int RESULT_DRAW_GAME = 1;
/*     */   
/*     */   public static final int RESULT_RED_WINS = 2;
/*     */   
/*     */   public static final int RESULT_GREEN_WINS = 3;
/*     */   
/*     */   public static final int FINISH_REGULAR = 0;
/*     */   
/*     */   public static final int FINISH_ABORTED = 1;
/*     */   
/*     */   public static final int FINISH_CHEATED = 2;
/*     */   
/*     */   public static final int CHEATED_ILLEGAL_MOVE = 1;
/*     */   public static final int CHEATED_TIME_EXCEEDED = 2;
/*  20 */   private int redStones = 0;
/*  21 */   private int greenStones = 0;
/*  22 */   private int result = 0;
/*  23 */   private int finish = 1;
/*  24 */   private int cheat = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void regularFinish(int red, int green)
/*     */   {
/*  34 */     this.redStones = red;
/*  35 */     this.greenStones = green;
/*     */     
/*  37 */     if (this.greenStones == this.redStones)
/*     */     {
/*  39 */       this.result = 1;
/*     */     }
/*  41 */     else if (this.redStones < this.greenStones)
/*     */     {
/*  43 */       this.result = 3;
/*     */     }
/*     */     else
/*     */     {
/*  47 */       this.result = 2;
/*     */     }
/*     */     
/*  50 */     this.finish = 0;
/*     */   }
/*     */   
/*     */   public void cheatedFinish(int currentPlayer, int reason, int red, int green)
/*     */   {
/*  55 */     if ((reason != 1) && (reason != 2))
/*     */     {
/*  57 */       throw new IllegalArgumentException("Invalid cheat reason!");
/*     */     }
/*     */     
/*  60 */     this.redStones = red;
/*  61 */     this.greenStones = green;
/*     */     
/*  63 */     if (currentPlayer == 1)
/*     */     {
/*  65 */       this.result = 3;
/*     */     }
/*  67 */     else if (currentPlayer == 2)
/*     */     {
/*  69 */       this.result = 2;
/*     */     }
/*     */     else
/*     */     {
/*  73 */       throw new IllegalArgumentException("Player has to be RED OR GREEN!");
/*     */     }
/*     */     
/*  76 */     this.finish = 2;
/*  77 */     this.cheat = reason;
/*     */   }
/*     */   
/*     */   public int getRedStones()
/*     */   {
/*  82 */     return this.redStones;
/*     */   }
/*     */   
/*     */   public int getGreenStones()
/*     */   {
/*  87 */     return this.greenStones;
/*     */   }
/*     */   
/*     */   public int getResult()
/*     */   {
/*  92 */     return this.result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 100 */     String msg = "GameState: ";
/* 101 */     String csv = "CSV: ";
/* 102 */     if (this.finish == 1)
/*     */     {
/* 104 */       msg = msg + "Game aborted";
/* 105 */       csv = csv + "ABORT,,";
/*     */     }
/* 107 */     else if (this.finish == 0)
/*     */     {
/* 109 */       if (this.result == 2)
/*     */       {
/* 111 */         msg = msg + "RED wins";
/* 112 */         csv = csv + "RED,,";
/*     */       }
/* 114 */       else if (this.result == 3)
/*     */       {
/* 116 */         msg = msg + "GREEN wins";
/* 117 */         csv = csv + "GREEN,,";
/*     */       }
/*     */       else
/*     */       {
/* 121 */         msg = msg + "DRAW game";
/* 122 */         csv = csv + "DRAW,,";
/*     */       }
/*     */     }
/* 125 */     else if (this.finish == 2)
/*     */     {
/* 127 */       if (this.result == 2)
/*     */       {
/* 129 */         msg = msg + "GREEN cheated -> RED wins, ";
/* 130 */         if (this.cheat == 1)
/*     */         {
/* 132 */           msg = msg + "GREEN makes illegal move";
/* 133 */           csv = csv + "RED,ILLEGAL_MOVE,";
/*     */         }
/* 135 */         else if (this.cheat == 2)
/*     */         {
/* 137 */           msg = msg + "GREEN exceeds time limit";
/* 138 */           csv = csv + "RED,TIMEOUT,";
/*     */         }
/*     */       }
/* 141 */       else if (this.result == 3)
/*     */       {
/* 143 */         msg = msg + "RED cheated -> GREEN wins, ";
/* 144 */         if (this.cheat == 1)
/*     */         {
/* 146 */           msg = msg + "RED makes illegal move";
/* 147 */           csv = csv + "GREEN,ILLEGAL_MOVE,";
/*     */         }
/* 149 */         else if (this.cheat == 2)
/*     */         {
/* 151 */           msg = msg + "RED exceeds time limit";
/* 152 */           csv = csv + "GREEN,TIMEOUT,";
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 157 */     msg = msg + " (RED vs. GREEN = " + this.redStones + ":" + this.greenStones + ")";
/* 158 */     csv = csv + String.format("%d,%d", new Object[] { Integer.valueOf(this.redStones), Integer.valueOf(this.greenStones) });
/*     */     
/* 160 */     return msg + "\n" + csv;
/*     */   }
/*     */   
/*     */   public int getFinish()
/*     */   {
/* 165 */     return this.finish;
/*     */   }
/*     */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/GameState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */