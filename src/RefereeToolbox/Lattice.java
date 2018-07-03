/*
 *   Lattice.java : part of package RefereeToolbox; Interface for lattice.
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
 *   along with RefereeToolbox.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************
 */

package RefereeToolbox;

/**
 * Contains the minimal methods for a class implementing a Lattice structure.
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
public interface Lattice<L> extends Comparable<L>, Cloneable {

/**
 * Return a representation of the state of <i>this</i> printed as
 * a String.
 *
 */
    String state();

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
 * N.B. 1. By default, this method is not implemented by {@link Lattice } directly,
 * but by its subclasses.
 * <br>
 * N.B. 2. The size is not necessarilly the cardinal of the lattice, but may be
 * instead the number of atomic propositions of the lattice.
 *
 */
    int size(int newSize);

/**
 * Resize <i>this</i> at the same size than <i>input</i>.
 *
 */
    L size(L input);

 /**
 * Do an exact copy of input into <i>this</i>.
 *
 */
   L duplicate(L input);

/**
 * Create and return an instance of <i>this</i> (i.e. work like a <b>new</b> on
 * the Class of <i>this</i>). N.B. {@link Lattice#instance() } cannot be defined
 * for generic classes but <u>is necessary</u> for some methods. <u>It has to be
 * defined with the non generic (typically final) sub-classes</u>.
 * <br><br>
 * For a given non generic sub-class <i>myNonGenericSubclass</i>, a typical
 * definition of {@link Lattice#instance() } is as follows:
 * <br><br>
 * <font color="#004488"><code>
 * {@code @Override}
 * <br>
 * public myNonGenericSubclass instance() { return new myNonGenericSubclass(); }
 * </code></font>
 *
 */
    L instance();

/**
 * Create and return an instance of <i>this</i> and resize it at the same size than <i>this</i>.
 * This method is actually defined and make a call of {@link Lattice#instance() }.
 * It does not need to be defined with the non generic sub-classes.
 *  
 * @see Lattice#instance()
 * 
 */
    L instanceNsize();

 /**
 * Create  a clone of <i>this</i>.
 *
 */
   L clone();

/**
 * Set <i>this</i> to zero, the neutral element for
 * {@link Lattice#or(java.lang.Object, java.lang.Object) }.
 * N.B. It is necessary to call {@link Lattice#size } or
 * {@link Lattice#size(java.lang.Object) } before any first use of this
 * method.
 *
 */
    L zero();

/**
 * Set <i>this</i> to one, the neutral element for
 * {@link Lattice#and(java.lang.Object, java.lang.Object) }.
 * N.B. It is necessary to call {@link Lattice#size } or
 * {@link Lattice#size(java.lang.Object) } before any first use of this method.
 *
 */
    L one();

/**
 * Answer <i>true</i> if the proposition stored within <i>this</i> contains the
 * proposition contained within <i>aProposition</i>. Answer <i>false</i> otherwise.
 *
 */
    boolean contains(L aProposition);

/**
 * Answer <i>true</i> if the proposition stored within <i>this</i> intersects the
 * proposition contained within <i>aProposition</i> (<i>i.e.</i> the intersection
 * of both propositions is not <i>zero</i>). Answer <i>false</i> otherwise.
 *
 */
    boolean intersects(L aProposition);

/**
 * Compute the AND of <i>left</i> and <i>right</i> and store the result within <i>this</i>.
 *
 */
    L and(L left, L right);

/**
 * Compute the OR of <i>left</i> and <i>right</i> and store the result within <i>this</i>.
 *
 */
    L or(L left, L right);

/**
 * Compare <i>this</i> to <i>aProposition</i> and answer negative integer, zero,
 * positive integer as <i>this</i> is less than, equal to, or greater than
 * <i>aProposition</i>.
 * This comparison is related to a total ordering of the proposition; <em>it is not
 * related to the partial order implied by the logical operators AND and OR.</em>
 *
 */
    int compareTo(L aProposition);

    /**
     * Return the cardinal of <i>this</i>.
     * 
     * */
int cardinal();
}


