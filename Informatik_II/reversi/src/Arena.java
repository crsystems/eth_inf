/*     */ package reversi;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.util.Properties;
/*     */ import java.util.Vector;
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
/*     */ public class Arena
/*     */ {
/*     */   protected static final int BOARD_LENGTH = 8;
/*     */   protected static final int TIME_TOLERANCE = 150;
/*  59 */   int timeBeforeExit = 10000;
/*     */   
/*  61 */   String statusText = "";
/*     */   
/*     */   ReversiPlayer player_red;
/*     */   
/*     */   ReversiPlayer player_green;
/*     */   long timeOut;
/*     */   String name_red;
/*     */   String name_green;
/*  69 */   boolean verbose = false;
/*     */   
/*     */   String logFile;
/*     */   String matchId;
/*  73 */   Vector<PrintWriter> logStreams = null;
/*  74 */   StringBuffer backlog = null;
/*     */   
/*     */   protected ProgramParameters params;
/*     */   protected Thread performerThread;
/*  78 */   GameState state = new GameState();
/*     */   Visualization visualization;
/*     */   
/*     */   public Arena(ProgramParameters params, Visualization vis)
/*     */   {
/*  83 */     this.backlog = new StringBuffer();
/*  84 */     this.logStreams = new Vector();
/*     */     
/*  86 */     this.params = params;
/*     */     
/*  88 */     this.logFile = params.getLogfile();
/*  89 */     this.matchId = params.getGameId();
/*     */     
/*  91 */     String pr = params.getRedName();
/*  92 */     String pg = params.getGreenName();
/*  93 */     long timeOut = params.getTimeout();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  98 */     this.timeBeforeExit = ((int)params.getTimeBeforeExit());
/*     */     
/* 100 */     if (params.isUseJar()) {
/*     */       try {
/* 102 */         this.player_red = instantiatePlayerJar(pr, params.getRedPath());
/*     */ 
/*     */       }
/*     */       catch (Throwable e)
/*     */       {
/* 107 */         IrregularFinishException e2 = new IrregularFinishException(9);
/* 108 */         e.printStackTrace();
/* 109 */         System.exit(-1);
/*     */       }
/*     */       try
/*     */       {
/* 113 */         this.player_green = instantiatePlayerJar(pg, params.getGreenPath());
/*     */ 
/*     */       }
/*     */       catch (Throwable e)
/*     */       {
/* 118 */         IrregularFinishException e2 = new IrregularFinishException(9);
/* 119 */         e.printStackTrace();
/* 120 */         System.exit(-1);
/*     */       }
/*     */     } else {
/* 123 */       this.player_red = instantiatePlayer(pr);
/* 124 */       this.player_green = instantiatePlayer(pg);
/*     */     }
/*     */     
/* 127 */     writeToLog("new game");
/* 128 */     writeToLog("red=" + pr + " green=" + pg);
/*     */     
/* 130 */     this.visualization = vis;
/*     */     
/* 132 */     if (!params.getHeadless()) {
/* 133 */       this.visualization.init(this);
/*     */     }
/* 135 */     this.timeOut = timeOut;
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 140 */       this.player_red.initialize(1, timeOut);
/*     */     } catch (Exception e) {
/* 142 */       IrregularFinishException e2 = new IrregularFinishException(9);
/* 143 */       e.printStackTrace();
/* 144 */       System.exit(-1);
/*     */     }
/*     */     try
/*     */     {
/* 148 */       this.player_green.initialize(2, timeOut);
/*     */     } catch (Exception e) {
/* 150 */       IrregularFinishException e2 = new IrregularFinishException(9);
/* 151 */       e.printStackTrace();
/* 152 */       System.exit(-1);
/*     */     }
/*     */     
/* 155 */     this.name_red = pr.substring(pr.lastIndexOf('.') + 1);
/* 156 */     this.name_green = pg.substring(pg.lastIndexOf('.') + 1);
/*     */     
/* 158 */     if (!params.getHeadless())
/*     */     {
/* 160 */       this.visualization.setInfoLine("delay=" + params.getDelay());
/* 161 */       this.visualization.setInfoLine2(this.name_red + " (red) vs " + this.name_green + " (green)");
/*     */     }
/*     */     
/* 164 */     writeToLog("initialized");
/*     */   }
/*     */   
/*     */   boolean onBoard(int r, int c) {
/* 168 */     return (r >= 0) && (r < 8) && (c >= 0) && (c < 8);
/*     */   }
/*     */   
/* 171 */   int[] direction = { -1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   void computeTokensToFlip(GameBoard gb, Coordinates pos, int color, int[][] toFlip)
/*     */   {
/* 183 */     int opposite = color == 1 ? 2 : 1;
/*     */     
/* 185 */     int[][] currentBoard = new int[8][8];
/* 186 */     int row = pos.getRow() - 1;
/* 187 */     int col = pos.getCol() - 1;
/*     */     try
/*     */     {
/* 190 */       for (int i = 1; i <= 8; i++) {
/* 191 */         for (int j = 1; j <= 8; j++) {
/* 192 */           currentBoard[(i - 1)][(j - 1)] = gb.getOccupation(new Coordinates(i, j));
/* 193 */           toFlip[(i - 1)][(j - 1)] = 0;
/*     */         }
/*     */       }
/*     */     } catch (OutOfBoundsException e) {
/* 197 */       System.out.println("I checked an illegal position. I am sorry.");
/*     */     }
/*     */     
/* 200 */     for (int idx = 0; idx < this.direction.length; idx += 2) {
/* 201 */       int delta_row = this.direction[idx];
/* 202 */       int delta_col = this.direction[(idx + 1)];
/* 203 */       int drow = row + delta_row;
/* 204 */       int dcol = col + delta_col;
/*     */       
/* 206 */       boolean oneFlipped = false;
/* 207 */       while ((onBoard(drow, dcol)) && (currentBoard[drow][dcol] == opposite)) {
/* 208 */         oneFlipped = true;
/* 209 */         drow += delta_row;
/* 210 */         dcol += delta_col;
/*     */       }
/*     */       
/* 213 */       if ((onBoard(drow, dcol)) && (oneFlipped) && (currentBoard[drow][dcol] == color)) {
/* 214 */         drow = row + delta_row;
/* 215 */         dcol = col + delta_col;
/* 216 */         while (currentBoard[drow][dcol] == opposite) {
/* 217 */           toFlip[drow][dcol] = 1;
/* 218 */           drow += delta_row;
/* 219 */           dcol += delta_col;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   Coordinates performMove(int player, TextGameBoard board) throws IrregularFinishException {
/* 226 */     Coordinates currentMove = null;
/* 227 */     int[][] toFlip = new int[8][8];
/*     */     
/* 229 */     this.statusText = "";
/*     */     
/* 231 */     if ((player != 1) && (player != 2)) {
/* 232 */       throw new IllegalArgumentException("Player has to be RED or GREEN!");
/*     */     }
/*     */     
/*     */     ReversiPlayer reversiPlayer;
/*     */     String playerColor;
/*     */     String playerName;
/*     */     ReversiPlayer reversiPlayer;
/* 239 */     if (player == 1) {
/* 240 */       String playerColor = "Red";
/* 241 */       String playerName = this.name_red;
/* 242 */       reversiPlayer = this.player_red;
/*     */     } else {
/* 244 */       playerColor = "Green";
/* 245 */       playerName = this.name_green;
/* 246 */       reversiPlayer = this.player_green;
/*     */     }
/*     */     
/* 249 */     if ((!this.params.getHeadless()) && (this.params.getAnimations()))
/*     */     {
/* 251 */       this.visualization.showPossibleMoves(board, player);
/* 252 */       this.visualization.setStatusLine(playerName + " thinking...");
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 257 */       Thread.sleep(this.params.getDelay());
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {}
/*     */     try
/*     */     {
/* 262 */       currentMove = makeMove(reversiPlayer, board);
/*     */     } catch (ThreadDeath e) {
/* 264 */       writeToLog(playerColor + " exceeds time limit");
/* 265 */       cheatedFinish(player, 2, board);
/* 266 */       this.statusText = (playerColor + " exceeds time limit.");
/* 267 */       throw new IrregularFinishException(2);
/*     */     } catch (Throwable e) {
/* 269 */       writeToLog(playerColor + " failed: " + e.getClass().getSimpleName() + ": " + e.getMessage());
/* 270 */       this.statusText = (playerColor + " failed: " + e.getClass().getSimpleName());
/* 271 */       throw new IrregularFinishException(-1);
/*     */     }
/*     */     
/* 274 */     writeToLog(playerColor + "move=" + (
/* 275 */       currentMove == null ? "null" : new StringBuilder(String.valueOf(currentMove.getRow())).append(",").append(currentMove.getCol()).toString()));
/*     */     
/* 277 */     if (currentMove == null) {
/* 278 */       System.out.println(playerColor + " passes.");
/*     */     }
/*     */     
/* 281 */     boolean legalMove = board.checkMove(player, currentMove);
/*     */     
/* 283 */     if (!legalMove) {
/* 284 */       System.out.println(playerColor + " makes illegal move: " + currentMove);
/* 285 */       writeToLog(playerColor + " makes illegal move");
/* 286 */       if (this.verbose) {
/* 287 */         System.out.println(board.toString());
/*     */       }
/* 289 */       cheatedFinish(player, 1, board);
/* 290 */       this.statusText = (playerColor + " makes illegal move.");
/*     */       
/* 292 */       throw new IrregularFinishException(3);
/*     */     }
/*     */     
/* 295 */     if (currentMove != null) {
/* 296 */       computeTokensToFlip(board, currentMove, player, toFlip);
/* 297 */       if ((!this.params.getHeadless()) && (this.params.getAnimations())) {
/* 298 */         this.visualization.animateMove(board, currentMove, player, toFlip);
/*     */       }
/*     */     }
/*     */     
/* 302 */     board.makeMove(player, currentMove);
/* 303 */     if (!this.params.getHeadless()) {
/* 304 */       this.visualization.setInfoLine2(this.name_red + " (red) vs " + this.name_green + " (green): " + 
/* 305 */         board.countStones(1) + ":" + board.countStones(2));
/*     */     }
/*     */     
/* 308 */     return currentMove;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void performMatch()
/*     */   {
/* 315 */     TextGameBoard board = new TextGameBoard();
/*     */     
/*     */ 
/* 318 */     if (this.params.getStartPosition() != 0) {
/* 319 */       setStartingPosition(this.params.getStartPosition(), board);
/*     */     }
/* 321 */     Coordinates move_red = new Coordinates(0, 0);
/* 322 */     Coordinates move_green = new Coordinates(0, 0);
/*     */     
/* 324 */     if (!this.params.getHeadless()) {
/* 325 */       this.visualization.update(board);
/*     */     }
/*     */     
/* 328 */     boolean irregularFinish = false;
/* 329 */     this.statusText = "";
/*     */     
/* 331 */     while (!board.isFull())
/*     */     {
/*     */ 
/*     */       try
/*     */       {
/*     */ 
/* 337 */         move_red = performMove(1, board);
/*     */       } catch (IrregularFinishException e) {
/* 339 */         irregularFinish = true;
/* 340 */         break;
/*     */       }
/*     */       
/* 343 */       if (!this.params.getHeadless()) {
/* 344 */         this.visualization.update(board);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 352 */         move_green = performMove(2, board);
/*     */       } catch (IrregularFinishException e) {
/* 354 */         irregularFinish = true;
/* 355 */         break;
/*     */       }
/*     */       
/* 358 */       if (!this.params.getHeadless()) {
/* 359 */         this.visualization.update(board);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 365 */       if ((move_red == null) && (move_green == null)) {
/* 366 */         System.out.println("Vorzeitiges Ende");
/* 367 */         writeToLog("no moves left");
/* 368 */         this.statusText = "Finished, no moves left.";
/* 369 */         break;
/*     */       }
/*     */     }
/*     */     
/* 373 */     if (!irregularFinish) {
/* 374 */       if (board.isFull()) {
/* 375 */         this.statusText = "Finished.";
/*     */       }
/*     */       
/* 378 */       writeToLog("finished reds=" + board.countStones(1) + " greens=" + 
/* 379 */         board.countStones(2));
/*     */       
/* 381 */       int redStones = board.countStones(1);
/* 382 */       int greenStones = board.countStones(2);
/*     */       
/* 384 */       regularFinish(redStones, greenStones);
/*     */ 
/*     */     }
/* 387 */     else if (!this.params.getHeadless()) {
/* 388 */       this.visualization.setStatusLine(this.statusText);
/*     */     }
/*     */     
/*     */ 
/* 392 */     if (!this.params.getHeadless()) {
/* 393 */       if (this.state.getResult() == 1) {
/* 394 */         this.visualization.setStatusLine(
/* 395 */           this.statusText + " (DRAW " + this.state.getRedStones() + ":" + this.state.getGreenStones() + ")");
/* 396 */       } else if (this.state.getResult() == 3) {
/* 397 */         this.visualization.setStatusLine(this.statusText + " (GREEN " + this.name_green + " WON " + this.state.getGreenStones() + 
/* 398 */           ":" + this.state.getRedStones() + ")");
/* 399 */       } else if (this.state.getResult() == 2) {
/* 400 */         this.visualization.setStatusLine(this.statusText + " (RED " + this.name_red + " WON " + this.state.getRedStones() + ":" + 
/* 401 */           this.state.getGreenStones() + ")");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 408 */     if (!this.params.getHeadless()) {
/* 409 */       if (this.timeBeforeExit > 0) {
/*     */         try {
/* 411 */           Thread.sleep(this.timeBeforeExit);
/*     */         }
/*     */         catch (InterruptedException localInterruptedException) {}
/*     */       } else {
/*     */         try
/*     */         {
/*     */           for (;;)
/*     */           {
/* 419 */             Thread.sleep(500L);
/*     */           }
/*     */           
/*     */ 
/*     */           return;
/*     */         }
/*     */         catch (InterruptedException localInterruptedException1) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   Coordinates makeMove(ReversiPlayer player, GameBoard board)
/*     */     throws Throwable
/*     */   {
/* 434 */     PlayerManager manager = new PlayerManager(board, player);
/* 435 */     Thread t = new Thread(manager);
/*     */     
/* 437 */     t.start();
/*     */     try {
/* 439 */       t.join(this.timeOut + 150L);
/*     */     } catch (InterruptedException e) {
/* 441 */       System.err.println("main thread interrupted!");
/* 442 */       System.exit(1);
/*     */     }
/*     */     
/* 445 */     if (t.isAlive()) {
/* 446 */       stopThread(t);
/* 447 */       throw new ThreadDeath();
/*     */     }
/*     */     
/* 450 */     if (manager.moveDone != null) {
/* 451 */       throw manager.moveDone;
/*     */     }
/*     */     
/* 454 */     return manager.coord;
/*     */   }
/*     */   
/*     */   private static void stopThread(Thread t)
/*     */   {
/* 459 */     t.stop();
/*     */   }
/*     */   
/*     */   void cheatedFinish(int player, int reason, TextGameBoard board) {
/* 463 */     if (player == 2)
/*     */     {
/* 465 */       int redStones = Math.max(board.countStones(1), 32);
/* 466 */       this.state.cheatedFinish(2, reason, redStones, 0);
/*     */     }
/*     */     else {
/* 469 */       int greenStones = Math.max(board.countStones(2), 32);
/* 470 */       this.state.cheatedFinish(1, reason, 0, greenStones);
/*     */     }
/*     */     
/*     */ 
/* 474 */     System.out.println("CHEATED : " + this.state);
/*     */   }
/*     */   
/*     */   void regularFinish(int redStones, int greenStones)
/*     */   {
/* 479 */     if (greenStones == 0) {
/* 480 */       redStones = 64;
/* 481 */     } else if (redStones == 0) {
/* 482 */       greenStones = 64;
/*     */     }
/*     */     
/* 485 */     this.state.regularFinish(redStones, greenStones);
/* 486 */     System.out.println("REGULAR FINISH: " + this.state);
/*     */   }
/*     */   
/*     */   static ReversiPlayer instantiatePlayerJar(String name, String path) throws Exception {
/* 490 */     ReversiPlayer p = null;
/* 491 */     Class<?> c = null;
/*     */     try {
/* 493 */       File jar = new File(path);
/*     */       
/* 495 */       URL url = new URL("file", null, jar.getAbsolutePath());
/*     */       
/* 497 */       URLClassLoader loader = new URLClassLoader(new URL[] { url });
/*     */       
/* 499 */       c = loader.loadClass(name);
/* 500 */       Object o = c.newInstance();
/* 501 */       if (!(o instanceof ReversiPlayer)) {
/* 502 */         System.err.println("The class " + name + " does not implement " + "the interface ReversiPlayer");
/* 503 */         System.exit(1);
/*     */       }
/* 505 */       p = (ReversiPlayer)o;
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 510 */       System.err.println("********************************************************************");
/* 511 */       System.err.println("Error: could not instantiate \"" + name + "\" !");
/* 512 */       System.err.println("PATH: " + path + "!");
/* 513 */       System.err.println("PLEASE MAKE SURE THAT THE PLAYER JAR HAS A");
/* 514 */       System.err.println("CONSTRUCTOR THAT TAKES NO PARAMETERS");
/* 515 */       System.err.println("********************************************************************");
/* 516 */       e.printStackTrace();
/* 517 */       throw e;
/*     */     }
/*     */     catch (NoClassDefFoundError e)
/*     */     {
/* 521 */       System.err.println("********************************************************************");
/* 522 */       System.err.println("Error: could not instantiate \"" + name + "\" !");
/* 523 */       System.err.println("PATH: " + path + "!");
/* 524 */       System.err.println("PLEASE MAKE SURE THAT YOU USE THE CORRECT CASE,");
/* 525 */       System.err.println("SINCE CLASSNAMES ARE CASE-SENSITIVE!");
/* 526 */       System.err.println("********************************************************************");
/* 527 */       e.printStackTrace();
/* 528 */       throw e;
/*     */     }
/*     */     
/* 531 */     return p;
/*     */   }
/*     */   
/*     */   static ReversiPlayer instantiatePlayer(String name) {
/* 535 */     ReversiPlayer p = null;
/* 536 */     Class<?> c = null;
/*     */     try
/*     */     {
/* 539 */       c = Class.forName(name);
/* 540 */       Constructor<?> con = c.getConstructor(null);
/* 541 */       Object o = con.newInstance(null);
/*     */       
/* 543 */       if (!(o instanceof ReversiPlayer)) {
/* 544 */         System.err.println("The class " + name + " does not implement " + "the interface ReversiPlayer");
/* 545 */         System.exit(1);
/*     */       }
/*     */       
/* 548 */       p = (ReversiPlayer)o;
/*     */     }
/*     */     catch (Exception e) {
/* 551 */       System.err.println("********************************************************************");
/* 552 */       System.err.println("Error: could not instantiate \"" + name + "\" !");
/* 553 */       System.err.println("PLEASE MAKE SURE THAT THE PLAYER CLASS HAS A");
/* 554 */       System.err.println("CONSTRUCTOR THAT TAKES NO PARAMETERS");
/* 555 */       System.err.println("********************************************************************");
/* 556 */       e.printStackTrace();
/*     */       
/* 558 */       System.exit(1);
/*     */     }
/*     */     catch (NoClassDefFoundError e)
/*     */     {
/* 562 */       System.err.println("********************************************************************");
/* 563 */       System.err.println("Error: could not instantiate \"" + name + "\" !");
/* 564 */       System.err.println("PLEASE MAKE SURE THAT YOU USE THE CORRECT CASE,");
/* 565 */       System.err.println("SINCE CLASSNAMES ARE CASE-SENSITIVE!");
/* 566 */       System.err.println("********************************************************************");
/* 567 */       e.printStackTrace();
/*     */       
/* 569 */       System.exit(1);
/*     */     }
/*     */     
/* 572 */     return p;
/*     */   }
/*     */   
/*     */   public void startMatch() {
/* 576 */     if (!this.params.getHeadless()) {
/* 577 */       this.visualization.setVisible(true);
/*     */     }
/*     */     
/* 580 */     performMatch();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 587 */     System.out.println("java.class.path=" + System.getProperties().getProperty("java.class.path"));
/*     */     
/*     */ 
/* 590 */     ProgramParameters params = new ProgramParameters(args);
/* 591 */     Arena arena = new Arena(params, new Visualization2D());
/*     */     
/*     */ 
/* 594 */     arena.startMatch();
/*     */   }
/*     */   
/*     */   void computePossibleMoves(TextGameBoard board, int player, int[][] possibleMoves) {
/* 598 */     for (int row = 0; row < 8; row++) {
/* 599 */       for (int col = 0; col < 8; col++) {
/* 600 */         Coordinates coord = new Coordinates(row + 1, col + 1);
/* 601 */         if (board.checkMove(player, coord)) {
/* 602 */           possibleMoves[row][col] = 1;
/*     */         }
/*     */         else {
/* 605 */           possibleMoves[row][col] = 0;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   ProgramParameters getParams() {
/* 612 */     return this.params;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   String getBacklog()
/*     */   {
/* 620 */     return this.backlog.toString();
/*     */   }
/*     */   
/*     */   synchronized void addLogStream(PrintWriter pw) {
/* 624 */     this.logStreams.add(pw);
/*     */   }
/*     */   
/*     */   public void setTimeBeforeExit(int t) {
/* 628 */     this.timeBeforeExit = t;
/*     */   }
/*     */   
/*     */   synchronized void writeToLog(String s) {
/* 632 */     StringBuffer b = new StringBuffer();
/*     */     try {
/* 634 */       FileWriter w = new FileWriter(this.logFile, true);
/* 635 */       b.append("id=" + this.matchId + " ");
/* 636 */       b.append("time=" + System.currentTimeMillis() + " ");
/* 637 */       b.append(s);
/* 638 */       b.append("\n");
/* 639 */       w.write(b.toString());
/* 640 */       System.out.print("===== " + b.toString());
/* 641 */       w.close();
/*     */     } catch (IOException e) {
/* 643 */       System.err.println("Could not write to log file");
/*     */     }
/*     */     
/* 646 */     for (int i = 0; i < this.logStreams.size(); i++) {
/* 647 */       PrintWriter pw = (PrintWriter)this.logStreams.elementAt(i);
/* 648 */       pw.print(b.toString());
/* 649 */       pw.flush();
/*     */     }
/*     */     
/* 652 */     this.backlog.append(b);
/*     */   }
/*     */   
/*     */   public void setStartingPosition(int s, TextGameBoard board)
/*     */   {
/*     */     try
/*     */     {
/* 659 */       if (s == 1) {
/* 660 */         board.checkMove(1, new Coordinates(6, 5));
/* 661 */         board.makeMove(1, new Coordinates(6, 5));
/*     */         
/* 663 */         board.checkMove(2, new Coordinates(3, 4));
/* 664 */         board.makeMove(2, new Coordinates(3, 4));
/*     */         
/* 666 */         board.checkMove(1, new Coordinates(3, 3));
/* 667 */         board.makeMove(1, new Coordinates(3, 3));
/*     */         
/* 669 */         board.checkMove(2, new Coordinates(5, 6));
/* 670 */         board.makeMove(2, new Coordinates(5, 6));
/*     */ 
/*     */       }
/* 673 */       else if (s == 2)
/*     */       {
/* 675 */         board.checkMove(1, new Coordinates(6, 5));
/* 676 */         board.makeMove(1, new Coordinates(6, 5));
/*     */         
/* 678 */         board.checkMove(2, new Coordinates(3, 4));
/* 679 */         board.makeMove(2, new Coordinates(3, 4));
/*     */         
/* 681 */         board.checkMove(1, new Coordinates(4, 3));
/* 682 */         board.makeMove(1, new Coordinates(4, 3));
/*     */         
/* 684 */         board.checkMove(2, new Coordinates(5, 6));
/* 685 */         board.makeMove(2, new Coordinates(5, 6));
/*     */       }
/* 687 */       else if (s == 3)
/*     */       {
/* 689 */         board.checkMove(1, new Coordinates(6, 5));
/* 690 */         board.makeMove(1, new Coordinates(6, 5));
/*     */         
/* 692 */         board.checkMove(2, new Coordinates(3, 4));
/* 693 */         board.makeMove(2, new Coordinates(3, 4));
/*     */         
/* 695 */         board.checkMove(1, new Coordinates(5, 3));
/* 696 */         board.makeMove(1, new Coordinates(5, 3));
/*     */         
/* 698 */         board.checkMove(2, new Coordinates(6, 4));
/* 699 */         board.makeMove(2, new Coordinates(6, 4));
/*     */ 
/*     */ 
/*     */       }
/* 703 */       else if (s == 4)
/*     */       {
/* 705 */         board.checkMove(1, new Coordinates(6, 3));
/* 706 */         board.makeMove(1, new Coordinates(6, 3));
/*     */         
/* 708 */         board.checkMove(2, new Coordinates(3, 5));
/* 709 */         board.makeMove(2, new Coordinates(3, 5));
/*     */         
/* 711 */         board.checkMove(1, new Coordinates(6, 6));
/* 712 */         board.makeMove(1, new Coordinates(6, 6));
/*     */         
/* 714 */         board.checkMove(2, new Coordinates(5, 3));
/* 715 */         board.makeMove(2, new Coordinates(5, 3));
/*     */ 
/*     */ 
/*     */       }
/* 719 */       else if (s == 6)
/*     */       {
/* 721 */         board.checkMove(1, new Coordinates(6, 3));
/* 722 */         board.makeMove(1, new Coordinates(6, 3));
/*     */         
/* 724 */         board.checkMove(2, new Coordinates(5, 3));
/* 725 */         board.makeMove(2, new Coordinates(5, 3));
/*     */         
/* 727 */         board.checkMove(1, new Coordinates(4, 3));
/* 728 */         board.makeMove(1, new Coordinates(4, 3));
/*     */         
/* 730 */         board.checkMove(2, new Coordinates(3, 3));
/* 731 */         board.makeMove(2, new Coordinates(3, 3));
/*     */ 
/*     */ 
/*     */       }
/* 735 */       else if (s == 5)
/*     */       {
/* 737 */         board.checkMove(1, new Coordinates(6, 4));
/* 738 */         board.makeMove(1, new Coordinates(6, 4));
/*     */         
/* 740 */         board.checkMove(2, new Coordinates(5, 3));
/* 741 */         board.makeMove(2, new Coordinates(5, 3));
/*     */         
/* 743 */         board.checkMove(1, new Coordinates(6, 6));
/* 744 */         board.makeMove(1, new Coordinates(6, 6));
/*     */         
/* 746 */         board.checkMove(2, new Coordinates(3, 5));
/* 747 */         board.makeMove(2, new Coordinates(3, 5));
/*     */       }
/*     */       
/*     */     }
/*     */     catch (Exception e1)
/*     */     {
/* 753 */       System.out.println("Something went wrong within the DataBase access. The DataBase will not be tried again.");
/* 754 */       e1.printStackTrace();
/* 755 */       this.params.setUseDB(false);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/Arena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */