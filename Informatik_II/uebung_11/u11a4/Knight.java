//Gemeinsam gelöst von Nicole Narr und Christopher Reinwardt.

package u11a4;

import java.util.ArrayList;

public class Knight implements IKnight{

	final int boardSize = 8;

	public ArrayList<Position> getReachableSet(Position pos, int numberOfMoves){
		ArrayList<Position> set = new ArrayList<Position>();
		ArrayList<Position> possible = new ArrayList<Position>();

		possible.add(pos.add(new Position(1, 2)));
		possible.add(pos.add(new Position(2, 1)));
		possible.add(pos.add(new Position(2, -1)));
		possible.add(pos.add(new Position(1, -2)));
		possible.add(pos.add(new Position(-1, -2)));
		possible.add(pos.add(new Position(-2, -1)));
		possible.add(pos.add(new Position(-2, 1)));
		possible.add(pos.add(new Position(-1, 2)));
		possible.add(pos);

		if(numberOfMoves == 0){
			ArrayList<Position> tmp = new ArrayList<Position>();
			tmp.add(pos);
			return tmp;
		}else if(numberOfMoves > 1){
			for(int i = 0; i < possible.size(); i++){
					if(possible.get(i).x <= boardSize-1 && possible.get(i).y <= boardSize-1 && possible.get(i).x >= 0 && possible.get(i).y >= 0){
						ArrayList<Position> tmp = this.getReachableSet(possible.get(i), numberOfMoves-1);
						tmp.add(possible.get(i));	
						for(int k = 0; k < tmp.size(); k++){
							boolean twice = false;
							for(int j = 0; j < set.size(); j++){
								if(tmp.get(k).equals(set.get(j))){
									twice = true;
									break;
								}
							}
							if(!twice){
								set.add(tmp.get(k));
							}
						}
					}
			}

		}else{
			for(int i = 0; i < possible.size(); i++){
					if(possible.get(i).x <= boardSize-1 && possible.get(i).y <= boardSize-1 && possible.get(i).x >= 0 && possible.get(i).y >= 0){
							boolean twice = false;
							for(int j = 0; j < set.size(); j++){
								if(possible.get(i).equals(set.get(j))){
									twice = true;
									break;
								}
							}
							if(!twice){
								set.add(possible.get(i));
							}
						
					}
			}
		}
		return set;
	}

	public ArrayList<Position> findCompletePath(Position pos){
		return null;
	}
}

	/*Ansatz, der aber aus Zeitmangel nicht mehr fertig implementiert werden konnte.
	 *
	 * public ArrayList<Position> findCompletePath(Position pos){
		ArrayList<Position> left = new ArrayList<Position>();
		
		for(int i = 0; i < boardSize; i++){
			for(int k = 0; k < boardSize; k++){
				left.add(new Position(k, i));
			}
		}

		return this.findPath(pos, left);
	}


	public ArrayList<Position> findPath(Position pos, ArrayList<Position> left){

		if(left == null){
			return null;
		}
		
		ArrayList<Position> possible = this.getReachableSet(pos, 1);
		ArrayList<Position> to_check = new ArrayList<Position>();

		ArrayList<Position> path = new ArrayList<Position>();

		for(int i = 0; i < left.size(); i++){
			for(int k = 0; k < possible.size(); k++){
				if(left.get(i).equals(possible.get(k))){
					to_check.add(left.get(i));
					break;
				}
			}
		}

		ArrayList<Position> tmp_left = new ArrayList<Position>(left);
		
		ArrayList<Position> tmp = new ArrayList<Position>();

		for(int i = 0; i < to_check.size(); i++){
			tmp_left = new ArrayList<Position>(left);
			tmp_left.remove(tmp_left.indexOf(to_check.get(i)));

			tmp = this.findPath(to_check.get(i), tmp_left);
			
			if(tmp != null ){
				path.add(to_check.get(i));
				path.addAll(tmp);
				break;
			}
		}
		
		if(path.size() == 0){
			return null;
		}
		
		return path;
	}
}*/
