//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// Class LeafNode %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class LeafNode extends Node {
	int data;
	LeafNode(Item I){
		parent=null; guide=I.key; data=I.data;}
	LeafNode(Item I, InternalNode u){
		parent=u; guide=I.key; data=I.data;}
	//MEMBER:
	//////////////////////////////////////////
	Item item(){
		Item it=new Item(guide, data);
		return it; }
	void dump(){
		System.out.printf("<%s:%d>", guide, data); }
}//class LeafNode
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
