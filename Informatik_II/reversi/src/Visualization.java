package reversi;

abstract class Visualization
{
  abstract void init(Arena paramArena);
  
  abstract void animateMove(GameBoard paramGameBoard, Coordinates paramCoordinates, int paramInt, int[][] paramArrayOfInt);
  
  public abstract void showPossibleMoves(TextGameBoard paramTextGameBoard, int paramInt);
  
  public abstract void setInfoLine(String paramString);
  
  public abstract void setInfoLine2(String paramString);
  
  public abstract void setStatusLine(String paramString);
  
  public abstract void setVisible(boolean paramBoolean);
  
  public abstract void dispose();
  
  public abstract void update(TextGameBoard paramTextGameBoard);
}


/* Location:              /usr/share/java/reversi.jar!/reversi/Visualization.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */