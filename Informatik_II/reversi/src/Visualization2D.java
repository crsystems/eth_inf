/*     */ package reversi;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Label;
/*     */ import java.awt.Panel;
/*     */ import java.awt.event.ActionEvent;
/*     */ 
/*     */ class Visualization2D extends Visualization implements BoardMaster
/*     */ {
/*     */   public static final int BOARD_LENGTH = 8;
/*     */   public static final int TIME_TOLERANCE = 150;
/*  13 */   protected static Color boardGray = Color.gray;
/*  14 */   protected static Color boardWhite = Color.white;
/*  15 */   protected static Color boardFont = Color.black;
/*  16 */   protected static Color playGreen = Color.green;
/*  17 */   protected static Color playRed = Color.red;
/*     */   
/*  19 */   protected static final Color RED_TRANSPARENT = new Color(255, 0, 0, 100);
/*  20 */   protected static final Color GREEN_TRANSPARENT = new Color(0, 255, 0, 100);
/*     */   
/*  22 */   static final Color animBoxColor = Color.blue;
/*     */   
/*     */   Arena arena;
/*     */   
/*     */   ProgramParameters params;
/*  27 */   private boolean showPossibleMoves = true;
/*  28 */   private boolean showAnimations = true;
/*  29 */   private boolean highlightTurn = true;
/*     */   
/*  31 */   protected java.awt.Image boardImage = null;
/*     */   
/*  33 */   protected java.awt.Image progressImage = null;
/*     */   
/*     */   protected java.awt.Frame main_frame;
/*     */   
/*     */   protected BoardCanvas boardArea;
/*     */   
/*     */   protected Label infoLine;
/*     */   
/*     */   protected Label infoLine2;
/*     */   protected Label statusLine;
/*     */   protected java.awt.Button quitButton;
/*     */   protected java.awt.Button make_slower;
/*     */   protected java.awt.Button make_quicker;
/*  46 */   int tileWidth = 80;
/*  47 */   int tileHeight = 80;
/*  48 */   int w_offset = this.tileWidth / 20;
/*  49 */   int h_offset = this.tileHeight / 20;
/*     */   
/*     */ 
/*     */   void init(Arena a)
/*     */   {
/*  54 */     this.arena = a;
/*  55 */     this.params = this.arena.getParams();
/*     */     
/*  57 */     if (this.params.getFastMode()) {
/*  58 */       this.showPossibleMoves = false;
/*  59 */       this.showAnimations = false;
/*  60 */       this.highlightTurn = false;
/*     */     }
/*     */     
/*  63 */     java.awt.Font myFont = new java.awt.Font("Arial", 1, 16);
/*     */     
/*  65 */     this.boardArea = new BoardCanvas();
/*  66 */     this.boardArea.setMaster(this);
/*  67 */     this.infoLine = new Label("[info line]");
/*  68 */     this.infoLine2 = new Label("[info line2]");
/*  69 */     this.statusLine = new Label("[status line]");
/*  70 */     this.make_slower = new java.awt.Button("increase delay");
/*  71 */     this.make_quicker = new java.awt.Button("decrease delay");
/*  72 */     this.quitButton = new java.awt.Button("Quit");
/*     */     
/*  74 */     this.infoLine.setFont(myFont);
/*  75 */     this.infoLine2.setFont(myFont);
/*  76 */     this.statusLine.setFont(myFont);
/*  77 */     this.make_slower.setFont(myFont);
/*  78 */     this.make_quicker.setFont(myFont);
/*  79 */     this.quitButton.setFont(myFont);
/*     */     
/*  81 */     Panel topPanel = new Panel();
/*  82 */     topPanel.setLayout(new java.awt.BorderLayout());
/*  83 */     Panel topPanel2 = new Panel();
/*  84 */     topPanel2.setLayout(new java.awt.FlowLayout());
/*     */     
/*  86 */     topPanel.add(this.infoLine, "Center");
/*  87 */     topPanel.add(this.infoLine2, "South");
/*  88 */     topPanel.add(topPanel2, "East");
/*  89 */     topPanel2.add(this.make_slower);
/*  90 */     topPanel2.add(this.make_quicker);
/*  91 */     topPanel2.add(this.quitButton);
/*     */     
/*  93 */     java.awt.MenuBar menu_bar = new java.awt.MenuBar();
/*  94 */     java.awt.Menu opt_menu = new java.awt.Menu("Options");
/*  95 */     java.awt.MenuItem noExitTimeItem = new java.awt.MenuItem("Don't exit after match");
/*  96 */     java.awt.MenuItem someExitTimeItem = new java.awt.MenuItem("10 sec delay before exit");
/*  97 */     java.awt.MenuItem moreExitTimeItem = new java.awt.MenuItem("1 min delay before exit");
/*  98 */     java.awt.MenuItem exit_item = new java.awt.MenuItem("Exit");
/*     */     
/* 100 */     java.awt.CheckboxMenuItem item_showAnimations = 
/* 101 */       new java.awt.CheckboxMenuItem("Show animations", this.showAnimations);
/* 102 */     java.awt.CheckboxMenuItem item_showPossibleMoves = 
/* 103 */       new java.awt.CheckboxMenuItem("Show possible moves", this.showPossibleMoves);
/* 104 */     java.awt.CheckboxMenuItem item_highlightTurn = 
/* 105 */       new java.awt.CheckboxMenuItem("Highlight turn", this.highlightTurn);
/*     */     
/* 107 */     opt_menu.add(item_showPossibleMoves);
/* 108 */     opt_menu.add(item_showAnimations);
/* 109 */     opt_menu.add(item_highlightTurn);
/*     */     
/* 111 */     opt_menu.add(noExitTimeItem);
/* 112 */     opt_menu.add(someExitTimeItem);
/* 113 */     opt_menu.add(moreExitTimeItem);
/* 114 */     opt_menu.add(exit_item);
/* 115 */     menu_bar.add(opt_menu);
/*     */     
/* 117 */     this.main_frame = new java.awt.Frame("Reversi Arena");
/* 118 */     this.main_frame.setMenuBar(menu_bar);
/* 119 */     this.main_frame.setLayout(new java.awt.BorderLayout());
/* 120 */     this.main_frame.add(topPanel, "North");
/* 121 */     this.main_frame.add(this.statusLine, "South");
/* 122 */     this.main_frame.add(this.boardArea, "Center");
/* 123 */     this.main_frame.pack();
/*     */     
/* 125 */     this.main_frame.setSize(660, 660);
/* 126 */     this.boardImage = this.boardArea.createImage(640, 640);
/*     */     
/*     */ 
/*     */ 
/* 130 */     item_showPossibleMoves.addItemListener(new java.awt.event.ItemListener()
/*     */     {
/*     */       public void itemStateChanged(java.awt.event.ItemEvent i)
/*     */       {
/* 134 */         Visualization2D.this.showPossibleMoves = (i.getStateChange() == 1);
/*     */       }
/*     */       
/* 137 */     });
/* 138 */     item_showAnimations.addItemListener(new java.awt.event.ItemListener()
/*     */     {
/*     */       public void itemStateChanged(java.awt.event.ItemEvent i)
/*     */       {
/* 142 */         Visualization2D.this.showAnimations = (i.getStateChange() == 1);
/*     */       }
/*     */       
/* 145 */     });
/* 146 */     item_highlightTurn.addItemListener(new java.awt.event.ItemListener()
/*     */     {
/*     */       public void itemStateChanged(java.awt.event.ItemEvent i)
/*     */       {
/* 150 */         Visualization2D.this.highlightTurn = (i.getStateChange() == 1);
/*     */       }
/*     */       
/* 153 */     });
/* 154 */     exit_item.addActionListener(new java.awt.event.ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent s)
/*     */       {
/* 158 */         Visualization2D.this.arena.writeToLog("quit button pressed");
/* 159 */         System.exit(2);
/* 160 */         Visualization2D.this.main_frame.dispose();
/* 161 */         if (Visualization2D.this.arena.performerThread != null) {
/* 162 */           Visualization2D.this.arena.performerThread.interrupt();
/*     */         }
/*     */       }
/* 165 */     });
/* 166 */     noExitTimeItem.addActionListener(new java.awt.event.ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/* 170 */         Visualization2D.this.arena.setTimeBeforeExit(0);
/*     */       }
/*     */       
/* 173 */     });
/* 174 */     someExitTimeItem.addActionListener(new java.awt.event.ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/* 178 */         Visualization2D.this.arena.setTimeBeforeExit(10000);
/*     */       }
/*     */       
/* 181 */     });
/* 182 */     moreExitTimeItem.addActionListener(new java.awt.event.ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/* 186 */         Visualization2D.this.arena.setTimeBeforeExit(60000);
/*     */       }
/*     */       
/* 189 */     });
/* 190 */     this.make_slower.addActionListener(new java.awt.event.ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent ev)
/*     */       {
/* 194 */         Visualization2D.this.params.setDelay(Visualization2D.this.params.getDelay() + 500L);
/*     */         
/* 196 */         Visualization2D.this.infoLine.setText("delay=" + Visualization2D.this.params.getDelay());
/*     */       }
/*     */       
/* 199 */     });
/* 200 */     this.make_quicker.addActionListener(new java.awt.event.ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent ev)
/*     */       {
/* 204 */         if (Visualization2D.this.params.getDelay() >= 500L)
/*     */         {
/* 206 */           Visualization2D.this.params.setDelay(Visualization2D.this.params.getDelay() - 500L);
/*     */         }
/*     */         else
/*     */         {
/* 210 */           Visualization2D.this.params.setDelay(0L);
/*     */         }
/* 212 */         Visualization2D.this.infoLine.setText("delay=" + Visualization2D.this.params.getDelay());
/*     */       }
/*     */       
/* 215 */     });
/* 216 */     this.quitButton.addActionListener(new java.awt.event.ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent ev)
/*     */       {
/* 220 */         Visualization2D.this.arena.writeToLog("quit button pressed");
/* 221 */         System.exit(2);
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   void drawTheBoardBase()
/*     */   {
/* 233 */     int fsize = 20;
/*     */     
/* 235 */     Graphics g = this.boardImage.getGraphics();
/*     */     
/*     */ 
/* 238 */     java.awt.Font default_font = g.getFont();
/* 239 */     g.setFont(new java.awt.Font(null, 0, 20));
/* 240 */     java.awt.FontMetrics fm = g.getFontMetrics();
/*     */     
/* 242 */     for (int row = 0; row < 8; row++)
/*     */     {
/* 244 */       for (int col = 0; col < 8; col++)
/*     */       {
/* 246 */         if ((row + col) % 2 == 0)
/*     */         {
/* 248 */           g.setColor(boardGray);
/*     */         }
/*     */         else
/*     */         {
/* 252 */           g.setColor(boardWhite);
/*     */         }
/* 254 */         g.fillRect(col * this.tileWidth, row * this.tileHeight, this.tileWidth, 
/* 255 */           this.tileHeight);
/*     */         
/*     */ 
/* 258 */         if (boardFont != null)
/*     */         {
/* 260 */           String strpos = String.valueOf(row + 1) + 
/* 261 */             String.valueOf((char)(col + 65));
/*     */           
/* 263 */           int xxx = col * this.tileWidth;
/* 264 */           int yyy = (row + 1) * this.tileHeight;
/*     */           
/* 266 */           xxx -= fm.stringWidth(strpos) / 2;
/* 267 */           yyy += 10;
/*     */           
/* 269 */           g.setColor(boardFont);
/* 270 */           g.drawString(strpos, xxx + this.tileWidth / 2, yyy - this.tileHeight / 
/* 271 */             2);
/*     */         }
/*     */       }
/*     */     }
/* 275 */     g.setFont(default_font);
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
/*     */   void drawTheBoard(GameBoard board)
/*     */   {
/* 288 */     if (this.boardImage == null)
/*     */     {
/* 290 */       System.out.print("[sorry - no boardImage]");
/* 291 */       return;
/*     */     }
/*     */     
/*     */ 
/* 295 */     drawTheBoardBase();
/* 296 */     Graphics g = this.boardImage.getGraphics();
/*     */     
/* 298 */     for (int row = 1; row <= 8; row++)
/*     */     {
/* 300 */       for (int col = 1; col <= 8; col++)
/*     */       {
/*     */         int player;
/*     */         try {
/* 304 */           player = board.getOccupation(new Coordinates(row, col));
/*     */         }
/*     */         catch (OutOfBoundsException e) {
/*     */           int player;
/* 308 */           player = 0;
/*     */         }
/* 310 */         switch (player)
/*     */         {
/*     */         case 2: 
/* 313 */           g.setColor(playGreen);
/* 314 */           break;
/*     */         case 1: 
/* 316 */           g.setColor(playRed);
/* 317 */           break;
/*     */         }
/*     */         
/*     */         
/* 321 */         g.fillOval((col - 1) * this.tileWidth + this.w_offset, (row - 1) * 
/* 322 */           this.tileHeight + this.h_offset, this.tileWidth - 2 * this.w_offset, 
/* 323 */           this.tileHeight - 2 * this.h_offset);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawFilledOval(Graphics g, int x, int y, int xrad, int yrad)
/*     */   {
/* 333 */     g.fillOval(x - xrad, y - yrad, xrad + xrad, yrad + yrad);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   void animateMove(GameBoard oldBoard, Coordinates move, int player, int[][] toFlip)
/*     */   {
/* 342 */     int newRow = move.getRow() - 1;
/* 343 */     int newCol = move.getCol() - 1;
/*     */     
/* 345 */     Color mycolor = player == 1 ? playRed : playGreen;
/* 346 */     Color opposite = player == 1 ? playGreen : playRed;
/*     */     
/* 348 */     Graphics g = this.boardImage.getGraphics();
/*     */     
/* 350 */     int step = 0;
/*     */     
/* 352 */     int B = this.tileWidth - 2 * this.w_offset;
/* 353 */     int Bh = this.tileHeight - 2 * this.h_offset;
/*     */     
/*     */ 
/* 356 */     if (this.highlightTurn)
/*     */     {
/* 358 */       animateNewPosition(player, move);
/*     */     }
/*     */     
/*     */ 
/* 362 */     drawTheBoard(oldBoard);
/* 363 */     this.boardArea.repaint();
/*     */     
/* 365 */     while ((this.showAnimations) && (step < 100))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 371 */       if ((newRow + newCol) % 2 == 0)
/*     */       {
/* 373 */         g.setColor(boardGray);
/*     */       }
/*     */       else
/*     */       {
/* 377 */         g.setColor(boardWhite);
/*     */       }
/* 379 */       g.fillRect(newCol * this.tileWidth, newRow * this.tileHeight, this.tileWidth, 
/* 380 */         this.tileHeight);
/* 381 */       g.setColor(mycolor);
/* 382 */       int X = B * step / 200;
/* 383 */       int Y = Bh * step / 200;
/* 384 */       int x = newCol * this.tileWidth + this.tileWidth / 2;
/* 385 */       int y = newRow * this.tileHeight + this.tileHeight / 2;
/* 386 */       drawFilledOval(g, x, y, X, Y);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 391 */       for (int row = 0; row < 8; row++)
/*     */       {
/* 393 */         for (int col = 0; col < 8; col++)
/*     */         {
/* 395 */           if (toFlip[row][col] == 1)
/*     */           {
/*     */ 
/* 398 */             if ((row + col) % 2 == 0)
/*     */             {
/* 400 */               g.setColor(boardGray);
/*     */             }
/*     */             else
/*     */             {
/* 404 */               g.setColor(boardWhite);
/*     */             }
/* 406 */             g.fillRect(col * this.tileWidth, row * this.tileHeight, 
/* 407 */               this.tileWidth, this.tileHeight);
/*     */             
/* 409 */             int q = 0;
/* 410 */             if (step < 50)
/*     */             {
/*     */ 
/* 413 */               g.setColor(opposite);
/* 414 */               q = step * B / 100;
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 419 */               g.setColor(mycolor);
/* 420 */               q = (100 - step) * B / 100;
/*     */             }
/* 422 */             g.fillOval(col * this.tileWidth + this.w_offset + q, row * 
/* 423 */               this.tileHeight + this.h_offset, this.tileWidth - 2 * 
/* 424 */               this.w_offset - 2 * q, this.tileHeight - 2 * this.h_offset);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 433 */       if (!this.params.getHeadless())
/*     */       {
/* 435 */         this.boardArea.repaint();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 441 */       step += 3;
/*     */       try
/*     */       {
/* 444 */         Thread.sleep(15L);
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {}
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void animateNewPosition(int player, Coordinates move)
/*     */   {
/* 454 */     int newRow = move.getRow() - 1;
/* 455 */     int newCol = move.getCol() - 1;
/*     */     
/* 457 */     Color mycolor = player == 1 ? playRed : playGreen;
/* 458 */     Graphics g = this.boardImage.getGraphics();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 463 */     for (int idx = 0; idx < 3; idx++)
/*     */     {
/* 465 */       g.setColor(mycolor);
/* 466 */       g.fillOval(newCol * this.tileWidth + this.w_offset, newRow * this.tileHeight + 
/* 467 */         this.h_offset, this.tileWidth - 2 * this.w_offset, this.tileHeight - 2 * 
/* 468 */         this.h_offset);
/* 469 */       if (!this.params.getHeadless())
/*     */       {
/* 471 */         this.boardArea.repaint();
/*     */       }
/*     */       try
/*     */       {
/* 475 */         Thread.sleep(300L);
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {}
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 482 */       if ((newRow + newCol) % 2 == 0)
/*     */       {
/* 484 */         g.setColor(boardGray);
/*     */       }
/*     */       else
/*     */       {
/* 488 */         g.setColor(boardWhite);
/*     */       }
/* 490 */       g.fillRect(newCol * this.tileWidth, newRow * this.tileHeight, this.tileWidth, 
/* 491 */         this.tileHeight);
/* 492 */       if (!this.params.getHeadless())
/*     */       {
/* 494 */         this.boardArea.repaint();
/*     */       }
/*     */       try
/*     */       {
/* 498 */         Thread.sleep(300L);
/*     */       }
/*     */       catch (InterruptedException localInterruptedException1) {}
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void drawPossibleMoves(int player, int[][] possibleMoves)
/*     */   {
/* 508 */     Graphics g = this.boardImage.getGraphics();
/*     */     
/* 510 */     Color playerColor = player == 1 ? RED_TRANSPARENT : 
/* 511 */       GREEN_TRANSPARENT;
/* 512 */     g.setColor(playerColor);
/*     */     
/* 514 */     for (int row = 0; row < 8; row++)
/*     */     {
/* 516 */       for (int col = 0; col < 8; col++)
/*     */       {
/* 518 */         if (possibleMoves[row][col] == 1)
/*     */         {
/*     */ 
/* 521 */           g.fillOval(col * this.tileWidth + this.w_offset, row * this.tileHeight + 
/* 522 */             this.h_offset, this.tileWidth - 2 * this.w_offset, this.tileHeight - 
/* 523 */             2 * this.h_offset);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void showPossibleMoves(TextGameBoard board, int player)
/*     */   {
/* 536 */     if (this.showPossibleMoves)
/*     */     {
/* 538 */       int[][] possibleMoves = new int[8][8];
/*     */       
/* 540 */       this.arena.computePossibleMoves(board, player, possibleMoves);
/* 541 */       drawTheBoard(board);
/* 542 */       drawPossibleMoves(player, possibleMoves);
/* 543 */       this.boardArea.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setInfoLine(String text)
/*     */   {
/* 549 */     this.infoLine.setText(text);
/*     */   }
/*     */   
/*     */   public void setInfoLine2(String text)
/*     */   {
/* 554 */     this.infoLine2.setText(text);
/*     */   }
/*     */   
/*     */   public void setStatusLine(String text)
/*     */   {
/* 559 */     this.statusLine.setText(text);
/*     */   }
/*     */   
/*     */   public void repaint()
/*     */   {
/* 564 */     this.boardArea.repaint();
/*     */   }
/*     */   
/*     */   public java.awt.Image getImage()
/*     */   {
/* 569 */     return this.boardImage;
/*     */   }
/*     */   
/*     */   public void setVisible(boolean visible)
/*     */   {
/* 574 */     this.main_frame.setVisible(visible);
/*     */   }
/*     */   
/*     */   public void dispose()
/*     */   {
/* 579 */     this.main_frame.dispose();
/*     */   }
/*     */   
/*     */   public void update(TextGameBoard board)
/*     */   {
/* 584 */     drawTheBoard(board);
/* 585 */     repaint();
/*     */   }
/*     */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/Visualization2D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */