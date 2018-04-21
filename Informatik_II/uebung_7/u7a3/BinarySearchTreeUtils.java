package u7a3;

import java.util.ArrayList;


public class BinarySearchTreeUtils<T> implements IBinarySearchTreeUtils<T>{

	public int height(BinarySearchTree<T> tree){
		if(tree == null){
			return 0;
		}else if(this.isLeaf(tree)){
			return 1;
		}else{
			return 1 + Math.max(this.height(tree.left), this.height(tree.right));
		}
	}

	public boolean isLeaf(BinarySearchTree<T> tree){
		if(tree.left == null && tree.right == null)
			return true;
		return false;
	}

	public boolean hasOneChild(BinarySearchTree<T> tree){
		if((tree.left != null && tree.right == null) || (tree.left == null && tree.right != null))
			return true;
		return false;

	}

	public ArrayList<T> preOrder(BinarySearchTree<T> tree){
		ArrayList<T> ntree= new ArrayList<T>();
		if(tree == null){
			return ntree;
		}else{
			ntree.add(tree.thing);
		}


		if(tree.left != null)			
			ntree.addAll(this.preOrder(tree.left));

		if(tree.right != null)
			ntree.addAll(this.preOrder(tree.right));

		return ntree;
	}

	public ArrayList<T> inOrder(BinarySearchTree<T> tree){
		ArrayList<T> lst = new ArrayList<T>();

		if(this.isLeaf(tree)){
			lst.add(tree.thing);
		}else{
			if(tree.left != null)
				lst.addAll(this.inOrder(tree.left));

			lst.add(tree.thing);

			if(tree.right != null)
				lst.addAll(this.inOrder(tree.right));
		}
		return lst;
	 }


	public ArrayList<T> postOrder(BinarySearchTree<T> tree){
		ArrayList<T> lst = new ArrayList<T>();

		if(tree.left != null)
			lst.addAll(this.postOrder(tree.left));

		if(tree.right != null)
			lst.addAll(this.postOrder(tree.right));

		lst.add(tree.thing);
		return lst;
	}


	public BinarySearchTree<T> insert(BinarySearchTree<T> tree, int key, T thing){
		if(tree == null){
			return new BinarySearchTree<T>(key, thing);
		}else if(key < tree.key){
			if(tree.left != null){
				tree.left = this.insert(tree.left, key, thing);
			}else{
				tree.left = new BinarySearchTree<T>(key, thing);
			}
		}else if(key > tree.key){
			if(tree.right != null){
				tree.right = this.insert(tree.right, key, thing);
			}else{
				tree.right = new BinarySearchTree<T>(key, thing);
			}
		}else{
			tree.thing = thing;
		}
		return tree;
	}

	public T find(BinarySearchTree<T> tree, int key){
		if(tree == null){
			return null;
		}else if(key < tree.key){
			return this.find(tree.left, key);
		}else if(key > tree.key){
			return this.find(tree.right, key);
		}else{
			return tree.thing;
		}
	}

	public BinarySearchTree<T> remove(BinarySearchTree<T> tree, int key){
		if(tree == null){
			return null;
		}else if(key < tree.key){
			tree.left = this.remove(tree.left, key);
		}else if(key > tree.key){
			tree.right = this.remove(tree.right, key);
		}else{
			if(this.isLeaf(tree)){
				tree = null;
			}else if(this.hasOneChild(tree)){
				if(tree.left != null){
					tree = tree.left;
				}else{
					tree = tree.right;
				}
			}else{
				BinarySearchTree<T> t = tree.right;
				while(t.left != null){
					t = t.left;
				}

				int tmp_key = t.key;
				T tmp = t.thing;
				
				tree = this.remove(tree, tmp_key);

				tree.key = tmp_key;
				tree.thing = tmp;
			}
		}
		return tree;
	}

}

