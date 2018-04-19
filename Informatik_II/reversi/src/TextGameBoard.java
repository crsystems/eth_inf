/*     */ package reversi;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TextGameBoard
/*     */   implements GameBoard
/*     */ {
/*     */   private static final int SIZE = 10;
/*  19 */   private int[][] theBoard = new int[10][10];
/*     */   private int filled;
/*     */   private boolean left;
/*     */   private boolean upleft;
/*     */   private boolean up;
/*     */   private boolean upright;
/*     */   private boolean right;
/*     */   private boolean downright;
/*  27 */   private boolean down; private boolean downleft; private Coordinates lastCheckMove = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextGameBoard()
/*     */   {
/*  35 */     initBoard();
/*     */   }
/*     */   
/*     */   public GameBoard clone()
/*     */   {
/*  40 */     return new TextGameBoard(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextGameBoard(GameBoard gb)
/*     */   {
/*  52 */     updateBoard(gb);
/*     */   }
/*     */   
/*     */   public int getSize()
/*     */   {
/*  57 */     return 8;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean validCoordinates(Coordinates c)
/*     */   {
/*  64 */     return (c != null) && (validCoordinates(c.getCol(), c.getRow()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean validCoordinates(int x, int y)
/*     */   {
/*  84 */     return (x > 0) && (x < 9) && (y > 0) && (y < 9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void initBoard()
/*     */   {
/*  93 */     for (int i = 0; i < 10; i++)
/*     */     {
/*  95 */       for (int j = 0; j < 10; j++)
/*     */       {
/*  97 */         this.theBoard[i][j] = 0;
/*     */       }
/*     */     }
/*     */     
/* 101 */     this.theBoard[4][4] = 1;
/* 102 */     this.theBoard[5][4] = 1;
/* 103 */     this.theBoard[4][5] = 2;
/* 104 */     this.theBoard[5][5] = 2;
/* 105 */     this.filled = 4;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateBoard(GameBoard gb)
/*     */   {
/*     */     try
/*     */     {
/* 116 */       for (int x = 1; x <= getSize(); x++)
/*     */       {
/* 118 */         for (int y = 1; y <= getSize(); y++)
/*     */         {
/* 120 */           Coordinates c = new Coordinates(x, y);
/* 121 */           int color = gb.getOccupation(c);
/* 122 */           this.theBoard[y][x] = color;
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (OutOfBoundsException e)
/*     */     {
/* 128 */       System.err.println("TextGameBoard.updateBoard: internal error");
/* 129 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public int getOccupation(Coordinates c) throws OutOfBoundsException
/*     */   {
/* 135 */     if (validCoordinates(c))
/*     */     {
/* 137 */       return this.theBoard[c.getCol()][c.getRow()];
/*     */     }
/*     */     
/*     */ 
/* 141 */     throw new OutOfBoundsException(c.toString(), c);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean check(int player, int i, int j, int iinc, int jinc)
/*     */   {
/* 159 */     int other = 3 - player;
/*     */     
/* 161 */     boolean once = false;
/*     */     
/* 163 */     int k = i + iinc;
/* 164 */     int l = j + jinc;
/* 165 */     while (this.theBoard[k][l] == other)
/*     */     {
/* 167 */       k += iinc;
/* 168 */       l += jinc;
/* 169 */       once = true;
/*     */     }
/*     */     
/* 172 */     return (once) && (this.theBoard[k][l] == player);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void flip(int player, int i, int j, int iinc, int jinc)
/*     */   {
/* 180 */     int other = 3 - player;
/*     */     
/*     */ 
/* 183 */     int k = i + iinc;
/* 184 */     int l = j + jinc;
/* 185 */     while (this.theBoard[k][l] == other)
/*     */     {
/* 187 */       this.theBoard[k][l] = player;
/* 188 */       k += iinc;
/* 189 */       l += jinc;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isMoveAvailable(int player)
/*     */   {
/* 195 */     if ((player != 2) && (player != 1)) {
/* 196 */       throw new IllegalArgumentException("unknown player color: " + player);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 201 */     for (int i = 1; i < 9; i++)
/*     */     {
/* 203 */       for (int j = 1; j < 9; j++)
/*     */       {
/* 205 */         if (this.theBoard[i][j] == 0)
/*     */         {
/* 207 */           for (int a = -1; a < 2; a++)
/*     */           {
/* 209 */             for (int b = -1; b < 2; b++)
/*     */             {
/* 211 */               if (check(player, i, j, a, b))
/*     */               {
/* 213 */                 return true;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 222 */     return false;
/*     */   }
/*     */   
/*     */   public boolean checkMove(int player, Coordinates c)
/*     */   {
/* 227 */     if ((player != 2) && (player != 1)) {
/* 228 */       throw new IllegalArgumentException("unknown player color: " + player);
/*     */     }
/* 230 */     this.lastCheckMove = c;
/* 231 */     if (c == null)
/*     */     {
/* 233 */       return !isMoveAvailable(player);
/*     */     }
/* 235 */     int i = c.getCol();int j = c.getRow();
/*     */     
/* 237 */     if ((!validCoordinates(c)) || (this.theBoard[i][j] != 0))
/* 238 */       return false;
/* 239 */     this.left = check(player, i, j, -1, 0);
/* 240 */     this.upleft = check(player, i, j, -1, -1);
/* 241 */     this.up = check(player, i, j, 0, -1);
/* 242 */     this.upright = check(player, i, j, 1, -1);
/* 243 */     this.right = check(player, i, j, 1, 0);
/* 244 */     this.downright = check(player, i, j, 1, 1);
/* 245 */     this.down = check(player, i, j, 0, 1);
/* 246 */     this.downleft = check(player, i, j, -1, 1);
/* 247 */     return (this.left) || (this.upleft) || (this.up) || (this.upright) || (this.right) || (this.downright) || (this.down) || (this.downleft);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void makeMove(int player, Coordinates c)
/*     */   {
/* 256 */     if ((player != 2) && (player != 1)) {
/* 257 */       throw new IllegalArgumentException("unknown player color: " + player);
/*     */     }
/*     */     
/* 260 */     if (c == null)
/*     */     {
/* 262 */       System.out.println("makeMove: Move skipped ...");
/* 263 */       return;
/*     */     }
/*     */     
/* 266 */     if (!c.equals(this.lastCheckMove)) {
/* 267 */       throw new IllegalStateException("makeMove for coordinates " + c + " was not preceeded by checkMove");
/*     */     }
/* 269 */     int i = c.getCol();int j = c.getRow();
/*     */     
/* 271 */     this.theBoard[i][j] = player;
/* 272 */     this.filled += 1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 280 */     if (this.left)
/* 281 */       flip(player, i, j, -1, 0);
/* 282 */     if (this.upleft)
/* 283 */       flip(player, i, j, -1, -1);
/* 284 */     if (this.up)
/* 285 */       flip(player, i, j, 0, -1);
/* 286 */     if (this.upright)
/* 287 */       flip(player, i, j, 1, -1);
/* 288 */     if (this.right)
/* 289 */       flip(player, i, j, 1, 0);
/* 290 */     if (this.downright)
/* 291 */       flip(player, i, j, 1, 1);
/* 292 */     if (this.down)
/* 293 */       flip(player, i, j, 0, 1);
/* 294 */     if (this.downleft) {
/* 295 */       flip(player, i, j, -1, 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isFull()
/*     */   {
/* 301 */     return this.filled == 64;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int countStones(int player)
/*     */   {
/* 310 */     int count = 0;
/*     */     
/* 312 */     for (int i = 1; i < 9; i++)
/*     */     {
/* 314 */       for (int j = 1; j < 9; j++)
/*     */       {
/* 316 */         if (this.theBoard[i][j] == player)
/*     */         {
/* 318 */           count++;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 323 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 332 */     String gb = " |1|2|3|4|5|6|7|8|\n";
/*     */     
/* 334 */     for (int i = 1; i < 9; i++)
/*     */     {
/* 336 */       gb = gb + i;
/* 337 */       for (int j = 1; j < 9; j++)
/*     */       {
/* 339 */         if (this.theBoard[j][i] == 1)
/*     */         {
/* 341 */           gb = gb + "|r";
/*     */         }
/* 343 */         else if (this.theBoard[j][i] == 2)
/*     */         {
/* 345 */           gb = gb + "|g";
/*     */         }
/*     */         else
/*     */         {
/* 349 */           gb = gb + "| ";
/*     */         }
/*     */       }
/* 352 */       gb = gb + "|\n";
/*     */     }
/* 354 */     gb = gb + "\n";
/*     */     
/* 356 */     return gb;
/*     */   }
/*     */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/TextGameBoard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */