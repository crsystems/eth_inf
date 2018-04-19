package reversi;

public abstract interface GameBoard
{
  public static final int EMPTY = 0;
  public static final int RED = 1;
  public static final int GREEN = 2;
  
  public abstract GameBoard clone();
  
  public abstract int getSize();
  
  public abstract boolean validCoordinates(Coordinates paramCoordinates);
  
  public abstract int getOccupation(Coordinates paramCoordinates)
    throws OutOfBoundsException;
  
  public abstract boolean isFull();
  
  public abstract boolean isMoveAvailable(int paramInt);
  
  public abstract boolean checkMove(int paramInt, Coordinates paramCoordinates);
  
  public abstract void makeMove(int paramInt, Coordinates paramCoordinates);
  
  public abstract int countStones(int paramInt);
}


/* Location:              /usr/share/java/reversi.jar!/reversi/GameBoard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */