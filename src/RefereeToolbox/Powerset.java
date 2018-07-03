/*
 *   Powerset.java : part of package RefereeToolbox; Implementation of powerset.
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
 * Implementation of a Powerset structure by means of an array of {@code long}.
 * Structural methods are redefined, {@link Freeboolean#size(int)  } and
 * {@link Freeboolean#size(RefereeToolbox.Freeboolean)  },
 * and the atomic are defined {@link Freeboolean#atomic(int) }.
 * The AND and OR operators are inherited from the operators {@code & } and {@code | }
 * working on {@code long}.
 * <i>zero</i> is defined by zeroing all bits. <i>one</i> is defined by setting to
 * 1 all active bits; the right bits of the {@code long } vector may be inactive,
 * depending on the size of the Lattice.
 * The complement/cocomplement operators are inherited from the operators {@code ~ }
 *  working on {@code long}; exceeding bits are masked by a AND with <i>one</i>.
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
public class Powerset<L extends Powerset<L>> extends ArrayBoolean<L>
        implements GeneratedLattice<L> {

    protected static int sizeMax=1<<16;

    protected int sizeFrame = -1; // Frame of discernmment is undefined by default


    //////////////////////////////////////////////////:
    // public part
    ///////////////

/**
 * Return the <i>i<SUP>th</SUP></i> atomic proposition.
 * For class {@link Powerset }, the <i>i<SUP>th</SUP></i> atomic proposition is
 * constituted by the <i>i<SUP>th</SUP></i> bit stored in the
 * <i>long</i> array.
 * <BR><BR>
 * return <i>null</i> if <i>i</i> is out of bounds.
 */
    public L atomic(int i) {
        if((i>=sizeFrame)||(i<0)) {
            return null;
        }
        zero();
        int offset = i%64;
        int index = i/64;
        _memory[index]=( 1l << offset);
        return (L) this;
    }

/**
 * Set or return the size of <i>this</i>.
 * Diffenrent cases are considered:
 * <br><br>
 * <i>newSize<0</i>  --  The size of <i>this</i> is kept unchanged, and this size
 * is returned by the method.
 * <br>
 * <i>newSize>=0</i>  --  If <i>newSize</i> is a valid new size, then the size of
 * <i>this</i> is changed to <i>newSize</i>, and this size is returned by the method.
 * <br><br>
 * Valid new size is such that <i>1<=newSize<=sizeMax</i>. By default,
 * <i>{@link Powerset#sizeMax }=1<<16</i>.
 * <br>
 * For class {@link Powerset }, <i>newSize</i> is stored in protected variable
 * <i>sizeFrame</i> and is equals to the number <em>(has to be >1)</em> of activated
 * bits stored in the <i>long</i> array.
 * Moreover, each activated bit constitute an atomic proposition.
 * <br><br>
 *
 * N.B. 1. By default, this method is not implemented by {@link Lattice } directly,
 * but by its subclasses.
 * <br>
 * N.B. 2. The size is not necessarilly the cardinal of the lattice, but may be
 * instead the number of atomic propositions of the lattice.
 *
 */
    @Override
    public int size(int newSize) { // if newSize is possible, then change size to newSize
        if((newSize>0)&&(newSize<=sizeMax)) {
            sizeFrame=newSize;
            size_mem_1=(sizeFrame-1)/64;
            highest_long_one=sizeFrame-64*size_mem_1;
            highest_long_one=(((1L<<(highest_long_one - 1))-1)<<1)+1;
            //
            _memory= new long[size_mem_1+1];
        }
        return sizeFrame;
    }
    
    /**
     * Return the cardinal of <i>this</i>.
     * 
     * */    
    @Override
    public int cardinal() { // if newSize is possible, then change size to newSize
    	int cardinality = 0;
        for(int i=size_mem_1;i>=0;i--) {
        	cardinality =+Long.bitCount(_memory[i]);
        }  	

        return cardinality;
    }

    @Override
    public L size(L input) {
        size(input.size(-1));
        return (L) this;
    }

}