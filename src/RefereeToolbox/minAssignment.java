/*
 *   minAssignment.java : part of package RefereeToolbox; Interface for
 *          minimal implementation of Basic Belief Assignment.
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
 * Contains the minimal declaration for classes managing belief assignments.
 * This interface is implemented by all these classes.
 *
 * @see         BasicBeliefAssignment
 * @see         RefereeFunctionDefault
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
public interface minAssignment<Prop extends Lattice<Prop> > {

/**
 * Create an array of the assignments stored within the class. These assignments not
 * necessary sum to 1. Notice that the assignments should be positively valued
 * (focal elements), although <i>this</i> is not a strict requirement.
 *
 */
    ArrayList<Assignment<Prop>> toArray();

/**
 * Get the basic belief assigned to <i>aProposition</i>. May be zero.
 *
 */
    double m(Prop aProposition);

/**
 * Get the belief of <i>aProposition</i>. Although not required, it is assumed that
 * the assignments sum to 1.
 *
 */
    double Bel(Prop aProposition);

/**
 * Get the plausibility of <i>aProposition</i>. Although not required, it is assumed that
 * the assignments sum to 1.
 *
 */
    double Pl(Prop aProposition);


/**
 * Search if there is an assignment stored within the class, and which attribute
 * is equal to the attribute of <i>anAssignment</i>.
 * Return the found assignment, if there is one. Othewise, return <i>null</i>.
 *
 */
    public Assignment<Prop> findProposition(Assignment<Prop> anAssignment);

/**
 * Search if there is an assignment stored within the class, and which attribute
 * is equal to the proposition <i>aProposition</i>.
 * Return the found assignment, if there is one. Othewise, return <i>null</i>.
 *
 */
    public Assignment<Prop> findProposition(Prop aProposition);

}
