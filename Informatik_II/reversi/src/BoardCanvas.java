/*    */ package reversi;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ class BoardCanvas extends java.awt.Canvas
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 10 */   private BoardMaster master = null;
/*    */   
/*    */   public void update(Graphics g)
/*    */   {
/* 14 */     paint(g);
/*    */   }
/*    */   
/*    */   public synchronized void paint(Graphics g)
/*    */   {
/* 19 */     Dimension d = getSize();
/* 20 */     g.drawImage(this.master.getImage(), 0, 0, d.width, d.height, Color.white, 
/* 21 */       this);
/*    */   }
/*    */   
/*    */   public void setMaster(BoardMaster bm)
/*    */   {
/* 26 */     this.master = bm;
/*    */   }
/*    */ }


/* Location:              /usr/share/java/reversi.jar!/reversi/BoardCanvas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */