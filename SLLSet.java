public class SLLSet
{
    private int size;
    private SLLNode start;

    public SLLSet()
    {
        size = 0;
        start = null;
    }

    public SLLSet(int[] sortedArray)
    {
        int i;
        size = sortedArray.length; //size is length of array
        start = new SLLNode(sortedArray[0],null); //start is first element of array and null for next 
        SLLNode current = start;  //current shows what node we are at
        for(i = 1;i < size; i++)//start.next is now the next object in the linked list
        {
            current.next = new SLLNode(sortedArray[i], null);
            current = current.next; //current moves on to the next iteration

        }
    }

    public int getSize()
    {
        return size;
    }
    SLLSet copy()
    {
        SLLSet copy = new SLLSet(); //deepcopy is an empty SLLset
        SLLNode current = start;
        while(current != null)
        {
            copy.add(current.value); //Adds the value in SLLNode to the end of deepCopy
            current = current.next;
        }
        return copy;
    }

    public boolean isIn(int v)
    {
        SLLNode current = start; //sets the postition checker
        while(current != null)
        {
            if (v == current.value) { //If v is ever in the set, it returns true
                return true;
            }
            current = current.next;
        }
        return false; //if it gets through the whole set without hitting true, theres no v.
    }

    public void add(int v)
    {
        // if list is empty, adds the current to the empty list
        if(start == null)   //If the list is empty already, it adds value v
        {
            size = 1;
            start = new SLLNode(v, null);
        }
        if(v < start.value) //if v is the smallest value, it becomes the new start of the list
        {
            start = new SLLNode(v,start);//puts a reference to the previous start as the next value in list
            size++; // add 1 since a value is going to be added
        }
        else
        {
            SLLNode current = start;
            SLLNode previous = null;

            while(current != null && v > current.value) //checks if v is still bigger than the current elemtn
            {
                previous = current; // previous is the old current
                current = current.next;  // current is the next value in the list
            } //the while loop puts it on the postition that v needs to be inserted
        if (current == null ||current.value !=v) //if that current doesn't equal add in value or if add in value greater than all currents
            {
                previous.next = new SLLNode(v,current); //if v is bigger than all values OR v doesnt equal that current value
                size++; //size is increased by 1
            }
        }
    }

    public void remove(int v)
    {
        if (start == null) // if empty list, returns empty list
        {
            return; //returns nothing since void function
        }

        if (start.value == v)  //If its the first value, just make the new head the next value
        {
            start = start.next;
            size = size - 1;
            return;
        }

        SLLNode current = start;
        SLLNode previous = null;

        while (current != null && current.value != v)
        {
            previous = current; //previous becomes the old current and current moves to the next value
            current = current.next; //gives the position where v needs to be removed
        }

        if (current != null)
        {
            previous.next = current.next; //with 2 and 3, taking away 3, 3 now becomes 4 and 2 just points to 4.
            size = size - 1;
        }
    }

    public SLLSet union(SLLSet s)
    {
        if(this.start == null) //if the list is empty, the union is the set S
            return s;

        else if(s.start == null) //if the s is empty, just return the list we're using
            return this;

        SLLSet union = new SLLSet(); //makes a new union set
        SLLNode current = this.start; //makes a current variable for this matrix and for the set s given
        SLLNode scurrent = s.start; //since its in increasing order, need to check which has the lowest number

        if(current.value < scurrent.value) //if,elif,elif statements that check which start of sets is the start of the union
        {
            union.size = 1; //If this matrix has the smallest first value, itll be the first value of union
            union.start = new SLLNode(current.value, null);
            current = current.next; //Then this matrix is incremeneted to the next one
        }

        else if(current.value == scurrent.value)
        {
            union.size = 1;
            union.start = new SLLNode(current.value, null); //If the first element of each are the same, it just takes one
            current = current.next; //both current's are being incremented here
            scurrent = scurrent.next;
        }

        else if(current.value > scurrent.value)
        {
            union.size = 1;
            union.start = new SLLNode(scurrent.value, null);
            scurrent = scurrent.next; // Opposite of the first if statement
        }

        SLLNode unioncurrent = union.start; //Now the union current is the start of the union matrix
        //Goes through the two sets. Checks for the lower number or equal number and incremenets based on that. 
        while(current != null && scurrent != null)
        {
            if(current.value < scurrent.value)
            {
                union.size = union.size + 1;
                unioncurrent.next = new SLLNode(current.value, null);
                unioncurrent = unioncurrent.next;
                current = current.next;
            }

            else if(current.value == scurrent.value)
            {
                union.size = union.size + 1;
                unioncurrent.next = new SLLNode(scurrent.value, null);
                unioncurrent = unioncurrent.next;
                current = current.next;
                scurrent = scurrent.next;
            }

            else if(current.value > scurrent.value)
            {
                union.size = union.size + 1;
                unioncurrent.next = new SLLNode(scurrent.value, null);
                unioncurrent = unioncurrent.next;
                scurrent = scurrent.next;
            }
        }
//This next loop will start when one of the lists run out of values
//Checks each of the lists to see which one is null then adds the rest of the values from that one
        if(current == null) //if one of the two sets runs out of currents
        {
            while(scurrent != null)
            {
                union.size = union.size + 1;
                unioncurrent.next = new SLLNode(scurrent.value, null);
                unioncurrent = unioncurrent.next;
                scurrent = scurrent.next;
            }
        }

        else if(scurrent == null)
        {
            while(current != null)

            {
                union.size = union.size + 1;
                unioncurrent.next = new SLLNode(current.value, null);
                unioncurrent = unioncurrent.next;
                current = current.next;
            }
        }
        return union;
    }

    public SLLSet intersection(SLLSet s)
    {
        SLLSet intersect = new SLLSet(); //If either list is empty, just return an empty list called intersection
        if(this.start == null || s.start == null) //if one of the sets have nothing in them, the intersect is nothing
        {
            return intersect;
        }
        SLLNode current = this.start;
        SLLNode scurrent = s.start;

        while(current != null && scurrent != null) //finds start of intersect
        {
            if(current.value < scurrent.value) //if current first value is smaller, it incremenets current
            {
                current = current.next;
            }
            else if(current.value == scurrent.value) //if values are the same, inter gets its first value and everything is incremented
            {
                intersect.start = new SLLNode(current.value, null);
                current = current.next;
                scurrent = scurrent.next;
                intersect.size = 1;
                break; // breaks the while loop since it got its starting point
            }
            else if(current.value > scurrent.value) //if currentS first value is smaller, it incremenets currentS
            {
                scurrent = scurrent.next;
            }
        }

        SLLNode intersectcurrent = intersect.start;
        while(current != null && scurrent != null) // fills in the rest of the inter set with the rest of the values
        {
            if(current.value < scurrent.value)
            {
                current = current.next;
            }
            else if(scurrent.value < current.value)
            {
                scurrent = scurrent.next;
            }
            else if(current.value == scurrent.value)
            {
                intersect.size = intersect.size + 1;
                intersectcurrent.next = new SLLNode(current.value, null);
                intersectcurrent = intersectcurrent.next;
                current = current.next;
                scurrent = scurrent.next;
            }
        }
        return intersect;
    }

    public SLLSet difference(SLLSet s)
    {
        SLLSet difference = new SLLSet();
        SLLNode current = this.start;  //takes the first value of this

        while(current != null) //adds all the values from this matrix to diff
        {
            difference.add(current.value); // adds all of current to difference set
            current = current.next;
        }

        SLLNode scurrent = s.start; // uses the remove() method to remove any values that the set s has in common with this matrix
        while(scurrent != null) 
        {
            difference.remove(scurrent.value); // removes any current in difference set that it has in common with scurrent set
            scurrent = scurrent.next;
        }
        return difference;
    }

    public static SLLSet union(SLLSet[] sArray)
    {
        SLLSet union = sArray[0]; // union first is equal to the first element in sArray
        for(int i = 1; i < sArray.length - 1; i++) //loop goes through all elements of the array and finds the union of all the sets
        {
            union = union.union(sArray[i]); //makes union into something that holds the union of all the elements of the array
        }
        return union;
    }

    public String toString()
    {
        String output = "{";
        SLLNode current = start;

        while(current != null)
        {
            output = output + current.value;

            if(current.next != null)
                output = output + ",";

            current = current.next;
        }

        output = output + "}";

        return output;
    }
}