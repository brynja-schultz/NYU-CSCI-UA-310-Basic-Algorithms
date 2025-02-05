//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// Class InternalNode %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
class InternalNode extends Node {
	//MEMBERS:
		Node[] child = new Node[4];
		// Q: Why 4 children?  A: Because the degree
		// might temporarily be 4 during insertion.
	//CONSTRUCTORS:
		InternalNode(){
			this(null,null,null); }
		InternalNode(Node u0, Node u1, InternalNode p){
			this(u0, u1); 
			if (u0!=null) u0.parent=this;
			if (u1!=null) u1.parent=this;}
		//======================================================
		InternalNode(InternalNode u0, InternalNode u1, InternalNode p){
			this((Node)u0, (Node)u1, p); }
		InternalNode(Node u0, Node u1){
			assert(u0==null || u1==null || u0.guide.compareTo(u1.guide)<0);
			if (u1!=null) guide = u1.guide;	
			child[0] = u0; child[1] = u1;
			} // REMEMBER to update u0.parent and u1.parent.
		InternalNode(InternalNode u0, InternalNode u1){
			this((Node)u0, (Node)u1); }
	//METHODS:
	//////////////////////////////////////////
	int degree(){ 
		// get the degree of this InternalNode
		int d=0;
		for (int i=0; i<4; i++)
			if (this.child[i] !=null) d++;
		return d; }//degree
	void newParent (InternalNode u0, InternalNode u1, InternalNode p){
		// newParent(u0, u1, p) sets up p as parent of u0, u1.
		newParent((Node)u0, (Node)u1, p); }
	void newParent (Node u0, Node u1, InternalNode p){
		//assert(u0.key < u1.key)
		p.guide = u1.guide;
		p.child[0] = u0;
		p.child[1] = u1; 
		u0.parent = u1.parent = p; }//newParent
	int addLeaf (Item it){
		// addLeaf(it) returns d:{0,1,2,3,4}
		//		where d=0 means addLeaf failed 
		//			(either it.key is a duplicate or this->degree is 4)
		//		else d is the new degree of this InternalNode.
		//	ALSO: this InternalNode is sorted.
		// assert(this.child[] are leaves)
		int d = degree();
		for (int i=0; i<d; i++)
			// Note: if d=0, this for-loop is NOT entered!!
			if (it.key.compareTo(child[i].guide) == 0)
				return 0;
		if (d==4) return 0;
		child[d] = (Node)(new LeafNode(it, this));
		sortNode();//sort this node
		guide = child[d].guide;
		return d+1; }//addLeaf
	Item removeLeaf (String x){
		// removeLeaf(x) returns the deleted item whose key is x
		//		or it returns null if no such item.
		// assert(this is a pseudo-leaf),  i.e., this.child[] are leaves)
		int d = degree();
		Item it = null;
		int i=0;
		// First locate the leaf containing x:
		for (; i<d; i++)
			if (x.compareTo(child[i].guide) == 0) {
				it = ((LeafNode)child[i]).item(); break; }
		if (i==d)
			return null;
		// Next, shift each child[j+1] into child[j] for j>=i:
		for (int j=i; j<d; j++)
			child[j] = child[j+1];
		child[d-1] = null; // remove the last child!
		return it; }//removeLeaf
	int getIndexOf(InternalNode u){
		//		returns the index c such that this->child[c]==u;
		// assert(u is a child of "this")
		int c=0;
		for (; c<4; c++)
			if (child[c] == u) break;
		return c; }//getIndexOf
	void shiftLeft (int c){ // don't forget to delete the last child
		//		this->child[c] is a hole which we must fill up;
		//		so for each i>c: child[i-1] = child[i]
		int d = degree();
//debug("SHIFTLEFT ====> c=" +c +", d=" + d+ ", MY GUIDE=" + guide);
		for (int i=c+1; i<d; i++)
			child[i-1] = child[i]; 
		child[d-1] = null;
		}//shiftLeft
	void shiftRight (int c){
		// shiftRight(c)
		//		create a hole at child[c], so for each i>c,
		//				child[i] = child[i-1]
		//		(but start with i=degree down to i=c+1)
		// ASSERT("c < this->degree < 4");
		int d=degree();
		for (int i=d; i>c; i--){
			child[i] = child[i-1]; } }//shiftRight
	boolean proposeLeft (int c){
		// 			ASSERT("c>0 and child[c].degree=1")
		//	return TRUE if the child[c] merges into child[c-1]
		//	return FALSE child[c] adopts a child of child[c-1].
		//		REMARK:	return TRUE means non-terminal case
		int test = ((InternalNode)child[c]).degree();
		assert(c>0 && test == 1);
		int d= ((InternalNode)child[c-1]).degree();
		assert(d==2 || d==3);
		if (d==2){// child[c] and child[c-1] will merge
			((InternalNode)child[c-1]).child[d]
					= ((InternalNode)child[c]).child[0];
			//!! update moved child's parent pointer
			((InternalNode) child[c - 1]).child[d].parent
					= (InternalNode) child[c - 1];
			//!!
		// COMMENT: we want to update child[c-1].guide.
		//	Originally, I took it from child[c-1].child[d].guide,
		// as follows:
		//    child[c-1].guide = ((InternalNode)child[c-1]).child[d].guide;
		// Instead, we can ALSO take the guide from child[c]:
		// It is slightly simpler (and more "relaxed"):
			child[c-1].guide = child[c].guide;
		// Now, we kill the old child[c], replacing it with child[c+1]:
			child[c] = child[c+1];
			child[c+1] = null; // not forgetting to nullify child[c+1]!
			return true;
		}
		// Else (d==3) and child[c] will adopt a child of child[c-1]:
		//	first, child[c] makes room for adoption:
		((InternalNode)child[c]).child[1] 
				= ((InternalNode)child[c]).child[0];
		((InternalNode)child[c]).child[0]
				= ((InternalNode)child[c-1]).child[2];
		//!! update moved child's parent pointer
		((InternalNode) child[c]).child[0].parent
				= ((InternalNode) child[c]);
		//!!
		((InternalNode)child[c-1]).child[2] = null;
		// update guide of child[c-1]
		((InternalNode)child[c-1]).guide =
			((InternalNode)child[c-1]).child[1].guide;
		return false;
		}//proposeLeft
	boolean proposeRight (int c){
		// 		ASSERT("c+1<degree and child[c].degree=1")
		//	returns TRUE if the child[c] and child[c+1] are merged.
		//	returns FALSE if child[c] adopts a child of child[c+1].
		// 	REMARKS: under our policy, we KNOW that c==0!
		//		return TRUE means this is a non-terminal case
		assert(c==0);
		int d= ((InternalNode)child[c+1]).degree();
		if (d==2){// child[c+1] will merge into child[c]
			((InternalNode)child[c]).child[1]
				= ((InternalNode)child[c+1]).child[0];
			((InternalNode)child[c]).child[2]
				= ((InternalNode)child[c+1]).child[1];
		//!! update moved children's parent pointer
			((InternalNode) child[c]).child[1].parent
					= ((InternalNode) child[c]);
			((InternalNode) child[c]).child[2].parent
					= ((InternalNode) child[c]);
		//!!
		// COMMENT: we want to update child[c].guide.
		//	Originally, I took it from child[c].child[2].guide,
		// as follows:
		//     child[c].guide =((InternalNode)child[c]).child[2].guide;
		// Instead, we can ALSO take the guide from child[c+1]:
		// It is slightly simpler (and more "relaxed"):
			child[c].guide =child[c+1].guide;
		//!! shift third child
			child[c + 1] = child[c + 2];
			child[c + 2] = null;
		//!!
			return true;
		}
		// else d==3, and child[c] will adopt from child[c+1]
		((InternalNode)child[c]).child[1]
		 	= ((InternalNode)child[c+1]).child[0];
		//!! update moved child's parent pointer
		((InternalNode) child[c]).child[1].parent
				= ((InternalNode) child[c]);
		//!!
		// update guide of child[c]
		child[c].guide = ((InternalNode)child[c]).child[1].guide;
		// fill up the hole at 0 of child[c+1] :
		((InternalNode)child[c+1]).shiftLeft(0);
		return false;
	}//proposeRight
	/////////////////////////////////////////// Swaps:
	// We have 3 overloaded versions of "swapNodes"
	// 	taking 2, 3, or 4 int's.  These int's are indices of
	//  the child[] array, so we are swapping entries of child[]:
		void swapNodes(int u0, int u1){// (u0,u1) <- (u1,u0)
			Node tmp = child[u1];
			child[u1] = child[u0];
			child[u0] = tmp; }
		void swapNodes(int u0, int u1, int u2){// (u0,u1,u2) <- (u2,u0,u1)
			Node tmp = child[u2];
			child[u2] = child[u1];
			child[u1] = child[u0];
			child[u0] = tmp; }
		void swapNodes(int u0, int u1, int u2, int u3){
			Node tmp = child[u3];
			child[u3] = child[u2];
			child[u2] = child[u1];
			child[u1] = child[u0];
			child[u0] = tmp; }
	void sortNode (){
		// We use swapNodes to sort the keys in an InternalNode:
			if (child[1] == null) return;
			if (child[0].guide.compareTo(child[1].guide) >0) 
					swapNodes(0, 1); 
			// assert(child0 < child1)
			if (child[2] == null) return;
			if (child[0].guide.compareTo(child[2].guide) >0)
					swapNodes(0, 1, 2);
			else if (child[1].guide.compareTo(child[2].guide) >0)
					swapNodes(1, 2);
			// assert(child0 < child1 < child2)
			if (child[3] == null) return;
			if (child[0].guide.compareTo(child[3].guide) >0)
					swapNodes(0, 1, 2, 3);
			else if (child[1].guide.compareTo(child[3].guide) >0)
					swapNodes(1, 2, 3);
			else if (child[2].guide.compareTo(child[3].guide) >0)
					swapNodes(2, 3);
		}//SortNode
	void dump(){ dump("");}
	void dump (String msg){// print this
		dbug(msg +"IntNode(G:"+guide+"<");
		for (int i=0; i<4; i++)
			if (child[i]!=null) dbug("["+String.valueOf(i)
					+ "]=" + String.valueOf(child[i].guide));
		debug(">)");
		}//dump
	int isSorted (){
		// return degree in [0..3] if children guides are sorted!
		// CAREFUL: this is different from "ok()" method of Tree23
		int d=degree();
		assert(0<=d && d<=3)
			: "degree out of bounds";
		for (int i=1; i<d; i++) {
			assert(child[i-1].guide.compareTo(child[i].guide)<0) 
				: "children are not sorted";
debug("ISSORTED: child[i-1].guide="+child[i-1].guide);
debug("          child[i].guide="+child[i].guide);
		}
		return d;
	}//isSorted
}//class InternalNode
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
