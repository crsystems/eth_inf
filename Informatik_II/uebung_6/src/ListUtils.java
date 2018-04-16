package u6a3;



public class ListUtils implements IListUtils{

	public ListUtils(){
	}

	public String toString(GenericList list){
		if(list == null) return "null";

		StringBuffer buf = new StringBuffer();
		buf.append(list.value).append(", ").append(this.toString(list.next));
		return buf.toString();
	}

	public GenericList add(GenericList list, Object value){
		GenericList n_list = new GenericList(value, list);
		return n_list;
	}

	public int size(GenericList list){
		if(list == null){
			return 0;
		}
		return 1 + this.size(list.next);
	}

	public GenericList sort(GenericList list){
		if(list == null){
			return null;
		}else{
			list = this.insertSorted(this.sort(list.next), list.value);
			return list;
		}
	}

	private GenericList insertSorted(GenericList list, Object value){
		if(list == null){
			return new GenericList(value, null);
		}else if(((Comparable) list.value).smallerThan((Comparable) value) == false) {
			return this.add(list, value);
		}else{
			list.next = this.insertSorted(list.next, value);
			return list;
		}
	}
}
