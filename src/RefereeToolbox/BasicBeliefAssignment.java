/*
 *   BasicBeliefAssignment.java : part of package RefereeToolbox; Interface for
 *          Basic Belief Assigments.
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

import java.util.*;

/**
 * Contains the minimal declaration for classes managing belief assignment
 * structures, belief assignment processes and fusers.
 * This interface is implemented by all the these classes.
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
public interface BasicBeliefAssignment<Prop extends Lattice<Prop>,
                        B extends BasicBeliefAssignment<Prop,B> >
                                        extends minAssignment<Prop> , Cloneable {

/**
 * Clear the assignments stored within {@link BasicBeliefAssignment }, and
 * store new assignments from the collection of assignment, <i>anAssignmentTab</i>.
 *
 */
    boolean load(Collection<Assignment<Prop>> anAssignmentTab);

/**
 * Do an exact copy of input into <i>this</i>.
 *
 */
    B duplicate(B input); // make this a duplicate of input

/**
 * Create and return an instance of <i>this</i> (i.e. work like a <b>new</b> on
 * the Class of <i>this</i>).
 * N.B. {@link BasicBeliefAssignment#instance() } cannot be defined for generic
 * classes but <u>is necessary</u> for some methods. <u>It has to be defined with
 * the non generic (typically final) sub-classes</u>.
 * <br><br>
 * For a given non generic sub-class <i>myNonGenericSubclass</i>, a typical
 * definition of {@link BasicBeliefAssignment#instance() } is as follows:
 * <br><br>
 * <font color="#004488"><code>
 * {@code @Override}
 * <br>
 * public myNonGenericSubclass instance() { return new myNonGenericSubclass(); }
 * </code></font>
 *
 */
    B instance(); 
   
/**
 * Create  a clone of <i>this</i>.
 *
 */
    B clone();

/**
 * Make a mix of the basic belief assignments stored within the entry, <i>bbaIn</i>
 * according to their respective weight <i>weight</i>. This mix is stored into <i>this</i>.
 *
 */
    B mix(ArrayList<B> bbaIn, double[] weight);

/**
 * Called each time a change is done to <i>this</i>. This is usefull for processes which need an
 * initialization after each change of the assignments. Typically, the
 * sampling methods require an action of {@link BasicBeliefAssignment#update_notification() }
 * in order to decide for a new computation of the sampling tables, which are made
 * necessary by a change of the assignments.
 *
 */
    void update_notification(); // 

/**
 * Return a representation of the state of <i>this</i> printed as
 * a String. Typically, this state is the list of all stored assignments (proposition
 * and value). Depending on the parameter <i>choix</i> in entry, the representation
 * is modified as folllows:<br><br>
 * <i>choix==0</i>  --  Nothing is printed.
 * <br>
 *<i>choix==1</i>  --  Assignments are printed in increasing order of their
 * propositions,
 * <br>
 *<i>choix==2</i>  --  Assignments are printed in decreasing order of their
 * value,
 * <br>
 *<i>choix==3</i>  --  Do both print in that order.
 * <br>
 *
 */
    String state(int choix); // a string representing the state of the BBA

/**
 * Remove all assignments from <i>this</i>. The class instance is cleared.
 *
 */
    void clear();

/**
 * Add an assignment characterized by a proposition <i>aProposition</i> and a value
 * <i>anAssignmentValue</i> to <i>this</i>.
 * If an assignment already exists for <i>aProposition</i>, say with value <i>theOldValue</i>,
 * then the new assignement of <i>aProposition</i> is stored with value
 * <i>theOldValue+anAssignmentValue</i>.
 *
 */
    boolean add(Prop aProposition, double anAssignmentValue);

/**
 * Do exactly as {@link BasicBeliefAssignment#add(RefereeToolbox.Lattice, double) }
 * but takes an entry of type {@link Assignment }, which is actually
 * equivalent.
 *
 */
    boolean add(Assignment<Prop> anAssignment);

/**
 * Add all assignments of collection <i>assignTab</i> to <i>this</i>.
 * This method is equivalent to applying
 * {@link BasicBeliefAssignment#add(RefereeToolbox.Assignment) }
 * to each assignment of collection <i>assignTab</i>.
 *
 */
    boolean addAll(Collection<Assignment<Prop>> assignTab);

/**
 * Remove the assignment related to proposition <i>aProposition</i>, if there is
 * such assignment stored within <i>this</i>.
 * Return the value assigned to the proposition.
 *
 */
    double remove(Prop aProposition);
}

