/*
 *   ArrayLattice.java : part of package RefereeToolbox; Implementation of
 *          Lattice.
 *******************************************************************************
 *   Copyright (c) 2010 Frédéric Dambreville
 *
 *   Author:    Frédéric Dambreville <http://email.fredericdambreville.com>
 *
 *   This file is part of RefereeToolbox.
 *
 *   RefereeToolbox is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   RefereeToolbox is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with RefereeToolbox. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************
 */

package RefereeToolbox;

/**
 * Implementation of a Lattice structure by means of an array of {@code long}.
 * The AND and OR operators are inherited from the operators {@code & } and {@code | }
 * working on {@code long}.
 * <i>zero</i> is defined by zeroing all bits. <i>one</i> is defined by setting to
 * 1 all active bits; the right bits of the {@code long } vector may be inactive,
 * depending on the size of the Lattice.
 *
 * @author      <A href="http://email.fredericdambreville.com">Frédéric Dambreville</A>
 *
 *
 *
 * <BR><BR><table border='1' cellPadding='4'><tr><td>
 * <font color="#008000" style="font-family: georgia">
 *   Copyright (c) 2010 Frédéric Dambreville<BR>
 * <BR>
 *   RefereeToolbox is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.<BR>
 * <BR>
 *   RefereeToolbox is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.<BR>
 * <BR>
 *   You should have received a copy of the GNU General Public License
 *   along with RefereeToolbox. If not, see
 * <a href='http://www.gnu.org/licenses/'>http://www.gnu.org/licenses/</a>.
 * </font></table><BR>
 *
 */
public class ArrayLattice<L extends ArrayLattice<L>>
        extends LatticeCommon<L> {

    protected long[] _memory=null;
    
    protected int size_mem_1=-1; // undefined, by default
    protected long highest_long_one;


    //////////////////////////////////////////////////:
    // public part
    ///////////////

/**
 * Return a representation of the state of <i>this</i> printed as a String. States
 * of this class are printed as the concatenation of the Hexadecimal representation
 * of the <i>long</i> array.
 *
 */
    @Override
    public String state() {
        String theState="";
        String tmp;
        int i;
        for(i=size_mem_1;i>=0;i--) {
            tmp=Long.toHexString(_memory[i]);
            theState+="x"+"0000000000000000".substring(0, 16-tmp.length())+tmp;
        }
        return theState;
    }
    public int stateAsInt() {

        return (int)_memory[0];
    }

/**
 * Set <i>this</i> to zero, the neutral element for
 * {@link Lattice#or(java.lang.Object, java.lang.Object) }.
 * N.B. It is necessary to call {@link Lattice#size } or
 * {@link Lattice#size(java.lang.Object) } before any first use of this
 * method.
 *
 */
    @Override
    public L zero() {
        int i;
        for(i=0;i<=size_mem_1;i++) _memory[i]=0;
        return (L) this;
    }

/**
 * Set <i>this</i> to one, the neutral element for
 * {@link Lattice#and(java.lang.Object, java.lang.Object) }.
 * N.B. It is necessary to call {@link Lattice#size } or
 * {@link Lattice#size(java.lang.Object) } before any first use of this method.
 *
 */
    @Override
    public L one() { 
        int i;
        for(i=0;i<size_mem_1;i++)  _memory[i]=0xFFFFFFFFFFFFFFFFL;
        _memory[i]=highest_long_one;
        return (L) this;
    }

/**
 * Compute the AND of <i>left</i> and <i>right</i> and store the result within <i>this</i>.
 *
 */
    @Override
    public L and(L left, L right) {
        long[] leftmem=left._memory;
        long[] rightmem=right._memory;
        int i;
        for(i=0;i<=size_mem_1;i++)  _memory[i]=leftmem[i]&rightmem[i];
        return (L) this;
    }

/**
 * Compute the OR of <i>left</i> and <i>right</i> and store the result within <i>this</i>.
 *
 */
    @Override
    public L or(L left, L right) {
        long[] leftmem=left._memory;
        long[] rightmem=right._memory;
        int i;
        for(i=0;i<=size_mem_1;i++)  _memory[i]=leftmem[i]|rightmem[i];
        return (L) this;
    }

/**
 * Answer <i>true</i> if the proposition stored within <i>this</i> intersects the
 * proposition contained within <i>aProposition</i> (<i>i.e.</i> the intersection
 * of both propositions is not <i>zero</i>). Answer <i>false</i> otherwise.
 *
 */
    @Override
    public  boolean intersects(L aProposition) {
        long[] rightmem=aProposition._memory;
        int i;
        for(i=0;i<=size_mem_1;i++) {
            if((_memory[i]&rightmem[i])!=0) return true;
        }
        return false;
    }

/**
 * Answer <i>true</i> if the proposition stored within <i>this</i> contains the
 * proposition contained within <i>aProposition</i>. Answer <i>false</i> otherwise.
 *
 */
    @Override
    public boolean contains(L aProposition) {
        long[] rightmem=aProposition._memory;
        int i;
        for(i=0;i<=size_mem_1;i++) {
            if((_memory[i]&rightmem[i])!=rightmem[i]) return false;
        }
        return true;
    }

/**
 * Compare <i>this</i> to <i>aProposition</i> and answer negative integer, zero,
 * positive integer as <i>this</i> is less than, equal to, or greater than
 * <i>aProposition</i>.
 * This comparison is related to a total ordering of the proposition; <em>it is not
 * related to the partial order implied by the logical operators AND and OR.</em>
 *
 */
    @Override
    public int compareTo(L aProposition) {
        long[] rightmem=aProposition._memory;
        int i;
        long delta;
        for(i=0;i<=size_mem_1;i++) {
            delta=_memory[i]-rightmem[i];
            if(delta>0) return 1;
            if(delta<0) return -1;
        }
        return 0;
    }

/**
 * Resize <i>this</i> at the same size than <i>input</i>.
 *
 */
    @Override
    public L size(L input) {
        size_mem_1=input.size_mem_1;
        highest_long_one=input.highest_long_one;
        _memory=new long[size_mem_1+1];
        return (L) this;
    }


 /**
 * Do an exact copy of input into <i>this</i>.
 *
 */
    @Override
    public L duplicate(L input) {
        size(input);
        int i;
        for(i=0;i<=size_mem_1;i++) _memory[i]=input._memory[i];
        return (L) this;
    }

}