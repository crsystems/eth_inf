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
/*     */ 
/*     */ class ProgramParameters
/*     */ {
/*     */   public static final int BOARD_LENGTH = 8;
/*     */   public static final int TIME_TOLERANCE = 150;
/*     */   private long timeOut;
/*     */   private long delay;
/*     */   private String redName;
/*     */   private String greenName;
/*     */   private String game_id;
/*     */   private String logfile;
/*     */   private boolean headless;
/*     */   private boolean noAnimations;
/*     */   private boolean fast;
/*     */   private long timeBeforeExit;
/*     */   private boolean useJar;
/*     */   private String redPath;
/*     */   private String greenPath;
/*     */   private String username;
/*     */   private String password;
/*     */   private String url;
/*     */   private boolean useDB;
/*     */   private int startPosition;
/*     */   private static final String usage_str = "Usage:\njava Arena [-t timeout] [-d delay] [-l logfile] [-s] gameId redName greenName\n\tValid args are:\n\t-h         : print this help\n\t-t timeout : timeout for each move in ms. A player is\n\t             interrupted and looses the game if it takes\n\t             more than the specified amount of time.\n\t-d delay   : delay after each move in ms.\n\t-l logfile : name of logfile to be used\n\t-e exittime: time before exit (in seconds; 0=wait forever)\n\t-c         : headless (no GUI displayed)\n\t-f         : fast (no GUI animations)\n\t-i url username password: \n\t \t\t\t: use a MySQL database with the given credentials\n\t-j redPath greenPath: \n\t\t\t\t: Use jar files instead of class files. \n\t \t\t\t  redPath: path to the jar file of the red Player.\n\t \t\t\t  greenPath: path to the jar file of the green Player.\n\t-s startPosNr\n\t\t\t\t: startingPositiion (0: standart, 1-6 non standart)";
/*     */   
/*     */   public ProgramParameters()
/*     */   {
/*  42 */     this.timeOut = 5000L;
/*  43 */     this.delay = 0L;
/*  44 */     this.logfile = "reversilog";
/*  45 */     this.headless = false;
/*  46 */     this.timeBeforeExit = 5000L;
/*  47 */     this.noAnimations = false;
/*  48 */     this.fast = false;
/*  49 */     this.useDB = false;
/*  50 */     this.useJar = false;
/*  51 */     this.startPosition = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public ProgramParameters(String[] args)
/*     */   {
/*  57 */     this();
/*     */     
/*     */ 
/*  60 */     int index = 0;
/*     */     
/*     */ 
/*     */     try
/*     */     {
/*  65 */       index = indexOfArg("-h", args);
/*  66 */       if (index >= 0)
/*     */       {
/*  68 */         printUsage();
/*  69 */         System.exit(0);
/*     */       }
/*     */       
/*  72 */       index = indexOfArg("-t", args);
/*  73 */       if (index >= 0)
/*     */       {
/*  75 */         this.timeOut = Integer.parseInt(args[(index + 1)]);
/*  76 */         if (this.timeOut == 0L)
/*     */         {
/*  78 */           this.timeOut = 2305843009213693951L;
/*     */         }
/*  80 */         System.out.println("TIMEOUT: " + this.timeOut);
/*     */       }
/*     */       
/*  83 */       index = indexOfArg("-i", args);
/*  84 */       if (index >= 0)
/*     */       {
/*  86 */         this.useDB = true;
/*  87 */         this.url = args[(index + 1)];
/*  88 */         this.username = args[(index + 2)];
/*  89 */         this.password = args[(index + 3)];
/*  90 */         System.out.println("DataBase set to: " + this.url + " username: " + this.username + " password: " + this.password);
/*     */       }
/*     */       
/*     */ 
/*  94 */       index = indexOfArg("-d", args);
/*  95 */       if (index >= 0)
/*     */       {
/*  97 */         this.delay = Integer.parseInt(args[(index + 1)]);
/*  98 */         System.out.println("DELAY: " + this.delay);
/*     */       }
/*     */       
/* 101 */       index = indexOfArg("-l", args);
/* 102 */       if (index >= 0)
/*     */       {
/* 104 */         this.logfile = args[(index + 1)];
/* 105 */         System.out.println("LOGFILE: " + this.logfile);
/*     */       }
/*     */       
/* 108 */       index = indexOfArg("-c", args);
/* 109 */       if (index >= 0)
/*     */       {
/* 111 */         this.headless = true;
/* 112 */         System.out.println("HEADLESS: " + this.headless);
/*     */       }
/*     */       
/* 115 */       index = indexOfArg("-n", args);
/* 116 */       if (index >= 0)
/*     */       {
/* 118 */         this.noAnimations = true;
/* 119 */         System.out.println("ANIMATIONS: " + (!this.noAnimations));
/*     */       }
/*     */       
/* 122 */       index = indexOfArg("-e", args);
/* 123 */       if (index >= 0)
/*     */       {
/* 125 */         this.timeBeforeExit = (Integer.parseInt(args[(index + 1)]) * 1000);
/* 126 */         System.out.println("DELAYTIMEBEFOREEXIT: " + this.timeBeforeExit);
/*     */       }
/*     */       
/* 129 */       index = indexOfArg("-f", args);
/* 130 */       if (index >= 0)
/*     */       {
/* 132 */         this.fast = true;
/* 133 */         System.out.println("FAST: true");
/*     */       }
/* 135 */       index = indexOfArg("-j", args);
/* 136 */       if (index >= 0)
/*     */       {
/* 138 */         this.useJar = true;
/* 139 */         this.redPath = args[(index + 1)];
/* 140 */         this.greenPath = args[(index + 2)];
/* 141 */         System.out.println("useJar: true \t redPath: " + this.redPath + " greenPath: " + this.greenPath);
/*     */       }
/*     */       
/* 144 */       index = indexOfArg("-s", args);
/* 145 */       if (index >= 0)
/*     */       {
/* 147 */         this.startPosition = Integer.valueOf(args[(index + 1)]).intValue();
/* 148 */         if (this.startPosition > 6) {
/* 149 */           System.err.println("invalid start-position number!");
/* 150 */           printUsage();
/* 151 */           System.exit(1);
/* 152 */           return;
/*     */         }
/* 154 */         System.out.println("StartPosition: " + this.startPosition);
/*     */       }
/*     */       
/* 157 */       if (args.length < 3)
/*     */       {
/* 159 */         printUsage();
/* 160 */         System.exit(1);
/* 161 */         return;
/*     */       }
/*     */       
/* 164 */       this.game_id = args[(args.length - 3)];
/* 165 */       this.redName = args[(args.length - 2)];
/* 166 */       this.greenName = args[(args.length - 1)];
/*     */       
/* 168 */       System.out.println("GAMEID: " + this.game_id + ", RED: " + this.redName + 
/* 169 */         ", GREEN: " + this.greenName);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 173 */       printUsage();
/* 174 */       System.exit(1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   int indexOfArg(String arg, String[] args)
/*     */   {
/* 187 */     for (int i = 0; i < args.length; i++)
/*     */     {
/* 189 */       if (args[i].startsWith(arg))
/* 190 */         return i;
/*     */     }
/* 192 */     return -1;
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
/*     */   static void printUsage()
/*     */   {
/* 226 */     System.out.println("Usage:\njava Arena [-t timeout] [-d delay] [-l logfile] [-s] gameId redName greenName\n\tValid args are:\n\t-h         : print this help\n\t-t timeout : timeout for each move in ms. A player is\n\t             interrupted and looses the game if it takes\n\t             more than the specified amount of time.\n\t-d delay   : delay after each move in ms.\n\t-l logfile : name of logfile to be used\n\t-e exittime: time before exit (in seconds; 0=wait forever)\n\t-c         : headless (no GUI displayed)\n\t-f         : fast (no GUI animations)\n\t-i url username password: \n\t \t\t\t: use a MySQL database with the given credentials\n\t-j redPath greenPath: \n\t\t\t\t: Use jar files instead of class files. \n\t \t\t\t  redPath: path to the jar file of the red Player.\n\t \t\t\t  greenPath: path to the jar file of the green Player.\n\t-s startPosNr\n\t\t\t\t: startingPositiion (0: standart, 1-6 non standart)");
/* 227 */     System.exit(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   long getTimeout()
/*     */   {
/* 235 */     return this.timeOut;
/*     */   }
/*     */   
/*     */   boolean getAnimations()
/*     */   {
/* 240 */     return !this.noAnimations;
/*     */   }
/*     */   
/*     */   long getDelay()
/*     */   {
/* 245 */     return this.delay;
/*     */   }
/*     */   
/*     */   String getGameId()
/*     */   {
/* 250 */     return this.game_id;
/*     */   }
/*     */   
/*     */   String getRedName()
/*     */   {
/* 255 */     return this.redName;
/*     */   }
/*     */   
/*     */   String getGreenName()
/*     */   {
/* 260 */     return this.greenName;
/*     */   }
/*     */   
/*     */   String getLogfile()
/*     */   {
/* 265 */     return this.logfile;
/*     */   }
/*     */   
/*     */   boolean getHeadless()
/*     */   {
/* 270 */     return this.headless;
/*     */   }
/*     */   
/*     */   long getTimeBeforeExit()
/*     */   {
/* 275 */     return this.timeBeforeExit;
/*     */   }
/*     */   
/*     */   int getStartPosition() {
/* 279 */     return this.startPosition;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   long setTimeOut(long new_value)
/*     */   {
/* 287 */     long prev = this.timeOut;
/* 288 */     this.timeOut = new_value;
/* 289 */     return prev;
/*     */   }
/*     */   
/*     */   long setDelay(long new_value)
/*     */   {
/* 294 */     long prev = this.delay;
/* 295 */     this.delay = new_value;
/* 296 */     return prev;
/*     */   }
/*     */   
/*     */   void setRedName(String red)
/*     */   {
/* 301 */     this.redName = red;
/*     */   }
/*     */   
/*     */   void setGreenName(String green)
/*     */   {
/* 306 */     this.greenName = green;
/*     */   }
/*     */   
/*     */   void setGameId(String gameid)
/*     */   {
/* 311 */     this.game_id = gameid;
/*     */   }
/*     */   
/*     */   boolean getFastMode()
/*     */   {
/* 316 */     return this.fast;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 321 */     return "";
/*     */   }
/*     */   
/* 324 */   String getUsername() { return this.username; }
/*     */   
/*     */   String getPassword()
/*     */   {
/* 328 */     return this.password;
/*     */   }
/*     */   
/*     */   String getUrl() {
/* 332 */     return this.url;
/*     */   }
/*     */   
/*     */   boolean getUseDB() {
/* 336 */     return this.useDB;
/*     */   }
/*     */   
/*     */   void setUseDB(boolean input) {
/* 340 */     this.useDB = input;
/*     */   }
/*     */   
/* 343 */   String getRedPath() { return this.redPath; }
/*     */   
/*     */   String getGreenPath()
/*     */   {
/* 347 */     return this.greenPath;
/*     */   }
/*     */   
/*     */   boolean isUseJar() {
/* 351 */     return this.useJar;
/*     */   }
/*     */   
/*     */   void setUseJar(boolean useJar) {
/* 355 */     this.useJar = useJar;
/*     */   }
/*     */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/ProgramParameters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */