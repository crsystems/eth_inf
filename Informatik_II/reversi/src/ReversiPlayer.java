package reversi;

public abstract interface ReversiPlayer
{
  public abstract void initialize(int paramInt, long paramLong);
  
  public abstract Coordinates nextMove(GameBoard paramGameBoard);
}


/* Location:              /usr/share/java/reversi.jar!/reversi/ReversiPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */